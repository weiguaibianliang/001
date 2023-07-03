package com.OfferAlgorithm.FirstWeek.listNode;

public class ChangeListNode {

    //创建单链表
    //在单链表中插入节点
    //在单链表中删除节点
    //循环遍历单链表
    public static void main(String[] args) {
        //定义一个虚拟节点
        ListNode listNode = new ListNode(-1);
        listNode.setNext(new ListNode(1));
        listNode.getNext().setNext(new ListNode(2));
        listNode.getNext().getNext().setNext(new ListNode(4));
        listNode.getNext().getNext().getNext().setNext(new ListNode(5));
        ListNode first = listNode.getNext();

        //想要在节点1和节点2之间插入节点3
        ListNode insertListNode = new ListNode(3);
        assert false;
        insertListNode.setNext(first.getNext());
        first.setNext(insertListNode);
//        //我想要把节点1和节点2之间的3节点删掉
//        first.setNext(first.getNext().getNext());

        //我想删掉节点2和节点5之间的节点4
        //是把内存地址赋值过去，删除的是内存地址
        ListNode p = first;
        while (p.getValue() != 2 && p!= null){
            p= p.getNext();
        }
        p.setNext(p.getNext().getNext());
        while (first!= null){
            System.out.print(first.getValue());
            System.out.print(" ");
            first = first.getNext();
        }
        System.out.println();


    }


}
