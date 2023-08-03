package com.OfferAlgorithm.SecondWeek.greedy;

import java.util.Arrays;
import java.util.Comparator;

public class FindMinArrowShots {

    /**
     * 题目：用最少数量的箭引爆气球
     * 对于每个气球，提供的输入是水平方向上，气球直径的开始和结束坐标，只要知道开始和结束的横坐标就足够了。
     * 一支弓箭可以沿着x轴从不同点完全垂直地射出。在坐标x处射出一支箭，若有一个气球的直径的开始和结束坐标为
     * X-start,X-end,且满足X-start,<=x<=X-end,则该气球会被引爆。
     * 弓箭一旦被射出之后，可以无限地前进。我们想找到使得所有气球全部被引爆，所需的弓箭的最小数量。
     */

    //给你一个数组points,其中points[i] = [x-start,x-end],返回引爆所有气球所必须射出的最小弓箭数。
    public static void main(String[] args) {
        //输入气球的水平路线
        int[][] points = {{10,16},{2,8},{1,6},{7,12}};
        //使用右端排序
        int count = findMinArrowsShots(points);
        //输出弓箭的最小数量
        System.out.println(count);
    }

    private static int findMinArrowsShots(int[][] points) {

//        Arrays.sort(points, new Comparator<int[]>() {
//            public int compare(int[] point1, int[] point2) {
//                // points 是一个二维数组，比如为 [[10,16],[2,8],[1,6],[7,12]]
//                // point1 是其中的一个对象，比如为 [10,16]
//                // point2 是其中的一个对象，比如为 [2,8]
//
//                if (point1[1] > point2[1]) {
//                    // 代表需要交换位置
//                    return 1;
//                } else if (point1[1] < point2[1]) {
//                    // 代表不交换位置
//                    return -1;
//                } else {
//                    // 不交换位置，不排序
//                    return 0;
//                }
//            }
//        });

        Arrays.sort(points,new Comparator<int[]>(){
            @Override
            public int compare(int[] points1, int[] points2) {
                if(points1[1] > points2[1]){
                    //代表交换位置
                    return 1;
                }else if(points1[1] < points2[1]){
                    //代表不交换位置
                    return -1;
                }else {
                    //不交换位置，不排序
                    return 0;
                }
            }
        });

        // 此时，第一只箭的位置在第一个数组的末端，即最右侧位置
        int pos = points[0][1];

        // res 代表箭的数量
        int res = 1;

        // 开始遍历排序好后的那些气球数组
        // 这些气球数组按照末端的大小进行了升序排序
        for (int[] balloon: points) {

            // 在遍历过程中，只要发现当前气球的左端位置超过了当前箭头的位置
            // 那么代表着此时这只 pos 箭无法引爆 balloon 这个气球
            // 那么需要增加其它的箭
            if (balloon[0] > pos) {
                // 新增的箭放在当前气球的最右端
                pos = balloon[1];

                // 同时，更新了箭的数量
                res++;
            }
        }
        return res;
    }

}
