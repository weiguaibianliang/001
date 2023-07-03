package com.OfferAlgorithm.SecondWeek.sorting;

import java.util.Arrays;

public class BubbleSort {

    /**
     * 冒泡排序
     * 原方法：直接两层for循环，一个一个去比较，最后时间复杂度为O（N^2）.
     * 改进方法一：通过设置标志来记录此次遍历有无数据交换，进而可以判断是否要继续循环，设置一个flag标记，
     * 当在一趟序列中没有发生交换，则该排序已排序好，但优化后排序的时间复杂度没有发生量级的改变。
     *
     */

    public static void main(String[] args) {
        int[] a = {3,1,6,2,9,0,7,4,5,8};
//        bubbleSort(a);
//        bubbleSort1(a);
        bubbleSort2(a);
    }




    public static void bubbleSort(int[] a) {
        for (int i = 0; i < a.length - 1; i++){
            for (int j = 0; j < a.length - 1 - i; j++){
                if(a[j] > a[j + 1]){
                    int temp = a[j];
                    a[j] = a[j+1];
                    a[j+1]= temp;
                }
            }
        }
        System.out.println(Arrays.toString(a));
    }

    //改进方法一：设置标记
    public static void bubbleSort1(int[] a) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < a.length; i++) {
            //判断元素是否进行交换
            boolean flag = true;
            for (int j = 0; j < a.length - i - 1; j++) {
                if (a[j] > a[j + 1]) {
                    //借用中间变量来进行交换
                    int temp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = temp;
                    //元素交换了就记录下来发生了交换
                    flag = false;
                }
            }
            //假如元素没有进入内循环，也就是没有发生交换，则直接结束循环，这样可以节省时间
            if (flag){
                System.out.println("结束");
                break;
            }
            System.out.println();
        }
        long end = System.currentTimeMillis();
        System.out.println("冒泡排序一共花了多长时间"+ (end - start));
        System.out.println(Arrays.toString(a));
    }


    //方法二：传统的冒泡算法每次排序只确定了最大值，我们可以在每次循环之中进行正反两次冒泡，分别找到最大值和最小值，
    //如此可使排序的轮数减少一半。
    public static void bubbleSort2(int[] a) {
        int left = 0;
        int right = a.length - 1;
        while (left < right){

            //正向冒泡，确定最大值
            for (int i = left; i < right; ++i){
                //如果前一位大于后一位,交换位置
                if(a[i] > a[i+1]){
                    //取中间变量
                    int temp = a[i];
                    a[i] = a[i+1];
                    a[i+1] = temp;
                }
            }
            --right;

            //反向冒泡，确定最小值
            for (int j = right; j > left; --j){
                //如果前一位大于后一位，交换位置
                if (a[j] < a[j-1]){
                    int temp = a[j];
                    a[j] = a[j -1];
                    a[j-1] = temp;
                }
            }
            ++left;
            System.out.println(Arrays.toString(a));
        }

    }


}
