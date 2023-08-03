package com.ManyThread.CustomizableThreadPool;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.formula.functions.T;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

//自定义拒绝策略接口

@FunctionalInterface//拒绝策略
public interface RejectPolicy<T> {
    void reject(BlockingQueue<T> queue, T task);
}


@Slf4j
//自定义任务队列
class BlockingQueue<T>{

    //1、任务队列
    private Deque<T> queue = new ArrayDeque<>();

    //2、锁
    private ReentrantLock lock = new ReentrantLock();

    //3、生产者条件变量
    private Condition fullWaitSet = lock.newCondition();

    //4、消费者条件变量
    private Condition emptyWaitSet = lock.newCondition();

    //5、容量
    private int capacity;


   public BlockingQueue(int capacity) {
        this.capacity = capacity;
    }


    //阻塞获取
    public T take(){
        lock.lock();
        try {
            while (queue.isEmpty()){
                try {
                    emptyWaitSet.await();
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
            T t = queue.removeFirst();
            fullWaitSet.signal();
            return t;
        }finally {
            lock.unlock();
        }
    }

    //带超时时间阻塞获取
    public T poll(long timeout, TimeUnit unit){
        lock.lock();
        try {
            //将timeout统一转换为纳秒
            long nanos = unit.toNanos(timeout);
            while (queue.isEmpty()){
                try {
                    //返回值是剩余时间
                    if(nanos <= 0){
                        return null;
                    }
                    nanos =  emptyWaitSet.awaitNanos(nanos);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
            T t = queue.removeFirst();
            fullWaitSet.signal();
            return t;
        }finally {
            lock.unlock();
        }
    }

    //阻塞添加
    public void put(T task){
        lock.lock();
        try {
            while (queue.size() == capacity){
                try {
                    log.debug("等待加入任务队列{}...",task);
                    fullWaitSet.await();
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
            log.debug("加入任务列{}",task);
            queue.addLast(task);
            emptyWaitSet.signal();
        }finally {
            lock.unlock();
        }
    }

    //带阻塞超时添加
    public boolean offer(T task,long timeout, TimeUnit timeUnit){
        lock.lock();
        try {
            long nanos = timeUnit.toNanos(timeout);
            while (queue.size() == capacity){
                try {
                    if(nanos <= 0){
                        return false;
                    }
                    log.debug("等待加入任务对列{}...",task);
                    nanos = fullWaitSet.awaitNanos(nanos);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
            log.debug("加入任务对列{}",task);
            queue.addLast(task);
            emptyWaitSet.signal();
            return true;
        }finally {
            lock.unlock();
        }
    }

    public int size(){
        lock.lock();
        try {
            return queue.size();
        }finally {
            lock.unlock();
        }
    }

    public void tryPut(RejectPolicy<T> rejectPolicy, T task){
        lock.lock();
        try {
            //判断队列是否满
            if(queue.size() == capacity){
                rejectPolicy.reject(this,task);
            }else {//有空闲
                log.debug("加入任务队列{}",task);
                queue.addLast(task);
                emptyWaitSet.signal();
            }
        }finally {
            lock.unlock();
        }
    }
}
