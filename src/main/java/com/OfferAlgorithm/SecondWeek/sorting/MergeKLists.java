package com.OfferAlgorithm.SecondWeek.sorting;

public class MergeKLists {

    //归并排序，分治策略，分而治之

    public static void main(String[] args) {
        //定义几个升序链表
        ListNode[] lists = new ListNode[3];
        // 创建第一个链表：1->2->3
        lists[0] = new ListNode(1);
        lists[0].next = new ListNode(2);
        lists[0].next.next = new ListNode(3);

        // 创建第二个链表：4->5
        lists[1] = new ListNode(4);
        lists[1].next = new ListNode(5);

        // 创建第三个链表：6
        lists[2] = new ListNode(6);
        lists[2].next = new ListNode(7);
        lists[2].next.next = new ListNode(8);
        //调用分治合并的方法
        ListNode headNode = merge(lists,0,lists.length - 1);

        while (headNode != null){
            System.out.print(headNode.value);
            headNode = headNode.next;
            System.out.print(" ");
        }
        System.out.println();

    }

    private static ListNode merge(ListNode[] lists, int left, int right) {
        //分治返回合并的结点
        if(left == right){
            return lists[left];
        }
        //否则就要进行分治合并
        int mid = (left + right)/2;
        //合并左半边
        ListNode l1 = merge(lists,left,mid);
        //合并右半边
        ListNode l2 = merge(lists,mid+1,right);

        return mergeTwoLists(l1,l2);
    }

    private static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        //处理初始传递过程中存在的null
        if(l1 == null || l2 == null){
            return l1 == null ? l2 : l1;
        }
        //l1和l2均不等于null，进行合并
        //创建一个头结点（虚拟）
        ListNode head = new ListNode(-1);
        ListNode cur = head;
        while (l1 != null && l2 != null){
            if(l1.value <= l2.value){
                cur.next = l1;
                cur = cur.next;
                l1 = l1.next;
            }else {
                cur.next = l2;
                cur = cur.next;
                l2 = l2.next;
            }
        }
        //当l1或者l2中有一个是Null时，那么就要依次拼接
        if(l1 != null){
            cur.next = l1;
        }
        if(l2 != null){
            cur.next = l2;
        }

        return head.next;

    }

    static class ListNode{
        private int value;
        private ListNode next;



        ListNode(int value, ListNode next) {
            this.value = value;
            this.next = next;
        }

         public ListNode(int value) {
            this.value = value;
         }

         public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public ListNode getNext() {
            return next;
        }

        public void setNext(ListNode next) {
            this.next = next;
        }

    }

}

