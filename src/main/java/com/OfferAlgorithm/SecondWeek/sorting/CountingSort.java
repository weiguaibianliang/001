package com.OfferAlgorithm.SecondWeek.sorting;

import java.util.Arrays;

public class CountingSort {
    //一种稳定的线性时间排序算法

    /**
     * 基本思想：计数排序使用一个额外的数组c，其中第i个元素是待排序数组A中值等于i的元素的个数。
     * 用来计数的数组c的长度取决于待排序数组中数据的范围（等于待排序数组的最大值与最小值的差加上1）
     *
     * 实现逻辑：
     * 1、找出待排序的数组中最大和最小的元素
     * 2、统计数组中每个值为i的元素出现的次数，存入数组c的第i项
     * 3、对所有的计数累加（从C中的第一个元素开始，每一项和前一项相加）
     * 4、反向填充目标数组：将每个元素i放在新数组的第C(i)项，每一个元素就将C(i)减去1
     */

    public static void main(String[] args) {
        //创建待排序数组
        int[] A = {1,1,1,1,1,1,1};
        int[] countingSort = CountingSort.countingSort(A);
        System.out.println(Arrays.toString(countingSort));
    }

    private static int[] countingSort(int[] A) {
        //创建排序后排列的数组
        int[] B = new int[A.length];
        //找出待排序的数组中最大和最小的元素
        //假设最大元素和最小元素都是第一个
        int max = A[0];
        int min = A[0];
        //然后再数组A中进行搜索最大值和最小值
        for (int a : A) {
            if(a > max){
                max = a;
            }
            if(a < min){
                min = a;
            }
        }
        //由上述循环可知最大值和最小值，那么计数数组的长度就可以确定了
        int k = max - min + 1;
        //创建待存入的数组
        int[] C = new int[k];
        //统计原数组中元素出现的次数
        for (int j : A) {
            //计算原数组元素在统计数组中的索引
            int dataInCountIndex = j - min;
            C[dataInCountIndex] += 1;
        }
        //对所有的计数累加（从C中的第一个元素开始，每一项和前一项相加）
        for (int i = 1; i < C.length; i++) {
            C[i] = C[i] + C[i-1];
        }
        //反向填充目标数组：将每个元素i放在新数组的第C(i)项，每一个元素就将C(i)减去1
        for (int i = A.length -1; i >= 0; i--){
            //计算原数组元素在统计数组中的索引
            int dataInCountIndex = A[i] - min;
            //计算其排序后的位置，因为数组索引从0开始计算，故应对排序位置减1
            //例如，排在最前面的元素，排序位置为1，则其在数组中的位置索引应为0.
            int sortIndex = C[dataInCountIndex] - 1;
            //将原数组元素放入排序后的位置上
            B[sortIndex] = A[i];
            //下一个重复的元素，应该排前一个位置，以保证稳定性。
            C[dataInCountIndex]--;
        }

        return B;

    }


}
