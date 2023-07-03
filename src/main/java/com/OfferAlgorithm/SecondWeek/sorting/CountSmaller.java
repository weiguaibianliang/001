package com.OfferAlgorithm.SecondWeek.sorting;

import java.util.ArrayList;
import java.util.List;

public class CountSmaller {

    //计算右侧小于当前元素的个数
    /**
     * 给你一个整数数组nums，按要求返回一个新数组counts.
     * 数组counts有该性质：count[i]的值是nums[i]右侧小于nums[i]的元素的数量
     * eg:
     * 输入：[5,2,6,1]
     * 输出：[2,1,1,0]
     * <p>
     * 方法：树状数组、归并排序、线段树
     */

    //索引数组、辅助数组、结果数组（方便记录结果）

    //定义各数组的成员变量
    private static int[] temp;
    private static int[] counter;
    private static int[] indexes;

    //用到归并排序，和之前的逆序对差不多
    //建立需要计算的数组
    public static void main(String[] args) {
        int[] nums = {5, 2, 6, 1};
        //调用计算右侧小于当前元素个数的算法
        List<Integer> result = countSmaller(nums);
        System.out.println(result);
    }

    private static List<Integer> countSmaller(int[] nums) {
        //建立输出结果list
        List<Integer> result = new ArrayList<>();
        if (nums.length == 0) {
            return new ArrayList<>();
        }
        //建立中间变量临时数组
        temp = new int[nums.length];
        //建立计数数组
        counter = new int[nums.length];
        //建立下标索引数组
        indexes = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            indexes[i] = i;
        }
        //调用归并排序并计算每个元素逆序对的个数
        mergeAndCountSmaller(nums, 0, nums.length - 1);
        //把每个元素的逆序对个数都添加到最终的结果result中
        for (int i = 0; i < nums.length; i++) {
            result.add(counter[i]);
        }
        return result;

    }

    private static void mergeAndCountSmaller(int[] nums, int left, int right) {
        //正常走归并排序的流程
        if(left == right){
            return;
        }
        //找到要待排序数组的中间值
        int mid = (left + right) / 2;
        //进行分治递归
        //左边
        mergeAndCountSmaller(nums, left, mid);
        //右边
        mergeAndCountSmaller(nums, mid + 1, right);
        //如果right1的数字index[right1] 大于了left2的数字index[left2]
        //说明出现了逆序对，需要计算右侧小于当前元素的个数
        if (nums[indexes[mid]] > nums[indexes[mid + 1]]) {
            //开始执行归并操作，并且在归并过程中统计出右侧小于当前元素的个数
            mergeOfTwoSortedArrAndCountSmaller(nums, left, mid, right);
        }
    }

    private static void mergeOfTwoSortedArrAndCountSmaller(int[] nums, int left, int mid, int right) {
        //先将index中的元素赋值到temp中
        for (int i = left; i <= right; i++) {
            temp[i] = indexes[i];
        }
        //此时，【left,mid】为一个区间
        //此时，【mid+1,right】为一个区间
        int i = left;
        int j = mid + 1;
        //开始从头比较两个区间的元素
        for (int k = left; k <= right; k++) {
            //如果i > mid说明【left,mid】这个区间的所有元素都访问完毕了
            if (i > mid) {
                //还原index[k]的值
                indexes[k] = temp[j];
                j++;
                //否则说明i还在【left,mid】这个区间
                //如果j > right, 说明【mid+1,right】这个区间的所有元素都访问完毕了
                //由于【left,mid】这个区间能出现的逆序数只能是在【mid +1,right】这个区间
            } else if (j > right) {
                //还原index[k]的值
                indexes[k] = temp[i];
                i++;
                //【 mid + 1，right 】这个区间的所有元素都访问完毕，这个区间总共有 right - mid 个元素
                // 这些元素都是 i 指向的那个元素的逆序数，总共有 right - mid 个
                int count = right - mid;

                // index[k] 对应着原数组 nums 中索引为 k 的那个数
                int num = indexes[k];
                // 所以，这个数的逆序数需要加上 count
                counter[num] += count;
                // 否则说明 i 还在 【 left，mid 】这个区间
                // 同时 j 还在【 mid + 1，right 】 这个区间
                // 比较 i 和 j 指向的两个元素值的大小
            } else if (nums[temp[i]] <= nums[temp[j]]) {
                indexes[k] = temp[i];
                i++;
                // j 在 【 mid + 1，right 】 这个区间
                // 从 mid + 1 到 j 有  j - (mid + 1) 是小于 nums[aux[i]]
                // 所以逆序数有 j - (mid + 1) 个
                int count = j - (mid + 1);
                // index[k] 对应着原数组 nums 中索引为 k 的那个数
                int num = indexes[k];
                // 所以，这个数的逆序数需要加上 count
                counter[num] += count;
            } else {
                indexes[k] = temp[j];
                j++;
            }
        }

    }


}
/*
class Solution {
    private int[] index;
    private int[] temp;
    private int[] tempIndex;
    private int[] ans;

    public List<Integer> countSmaller(int[] nums) {
        this.index = new int[nums.length];
        this.temp = new int[nums.length];
        this.tempIndex = new int[nums.length];
        this.ans = new int[nums.length];
        for (int i = 0; i < nums.length; ++i) {
            index[i] = i;
        }
        int l = 0, r = nums.length - 1;
        mergeSort(nums, l, r);
        List<Integer> list = new ArrayList<Integer>();
        for (int num : ans) {
            list.add(num);
        }
        return list;
    }

    public void mergeSort(int[] a, int l, int r) {
        if (l >= r) {
            return;
        }
        int mid = (l + r) >> 1;
        mergeSort(a, l, mid);
        mergeSort(a, mid + 1, r);
        merge(a, l, mid, r);
    }

    public void merge(int[] a, int l, int mid, int r) {
        int i = l, j = mid + 1, p = l;
        while (i <= mid && j <= r) {
            if (a[i] <= a[j]) {
                temp[p] = a[i];
                tempIndex[p] = index[i];
                ans[index[i]] += (j - mid - 1);
                ++i;
                ++p;
            } else {
                temp[p] = a[j];
                tempIndex[p] = index[j];
                ++j;
                ++p;
            }
        }
        while (i <= mid)  {
            temp[p] = a[i];
            tempIndex[p] = index[i];
            ans[index[i]] += (j - mid - 1);
            ++i;
            ++p;
        }
        while (j <= r) {
            temp[p] = a[j];
            tempIndex[p] = index[j];
            ++j;
            ++p;
        }
        for (int k = l; k <= r; ++k) {
            index[k] = tempIndex[k];
            a[k] = temp[k];
        }
    }
}

 */
