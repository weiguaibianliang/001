package com.OfferAlgorithm.SecondWeek.sorting;

import java.util.Arrays;
import java.util.PriorityQueue;

public class GetMedian2 {
    /**
     * 1、使用大顶堆 + 小顶堆的容器
     * 2、两个堆中的数据数目差不能超过1，这样就可以使中位数只会出现在两个堆的交接处。
     * 3、大顶堆的所有数据都小于小顶堆，这样就满足了排序要求。平均数就在两个堆顶的数之中。
     */
    private int count;
    private PriorityQueue<Integer> maxHeap;
    private PriorityQueue<Integer> minHeap;

    /** initialize your data structure here. */
    public GetMedian2() {
        this.count = 0;
        //默认是小顶堆，转为大顶堆
        this.maxHeap = new PriorityQueue<>((a, b) -> b - a);
        this.minHeap = new PriorityQueue<>();
    }

    public void addNum(int num) {
        count += 1;
        maxHeap.offer(num);
        minHeap.offer(maxHeap.poll());

        if ((count & 1) == 1) {
            maxHeap.offer(minHeap.poll());
        }
    }

    public double findMedian() {
        if ((count & 1) == 0) {
            return (maxHeap.peek() + minHeap.peek()) / 2.0;
        } else {
            return (double) maxHeap.peek();
        }
    }

    public static void main(String[] args) {
        //线程安全问题、不可变
        final int[] value = {1,2,3};
        value[2] = 100;
        int[] another = {4,5,6};
        System.out.println(Arrays.toString(value));
    }
}
