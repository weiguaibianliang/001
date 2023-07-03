package com.OfferAlgorithm.SecondWeek.sorting;

import java.util.Arrays;

public class MergeSort {

    //创建在归并操作上的一种有效的排序算法，算法采用分治法，各层分治递归可以同时进行，速度仅次于快速排序，为稳定排序算法。

    /**
     * 1、基本思想：用分治思想，分治模式在每一层递归上有三个步骤：
     * 分解：将n个元素分成个含n/2个元素的子序列。
     * 解决：用合并排序法对两个子序列递归的排序。
     * 合并：合并两个已排序的子序列已得到排序结果。
     * 2、实现逻辑
     * （1）迭代法
     * （2）递归法
     */

//    public static void main(String[] args) {
//        //定义一个待排序的数组
//        int[] arr = {8,4,5,7,1,3,6,2};
//        //假设已经到达最后一步
//        int[] arrays = {4,5,7,8,1,2,3,6};
//        //最后输出的结果
//        int[] add = new int[8];
//        //第一个参数表示传入数组arrays
//        //第二个参数表示这个数组的第一个位置即是0
//        //第三个参数表示这个数组的中间位置（0+7）/2 = 3
//        //第四个参数，存放临时数组的
//        merge(arrays,0,3,7,add);
//    }
//
//    public static void merge(int[] arr, int left, int mid, int right, int[] temp) {
//        //初始i，左边有序序列的初始索引
//        int i = left;
//        //初始j，右边有序序列的初始索引
//        int j = mid + 1;
//        //指向temp数组的当前索引
//        int t = 0;
//
//        //先把左右两边的有序序列填到temp数组中
//        //直到有一边的序列填充完为止
//        while (i <= mid && j <= right){
//            if(arr[i] < arr[j]){
//                temp[t] = arr[i];
//                i++;
//            }else {
//                temp[t] = arr[j];
//                j++;
//            }
//            t++;
//        }
//        //有一边序列的填充完后，直接把另一边序列的元素依次添加到temp数组中
//        while (i <= mid || j <= right){
//            if(j > right){
//                temp[t] = arr[i];
//                i++;
//            }else {
//                temp[t] = arr[j];
//                j++;
//            }
//            t++;
//        }
//        //把元素都存到temp中后，还要把temp中的元素赋给arr
//        System.arraycopy(temp, 0, arr, 0, temp.length);
//        System.out.println(Arrays.toString(arr));
//    }
    public static void main(String[] args) {
        int[] arr = {8,4,5,7,1,3,6,2};
        int[] add = new int[arr.length];
        System.out.println("排序前："+ Arrays.toString(arr));
        System.out.println("排序过程：");
        mergeSort(arr,0,add.length - 1,add);
        System.out.println("排序后：" + Arrays.toString(arr));
    }

    private static void mergeSort(int[] arr, int left, int right, int[] temp) {
        //求中间索引
        int mid = (left + right)/2;
        //先进行递归分解
        if(left < right){
            //先对左边进行递归分解
            mergeSort(arr,left,mid,temp);
            //再对右边进行递归分解
            mergeSort(arr,mid+1,right,temp);
            //左边和右边都分解后，然后进行组合
            merge(arr,left,mid,right,temp);
            System.out.println("最左边索引："+left+"\t最右边索引："+right+"\t"+Arrays.toString(arr));
        }
    }

    private static void merge(int[] arr, int left, int mid, int right, int[] temp) {
        //初始i,左边序列的初始索引
        int i = left;
        //初始j,右边序列的初始索引
        int j = mid + 1;
        //定义temp的初始索引
        int t = 0;

        //进行组合治理
        while (i <= mid && j <= right){
            //对左右两个序列进行排序组合
            if(arr[i] < arr[j]){
                temp[t] = arr[i];
                i++;
            }else {
                temp[t] = arr[j];
                j++;
            }
            t++;
        }
        //当其中一个序列完成后，另外一个序列依次添加即可
        while (i <= mid || j <= right){
            if(j > right){
                temp[t] = arr[i];
                i++;
            }else {
                temp[t] = arr[j];
                j++;
            }
            t++;
        }
        //当完成这个组合之后，需要把每次临时的数组temp赋给arr
        int n = 0;
        int tempLeft = left;
        while (tempLeft <= right){
            arr[tempLeft] = temp[n];
            n++;
            tempLeft++;
        }
    }


}
