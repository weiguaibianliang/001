package com.OfferAlgorithm.SecondWeek.greedy;

import java.util.Stack;

public class RemoveKDigits {

    /**
     * 给你一个字符串表示的非负整数num和一个整数k，移除这个数中的k位数字，使得剩下的数字最小。
     * 请你以字符串形式返回这个最小的数字。
     */
    public static void main(String[] args) {
        //定义一个非负整数num和一个整数k
        String num = "1345221";
        int k = 3;
        //调用方法得到以字符串形式返回这个最小的数字
        String result = removeKDigits(num, k);
        //调用归并排序，然后取去掉最大的三位数

        System.out.println(result);
    }

    private static String removeKDigits(String num, int k) {

        //初始化栈，用来存储需要保留的数字
        Stack<Character> stack = new Stack<>();
        //初始化字符串，用来保留最后的结果
        StringBuilder result = new StringBuilder();
        //遍历字符串num
        for (int i = 0; i < num.length(); i++) {
            //获取此时遍历的字符
            char digit = num.charAt(i);
            //如果此时
            //1、栈不为空
            //2、栈顶元素大于此时遍历的字符
            //3、还没有删除足够多的数字，即k > 0
            //那么这个时候需要把栈顶元素弹出
            while (!stack.empty() && stack.peek() > digit && k > 0) {
                stack.pop();
                k--;
            }
            //如果发现此时遍历的字符为0，并且栈为空
            //那么就不要把0放入到栈中，否则最终的结果会以0开头
            if (digit == 0 && stack.empty()) {
                continue;
            }
            stack.push(digit);
        }
        //遍历完所有字符后，还没有删除足够多的元素
        //比如：123456789，k=1
        while (!stack.empty() && k > 0){
            stack.pop();
            k--;
        }
        //操作完毕后，如果发现栈为空，按上面逻辑我们会返回字符“”
        //需要返回0
        if(stack.empty()){
            return "0";
        }

        //如果栈中还有元素，继续操作
        while (!stack.empty()){
            result.append(stack.peek());
            stack.pop();
        }
        return result.reverse().toString();
    }
}
