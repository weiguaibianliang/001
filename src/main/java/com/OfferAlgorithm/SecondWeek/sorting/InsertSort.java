package com.OfferAlgorithm.SecondWeek.sorting;

import java.util.Arrays;

public class InsertSort {

    /**
     * 与打扑克牌差不多，需要把新拿到的牌插进去(在已排序序列中从后向前扫描，找到相应位置并插入)
     * 基本思想：一组数字{5,2,4,6,1,3}，从第二个数字开始，将其认为是新增加的数字，
     * 这样第二个数字只需与其左边的第一个数字比较后排序，以此类推，直到最后一个数字与前面的所有数字比较结束，
     * 插入排序完成。
     */

    public static void main(String[] args) {
        //新建一个待排序的数组
        int[] arr = {5,2,4,6,1,3};
        for (int i = 1; i < arr.length; i++) {
            int temp = arr[i];
            int j;
            for (j = i - 1; j >= 0; j-- ){
                //判断此时拿的牌与前面已拿的牌的大小
                //在已排序序列中从后向前扫描，找到相应位置并插入
                if(arr[j] > temp){
                    arr[j+1] = arr[j];
                }else {
                    break;
                }
            }
            arr[j + 1] = temp;
        }
        System.out.println(Arrays.toString(arr));
    }


}
