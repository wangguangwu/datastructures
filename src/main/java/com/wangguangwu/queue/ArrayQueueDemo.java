package com.wangguangwu.queue;

import java.rmi.server.ServerNotActiveException;
import java.util.Scanner;

/**
 * @author wangguangwu
 */
public class ArrayQueueDemo {

    public static void main(String[] args) {
        // create a queue
        ArrayQueue queue = new ArrayQueue(3);
        // test
        Scanner scanner = new Scanner(System.in);
        char key;
        boolean loop = true;
        System.out.println("s(show): 显示队列");
        System.out.println("e(exit): 退出程序");
        System.out.println("a(add): 添加数据到队列");
        System.out.println("g(get): 从队列取出数据");
        System.out.println("h(head): 查看队列头的数据");
        while (loop) {
            // accept one char
            key = scanner.next().charAt(0);
            switch (key) {
                case 's':
                    queue.showQueue();
                    break;
                case 'a':
                    System.out.println("输入一个数");
                    int value = scanner.nextInt();
                    queue.addQueue(value);
                    System.out.println("添加成功");
                    break;
                case 'g':
                    try {
                        int res = queue.getQueue();
                        System.out.printf("取出的数据是%d\n", res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try {
                        int res = queue.headQueue();
                        System.out.printf("队列头的数据是%d\n", res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e':
                    scanner.close();
                    loop = false;
                    System.out.println("程序退出~~");
                    break;
                default:
                    break;
            }
        }
    }

}

/**
 * Emulate queues with arrays.
 */
class ArrayQueue {

    /**
     * the max size of the queue
     */
    private final int maxSize;

    /**
     * the head pointer of the queue
     */
    private int front;

    /**
     * the tail pointer of the queue
     */
    private int rear;

    /**
     * the array is used to store data
     */
    private final int[] arr;

    private static final String EMPTY_DESCRIPTION = "The queue is empty, no data can be fetched";

    public ArrayQueue(int maxSize) {
        this.maxSize = maxSize;
        this.arr = new int[maxSize];
        // to the head of the queue
        this.front = -1;
        // to the end of the queue
        this.rear = -1;
    }

    /**
     * check if the queue is full
     *
     * @return boolean
     */
    public boolean isFull() {
        return rear == maxSize - 1;
    }

    /**
     * check if the queue is empty
     *
     * @return boolean
     */
    public boolean isEmpty() {
        return rear == front;
    }

    /**
     * add data
     *
     * @param n data
     */
    public void addQueue(int n) {
        if (isFull()) {
            System.out.println("The queue is fulled, no data can be added");
            return;
        }
        rear++;
        arr[rear] = n;
    }


    /**
     * get data
     *
     * @return data
     * @throws ServerNotActiveException queue is empty
     */
    public int getQueue() throws ServerNotActiveException {
        if (isEmpty()) {
            throw new ServerNotActiveException(EMPTY_DESCRIPTION);
        }
        front++;
        return arr[front];
    }

    /**
     * show data
     */
    public void showQueue() {
        if (isEmpty()) {
            System.out.println(EMPTY_DESCRIPTION);
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.printf("arr[%d]=%d\n", i, arr[i]);
        }
    }


    /**
     * show the head data of the queue, but not get it
     *
     * @return data
     * @throws ServerNotActiveException queue is empty
     */
    public int headQueue() throws ServerNotActiveException {
        if (isEmpty()) {
            throw new ServerNotActiveException(EMPTY_DESCRIPTION);
        }
        return arr[front];
    }

}
