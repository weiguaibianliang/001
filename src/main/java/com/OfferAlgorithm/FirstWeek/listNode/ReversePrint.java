package com.OfferAlgorithm.FirstWeek.listNode;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class ReversePrint {

    /**
     * 输入一个链表的头节点，从尾到头反过来返回每个节点的值（用数组返回）
     */

    /**
     * 方法一：直接反转链表，然后把链表按反转的顺序打印出来
     * 方法二：新建一个栈，存放初始遍历的单链表，根据栈的特性（先进后出）来打印栈中的元素
     */

    public static void main(String[] args) {
        //初始化一个单链表
        ListNode headNode = createListNode(Arrays.asList(1,2,3,4,5,6,7));
        //方法二
//        print(headNode);
        //方法一
        print1(headNode);

    }

    private static void print1(ListNode headNode) {
        if(headNode == null){
            return;
        }
        //双指针反转链表
        ListNode pre = null;
        ListNode cur = headNode;
        while (cur != null){
            //定义中间变量
            ListNode temp = cur.getNext();
            cur.setNext(pre);
            //迭代当前指向
            pre = cur;
            //使临时变量指向当前
            cur = temp;
        }
        while (pre != null){
            System.out.print(pre.getValue());
            System.out.print(" ");
            pre = pre.getNext();
        }
        System.out.println();

    }

    private static void print(ListNode headNode) {
        if(headNode == null){
            return;
        }
        //新建一个栈存放初始遍历的单链表
        Stack<Integer> stack = new Stack<>();
        while (headNode != null){
            stack.push(headNode.getValue());
            headNode = headNode.getNext();
        }
        //把链表中的元素全部放进stack后
        while (!stack.isEmpty()){
            System.out.print(stack.pop());
            System.out.print(" ");
        }
        System.out.println();
    }

    private static ListNode createListNode(List<Integer> asList) {
        if(asList.isEmpty()){
            return null;
        }
        //定义一个虚拟节点
        ListNode dummy = new ListNode(-1);
        //使尾结点指向虚拟节点
        ListNode tail = dummy;
        for (Integer integer : asList){
            ListNode node = new ListNode(integer);
            tail.setNext(node);
            tail = node;
        }
        return dummy.getNext();
    }
}
