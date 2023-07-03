package com.OfferAlgorithm.FirstWeek.listNode;

import java.util.Arrays;
import java.util.List;

public class MergeTwoLists {


    public static void main(String[] args) {
        MergeTwoLists mergeTwoLists = new MergeTwoLists();
        //创建两个单链表，这两个单链表都是有序的（如果无序，还是要排序一下）
        ListNode listNode = createMergeTwoLists(Arrays.asList(2,2,3,5,8));
        ListNode listNode1 = createMergeTwoLists(Arrays.asList(2,6,7,9,10,11));
        //运用指针调用合并两个链表的算法
        ListNode headNode = mergeTwoLists(listNode,listNode1);
        //打印合并后的链表
        mergeTwoLists.printMergeTwoLists(headNode);

        //运用递归调用合并两个链表的算法
//        ListNode headNode1 = mergeTwoLists1(listNode,listNode1);
//        mergeTwoLists.printMergeTwoLists(headNode1);
    }

    private void printMergeTwoLists(ListNode headNode) {

        while (headNode != null){
            System.out.print(headNode.getValue());
            System.out.print(" ");
            headNode = headNode.getNext();
        }
        System.out.println();
    }

    private static ListNode mergeTwoLists(ListNode list1, ListNode list2) {

        //一开始设置一个虚拟节点，它的值为-1，它的值可以设置为任何的数，因为我们根本不需要使用它的值。
        ListNode dummy = new ListNode(-1);
        //设置一个指针，指向虚拟节点
        ListNode pre = dummy;
        //通过一个循环，不断的比较l1和l2中当前节点值的大小，直到l1或者l2遍历完毕为止。
        while (list1 != null && list2 != null){
            //如果list1当前的节点值小于等于list2当前节点的值
            if(list1.getValue() <= list2.getValue()){
                //让pre指向节点的next指针指向这个更小的节点
                //即指向list1
                pre.setNext(list1);
                //让list1向后移动
                list1 = list1.getNext();
            }else {
                //让pre指向节点的next指针指向这个更小值的节点。
                //即指向list2
                pre.setNext(list2);
                //让list2向后移动
                list2 = list2.getNext();
            }
            //让pre向后移动
            pre = pre.getNext();
        }

        //跳出循环后，list1或者list2中可能有剩余的节点没有被观察过
        //直接把剩下的节点加入到pre的next指针位置

        //如果list1中还有节点
        if(list1 != null){
            //把list1中剩下的节点全部加入到pre的next指针位置
            pre.setNext(list1);
        }

        //如果list2中还有节点
        if(list2 != null){
            //把list2中剩下的节点全部加入到pre的next指针位置
            pre.setNext(list2);
        }
        //对完成后的链表进行去重
        ListNode cur = dummy.getNext();
        while(cur.getNext() !=null){
            if(cur.getValue() == cur.getNext().getValue()){
                cur.setNext(cur.getNext().getNext());
            }else {
                cur = cur.getNext();
            }
        }

        //最后返回虚拟节点的next指针
        return dummy.getNext();


    }

    //递归调用
    private static ListNode mergeTwoLists1(ListNode list1,ListNode list2){
        if(list1 == null){
            return list2;
        }
        if(list2 == null){
            return list1;
        }
        if(list1.getValue() < list2.getValue()){
            list1.setNext(mergeTwoLists1(list1.getNext(),list2));
            return list1;
        }else {
            list2.setNext(mergeTwoLists1(list1,list2.getNext()));
            return list2;
        }
    }

    private static ListNode createMergeTwoLists(List<Integer> asList) {
        if(asList.isEmpty()){
            return null;
        }
        ListNode headNode = new ListNode(asList.get(0));
        //建立一个临时变量
        ListNode tempListNode = createMergeTwoLists(asList.subList(1,asList.size()));
        headNode.setNext(tempListNode);

        return headNode;

    }

}
