package com.OfferAlgorithm.FirstWeek.stack;

import java.util.Stack;

public class ValidateStackSequences {

    /**
     * 给定pushed和popped两个序列,每个序列中的值都不重复，
     * 只有当它们可能是在最初空栈上进行推入push和弹出pop操作序列的结果时，
     * 返回true;否则，返回false。
     */

    //例：输入:pushed = [1,2,3,4,5],popped = [4,5,3,2,1],true
    //反例：输入：pushed = [1,2,3,4,5],popped = [4,3,5,1,2],false
    public static void main(String[] args) {
        //创建pushed和popped两个序列
        int[] pushed = {1,2,3,4,5};
        int[] popped = {4,5,3,2,1};
        boolean flag = validateStackSequences(pushed,popped);
        System.out.println(flag);
    }

    private static boolean validateStackSequences(int[] pushed, int[] popped) {
        //创建一个栈来入栈和出栈
        Stack<Integer> stack = new Stack<Integer>();
        //创建一个索引来指定popped里面的元素
        int index = 0;
        //遍历pushed中的元素来进行入栈和判断popped里的元素是否符合规则
        for (int j : pushed) {
            //将pushed序列中的元素
            stack.push(j);
            //判断popped序列中的元素是否与pushed序列的栈顶元素匹配
            //索引对呀popped中的元素
            while(!stack.isEmpty() && popped[index] == stack.peek()) {
                //栈中的元素出栈
                stack.pop();
                //索引叠加
                index++;
            }
        }
        //如果栈不为空，说明popped的元素没有与栈中的元素出栈顺序没有一一对应
        return stack.isEmpty();

    }
}
