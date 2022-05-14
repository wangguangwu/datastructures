package com.wangguangwu.queue;

import java.rmi.server.ServerNotActiveException;
import java.util.Scanner;

/**
 * @author wangguangwu
 */
public class CircleArrayQueueDemo {

    public static void main(String[] args) {
        // create a circle queue
        // maxSize = 4, the valid data size is 3
        CircleArray queue = new CircleArray(4);
        char key;
        Scanner scanner = new Scanner(System.in);
        System.out.println("s(show): 显示队列");
        System.out.println("e(exit): 退出程序");
        System.out.println("a(add): 添加数据到队列");
        System.out.println("g(get): 从队列取出数据");
        System.out.println("h(head): 查看队列头的数据");
        boolean loop = true;
        while (loop) {
            key = scanner.next().charAt(0);
            switch (key) {
                case 's':
                    queue.showQueue();
                    break;
                case 'a':
                    System.out.println("输入一个数");
                    int value = scanner.nextInt();
                    queue.addQueue(value);
                    System.out.println("加入成功");
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
                    System.out.println("程序退出");
                    break;
                default:
                    break;
            }
        }
    }

}

class CircleArray {

    /**
     * the max size of the queue
     */
    private final int maxSize;

    /**
     * points to the first element of the queue
     * the initial value is 0
     */
    private int front;

    /**
     * points to the position after the last element of the queue
     * because it is desired to free up a space as a convention
     */
    private int rear;

    /**
     * the array to store data
     */
    private final int[] arr;

    public CircleArray(int maxSize) {
        this.maxSize = maxSize;
        arr = new int[maxSize];
    }

    private static final String EMPTY_DESCRIPTION = "The queue is empty, no data can be fetched";

    /**
     * check if the queue is full
     *
     * @return boolean
     */
    public boolean isFull() {
        return (rear + 1) % maxSize == front;
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
        // add data
        arr[rear] = n;
        // rear move back
        rear = (rear + 1) % maxSize;
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
        int value = arr[front];
        front = (front + 1) % maxSize;
        return value;
    }

    /**
     * find the number of valid data in the current queue
     *
     * @return size
     */
    public int size() {
        return (rear + maxSize - front) % maxSize;
    }

    /**
     * show all the data of the queue
     */
    public void showQueue() {
        if (isEmpty()) {
            System.out.println(EMPTY_DESCRIPTION);
            return;
        }
        int length = front + size();
        for (int i = front; i < length; i++) {
            int index = i % maxSize;
            System.out.printf("arr[%d]=%d\n", index, arr[index]);
        }
    }

    /**
     * just show, don't fetched data
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
