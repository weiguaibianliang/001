package com.OfferAlgorithm.SecondWeek.sorting;

import java.util.Arrays;

public class QuickSort {

    //快速排序（划分交换排序 partition-exchange sort ）
    //使用分治法策略把一个序列分为两个子序列

    /**
     * 基本思想：通过一趟排序将待排记录分隔成独立的两部分，其中一部分记录的关键字均比另一部分的关键字小，
     * 则可分别对这两部分记录继续进行排序，以达到整个序列有序
     * <p>
     * 实现逻辑：
     * 1、从数列中挑出一个元素，称为基准（pivot）(可以是中间，也可以是收尾)
     * 2、重新排序数列，所有元素比基准值小的摆放在基准前面，所有元素比基准值大的摆在基准的后面（相同的数可以到任一边）。分区操作（partition）
     * 3、递归地(recursive)把小于基准值元素的子数列和大于基准值元素的子数列排序。
     * 递归到最底部时，数列的大小是零或一。
     */

    public static void main(String[] args) {
        int[] array = {6, 72, 113, 11, 23};
        quickSort(array, 0, array.length - 1);
        System.out.println("排序后的结果：");
        System.out.println(Arrays.toString(array));

    }

    private static void quickSort(int[] array, int low, int high) {

        //如果low大于等于right，说明该区间只有1个或者没有元素
        if (low >= high) {
            return;
        }
        //获取划分分子数组的位置
        int position = partition(array, low, high);
        //左子数组递归调用
        quickSort(array, low, position - 1);
        //右子数组递归调用
        quickSort(array, position + 1, high);

    }

    private static int partition(int[] array, int low, int high) {
        //取最后一个元素作为中心元素
        int pivot = array[high];
        //定义指向比中心元素大的指针，首先指向第一个元素
        int pointer = low;
        //遍历数组中的所有元素，将比中心元素大的放在右边，比中心元素小的放在左边
        for (int i = low; i < high; i++) {
            if (array[i] <= pivot) {
                //将比中心元素小的元素和指针指向的元素交换位置
                //如果第一个元素比中心元素小，这里就是自己和自己交换位置，指针和索引都向下一位移动
                //如果元素比中心元素大，索引向下移动，指针指向这个较大的元素，直到找到比中心元素小的元素，并交换位置，指针向下移动
                int temp = array[i];
                array[i] = array[pointer];
                array[pointer] = temp;
                pointer++;
            }
            System.out.println(Arrays.toString(array));
        }
        //将中心元素和指针指向的元素交换位置
        int temp = array[pointer];
        array[pointer] = array[high];
        array[high] = temp;
        return pointer;
    }

}
