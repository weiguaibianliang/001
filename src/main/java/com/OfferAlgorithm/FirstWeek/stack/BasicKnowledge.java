package com.OfferAlgorithm.FirstWeek.stack;

import org.apache.commons.collections.ArrayStack;

import java.util.Scanner;

public class BasicKnowledge {

    /**
     * 1、栈是一个先入后出的有序链表
     * 2、栈中元素的插入和删除只能在线性表同一端进行的一种特殊线性表。允许插入和删除的一端，为变化的一端，称为栈顶（top）,
     * 另一端为固定的一端，称为栈底（bottom）。
     * 3、根据栈的定义可知，最先放入栈中元素在栈底，最后放入元素在栈顶，而删除元素刚好相反，最后放入的元素最先删除，最先放入的元素最后删除。
     */
    //由于栈是一种有序列表，当然可以使用数组的结构来存储栈的数据内容


    public static void main(String[] args) {
        //创建栈
        ArrayStack stack = new ArrayStack(4);
        String key = "";
        boolean loop = true;
        Scanner scanner = new Scanner(System.in);
        while (loop){
            System.out.println("(show)遍历栈");
            System.out.println("(exit)退出栈");
            System.out.println("(push)往栈中添加数据");
            System.out.println("(pop)从栈中取出数据");
            System.out.println("请输入你的选择");
            key = scanner.next();
            switch (key){
                case "show":
                    stack.showStack();
                    break;
                case "exit":
                    loop = false;
                    break;
                case "push":
                    System.out.println("请输入往栈中添加的数据");
                    int value = scanner.nextInt();
                    stack.push(value);
                    break;
                case "pop":
                    try {
                        int num = stack.pop();
                        System.out.printf("从栈中取出的数据为%d\n",num);
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
            }
        }

    }

    static class ArrayStack{
        //栈的大小
        public int maxSize;
        //使用数组来模拟栈
        public int[] stack;
        //表示的是栈顶
        public int top = -1;

        public ArrayStack(int maxSize){
            this.maxSize = maxSize;
            stack = new int[maxSize];
        }

        //判断栈是否满栈
        public boolean ifFull(){
            return top == maxSize -1;
        }
        //判断栈是否为空
        public boolean isEmpty(){
            return top == -1;
        }

        //遍历出栈
        public void showStack() {
            if(isEmpty()){
                System.out.println("栈空");
            }
            for (int i = top; i >= 0; i--){
                System.out.printf("stack[%d]=%d\n",i,stack[i]);
            }
        }

        //入栈
        public void push(int value) {
            if(ifFull()){
                System.out.println("栈满");
            }
            top++;
            stack[top] = value;
        }

        //出栈
        public int pop() {
            if(isEmpty()){
                throw new RuntimeException("栈里面没有数据了");
            }
            int value = stack[top];
            top--;
            return value;
        }
    }
}
