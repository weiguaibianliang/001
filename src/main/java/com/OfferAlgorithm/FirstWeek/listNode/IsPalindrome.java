package com.OfferAlgorithm.FirstWeek.listNode;

import java.util.Arrays;
import java.util.List;

public class IsPalindrome {

    /**
     * 回文链表：就是一个单链表，正着排序和逆着排序都是一样的
     * eg:1-2-2-1,这个就是一个回文单链表。
     */

    //解题思路：用快慢指针找到链表的中点，把后半部分链表逆序，再和前半部分链表做比较是否每个元素都相等。

    public static void main(String[] args) {
        IsPalindrome isPalindrome = new IsPalindrome();
        //创建一个单链表
        ListNode listNode = isPalindrome.createListNode(Arrays.asList(1,1,2,1,1));
        ListNode listNode1 = isPalindrome.createListNode(Arrays.asList(1,1,2,1,1));
        //打印单链表，看是否创建成功
        isPalindrome.printListNode(listNode);
//        //方法一：快慢指针
//        Boolean flag = isPalindrome.IsPaLind(listNode);
//        System.out.println(flag);
        //方法二：刚开始就利用反转链表，然后进行元素判断
        ListNode reverseNode = isPalindrome.IsReversePaLind(listNode1);
        Boolean flag1 = isPalindrome.IsEqual(listNode,reverseNode);
        System.out.println(flag1);


    }

    private Boolean IsEqual(ListNode listNode1, ListNode reverseNode) {
        //反转链表与起始单链表元素进行对比，看是否是回文链表
        while (reverseNode != null){
            if(reverseNode.getValue() != listNode1.getValue()){
                return false;
            }
            reverseNode = reverseNode.getNext();
            listNode1 = listNode1.getNext();
        }
        return true;
    }

    private ListNode IsReversePaLind(ListNode headNode) {
        //一开始就反转链表，然后进行判断
        if(headNode == null || headNode.getNext() == null){
            return null;
        }
        //开始反转链表
        ListNode pre = null;
        ListNode cur = headNode;
        while (cur != null){
            //定义一个临时变量
            ListNode tempNode = cur.getNext();
            //让第一个节点的指针指向null
            cur.setNext(pre);
            //迭代指针
            pre = cur;
            cur = tempNode;
        }
        return pre;
    }

    private Boolean IsPaLind(ListNode headNode) {
        if(headNode == null || headNode.getNext() == null){
            return true;
        }
        //定义快慢指针的初始位置
        ListNode fast = headNode;
        ListNode slow = headNode;
        //找到这个单链表的中间节点
        while (fast != null && fast.getNext() != null){
            fast = fast.getNext().getNext();
            slow = slow.getNext();
            if(slow == fast){
                break;
            }
        }
        //反转，将单链表中间节点的后半部分进行反转
        //利用双指针来反转
        ListNode pre = null;
        ListNode cur = slow;
        while (cur!=null){
            //temp保存临时变量
            ListNode temp = cur.getNext();
            //改变链表指针指向的节点
            cur.setNext(pre);
            //迭代当前指向
            pre = cur;
            cur = temp;
        }

        //只要后面的元素和前半部分相等，则链表是回文链表
        while (pre!= null){
            if(pre.getValue() != headNode.getValue()){
                return false;
            }
            pre = pre.getNext();
            headNode = headNode.getNext();
        }
        return true;

    }

    private void printListNode(ListNode listNode) {
        while (listNode != null){
            System.out.print(listNode.getValue());
            System.out.print(" ");
            listNode = listNode.getNext();
        }
        System.out.println();
    }

    private ListNode createListNode(List<Integer> asList) {

        if(asList.isEmpty()){
            return null;
        }
        //定义一个头结点
        ListNode headNode = new ListNode(-1);
        ListNode tail = headNode;
        for (Integer integer : asList) {
            ListNode node = new ListNode(integer);
            tail.setNext(node);
            tail = node;
        }
        return headNode.getNext();
    }

    public boolean isPaLind2(ListNode headNode){
        //如果单链表为空，满足条件
        if(headNode == null || headNode.getNext() == null){
            return true;
        }
        //定义快慢节点
        ListNode fast = headNode;
        ListNode slow = headNode;
        //找到满足回文链表的中间节点
        while (fast != null && fast.getNext() != null){
             fast = fast.getNext().getNext();
             slow = slow.getNext();
             if(fast == slow){
                 break;
             }
        }
        //找到中间节点后，需要把后半部分链表进行翻转
        ListNode pre = null;
        ListNode cur = slow;
        while (cur != null){
            //定义一个中间变量
            ListNode temp = cur.getNext();
            cur.setNext(pre);
            //迭代指向当前
            pre = cur;
            cur = temp;
        }
        //反转之后判断前半部分链表元素是否和后半部分链表元素相等
        while (pre != null){
            if(pre.getValue() != headNode.getValue()){
                return false;
            }
            pre = pre.getNext();
            headNode = headNode.getNext();
        }
        return true;
    }



}
