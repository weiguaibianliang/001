package com.OfferAlgorithm.FirstWeek.listNode;

import java.util.Arrays;
import java.util.List;

public class ReverseKGroup {

    /**
     * 给你一个链表，每k个节点一组进行翻转，请你返回翻转后的链表。
     * k是一个正整数，它的值小于或等于链表的长度。
     * 如果节点总数不是k的整数倍，那么请将最后剩余的节点保持原有顺序。
     */

    public static void main(String[] args) {
        //创建一个单链表用于示例
        ListNode headNode = createListNode(Arrays.asList(1,2,3,4,5,6,7));
        //对单链表每k个节点一组进行翻转，假设k = 2
        int k = 2;
        ListNode reverseNode = reverseKGroup(headNode,k);
        //遍历单链表
        printReverseNode(reverseNode);
    }

    private static void printReverseNode(ListNode reverseNode) {
        if(reverseNode == null || reverseNode.getNext() == null){
            return;
        }
        while (reverseNode != null){
            System.out.print(reverseNode.getValue());
            reverseNode = reverseNode.getNext();
            System.out.print(" ");
        }
        System.out.println();
    }

    private static ListNode reverseKGroup(ListNode headNode, int k) {
        //定义虚拟节点，利用首尾双指针
        ListNode dummy = new ListNode(-1);
        dummy.setNext(headNode);
        //定义首指针
        ListNode pre = dummy;
        //定义尾指针
        ListNode end = dummy;
        //遍历整个单链表，知道end的指针指向为Null才截止
        //如果整个单链表并不完全满足k个节点一组完全平分，不能平分的节点直接按照原来顺序输出即可
        while (end.getNext() != null){
            //找到需要翻转的一组节点
            //使首尾指针指向头和尾
            for (int i = 0; i < k && end != null; i++){
                end = end.getNext();
            }
            //如果end是null
            if(end == null){
                break;
            }
            //next表示【待翻转链表区域】里面的第一个节点
            ListNode next = end.getNext();
            //【翻转链表】最尾部的节点与后面节点断开
            end.setNext(null);
            //表示【翻转链表】的起始节点
            ListNode start = pre.getNext();
            //使起始节点与前面节点断开
            pre.setNext(null);

            //得到了一组需要翻转的节点,要翻转的链表的头结点【上一个节点】的next指针指向这次翻转的结果
            pre.setNext(reverse(start));
            //【翻转链表区域】里面的尾节点的next指针指向【待翻转链表区域】里面的第一个节点
            start.setNext(next);
            //找出【待翻转链表区域】的头结点的上一个节点
            pre = start;
            //将end重置为【待翻转区域】的头结点的上一个节点
            end = start;

        }

        return dummy.getNext();

    }

    private static ListNode reverse(ListNode start) {
        if(start == null || start.getNext() == null){
            return null;
        }
        ListNode pre = null;
        ListNode cur = start;
        while (cur != null){
            //定义一个临时变量
            ListNode temp = cur.getNext();
            cur.setNext(pre);
            pre = cur;
            cur = temp;
        }
        return pre;
    }

    private static ListNode createListNode(List<Integer> asList) {
        //定义一个虚拟节点表示头结点
        ListNode node = new ListNode(-1);
        ListNode tail = node;
        for (Integer integer : asList){
            ListNode temp = new ListNode(integer);
            tail.setNext(temp);
            tail = temp;
        }
        return node.getNext();
    }
}
