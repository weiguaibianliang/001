package com.ManyThread;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.formula.functions.T;

import java.util.ArrayList;
import java.util.concurrent.locks.LockSupport;

import static java.lang.Thread.sleep;

@Slf4j
public class Test10 {

    public static void main(String[] args) throws InterruptedException {
//        Thread t1 = new Thread(() -> {
//            log.debug("sleep....");
//            try {
//                sleep(5000);//wait,join
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//        },"t1");
//        t1.start();
//        log.debug("interrupt");
//        //如果是打断带有sleep的线程，打断标记是false，如果是打断正常线程，打断标记是true。
//        t1.interrupt();
//        //获取该线程的打断标记
//        log.debug("打断标记：{}",t1.isInterrupted());
//
        Thread t2 = new Thread(() ->{
            while (true){
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Thread thread = Thread.currentThread();
                boolean interrupted = thread.isInterrupted();
                if(interrupted){
                    log.debug("打断状态：{}", interrupted);
                    break;
                }
            }
        },"t2");
        t2.start();
        sleep(500);
        t2.interrupt();

        Thread t3 = new Thread(() ->{
            for (int i = 0; i < 5; i++){
                log.debug("park...");
                LockSupport.park();
                log.debug("打断状态：{}", Thread.currentThread().isInterrupted());
            }
        },"t3");
        t3.start();
        sleep(1000);
        t3.interrupt();
    }

}
