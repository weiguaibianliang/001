package com.OfferAlgorithm.FirstWeek.stack;

import java.util.Stack;

public class MinStack {
    /**
     * 设计一个支持push，pop,top操作，并能在常数时间内检索到最小元素的栈。
     * 由于需要在常数时间内找到最小的元素，那么说明肯定不能使用遍历，因为遍历是O（n）级别
     * 的时间，那么只能使用辅助空间进行存储，这是一种空间换时间的思想。
     */

    //首先定义好两个栈
    //一个栈叫做stack，负责栈的正常操作
    Stack<Integer> stack;
    //一个栈叫做minStack,负责获取stack中的最小值，它等价于遍历stack中的所有元素，把升序的数字都删除掉，留下一个从栈底到栈顶降序的栈。
    Stack<Integer> minStack;

    public MinStack(){
        //在这个函数中初始化两个栈，传入的参数为空，返回也为空
        //初始化stack
        stack = new Stack<>();
        //初始化minStack
        minStack = new Stack<>();
    }
    public void push(int x){
        //新添加的元素添加到stack中
        stack.push(x);
        //判断minStack是否为空，如果为空，直接同时把新添加的元素添加到minStack中
        //如果minStack不为空
        if(!minStack.isEmpty()){
            //获取辅助栈minStack的栈顶元素
            int peek = minStack.peek();
            //判断普通栈的栈顶元素与辅助栈栈顶元素的大小
            if(x < peek){
                minStack.push(peek);
            }
        }else {
            minStack.push(x);
        }
    }

    public void pop(){
        //首先移去普通栈的栈顶元素
        int pop = stack.pop();
        //如果普通栈的元素发生变化，那么辅助栈的元素肯定也要和普通栈的元素保持一致
        int top = minStack.peek();
        if(pop == top){
            minStack.pop();
        }
    }

    public int top(){
        //返回stack的栈顶元素
        return stack.peek();
    }

    public int getMin(){
        //返回minStack的栈顶元素
        return minStack.peek();
    }

    public static void main(String[] args) {
        MinStack minStack1 = new MinStack();
        minStack1.push(1);
        minStack1.push(3);
        minStack1.push(2);
        minStack1.push(5);
        minStack1.push(4);
        minStack1.pop();
        System.out.println(minStack1.top());
        System.out.println(minStack1.getMin());
    }


}
