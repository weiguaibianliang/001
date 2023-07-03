package com.OfferAlgorithm.SecondWeek.recursion;

import java.util.HashMap;
import java.util.Map;

public class FrogJumpStep {

    //使用hashmap，充当备忘录的作用,做临时储存的作用
    Map<Integer,Integer> tempMap = new HashMap<>();

    /**
     * 青蛙跳阶，递归解法的时间复杂度为O(1)*O(2^N),就是指数级别的，
     * 爆炸性增长的，如果n比较大的话，超过栈的空间是很正常的。
     */

    /**
     * 带【备忘录】的递归算法，子问题个数 = 树节点数 = n,所以【备忘录】
     * 的递归算法的时间复杂度是O(n)。
     */
    public static void main(String[] args) {
        //定义青蛙跳的阶数n
        int n = 1000;
        //调用备忘录递归算法计算青蛙跳阶的不同方法的总数量
        FrogJumpStep frogJumpStep = new FrogJumpStep();
        int count = frogJumpStep.frogJumpStep(n);
        System.out.println("青蛙跳阶的不同方法个数："+ count);
    }

    public  int frogJumpStep(int n) {

        if(n == 0){
            return 1;
        }
        if(n <= 2){
            return n;
        }
        //先判断有没有计算过，即看看备忘录有没有
        if(tempMap.containsKey(n)){
            //备忘录有的话，即计算过，直接返回
            return tempMap.get(n);
        }else {
            //备忘录没有，即没有计算过，执行递归计算，并且把结果保存到备忘录map中，对1000000007取余
            tempMap.put(n,(frogJumpStep(n-1) + frogJumpStep(n -2)) % 1000000007);
            return tempMap.get(n);
        }
    }

}
