package com.ManyThread.practice;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Vector;

public class practice3 {
    //卖票练习   测试下面代码是否存在线程安全问题，并尝试改正。
    public static void main(String[] args) {
        TicketWindow ticketWindow = new TicketWindow(8000);
        List<Thread> list = new ArrayList<>();
        //用来存储卖出去多少张票
        List<Integer> sellCount = new Vector<>();
        for (int i = 0; i < 2000; i++) {
            Thread t = new Thread(() -> {
                //分析这里的竞态条件
                int count = ticketWindow.sell(randomAmount());
                sellCount.add(count);
            });
            list.add(t);
            t.start();
        }
        list.forEach((t) -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        //卖出去的票求和
        System.out.println("sell count:{}"+ sellCount.stream().mapToInt(c -> c).sum());
        //剩余票数
        System.out.println("remainder count:{}" + ticketWindow.getCount());
    }

    //Random为线程安全
    static Random random = new Random();

    //随机1~5
    public static int randomAmount() {
        return random.nextInt(5) + 1;
    }
}

//定义卖票窗口
class TicketWindow {
    private int count;

    public TicketWindow(int count) {
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public synchronized int sell(int amount) {
        if (this.count >= amount) {
            this.count -= amount;
            return amount;
        } else {
            return 0;
        }
    }
}
