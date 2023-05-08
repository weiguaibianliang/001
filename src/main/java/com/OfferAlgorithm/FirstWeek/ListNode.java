package com.OfferAlgorithm.FirstWeek;

public class ListNode {

    //链表用于存储值
    private final int value;
    //指向下一个节点
    private ListNode next;

    public ListNode(int value) {
        this.value = value;
        this.next = null;
    }

    public int getValue() {
        return value;
    }

    public ListNode getNext() {
        return next;
    }

    public void setNext(ListNode next) {
        this.next = next;
    }
}
