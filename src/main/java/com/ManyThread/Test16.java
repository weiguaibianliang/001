package com.ManyThread;


import lombok.extern.slf4j.Slf4j;
import org.springframework.retry.backoff.Sleeper;

import java.io.IOException;
import java.util.List;

import static java.lang.Thread.sleep;


@Slf4j
public class Test16 {
    //线程1等待线程2的下载结果
    public static void main(String[] args) {
//        //两个线程公用一个guardedObject
//        GuardedObject guardedObject = new GuardedObject();
//        new Thread(() ->{
//            //等待结果
//            log.debug("等待结果");
//            List<String> list = (List<String>) guardedObject.get();
//            log.debug("结果大小：{}",list.size());
//        },"t1").start();
//
//        new Thread(() ->{
//            log.debug("执行下载");
//            try {
//               List<String> list =  DownLoader.downLoad();
//               guardedObject.complete(list);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        },"t2").start();
//    }
        GuardedObject guardedObject = new GuardedObject();
        new Thread(() -> {
            log.debug("begin");
            Object response = guardedObject.get(2000);
            log.debug("结果是：{}", response);
        },"t1").start();

        new Thread(() -> {
            log.debug("begin");
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            guardedObject.complete(new Object());
        },"t2").start();

    }
}

//同步模式之保护性暂停

    //增加超时效果
    class GuardedObject {
        //定义返回结果的成员变量
        private Object response;

        //获取上一个线程的结果
        public Object get(long timeout) {
            synchronized (this) {
                //开始时间
                long begin = System.currentTimeMillis();
                //经历的时间
                long passedTime = 0;
                while (response == null) {
                    //这一轮循环应该等待的时间
                    long waitTime = timeout - passedTime;
                    //经历时间超过了最大等待时间，退出循环
                    if (waitTime <= 0) {
                        break;
                    }
                    try {
                        this.wait(timeout - passedTime);//避免虚假唤醒时，等待的时间再次变长。
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    //求得经历时间
                    passedTime = System.currentTimeMillis() - begin;
                }
            }
            return response;
        }

        //上一个线程产生结果
        public void complete(Object response) {
            synchronized (this) {
                //给结果成员变量赋值
                this.response = response;
                //唤醒等待的线程
                this.notifyAll();
            }
        }
    }

