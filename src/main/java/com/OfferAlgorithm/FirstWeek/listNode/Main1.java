package com.OfferAlgorithm.FirstWeek.listNode;

class Node {
    int value;
    Node next;

    public Node(int value) {
        this.value = value;
    }
}

public class Main1 {
    public static void main(String[] args) {
        // 创建节点
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);

        // 构建链表
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node1; // 最后一个节点指向头节点

        // 遍历链表
        Node cur = node1;
        while (cur != null) {
            System.out.println(cur.value);
            cur = cur.next;
            if (cur == node1) {
                // 判断是否回到头节点
                break;
            }
        }
    }
}