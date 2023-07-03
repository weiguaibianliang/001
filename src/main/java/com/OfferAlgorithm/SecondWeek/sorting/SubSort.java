package com.OfferAlgorithm.SecondWeek.sorting;

import java.util.Arrays;

public class SubSort {

    /**
     * 题目描述：给定一个整数数组，找出索引m和n，只要将索引区间[m,n]的元素排好序，整个数组就是有序的。
     * 函数返回值为[m,n]，若不存在这样的m和n，请返回[-1,-1]。
     */

    /**
     * 部分排序：
     * 1、寻找最靠右的那个数，即它的左边存在大于它的数。
     * 2、寻找最靠左的那个数，即它的右边存在小于它的数。
     * 最靠右的数：它的左边存在大于它的数，它的右边数都比它更大。
     * 最靠左的数：它的右边存在小于它的数，它的左边数都比它更小。
     */

    public static void main(String[] args) {
        //定义一个数组
        int[] nums = {1,2,3,7,5,8,10};
        //调用部分排序算法
        int[] result = subSort(nums);
        System.out.println(Arrays.toString(result));
    }

    private static int[] subSort(int[] nums) {

        //如果数组为空、数组没有元素、数组只有一个元素
        if(nums == null || nums.length == 0 || nums.length == 1){
            return new int[]{-1,-1};
        }
        //寻找最靠左的那个数，第一次遍历是从尾到头进行遍历。
        //一开始默认最右边的数为最小值
        int min = nums[nums.length - 1];
        //如果没有最靠左的那个数
        int m = -1;
        //从尾到头进行遍历
        for (int i = nums.length - 2; i >= 0 ; i--) {
            //判断依次遍历的元素与默认最小值的大小
            if(nums[i] < min){
                min = nums[i];
            }else {
                m = nums[i];
            }
        }
        //寻找最靠右的那个数，第一次遍历是从头到尾进行遍历
        //一开始默认最左边的数为最大值
        int max = nums[0];
        //如果没有最靠右的那个数
        int n = -1;
        //从头到尾进行遍历
        for (int j = 1; j < nums.length ; j++) {
            //判断依次遍历的元素与默认最大值的大小
            if(nums[j] > max){
                max = nums[j];
            }else {
                n = nums[j];
            }
        }

        //如果找到m和n
        return new int[]{m,n};


    }

}
