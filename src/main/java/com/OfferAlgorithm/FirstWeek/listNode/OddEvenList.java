package com.OfferAlgorithm.FirstWeek.listNode;

import java.util.Arrays;
import java.util.List;

public class OddEvenList {

    /**
     * 给定一个单链表，把所有奇数节点和偶数节点分别排在一起。
     * 请注意，这里的奇数节点和偶数节点指的是节点编号的奇偶性，而不是节点的值的奇偶性
     */
    /**
     *
     * 应当保持奇数节点和偶数节点的相对顺序
     * 链表的第一个节点视为奇数节点，第二个节点视为偶数节点，依次类推。
     */

    public static void main(String[] args) {
        //创建一个单链表
        ListNode headNode = createListNode(Arrays.asList(1,2,3,4,5,6,7,8));
        //奇偶链表---把所有奇数节点和偶数节点分别在一起
        ListNode oddEvenListNode = oddEvenList(headNode);
        //遍历奇偶链表
        printOddEvenListNode(oddEvenListNode);
    }

    private static void printOddEvenListNode(ListNode oddEvenListNode) {

        while (oddEvenListNode != null){
            System.out.print(oddEvenListNode.getValue());
            oddEvenListNode = oddEvenListNode.getNext();
            System.out.print(" ");
        }
        System.out.println();
    }

    private static ListNode oddEvenList(ListNode headNode) {

        if(headNode == null || headNode.getNext() == null){
            return headNode;
        }
        //定义一个奇数节点
        ListNode odd = headNode;
        //定义一个偶数节点
        ListNode even = headNode.getNext();
        //设置一个指针，指向偶数节点的头节点
        ListNode elden = even;
        //满足操作条件
        while (even != null && even.getNext() != null){
            //偶数节点的下一节点必是奇数节点，
            odd.setNext(even.getNext());
            //得到下一个奇数节点
            odd = odd.getNext();
            //奇数节点的下一节点必是偶数节点
            even.setNext(odd.getNext());
            even = even.getNext();
        }
        //最后要把奇数节点的尾指针指向偶数节点的头指针
        odd.setNext(elden);

        return headNode;
    }

    private static ListNode createListNode(List<Integer> asList) {

        if (asList.isEmpty()){
            return null;
        }
        //定义一个虚拟节点
        ListNode headNode = new ListNode(-1);
        //定义尾节点指向虚拟节点
        ListNode tail = headNode;
        for (Integer integer : asList){
            ListNode node = new ListNode(integer);
            tail.setNext(node);
            tail = tail.getNext();
        }
        return headNode.getNext();
    }
}
