package com.ManyThread;

public class Test17 {

    /**
     * 同步模式之顺序控制,让两个线程按照一定的顺序输出
     * wait notify版
     */
    //用来同步的对象
    static Object obj = new Object();
    //t2线程运行标记，代表t2是否执行过
    static boolean t2runed = false;

    public static void main(String[] args) {


    }

}
