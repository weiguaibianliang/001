package com.OfferAlgorithm.FirstWeek;

import java.util.Arrays;
import java.util.List;

public class ReverseList {


    //双指针迭代
    public ListNode reverseList1(ListNode head){
        if (head== null || head.getNext() == null) {
            return head;
        }
        ListNode pre = null, cur = head;
        while (cur != null) {
            //temp保存临时变量
            ListNode tempNode = cur.getNext();
            //change改变链表
            cur.setNext(pre);
            //迭代当前指向
            pre = cur;
            cur = tempNode;
        }
        return pre;
    }

    //递归调用
    public ListNode reverseList2(ListNode head){
        if(head == null || head.getNext() == null){
            return head;
        }
        //递归调用自身方法
        ListNode tempNode = reverseList2(head.getNext());
        head.getNext().setNext(head);
        head.setNext(null);
        return tempNode;
    }

    //构建函数
    public ListNode createLinkedList(List<Integer> list){
        if(list.isEmpty()){
            return null;
        }
        ListNode headNode = new ListNode(list.get(0));
        ListNode tempNode = createLinkedList(list.subList(1,list.size()));
        headNode.setNext(tempNode);
        return headNode;
    }

    //测试方便的打印函数
    public void printList(ListNode listNode){
        while (listNode!=null){
            System.out.print(listNode.getValue());
            System.out.print(" ");
            listNode = listNode.getNext();
        }
        System.out.println();
    }

    public static void main(String[] args) {
        ReverseList reverseList = new ReverseList();
        ListNode listNode = reverseList.createLinkedList(Arrays.asList(1,2,3,4,5));
        ListNode listNode1 = reverseList.createLinkedList(Arrays.asList(1,2,3,4,5));
        ListNode listNode2 = reverseList.createLinkedList(Arrays.asList(1,2,3,4,5));

        //把运用的算法输出单链表
        //ListNode node = reverseList.reverseList1(listNode);
        ListNode node2 = reverseList.reverseList2(listNode1);
//        ListNode node3 = reverseList.reverseList3(listNode2);

        //reverseList.printList(node);
        reverseList.printList(node2);
//        reverseList.printList(node3);

    }

}
