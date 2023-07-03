package com.ManyThread.practice;


import lombok.extern.slf4j.Slf4j;
import org.springframework.retry.backoff.Sleeper;

import static java.lang.Thread.sleep;

@Slf4j
public class Practice1 {

    //应用统筹问题，给出烧水泡茶的多线程解决方案

    //方法一：洗水壶+烧水+洗茶壶+找茶叶+洗茶杯，这种串行的方法就比较浪费时间
    //方法二：使用两个线程，多线程解决方案，烧水的过程中去找茶叶、洗茶壶、洗茶杯

    public static void main(String[] args) throws InterruptedException {

        WashWaterBottle t1 = new WashWaterBottle();
        t1.start();
        BoilWater t2 = new BoilWater();
        t2.start();
        t1.thread.join();
        log.debug("泡茶");
    }

    public static class WashWaterBottle {

        //定义一个洗水壶和烧开水的线程
        private Thread thread;

        //开始洗水壶操作
        public void start() throws InterruptedException {
            thread = new Thread(() -> {
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
            }, "t1");
            thread.start();
        }
    }

    public static class BoilWater {

        //定义一个洗水壶、洗茶杯、拿茶叶的线程
        private Thread thread;

        //开始洗茶壶、洗茶杯、找茶叶
        public void start() {

            thread = new Thread(() -> {
                log.debug("洗茶壶");
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                log.debug("洗茶杯");
                try {
                    sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                log.debug("找茶叶");
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }, "t2");
            thread.start();
        }

    }
}
