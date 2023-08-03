package com.ManyThread.CustomizableThreadPool;


import lombok.extern.slf4j.Slf4j;
import java.util.HashSet;
import java.util.concurrent.TimeUnit;

//自定义线程池
@Slf4j
public class ThreadPool {

    //任务队列
    private BlockingQueue<Runnable> taskQueue;

    //线程集合
    private HashSet<Worker> workers = new HashSet<>();

    //核心线程数
    private int coreSize;

    //获取任务时的超过时间
    private long timeout;

    private TimeUnit timeUnit;

    private RejectPolicy<Runnable> rejectPolicy;

    public ThreadPool( int coreSize, long timeout, TimeUnit timeUnit, int queueCapacity,RejectPolicy<Runnable> rejectPolicy) {
        this.coreSize = coreSize;
        this.timeout = timeout;
        this.timeUnit = timeUnit;
        this.taskQueue = new BlockingQueue<>(queueCapacity);
        this.rejectPolicy = rejectPolicy;
    }

    //执行任务
    public void execute(Runnable task){
        //当任务数没有超过coreSize时，直接交给worker对象执行
        //如果任务数超过coreSize时，加入任务队列暂存
        synchronized (workers){
            if(workers.size() < coreSize){
                Worker worker = new Worker(task);
                log.debug("新增 worker{},{}",worker,task);
                workers.add(worker);
                worker.start();
            }else {
                //taskQueue.put(task)
                //(1)死等
                //(2)带超时等待
                //(3)让调用者放弃任务执行
                //(4)让调用者抛出异常
                //(5)让调用者自己执行任务
                taskQueue.tryPut(rejectPolicy,task);
            }
        }

    }

    class Worker extends Thread{
        private Runnable task;
        public Worker(Runnable task){
            this.task = task;
        }

        @Override
        public void run(){
            //执行任务
            //（1）当task不为空，执行任务
            //（2）当task执行完毕，再接着从任务队列获取任务并执行
            while (task != null || (task = taskQueue.poll(timeout,timeUnit)) != null){
                try{
                    log.debug("正在执行...{}",task);
                    task.run();
                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    task = null;
                }
            }
            synchronized (workers){
                log.debug("worker被移除{}",this);
                workers.remove(this);
            }
        }
    }

    //测试
    public static void main(String[] args) {
        ThreadPool threadPool = new ThreadPool(1,
                1000,TimeUnit.MILLISECONDS,1,(queue, task) -> {
                    //1、死等
//                    queue.put(task);
                    //2、带超时等待
                    queue.offer(task,1500,TimeUnit.MILLISECONDS);
                    //3、让调用者放弃任务执行
                    //log.debug("放弃{}",task);
                    //4、让调用者抛出异常
                    //throw new RuntimeException("任务执行失败" + task);
                    //5、让调用者自己执行任务
//                    task.run();

        });
        for (int i = 0; i < 4; i++) {
            int j = i;
            threadPool.execute(() ->{
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.debug("{}",j);
            });
        }
    }

}
