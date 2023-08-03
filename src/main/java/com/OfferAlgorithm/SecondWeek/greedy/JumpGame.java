package com.OfferAlgorithm.SecondWeek.greedy;

//跳跃游戏
public class JumpGame {

    /**
     * 跳跃游戏：
     * 给定一个非负整数数组nums，你最初位于数组的第一个下标。
     * 数组中的每个元素代表你在该位置可以跳跃的最大长度
     * 判断你是否能够到达最后一个下标
     */
    public static void main(String[] args) {
        //定义一个非负整数数组nums
        int[] nums = {1,5,1,1,1,0};
        //调用跳跃游戏方法
        //从初始位置跳到哪个位置，然后就以跳到的那个位置为初始位置。
        boolean flag = jumpGame(nums);
        //判断你是否能达到最后一个下标
        System.out.println(flag);
    }

    /**
     * [0] true
     * [1] true
     * [2,0] true
     * [0,2,1] false
     * [1,5,1,1,1,1,1] true
     * @param nums
     * @return
     */
    private static boolean jumpGame(int[] nums) {

//        //最后一个下标的长度
//        int lastIndex = nums.length - 1;
//        for (int i = 0; i <nums.length -1 ; ++i) {
//            if(i + nums[i] >= lastIndex){
//                return true;
//            }
//            i+=nums[i];
//
//        }
//        return false;
//    }
        int n = nums.length;
        int rightmost = 0;
        for (int i = 0; i < n; ++i) {
            if (i <= rightmost) {
                rightmost = Math.max(rightmost, i + nums[i]);
                if (rightmost >= n - 1) {
                    return true;
                }
            }
        }
        return false;
    }
}
