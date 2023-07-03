package com.OfferAlgorithm.FirstWeek.listNode;

import java.util.Arrays;
import java.util.List;


public class RemoveElements {

    /**
     * 给你一个链表的头结点head和一个整数val，
     * 请你删除链表中所有满足Node.val val的节点，并返回新的头结点
     * @param args
     */


    public static void main(String[] args) {
        //创建一个单链表
        ListNode headNode = createListNode(Arrays.asList(3,3,4,3,5,7));
        //定义一个value，需要删除的值
        int value = 3;
        //返回新的头结点
        assert headNode != null;
        ListNode newHeadNode = removeElements(headNode,value);
        //返回新的头结点
        System.out.println(newHeadNode.getValue());


    }

    private static ListNode removeElements(ListNode headNode, int value) {
        //移除的节点有两种情况
        //1、移除的节点就是头结点，那么返回新链表的头结点一定发生变化。
        //2、移除的节点不是原链表头结点，则新链表的头结点仍是原链表的头结点。
//        if(headNode.getValue() != value){
//            return headNode;
//        }
        //定义一个虚拟节点来描述链表的头结点位置
        ListNode newListNode = new ListNode(-1);
        //那么链表就会改变
        newListNode.setNext(headNode);
        //双指针
        //定义一个指针指向虚拟节点
        ListNode pre = newListNode;
        //定义一个指针指向原链表的头结点
        ListNode cur = headNode;
        //遍历原链表，找出相同value的节点，并删除该节点的元素
        while (cur != null) {
            //判断当前指针cur的元素是否与指定value相等
            if (cur.getValue() == value) {
                //pre指向的节点会发生变化
                pre.setNext(cur.getNext());
            } else {
                pre = cur;
            }
            //指针cur指定的节点不断向后前移，直到不满足条件
            cur = cur.getNext();
        }

        return newListNode.getNext();
    }

// 边界情况，如果链表为空，那么没有元素可以删除，直接返回空
//        if (head == null) return null;
//
//        // 一开始设置一个虚拟节点，它的值为 -1，它的值可以设置为任何的数，因为我们根本不需要使用它的值
//        // 设置虚拟节点的目的是为了让原链表中所有节点就都可以按照统一的方式进行移除
//        // 因为如果不设置虚拟节点，如果删除的元素是原链表中的头节点，那么需要额外的做一些判断，比较繁琐
//        ListNode dummy = new ListNode(-1);
//
//        // 虚拟头节点的下一节点指向 head 节点
//        // 如果原链表是  1 -->  2 -->  3
//        // 那么加上虚拟头节点就是  -1 -->  1 -->  2 -->  3
//        dummy.setNext(head);
//
//        // 设置一个指针，指向此时的虚拟节点
//        // pre: -1 -->  1 -->  2 -->  3
//        ListNode pre = dummy;
//
//        // 设置一个指针，指向原链表 head
//        ListNode cur = head;
//
//        // 让 cur 不断的向后移动，直到移动到链表的最尾部，指向 null 的那个位置
//        // 此时 pre 还在指向 dummy
//        // 也就是说一开始 pre 落后 cur 一个节点
//        while (cur != null) {
//
//            // 移动的过程中，如果发现当前的节点值和目标值一样
//            // 我们就让指针 pre 所指向的节点的下一节点跳过这个值
//            if (cur.getValue() == val) {
//                // 让指针 pre 所指向的节点的下一节点跳过这个值
//                pre.setNext(cur.getNext());
//            } else {
//                // 否则的话，pre 跟上 cur 的位置
//                pre = cur;
//            }
//            // 判断完当前的节点情况后，让 cur 向后移动
//            cur = cur.getNext();
//        }
//
//        // 最后返回 dummy 节点的下一节点
//        // 因为这个时候 dummy 指向的是一个我们设置的节点，它的下一节点才是原链表中的节点
//        return dummy.getNext();
//    }


    private static ListNode createListNode(List<Integer> asList) {
        //判断初始化值是否为空
        if(asList.isEmpty()){
            return null;
        }
        //在单链表中定义一个头结点
        ListNode headNode = new ListNode(-1);
        //此时头结点个尾结点都是一样的
        ListNode tail = headNode;
        for (Integer integer : asList) {
            //把数组中的元素转化为单链表中的元素
            ListNode node = new ListNode(integer);
            tail.setNext(node);
            tail = node;
        }
        return headNode.getNext();
    }
}
