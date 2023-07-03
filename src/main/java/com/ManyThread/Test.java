package com.ManyThread;

public class Test {
    public static void main(String[] args) {
        Thread thread = new Thread(){
            public void run(){

            }
        };
        //启动线程
        thread.start();
    }


}
