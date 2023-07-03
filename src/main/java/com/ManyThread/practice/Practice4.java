package com.ManyThread.practice;

import lombok.extern.slf4j.Slf4j;

import java.text.ChoiceFormat;
import java.text.NumberFormat;
import java.util.Random;

@Slf4j
public class Practice4 {
    //转账问题中的线程安全问题
    public static void main(String[] args) throws InterruptedException{
        Account a = new Account(1000);
        Account b = new Account(1000);
        Thread t1 = new Thread(() ->{
            for (int i = 0; i < 1000; i++) {
                a.transfer(b,randomAmount());
            }
        },"t1");
        Thread t2 = new Thread(() ->{
            for (int i = 0; i < 1000; i++) {
                b.transfer(a,randomAmount());
            }
        },"t2");
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        //查看2000次转账后两个账户的总金额
        log.debug("total:{}",(a.getMoney() + b.getMoney()));
    }
    NumberFormat currency = NumberFormat.getCurrencyInstance();
    NumberFormat percent = NumberFormat.getPercentInstance();
    //Random为线程安全
    static Random random = new Random();
    public static int randomAmount(){
        return random.nextInt(100) + 1;
    }
}

class Account{
    private int money;

    public Account(int money) {
        this.money = money;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    //转账方法
    //target-转账的目标账户
    public  void transfer(Account target, int amount){
        synchronized (Account.class) {
            if (this.money > amount) {
                this.setMoney(this.money - amount);
                target.setMoney(target.getMoney() + amount);
            }
        }
    }
}
