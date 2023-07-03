package com.OfferAlgorithm.SecondWeek.sorting;

import java.util.Arrays;

public class GetListNumbers {

    /**
     * 输入整数数组arr，找出其中最小的k个数，输入4、5、1、6、2、7、3、8这8个数字，
     * 则最小的4个数字是1、2、3、4。
     */
    //方法：先把整数数组进行排序，然后取前k个数就可以找到最小的k个数。
    //冒泡排序（O(N^2)）、快速排序(O(NlogN))、计数排序(输入元素是0到k之间的整数，O(n+k))、选择排序(O(N^2))、插入排序(O(N^2))、归并排序(NlogN)都可以。
    //选择一种时间复杂度低的排序方法—快速排序或者归并排序

    public static void main(String[] args) {
        //创建待排序的数组
        int[] arr = {4,5,1,6,2,7,3,8};
        //找出其中最小的k个数
        int k = 4;
//        //调用快速排序的方法找到最小的k个数的数组
//        int[] kArr = getListNumbers(arr,k);
//        System.out.println(Arrays.toString(kArr));

        //调用归并排序的方法找到最小的k个数的数组
        int[] kArray = getListNumbers2(arr,k);
        System.out.println(Arrays.toString(kArray));
    }

    private static int[] getListNumbers2(int[] arr, int k) {
        //定义首尾索引
        int left = 0;
        int right = arr.length - 1;
        //调用归并排序对初始数组进行排序
        mergeSort(arr,left,right);
        //定义一个临时数组来存放前k个元素
        int[] temp = new int[k];
        System.arraycopy(arr, 0, temp, 0, k);
        return temp;
    }

    private static void mergeSort(int[] arr, int left, int right) {
        //先把初始数组分为两部分
        //然后对两部分再进行递归拆分
        int mid = (left + right)/2;
        //构建临时数组来存放组合后的元素,分解到不能分解，然后左边或右边排序，然后再把不能不能分解的元素添加进去
        int[] temp = new int[arr.length];
        //采用分治方法，先对初始数组进行分解，在分解过程中进行组合，最后组合完成后初始数组就排序完成
        if(left < right){
            //先对左边部分进行分解，直到单个元素不能分解为止
            //递归
            mergeSort(arr,left,mid);
            //然后对右边部分进行分解，直到单个元素不能分解为止
            mergeSort(arr,mid+1,right);
            //在左边不能分解或者右边不能分解时，要进行组合
            merge(arr,left,mid,right,temp);
        }
        //System.out.println(Arrays.toString(arr));
    }

    private static void merge(int[] arr, int left, int mid, int right, int[] temp) {
        //初始i,左边序列的初始索引
        int i = left;
        //初始j,右边序列的初始索引
        int j = mid + 1;
        //初始组合数组的初始索引
        int t = 0;

        //定义完成之后，开始“治”
        while (i <= mid && j <= right){
            if(arr[i] < arr[j]){
                temp[t] = arr[i];
                i++;
            }else {
                temp[t] = arr[j];
                j++;
            }
            t++;
        }
        //如果某一边序列已经排序完成后，另外一边直接依次添加即可
        while (i <= mid || j <= right){
            if(i > mid){
                temp[t] = arr[j];
                j++;
            }else {
                temp[t] = arr[i];
                i++;
            }
            t++;
        }

        //上述组合完成后，需要把临时数组中的元素赋值到arr中
        //关键要清楚临时数组中有多少元素呢
        int n = 0;
        //得到临时数组Temp的初始索引
        int tempLeft = left;
        while (tempLeft <= right){
            arr[tempLeft] = temp[n];
            n++;
            tempLeft++;
        }
    }

    private static int[] getListNumbers(int[] arr, int k) {
        //首尾索引
        int low = 0;
        int high = arr.length - 1;
        //调用快速排序方法
        quickSort(arr,low,high);
        //定义一个临时数组来存放前k个元素
        int[] temp = new int[k];
        System.arraycopy(arr, 0, temp, 0, k);
        return temp;
    }

    private static void quickSort(int[] arr, int low, int high) {
        //如果low大于等于right，说明该区间只有1个或者没有元素
        if (low >= high) {
            return;
        }
        //获取划分分子数组的位置
        int position = partition(arr, low, high);
        //左子数组递归调用
        quickSort(arr, low, position - 1);
        //右子数组递归调用
        quickSort(arr, position + 1, high);
    }

    private static int partition(int[] arr, int low, int high) {
        //定义最后一个元素是中心元素
        int pivot = arr[high];
        //初始一个指针指向首元素
        int pointer = low;
        for (int i = low; i < high; i++) {
            if(arr[i] <= pivot){
                //将比中心元素小的元素与指针指向的元素交换位置
                int temp = arr[i];
                arr[i] = arr[pointer];
                arr[pointer] = temp;
                //随着数组的索引往右推进，如果满足索引往右推进的元素小于中心元素，那么指针也会向右推进。
                pointer++;
            }
        }

        //数组遍历结束后，需要将中心元素与指针指向的元素交换位置
        int temp = arr[pointer];
        arr[pointer] = arr[high];
        arr[high] = temp;
        return pointer;
    }
}
