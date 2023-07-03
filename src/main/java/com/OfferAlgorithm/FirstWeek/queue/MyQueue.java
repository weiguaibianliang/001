package com.OfferAlgorithm.FirstWeek.queue;

import java.util.Scanner;
import java.util.Stack;

public class MyQueue {

    /**
     * 描述：请你仅使用两个栈实现先入先出队列
     * 队列应当支持一般队列支持的所有操作（push、pop、peek、empty）
     * 实现MyQueue类
     * void push(int x)将元素x推到队列的末尾
     * int pop()从队列的开头移除并返回元素
     * int peek()返回队列开头的元素
     * boolean empty()如果队列为空，返回true;否则，返回false
     */
    //定义两个栈，一个入栈和一个出栈
    Stack<Integer> stackIn;
    Stack<Integer> stackOut;

    //默认构造方法
    public MyQueue(){
        //初始化两个栈
        stackIn = new Stack<>();
        stackOut = new Stack<>();
    }

    public static void main(String[] args) {
        //输入添加的元素到队列中
        Scanner scanner = new Scanner(System.in);
        System.out.print("输入你想添加到队列中的元素：");
        String s = scanner.nextLine();
        char[] chars = s.toCharArray();
        MyQueue myQueue = new MyQueue();
        myQueue.push(chars);
        System.out.println(myQueue.pop());
        System.out.println(myQueue.peek());
        System.out.println(myQueue.isEmpty());
    }


    //模拟队列的入队操作
    private void push(char[] chars) {
        int[] x = new int[chars.length];
        for (int i = 0; i < chars.length; i++) {
            x[i] = chars[i] - 48;
        }
        for (int j : x) {
            stackIn.push(j);
        }
    }

    //模拟队列的出队操作
    public int pop(){
        //如果出栈中的元素为空，那么就要把入栈中的栈顶元素添加到出栈中
        if(stackOut.isEmpty()){
            //把入栈中的删除元素之后的每一个元素都添加到出栈中
            while (!stackIn.isEmpty()){
                stackOut.push(stackIn.pop());
            }
        }
        return stackOut.pop();
    }

    //模拟队列的返回队列的开头元素
    public int peek(){
        //如果出栈中的元素为空，那么就要把入栈中的栈顶元素添加到出栈中
        if(stackOut.isEmpty()){
            //把入栈中的删除元素之后的每一个元素都添加到出栈中
            while (!stackIn.isEmpty()){
                stackOut.push(stackIn.pop());
            }
        }
        return stackOut.peek();
    }

    //判断队列是否为空
    public boolean isEmpty(){
        //如果入栈和出栈中的元素都为空，说明该队列就是空的
        return stackIn.isEmpty() && stackOut.isEmpty();
    }


}
