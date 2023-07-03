package com.OfferAlgorithm.FirstWeek.queue;

public class MyCircularDeque {
    //构建循环双端队列
    /**
     * MyCircularDeque(k):构造函数，双端队列的大小为k。
     * insertFront():将一个元素加到双端队列头部。如果操作成功返回true.
     * insertLast():将一个元素添加到双端队列尾部。如果操作成功，返回true。
     * deleteFront():从双端队列头部删除一个元素。如果操作成功，返回true.
     * deleteLast():从双端队列尾部删除一个元素。如果操作成功，返回true。
     * getFront():从双端队列头部获得一个元素。如果双端队列为空，返回-1.
     * getRear():获得双端队列的最后一个元素。如果双端队列为空，返回-1.
     * isEmpty():检查双端队列是否为空.
     * isFull():检查双端队列是否满了。
     */

    //capacity表示数组的容量，它的大小为k+1
    private int capacity;
    //front表示对头的位置
    private int front;
    //rear表示队列尾部的下一个位置
    private int rear;
    //这里使用数组来设计循环双端队列
    private int[] arr;

    //创建该类的构造函数
    public MyCircularDeque(int k) {
        //需要空出一个额外的空间
        //当队列中的元素有k个时，代表队列已满
        capacity = k + 1;
        //这里使用数组来设计循环双端队列，数组的容量为capacity
        //即在数组中会空出一个额外的空间来不存储任何元素
        //因为这样才能合理的区分出队列为空和队列已满这两种情况
        arr = new int[capacity];
        //初始化对头指针
        front = 0;
        //初始化队尾指针
        rear = 0;
    }

    //判断队列是否为空
    public boolean isEmpty(){
        //font和rear指向同一个位置时，队列为空
        return front==rear;
    }

    //判断队列是否已满
    public boolean ifFull() {
        //当队尾指针向后移动一个位置后，指向了对头时，那么说明队列已满
        return (rear + 1)%capacity == front;
    }

    //在双端队列的队头添加元素value
    public boolean insertFront(int value){
        //首先判断队列是否已满，没有其它的剩余空间
        //如果队列已满，那么元素无法添加进去
        if(ifFull()){
            return false;
        }
        //font向前移动、由于front会移动到数组的最前面，如果再向前移动会出界
        //那么这个时候就需要将front挪到数组结尾的位置，可以通过取余运算来实现循环移动的这个操作
        front = (front - 1 + capacity) % capacity;
        //添加元素value到front这个位置
        arr[front] = value;
        //添加元素成功，返回true
        return true;
    }

    //在双端队列的队尾添加元素value
    public boolean insertRear(int value){
        //首先判断队列是否已满，没有其它的剩余空间
        //如果队列已满，那么元素无法添加进去
        if(ifFull()){
            return false;
        }
        //rear向后移动、由于rear会移动到数组的最后面，如果再向后移动会出界
        //那么这个时候就需要将rear挪到数组开头的位置，可以通过取余运算来实现循环移动的这个操作
        rear =(rear + 1) % capacity;
        //添加元素value到rear这个位置
        arr[rear] = value;
        //添加元素成功，返回true
        return true;

    }

    //从双端队列头部删除一个元素
    public boolean deleteFront(){
        //如果队列为空，说明无法从头部删除元素
        if(isEmpty()){
            return false;
        }
        //删除元素
        front = (front + 1) % capacity;
        return true;
    }

    //从双端队列尾部删除一个元素
    public boolean deleteRear(){
        //如果队列为空，说明无法从尾部删除元素
        if(isEmpty()){
            return false;
        }
        //删除元素
        rear = (rear - 1 + capacity) % capacity;
        return true;
    }

    //从双端队列头部获取第一个元素
    public int getFront(){
        //如果队列为空，返回-1
        if(isEmpty()){
            return -1;
        }
        //返回头部第一个元素
        return arr[front];
    }

    //从双端队列尾部获取最后一个元素的前面一个元素
    public int getRear(){
        //如果队列为空，返回-1
        if(isEmpty()){
            return -1;
        }
        //返回尾部最后一个元素的前面一个元素
        return arr[rear - 1 + capacity] % capacity;
    }
}
