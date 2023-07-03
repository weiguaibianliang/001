package com.OfferAlgorithm.SecondWeek.sorting;

import java.util.Arrays;

public class SortColors {

    //利用双指针
    /**
     * left
     * right
     * cur
     * 双指针，一个指针指向左边，一个指针指向右边，从左到右遍历元素，等于0放左，
     * 移动左指针，等于2放右，移动右指针，当遍历到右指针时停止。
     * 此题中，使用整数0、1和2分别表示红色、白色和蓝色
     */
    public static void main(String[] args) {
        //输入你想排序的颜色类型
        int[] colors = {2,0,2,1,1,0};
        //调用双指针颜色排序算法
        sortColors(colors);
        System.out.println(Arrays.toString(colors));
    }

    private static void sortColors(int[] colors) {
        //定义双指针left和right
        int left = 0;
        int right = colors.length - 1;
        //定义当前元素索引
        int cur = 0;
        while (cur <= right){
            //红色2摆放在最后面
            while (colors[cur] == 2 && cur <= right){
                //交换当前元素值和最后面元素的值
                swap(cur,right,colors);
                right--;
            }
            while (colors[cur] == 0 && cur >= left){
                swap(cur,left,colors);
                left++;
            }
            cur++;
        }
    }

    private static void swap(int a, int b, int[] colors) {
        int temp = colors[b];
        colors[b] = colors[a];
        colors[a] = temp;
    }
}
