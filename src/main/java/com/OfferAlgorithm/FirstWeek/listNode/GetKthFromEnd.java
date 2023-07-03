package com.OfferAlgorithm.FirstWeek.listNode;

import java.util.Arrays;
import java.util.List;

public class GetKthFromEnd {

    /**
     * 输入一个链表，输出该链表中倒数第k个节点。为了符合大多数人的习惯，本题从1开始计数
     * 即链表的尾结点是倒数第一个节点。
     * 递归、反转、双指针、环
     */

    public static void main(String[] args) {
        //初始化一个单链表
        ListNode headNode = createListNode(Arrays.asList(1,2,3,4,5,6,7,8));
        //得到该链表倒数第二个节点
        int k = 2;
        ListNode kNode = getKthFromEnd(headNode,k);
        printKNode(kNode);

    }

    private static void printKNode(ListNode kNode) {
        while (kNode != null){
            System.out.print(kNode.getValue());
            System.out.print(" ");
            kNode = kNode.getNext();
        }
    }

    private static ListNode getKthFromEnd(ListNode headNode, int k) {
        //双指针之快慢指针
        //定义快指针
        ListNode former = headNode;
        //定义慢指针
        ListNode later = headNode;
        //先让首指针向前走k步
        for (int i = 0; i < k; i++){
            former = former.getNext();
        }
        //然后让快指针走完单链表，那么快指针比慢指针多走k步
        while (former != null){
            //不断迭代两个指针，直到不满足条件
            former = former.getNext();
            later = later.getNext();
        }
        return later;
    }

    private static ListNode createListNode(List<Integer> asList) {
        if(asList.isEmpty()){
            return null;
        }
        //初始化一个虚拟节点
        ListNode dummy = new ListNode(-1);
        //使尾节点指向虚拟节点
        ListNode tail = dummy;
        for (Integer integer : asList){
            ListNode temp = new ListNode(integer);
            tail.setNext(temp);
            tail = temp;
        }
        return dummy.getNext();
    }
}
