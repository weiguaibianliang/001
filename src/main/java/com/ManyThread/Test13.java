package com.ManyThread;

import lombok.extern.slf4j.Slf4j;

import static java.lang.Thread.sleep;

@Slf4j
public class Test13 {
    final static Object obj = new Object();

    public static void main(String[] args) throws InterruptedException {
        new Thread(() ->{
            synchronized (obj){
                log.debug("执行....");
                try {
                    obj.wait();//让线程在obj上一直等待下去
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.debug("其它代码.....");
            }
        }).start();

        new Thread(() ->{
            synchronized (obj){
                log.debug("执行....");
                try {
                    obj.wait();//让线程在obj上一直等待下去
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.debug("其它代码.....");
            }
        }).start();

        //主线程两秒后执行
        sleep(2000);
        log.debug("唤醒obj上其它线程");
        synchronized (obj){
            obj.notify();//唤醒上一个线程
        }
    }
}
