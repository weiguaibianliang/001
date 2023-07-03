package com.OfferAlgorithm.FirstWeek.listNode;

import java.util.Arrays;
import java.util.List;

public class ReverseList2 {
    /**
     * 反转链表2：
     * 给你单链表的头指针head和两个整数left和right，其中left<=right。
     * 请你反转从位置left到位置right的链表节点，返回反转后的链表。
     * eg:1-2-3-4-5变成1-4-3-2-5
     */

    public static void main(String[] args) {
        ReverseList2 reverseList2 = new ReverseList2();
        //创建一个单链表
        ListNode headNode = reverseList2.createListNode(Arrays.asList(1,2,3,4,5));
        int left = 2, right = 4;
        //反转链表2，只反转部分链表
        ListNode reverseListNode2 = reverseList2.ReverseListNode(headNode,left,right);
        //打印反转后的链表
        reverseList2.printReverseListNode2(reverseListNode2);

    }

    private void printReverseListNode2(ListNode reverseListNode2) {

        while (reverseListNode2 != null){
            System.out.print(reverseListNode2.getValue());
            System.out.print(" ");
            reverseListNode2 = reverseListNode2.getNext();
        }
        System.out.println();
    }

    private ListNode ReverseListNode(ListNode headNode, int left, int right) {
//        //用双指针的方法来做
//        //先定义一个虚拟节点，然后第一个指针指向这个虚拟节点，第二个指针指向首元节点
//        ListNode listNode = new ListNode(-1);
//        ListNode pre = listNode;
//        listNode.setNext(headNode);
//        ListNode cur = headNode;
//        //让两个指针到达需要反转的初始位置
//        while (cur.getValue() != left){
//            pre = pre.getNext();
//            cur = cur.getNext();
//        }
//
//        ListNode a = pre;
//        ListNode b = cur;
//        //让两个指针到达反转的截止位置
//        while (a.getValue() != right){
//            a = a.getNext();
//            b = b.getNext();
//        }
//        ListNode c = null;
//        while (cur.getValue() != b.getValue()){
//            //建立临时变量
//            ListNode tempNode = cur.getNext();
//            cur.setNext(c);
//            //迭代指针
//            c = cur;
//            cur = tempNode;
//        }
//        pre.setNext(c);
//        ListNode tail = pre;
//        do {
//            tail = tail.getNext();
//        } while (tail.getNext() != null);
//        tail.setNext(b);
//        return pre;
        //双指针(快慢指针)，定义虚拟节点
        ListNode dummy = new ListNode(-1);
        dummy.setNext(headNode);
        //定义前置指针pre
        ListNode pre = dummy;
        //定义当前指针
        ListNode cur = headNode;
        //找到开始反转节点的区间
        for (int i = 0; i < left - 1; i++){
            //找到pre在区间左边的节点
            pre = pre.getNext();
            //当前指针cur正好对应区间的第一个节点
            cur = cur.getNext();
        }
        //开始反转区间的节点
        for (int i = 0; i < right - left; i++){
            //定义临时节点temp
            ListNode temp = cur.getNext();
            //让2-4,3-2,1-3
            cur.setNext(cur.getNext().getNext());
            temp.setNext(pre.getNext());
            pre.setNext(temp);
        }
        return dummy.getNext();
    }

    private ListNode createListNode(List<Integer> asList) {
        if(asList.isEmpty()){
            return null;
        }
        ListNode headNode = new ListNode(-1);
        //让头结点指向尾结点
        ListNode tail = headNode;
        for (Integer integer : asList) {
            ListNode node = new ListNode(integer);
            tail.setNext(node);
            tail = node;
        }
        return headNode.getNext();
    }


}
