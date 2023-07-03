package com.OfferAlgorithm.SecondWeek.sorting;

import java.util.Arrays;

public class MergeNums {

    /**
     * 合并两个有序数组
     * 方法：
     * 1、合并后排序//时间复杂度较差，不推荐使用
     * 2、双指针，从前向后//需要单独创建数组空间，所占内存较大
     * 3、双指针，从后向前//运用倒序思维和双指针，简便运算，无需创造多余空间，推荐
     */
    public static void main(String[] args) {
        //定义两个数组
        int[] m = {1,3,5,7,9};
        int[] n = {2,4,6,8,10};
        int[] add = new int[m.length + n.length];
        //合并两个有序数组
        mergeNums(m,n,add);
        System.out.println(Arrays.toString(add));
    }

    private static void mergeNums(int[] m, int[] n, int[] add) {
        int i = m.length -1;
        int j = n.length -1;
        int cur = add.length - 1;
        //判断两个数组，进行遍历比较
        while(i>=0&&j>=0){
            if(m[i]>n[j]){
                add[cur--]=m[i--];
            }else{
                add[cur--]=n[j--];
            }
        }
        //如果nums1遍历提前结束，将nums2剩下的所有数字直接给nums1
        if(i<0){
            for(int b=j;b>=0;b--){
                add[cur--]=n[b];
            }
        }else {
            for (int c = i; c>=0; c--) {
                add[cur--] = m[c];
            }
        }
    }

}
