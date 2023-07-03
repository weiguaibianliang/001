package com.OfferAlgorithm.SecondWeek.sorting;

import java.util.ArrayList;
import java.util.Scanner;

public class GetMedian {

    private static  ArrayList<Integer> list = new ArrayList<>(5000);

    private static  ArrayList<Integer> medianList = new ArrayList<>(5000);

    /**
     * 题目描述：如何得到一个数据流中的中位数？如果从数据流中读出奇数个数值，那么中位数就是所有数值排序之后位于中间的数值。
     * 如果从数据流中读出偶数个数值，那么中位数就是所有数值排序之后中间两个数的平均值。
     */
    public static void main(String[] args) {
        //实时读入字符，能够实时读取该组数据的中位数
        //利用scanner来实时插入数据
        Scanner scanner = new Scanner(System.in);
        //实时插入数据
        insert(list,scanner);
        int[] arr = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            arr[i] = list.get(i);
        }
        //对插入的元素进行排序（归并排序）
        int left = 0;
        int right = arr.length - 1;
        int[] add = new int[arr.length];
        mergeSort(arr,left,right,add);
        //排完序后查找中位数
        System.out.println(getMedian(arr));
    }

    private static ArrayList<Integer> getMedian(int[] arr) {
        if(arr.length % 2 == 0){
            int a = arr.length/2 - 1;
            int b = arr.length/2;
            int medianNum = (arr[a] + arr[b])/2;
            medianList.add(medianNum);
        }else {
            int c = (arr.length - 1)/2;
            int medianNum = arr[c];
            medianList.add(medianNum);
        }
        return medianList;
    }

    private static void mergeSort(int[] arr, int left, int right, int[] add) {
        //找到中间排序的索引
        int mid = (left + right)/2;
        //分治：先分解再组合
        if(left < right){
            //先对左边序列进行分解
            mergeSort(arr,left,mid,add);
            //对右边序列进行分解
            mergeSort(arr,mid+1,right,add);
            //如果左边序列分解完成后，只剩下单独的一个元素，则需要先进行组合
            merge(arr,left,right,add);
        }
    }

    private static void merge(int[] arr, int left, int right, int[] add) {
        //得到中间索引的位置
        int mid = (left + right)/2;
        //左边序列的初始索引
        int i = left;
        //右边序列的初始索引
        int j = mid + 1;
        //定义需要添加数组的初始索引
        int t = 0;
        //然后对左右两边序列进行比较组合
        while(i <= mid && j <= right){
            if(arr[i] < arr[j]){
                add[t] = arr[i];
                i++;
            }else {
                add[t] = arr[j];
                j++;
            }
            t++;
        }
        //当某一边序列都添加结束后，需要把另一边数据一次添加进去（顺序已经递归排序好了）
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
        //把临时添加的数组的元素全部放进arr中
        int n = 0;//arr初始索引值
        int leftLeft = left;//临时数组的初始值
        while (leftLeft <= right){
            arr[n] = arr[leftLeft];
            n++;
            leftLeft++;
        }
    }

    private static void insert(ArrayList<Integer> list, Scanner scanner) {
        System.out.println("输入你要插入的数：");
        int n = scanner.nextInt();
        list.add(n);
    }




}
