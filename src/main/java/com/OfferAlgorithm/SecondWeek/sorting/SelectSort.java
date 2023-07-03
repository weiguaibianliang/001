package com.OfferAlgorithm.SecondWeek.sorting;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class SelectSort {

    /**
     * 基本思想：首先在未排序序列中找到最小（大）元素，存放到排序序列的起始位置，然后再从剩余未排序元素中继续寻找最小（大）元素，
     * 然后放到已排序序列的末尾。以此类推，直到所有元素均排序完毕。
     */

    public static void main(String[] args) {
        //输入一个即将排序的数组
        int[] arr = {12, 8, 7, 14, 27, 5, 3, 9, 33};
        //输入原数组的书序
        for (int a : arr) {
            System.out.print(a);
            System.out.print(" ");
        }
        System.out.println();
        //将数组进行选择排序
        selectSort(arr);
        //然后将排序好的数组在控制台输出
        for (int b : arr) {
            System.out.print(b);
            System.out.print(" ");
        }
        List<Integer> list = Arrays.asList(12, 8, 7, 14, 27, 5, 3, 9, 33);


    }

//    private static void selectSort(int[] arr) {
//        for (int i = 0; i < arr.length - 1; i++) {
//            int min = i;
//            for (int j = i + 1; j < arr.length; j++) {
//                if (arr[j] < arr[min]) {
//                    min = j;
//                }
//            }
//            if (min != i) {
//                swap(arr, i, min);
//            }
//
//        }
//
//    }
//
//    private static void swap(int[] arr, int a, int b) {
//        int temp = arr[b];
//        arr[b] = arr[a];
//        arr[a] = temp;
//    }


    public static void selectSort(int[] arr){

        for (int i = 0; i < arr.length -1; i++) {
            int min = i;
            for (int j = i + 1; j < arr.length; j++) {

                if(arr[j] < arr[min]){
                    min = j;
                }
            }
            if(min != i){
                swap(arr,i,min);
            }
        }
    }

    private static void swap(int[] arr, int i, int min) {

        int temp = arr[min];
        arr[min] = arr[i];
        arr[i] = temp;
    }


}
