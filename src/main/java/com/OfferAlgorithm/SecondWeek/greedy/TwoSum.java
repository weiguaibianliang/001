package com.OfferAlgorithm.SecondWeek.greedy;

import org.apache.commons.collections.map.AbstractMapDecorator;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TwoSum {
    /**
     * 两数之和：给定一个整数数组nums和一个整数目标值target，请你在该数组中找出和为目标值target的那两个整数，并返回它们的数组下标。
     * 你可以假设每种输入只会对应一个答案。数组中同一元素在答案里不能重复出现。
     * 输入：nums = [2,7,11,15],target = 9
     * 输出：[0,1]
     */

    public static void main(String[] args) {
        //输入需要处理的数组
        int[] nums = {3, 2, 4, 15};
        //输入目标值
        int target = 6;
        //new 输出数组
        int[] result = new int[2];
        //调用两数之和算法
        twoSum(nums, target, result);
        //输出结果
        System.out.println(Arrays.toString(result));

    }

    private static void twoSum(int[] nums, int target, int[] result) {

        //用map存储数组中的元素
        Map<Integer, Integer> integerMap = new HashMap<>(16);
        for (int i = 0; i < nums.length; i++) {
            Integer anotherValue = target - nums[i];
            if(integerMap.containsKey(anotherValue)){
                result[0] = integerMap.get(anotherValue);
                result[1] = i;
            }else {
                integerMap.put(nums[i],i);
            }
        }

    }

}
