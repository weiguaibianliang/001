package com.ManyThread;


import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class Test7 {

//    @Autowired
//    private static final Logger LOGGER = LoggerFactory.getLogger(Test7.class);


    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread("t1") {

            @Override
            public void run() {
//                System.out.println("enter sleep ....");
                log.debug("enter sleep .....");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    System.out.println("wake up ....");
                    throw new RuntimeException(e);
                }
            }
        };
        t1.start();
        log.error("5554");
        //主线程睡眠
        Thread.sleep(1000);
        System.out.println("interrupt");
        //打断t1线程睡眠
        t1.interrupt();

    }
}
