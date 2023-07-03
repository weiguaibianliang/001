package com.ManyThread;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import static java.lang.Thread.sleep;

@Slf4j
public class Test14 {

    //利用可重入锁（ReentrantLock），可以利用多个条件变量，当一个锁锁住时，可以在多个空间里面等待，分别唤醒。
    static  boolean hasCigarette = false;
    static boolean hasTakeOut = false;
    static ReentrantLock lock = new ReentrantLock();
    static Condition waitCigaretteQueue = lock.newCondition();
    static Condition waitTakeOut = lock.newCondition();


    public static void main(String[] args) throws InterruptedException {
        //创建线程
         Thread t1 = new Thread(() ->{
             try {
                 lock.lock();
                 while (!hasCigarette){
                     log.debug("没烟，先歇会儿！");
                     try {
                         waitCigaretteQueue.await();
                     } catch (InterruptedException e) {
                         e.printStackTrace();
                     }
                 }
                 log.debug("可以开始干活了！");
             }finally {
                 lock.unlock();
             }
        },"小南");
         t1.start();
        new Thread(() ->{
            try {
                lock.lock();
                while (!hasTakeOut){
                    log.debug("外卖没到，先歇会！");
                    try {
                        waitTakeOut.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                log.debug("可以开始干活了");
            }finally {
                lock.unlock();
            }
        },"小女").start();
        sleep(1000);
        //开始送烟
        sendCigarette();
        sleep(1000);
        //开始送外卖
        sendTakeOut();

    }

    private static void sendTakeOut() {
        lock.lock();
        try {
            log.debug("开始送外卖了！");
            hasTakeOut = true;
            waitTakeOut.signal();
        }finally {
            lock.unlock();
        }
    }

    private static void sendCigarette() {
        lock.lock();
        try {
            log.debug("开始送烟了！");
            hasCigarette = true;
            waitCigaretteQueue.signal();
        }finally {
            lock.unlock();
        }

    }


}
