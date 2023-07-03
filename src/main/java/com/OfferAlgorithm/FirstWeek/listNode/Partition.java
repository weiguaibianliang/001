package com.OfferAlgorithm.FirstWeek.listNode;

import java.util.Arrays;
import java.util.List;

public class Partition {

    /**
     * 分隔链表：给你一个链表的头节点head和一个特定值x，请你对链表进行分隔，使得所有小于x的节点
     * 都出现在大于或等于x的节点之前
     * 你不需要保留每个分区中各节点的初始相对位置
     */

    /**
     * 通过构建两个链表来分别处理大于等于x的那些节点和小于x的那些节点
     * 大链表：大链表中所有节点值都是大于或等于特定值（除了虚拟头节点的值）
     * 小链表：小链表中的所有节点值都是小于特定值（除了虚拟节点的值）
     * 在遍历原链表的过程中，让大链表去接收那些大于等于x的节点，用小链表去接收那些小于x的节点，
     * 接着让小链表去接收那些小于x的节点，接着让小链表的尾部接上大链表的虚拟节点的下一个节点，
     * 然后让大链表的尾部节点的next指针指向Null,最后返回小链表的虚拟节点的下一个节点就行。
     */


    //创建链表
    public static void main(String[] args) {
        Partition partition = new Partition();
        //创建一个杂乱的链表
        ListNode listNode = creatPartitionListCode(Arrays.asList(3,2,1,5,4,6,8,7,11,9));
        //调用分隔链表算法
        ListNode small = partition(listNode,2);
        //根据分隔链表算法输出的结果，打印分隔后的链表
        partition.printPartitionListCode(small);
    }

    private void printPartitionListCode(ListNode headCode) {

        while (headCode != null){
            System.out.print(headCode.getValue());
            System.out.print(" ");
            headCode = headCode.getNext();
        }
        System.out.println();
    }

    private static ListNode partition(ListNode head, int x) {

        //创建两个新链表（大链表和小链表）

        //设置一个指针，执行大链表的头节点
        ListNode bigHead = new ListNode(-1);
        //设置一个指针，执行大链表的尾节点
        ListNode bigTail = bigHead;

        //设置一个指针，执行小链表的头节点
        ListNode smallHead = new ListNode(-1);
        //设置一个指针。执行小链表的尾节点
        ListNode smallTail = smallHead;

        //开始遍历原链表head，直到遍历到尾部位置
        //在遍历的过程查看当前节点的值
        while (head!= null){

            //如果当前节点的值小于特定值x
            if(head.getValue() <  x){
                //那么我们就把这个节点添加到小链表中
                //操作就是将小链表尾节点的指针指向当前节点
                smallTail.setNext(head);
                //那么小链表尾节点的位置也会发生变化
                smallTail = head;
            }else {
                //那么我们就把这个节点添加到大链表中
                //操作就是将大链表尾节点的指针指向当前节点
                bigTail.setNext(head);
                //那么大链表尾节点的位置也会发生变化
                bigTail = head;
            }

            //遍历原链表，那么head的位置也会发生变化
            head = head.getNext();
        }

        //遍历完成之后，大链表和小链表中都有了各自的数据
        //那么需要将小链表的数据和大链表的数据拼接一起
        smallTail.setNext(bigHead.getNext());
        //然后令大链表的尾节点的Next指针为null
        bigTail.setNext(null);

        //需要返回链表首元节点
        return smallHead.getNext();





    }

    private static ListNode creatPartitionListCode(List<Integer> asList) {
        if(asList.isEmpty()){
            return null;
        }
        ListNode headCode = new ListNode(asList.get(0));
        //建立临时变量
        ListNode tempNextCode = creatPartitionListCode(asList.subList(1,asList.size()));
        headCode.setNext(tempNextCode);
        return headCode;
    }

}
