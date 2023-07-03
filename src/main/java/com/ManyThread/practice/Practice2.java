package com.ManyThread.practice;


import lombok.extern.slf4j.Slf4j;

import static java.lang.Thread.sleep;

@Slf4j
public class Practice2 {

    /**
     * 方案一：模拟小王等老王的水烧开了，小王泡茶
     * 方案二：要实现老王等小王的茶叶拿来了，老王泡茶
     * 方案三：模拟老王把水壶交给小王泡茶
     * 方案四：模拟小王把茶叶交给老王泡茶
     */

    //泡茶的过程：洗水壶（1min）、烧开水(15min)、洗茶壶(1min)、洗茶杯(2min)、找茶叶(1min)、泡茶
    //如何使泡茶的时间最短，涉及到多线程问题
    public static void main(String[] args) throws InterruptedException {
        //方案一
//        //线程一：老王操作
//        Thread t1 = new Thread(()->{
//            log.debug("洗水壶");
//            try {
//                sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            log.debug("烧开水");
//            try {
//                sleep(15000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        },"老王");
//
//        //线程二：小王操作
//        Thread t2 = new Thread(()->{
//            log.debug("洗茶壶");
//            try {
//                sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            log.debug("洗茶杯");
//            try {
//                sleep(2000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            log.debug("找茶叶");
//            try {
//                sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            try {
//                t1.join();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            log.debug("小王泡茶");
//        },"小王");
//
//        t1.start();
//        t2.start();
        //方案二
        //线程一：老王操作
        Thread t1 = new Thread(() -> {
            log.debug("洗水壶");
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.debug("烧开水");
            try {
                sleep(15000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.debug("小王泡茶");
        }, "老王");

        //线程二：小王操作
        Thread t2 = new Thread(() -> {
            log.debug("洗茶壶");
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.debug("洗茶杯");
            try {
                sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.debug("找茶叶");
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "小王");
        t1.start();
        t2.start();
    }
}
