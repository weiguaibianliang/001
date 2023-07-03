package com.OfferAlgorithm.SecondWeek.sorting;

import java.util.Arrays;

public class SortedSquares {


    /**
     * 题目描述：给你一个非递减顺序排序的整数数组nums，返回每个数字的平方组成的新数组，要求也按非递减顺序排序
     * 可能出现的情况：
     * 1、全部都是正数
     * 2、全部都是负数
     * 3、负数和正数掺杂
     */

    //方法一：先都化为整数，然后归并排序后再平方输出
    //方法二：根据整数和负数掺杂的数组，平方最大数一定是在两端的其中一个，用双指针进行判断
    public static void main(String[] args) {
        //输入一个需要处理的数组
        int[] nums = {-5, -3, 2, 1};
        //得出最后的结果数组
        int[] result = new int[nums.length];
        //调用方法二：双指针方法
        sortedSquares(nums, result);
        //输出结果
        System.out.println(Arrays.toString(result));

    }

    private static void sortedSquares(int[] nums, int[] result) {

        //设置两个指针
        int left = 0;
        int right = nums.length - 1;
        int i = nums.length - 1;
        while (left <= right) {
            if (nums[left] * nums[left] < nums[right] * nums[right]) {
                result[i] = nums[right] * nums[right];
                right--;
            } else {
                result[i] = nums[left] * nums[left];
                left++;
            }
            i--;
        }
    }
}
