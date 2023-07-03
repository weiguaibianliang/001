package com.OfferAlgorithm.FirstWeek.stack;

import java.util.Stack;

public class Trap {

    //接雨水

    /**
     * 给定n个非负整数表示每个宽度为1的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
     * 输入：height = [0,1,0,2,1,0,1,3,2,1,2,1]
     * 输出：6
     * 解释：上面是由数组[0,1,0,2,1,0,1,3,2,1,2,1]表示的高度图，在这种情况下，可以接6个单位的雨水（蓝色部分表示雨水）。
     */
    public static void main(String[] args) {
        //输入每个柱子的高度
        int[] height = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        //调用接雨水中有关栈的方法
        //输出接的雨水量
        int result = Trap.trapResult(height);
        System.out.println(result);

    }

    private static int trapResult(int[] height) {
        //如果接雨水柱子的个数小于等于2时，不能收集雨水
        if(height.length <= 2){
            return 0;
        }
        //利用栈的知识来求解
        Stack<Integer> stack = new Stack<>();
        //判断当前的元素与栈顶元素进行判断
        int result = 0;
        for (int i = 0; i < height.length; i++) {
            if (stack.isEmpty()) {
                //如果栈为空，则需要往栈中添加元素
                stack.push(i);
            } else if (height[i] < height[stack.peek()]) {
                //如果当前元素比栈中的栈顶元素要小
                stack.push(i);
            } else if (height[i] == height[stack.peek()]) {
                //如果当前元素等于栈中栈顶的元素
                stack.push(i);
            } else {
                //不满前面条件则会出现凹槽可以接住雨水
                while (!stack.isEmpty() && height[i] > height[stack.peek()]) {
                    // 1、获取栈顶的下标，bottom 为凹槽的底部位置
                    int bottom = stack.peek();

                    // 将栈顶元素推出，去判断栈顶之前的元素是否存在，即凹槽的左侧是否存在
                    stack.pop();

                    // 2、如果栈不为空，即栈中有元素，即凹槽的左侧存在
                    if (!stack.empty()) {

                        // 凹槽左侧的高度 height[stack.peek() 和 凹槽右侧的高度 height[i]
                        // 这两者的最小值减去凹槽底部的高度就是凹槽的高度
                        int h = Math.min(height[stack.peek()], height[i]) - height[bottom];

                        // 凹槽的宽度等于凹槽右侧的下标值 i 减去凹槽左侧的下标值 stack.peek 再减去 1
                        int w = i - stack.peek() - 1;

                        System.out.println("凹槽右侧-->" + i);
                        System.out.println("凹槽左侧-->" + stack.peek());
                        System.out.println("凹槽高度-->" + h);
                        System.out.println("凹槽宽度-->" + w);
                        System.out.println("凹槽面积-->" + h * w);
                        System.out.println("---------------");

                        // 将计算的结果累加到最终的结果上去
                        result += h * w;
                    }
                }

                // 栈中和此时的元素可以形成栈的情况在上述 while 循环中都已经判断了
                // 那么，此时栈中的元素必然不可能大于此时的元素，所以可以把此时的元素添加到栈中
                stack.push(i);
            }
        }
        return result;
    }


}

