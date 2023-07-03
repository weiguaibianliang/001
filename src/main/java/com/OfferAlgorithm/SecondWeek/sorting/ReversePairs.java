package com.OfferAlgorithm.SecondWeek.sorting;

import java.util.Arrays;

public class ReversePairs {

    private static int count;

    /**
     * 在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。
     * 输入一个数组，求出这个数组中的逆序对的总数。
     */

    public static void main(String[] args) {
        count = 0;
        //输入一个数组
        int[] arr = {7,5,6,4};
        //初始化最后需要排序的数组
        int[] add = new int[arr.length];
        //利用归并排序来查找逆序对的个数
        mergeSort(arr,0,arr.length-1,add);
        //输出逆序对的个数
        System.out.println("逆序对的个数为："+count);
    }

    private static void mergeSort(int[] arr, int left, int right, int[] add) {
        if(left >= right) {
            return ;
        }
        int mid = (left + right)/2;
        //先对左边进行递归分解
        mergeSort(arr,left,mid,add);
        //再对右边进行递归分解
        mergeSort(arr,mid+1,right,add);
        //如果左边不能再分解了就要进行组合
        merge(arr,left,mid,right,add);
        System.out.println(Arrays.toString(arr));

    }

    private static void merge(int[] arr, int left, int mid, int right, int[] add) {
        //定义左边数组的初始索引
        int i = left;
        //定义右边数组的初始索引
        int j = mid + 1;
        //定义临时数组的初始索引
        int t = 0;
        while (i <= mid && j <= right){
            if(arr[i] < arr[j]){
                add[t] = arr[i];
                i++;
            }else {
                add[t] = arr[j];
                //在这里统计逆序对
                count += (mid - i +1);
                j++;
            }
            t++;
        }
        //当某一边数组完成后，需要把另一边数组添加到临时数组中
        while (i <= mid || j <= right){
            if(j > right){
                add[t] = arr[i];
                i++;
            }else {
                add[t] = arr[j];
                j++;
            }
            t++;
        }

        //当临时数组左右组合完成后，需要把数组中的元素复制到原数组arr中。
        int n = 0;
        int tempLeft = left;
        while (tempLeft <= right){
            arr[tempLeft] = add[n];
            n++;
            tempLeft++;
        }
    }
}
