package com.OfferAlgorithm.FirstWeek.listNode;

import java.util.*;

public class DetectCircle {

    public static void main(String[] args) {

        DetectCircle detectCircle = new DetectCircle();
        //创建一个有环链表
        ListNode headCode = createCircleListCode(Arrays.asList(1,2,3,4,5,6));
        //判断链表是否有环
        //fast指针每次移动两格，slow指针每次移动一格，如果存在环路，则fast最终一定会和slow相遇
        Boolean hasCycle = hasCycle(headCode);
        System.out.println(hasCycle);
        //打印得出的链表
        detectCircle.printListCode(headCode);

        //返回一个存在环路的链表中环路开始的位置
        //先通过快慢指针判断链表中是否有环，然后根据快指针是慢指针速度的两倍列出关系式
        //通过关系式找出规律，然后假设两个指针的速度相等，确定两个指针放置的位置（通过链表起始点到环路开始位置的距离为参考参数）
        ListNode node = detectCycle(headCode);
        System.out.println();
        System.out.println(node.getValue());


    }

    private static ListNode detectCycle(ListNode headCode) {

        if(headCode == null || headCode.getNext() == null){
            return null;
        }
        //创建快指针和慢指针
        ListNode slow = headCode;
        ListNode fast = headCode;
        //首先判断这个单链表是否存在环
        //然后找到快慢指针相遇的位置
        while (true){
            if(fast == null || fast.getNext() == null){
                return null;
            }
            slow = slow.getNext();
            fast = fast.getNext().getNext();
            if(fast == slow){
                break;
            }
        }

        //然后根据推出的规律，让慢指针从起点重新开始，快指针在相遇点开始
        slow = headCode;
        while (slow != fast){
            //让快慢指针运行的速度相等
            fast = fast.getNext();
            slow = slow.getNext();

        }
        return fast;
    }

    private static Boolean hasCycle(ListNode headCode) {
        if(headCode == null || headCode.getNext() == null){
            return false;
        }
        //创建快指针和满指针,初始化快指针和慢指针。
        ListNode slow = headCode;
        ListNode fast = headCode.getNext();
        //判断快指针和慢指针会不会相遇
        while (slow != fast){
            if(fast == null || fast.getNext() == null){
                return false;
            }
            slow = slow.getNext();
            fast = fast.getNext().getNext();
        }
        return true;

    }

    private void printListCode(ListNode headCode) {

        //遍历链表
        ListNode cur = headCode;
        ListNode last = headCode.getNext().getNext();
        int a = 0;
        while (cur != null){
            System.out.print(cur.getValue());
            System.out.print(" ");
            cur = cur.getNext();
            if(Objects.equals(cur, last)){
                a++;
                //判断是否第二次回到指定节点
                if(a == 2){
                    break;
                }

            }
        }
    }

    private static ListNode createCircleListCode(List<Integer> asList) {
        //创建头结点
        ListNode head = new ListNode(-1);
        //初始化尾结点为头结点
        ListNode tail = head;
        Map<Integer,ListNode> listNodeMap = new HashMap<>();
        for (Integer integer : asList) {
            //创建新结点
            ListNode node = new ListNode(integer);
            listNodeMap.put(integer,node);
            //将新结点连接到尾结点后面
            tail.setNext(node);
            //更新尾结点为新结点
            tail = node;
        }
        //将最后一个结点指向指定结点
        //注意每一个节点的内存地址，内存地址变了，节点也会发生变化。
        ListNode last = listNodeMap.get(3);
        tail.setNext(last);
        return head.getNext();
    }
}
