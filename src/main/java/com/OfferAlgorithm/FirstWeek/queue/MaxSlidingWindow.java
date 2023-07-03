package com.OfferAlgorithm.FirstWeek.queue;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Objects;

public class MaxSlidingWindow {

    /**
     * 给定一个数组nums和滑动窗口的大小k，请你找出所有滑动窗口里的最大值。
     * 输入： nums = [1,3,-1,-3,5,3,6,7],和 k = 3
     * 输出：[3,3,5,5,6,7]
     */
    //创建一个双端队列，既可以尾部删除，也可以头部插入，还要满足是一个递减队列
    //在满足递减队列的情况下，直接去递减队列的首值即可满足最大值
    //满足滑动窗口的条件，使滑动窗口一直向右滑，直到结束为止。
    //创建一个数组来存储所有滑动窗口里的最大值，满足规律：n-k+1
    public static void main(String[] args) {
        //创建指定初始数组nums,和指定滑动窗口的大小k
        Integer[] nums = {1,3,-1,-3,5,3,6,7};
        int k = 3;
        //返回所有滑动窗口里的最大值
        Integer[] result = maxSlidingWindow(nums,k);
        System.out.println(Arrays.toString(result));
        Runtime runtime = Runtime.getRuntime();
        long memory = runtime.freeMemory();

    }

    private static Integer[] maxSlidingWindow(Integer[] nums, int k) {
        //考虑边界情况
        if(nums.length == 0 || k == 0){
            return new Integer[0];
        }
        //构建双端队列
        Deque<Integer> deque = new LinkedList<>();
        //构建存放最大值的数组，按照规则判断数组大小
        Integer[] res = new Integer[nums.length - k + 1];
        //遍历nums中满足滑动窗口k大小的元素，知道满足滑动窗口的条件
        for (int i = 0; i < k; i++){
            //满足双端队列的条件且是递减队列
            //考察元素与当前队尾元素的大小
            while (!deque.isEmpty() && deque.peekLast() < nums[i]){
                //满足条件删除队尾元素
                deque.removeLast();
            }
            //如果队列是空的话要把当前元素加入到队列中，才能方便考察元素进行判断
            //如果队列中有元素后，只会加入满足递减队列的元素
            deque.addLast(nums[i]);
        }
        //这时滑动窗口合格，那么最大值数组res中的第一个元素就是队列中的队首元素
        res[0] = deque.peekFirst();

        //满足上述条件后让窗口依次向后滑动
        for (int i = k; i < nums.length; i++){
            //要判断此时递减双端队列中的队首元素（就是最大值）是否即将与失去一个窗口的元素相等
            //只有判断了之后才能方便后续向右滑的过程中没有重复的最大值判断失衡
            //如果相等，就要删除队首元素
            if(Objects.equals(deque.peekFirst(), nums[i - k])){
                deque.removeFirst();
            }
            //然后反复判断窗口滑动的元素是否满足递减队列的条件
            while (!deque.isEmpty() && deque.peekLast() < nums[i]){
                deque.removeLast();
            }
            //然后再队列的末尾添加元素
            deque.addLast(nums[i]);
            //此时数组元素最大值就是递减队列的队首元素
            res[i - k + 1] = deque.peekFirst();
        }

        return res;
    }

}
