package com.OfferAlgorithm.FirstWeek.stack;

import java.util.Arrays;
import java.util.Stack;

public class DailyTemperatures {

    /**
     * 给定一个列表temperatures = [73,74,75,71,69,72,76,73],你的输出应该是[1,1,4,2,1,1,0,0]
     * 提示：气温列表的长度范围是[1,30000]。每个气温的值均为华氏度，都是在[30,100]范围内的整数。
     */
    public static void main(String[] args) {
        //建立一个列表temperatures
        int[] temperatures = {73,74,75,71,69,72,76,73};
        int[] result = DailyTemperatures.generateArgument(temperatures);
        //输出生成的结果
        System.out.println(Arrays.toString(result));
    }

    private static int[] generateArgument(int[] temperatures) {
        //根据生成规则建立一个新栈来存放列表温度
        Stack<Integer> stack = new Stack<>();
        //建立一个数组来存放输出的结果
        int[] res = new int[temperatures.length];
        //建立索引表示输出结果的元素值
        int index = 0;
        //遍历温度列表
        for (int i = 0; i < temperatures.length; i++){
            //往栈中添加元素
            stack.push(temperatures[i]);
            index = i + 1;
            //判断栈顶元素与
            while (index < temperatures.length){
                if(temperatures[index] > stack.peek()){
                    res[i] = index - i;
                    break;
                }else {
                    res[i] = 0;
                }
                index++;
            }
        }
        return res;

    }
}
