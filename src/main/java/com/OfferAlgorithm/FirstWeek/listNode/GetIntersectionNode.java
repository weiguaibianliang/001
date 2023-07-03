package com.OfferAlgorithm.FirstWeek.listNode;

import java.util.Arrays;
import java.util.List;

/**
 * 给你两个单链表的头结点headA和headB，请你找出并返回两个单链表相交的起始节点。
 * 如果两个链表不存在相交节点，返回Null。
 */
public class GetIntersectionNode {

    /*
    常规解法：计算两个链表的长度，然后算出长度差cha;让长度较长的链表先走cha步，
    这样就补齐了两个链表之间长度的差异，最后向后遍历，节点相等的地方即为解。
     */


    /*
    简单解法：该题关键是如何补齐两个链表之间的长度差。我们两个链表同时遍历，遍历结束时，
    让两个链表分别指向对方链表的头部，继续遍历，这样长度补齐了，当节点相等时，即为解。
     */
    //双指针
    private static ListNode getIntersectionNode2(ListNode headA, ListNode headB) {
        if(headA == null || headB == null){
            return null;
        }
        ListNode curA = headA;
        ListNode curB = headB;
        //这里判断结点是否相等，不仅要判断值，还要判断地址是否相同。
        while (curA != curB){

//            curA = curA == null ? headB : curA.getNext();
//            curB = curB == null ? headA : curB.getNext();
            //指针curA一开始在链表A上遍历，当走到链表A的尾部即Null时，跳转到链表B上。
            if(curA == null){
                //指针curA跳转到链B上
                curA = headB;
            }else {
                //否则的话，curA不断的向后移动
                curA = curA.getNext();
            }

            //指针curB一开始在链表B上遍历，当走到链表B的尾部即null时，跳转到链表A上。
            if (curB == null){
                //指针curB跳转到链表A上
                curB = headA;
            }else {
                //否则的话，curB不断向后移动。
                curB = curB.getNext();
            }

        }
        //1、此时curA和curB指向那个相交的节点，返回任意一个均可。
        //2、此时headA和headB不相交，那么curA和curB均为Null,也返回任意一个即可。
        return curA;
    }


    public static void main(String[] args) {
        GetIntersectionNode getIntersectionNode = new GetIntersectionNode();
        ListNode listA = getIntersectionNode.createIntersectionNode(Arrays.asList(4, 1, 8, 4, 5));
        ListNode listB = getIntersectionNode.createIntersectionNode(Arrays.asList(5, 0, 1, 8, 4, 5));

        //调用相交算法
        ListNode listNode = getIntersectionNode2(listA,listB);
        //输出相交的结点
        System.out.println(listNode);
    }


    private ListNode createIntersectionNode(List<Integer> asList) {
        if (asList.isEmpty()) {
            return null;
        }
        ListNode headNode = new ListNode(asList.get(0));
        //建立临时变量
        ListNode tempNode = createIntersectionNode(asList.subList(1,asList.size()));
        //在每一个头结点添加下一个结点的值next
        headNode.setNext(tempNode);
        return headNode;

    }
}
