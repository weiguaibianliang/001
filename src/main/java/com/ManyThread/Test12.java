package com.ManyThread;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Test12 {
    static int counter = 0;
    static Object lock = new Object();

    public static void main(String[] args) throws InterruptedException{

        //实例化一个对象
        Room room = new Room();
        Thread t1 = new Thread(() ->{
            for (int i = 0; i < 5000; i++) {
               room.increment();
            }
        },"t1");

        Thread t2 = new Thread(() ->{
            for (int i = 0; i < 5000 ; i++) {
                room.decrement();
            }
        },"t2");

        t1.start();
        t2.start();
        t1.join();
        t2.join();
        log.debug("{}",room.getCounter());
    }
}

//面向对象进行改进
class Room{
    //定义一个成员变量
    private int counter = 0;

    //对自增和自减方法进行加锁
    public void increment(){
        synchronized (this){
            counter++;
        }
    }

    public void decrement(){
        synchronized (this){
            counter--;
        }
    }

    //然后对获取数据的方法进行加锁
    public int getCounter(){
        synchronized (this){
            return counter;
        }
    }
}
