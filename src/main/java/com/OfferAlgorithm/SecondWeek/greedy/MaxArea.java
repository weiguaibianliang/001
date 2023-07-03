package com.OfferAlgorithm.SecondWeek.greedy;

import java.util.Arrays;

public class MaxArea {
    /**
     * 贪心算法：在对问题求解时，总是做出在当前看来是最好的选择，也就是说不从整体最优上考虑，
     * 他锁做出的是在某种意义上的局部最优解。贪心选择是指所求问题的整体最优解可以通过一系列局部最优的
     * 选择，即贪心选择来达到。
     *
     */

    //例：盛最多水的容器
    //给你n个非负整数a1,a2,....an,每个数代表坐标中的一个点（i,ai）。在坐标内画n条垂直线，垂直线i的两个端点分别为（i,ai）和（i,0）。
    //找出其中的两条线，使得他们与x轴共同构成的容器可以容纳最多的水。
    public static void main(String[] args) {
        //创建一个非负整数数组
        int[] nums = {3,2,1,4,7,5,6};
        //输出最后结果的两条线
        int[] result = new int[2];
        //利用双指针求解问题
        maxArea(nums,result);
        System.out.println(Arrays.toString(result));
    }

    private static void maxArea(int[] nums, int[] result) {
        //定义左指针
        int left = 0;
        //定义右指针
        int right = nums.length - 1;
        //定义容器盛水的面积
        int area = 0;
        while (left <= right){
            //定义两个直线之间的距离
            int distance = right - left;
            //左右两个指针指向的水柱中最短的那个作为高度
            int height = Math.min(nums[left],nums[right]);
            //盛水面积
            int res = distance * height;
            if(res > area){
                area = res;
                result[0] = nums[left];
                result[1] = nums[right];
            }
            if(nums[left] < nums[right]){
                left++;
            }else {
                right--;
            }
        }
    }
}
