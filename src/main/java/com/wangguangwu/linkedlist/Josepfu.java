package com.wangguangwu.linkedlist;

import lombok.Data;

/**
 * @author wangguangwu
 */
public class Josepfu {

    public static void main(String[] args) {
        CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
        circleSingleLinkedList.addBoy(5);
        circleSingleLinkedList.showBoy();

        circleSingleLinkedList.countBoy(1, 2, 5);
    }

}

/**
 * circle linked list
 */
class CircleSingleLinkedList {

    private Boy first = null;

    /**
     * add node, form a circle linked list
     */
    public void addBoy(int nums) {
        if (nums < 1) {
            System.out.println("nums的值不正确");
            return;
        }
        Boy curBoy = null;
        for (int i = 1; i <= nums; i++) {
            Boy boy = new Boy(i);
            if (i == 1) {
                // point to oneself
                first = boy;
                first.setNext(first);
                curBoy = first;
            } else {
                assert curBoy != null;
                curBoy.setNext(boy);
                boy.setNext(first);
                curBoy = boy;
            }
        }
    }

    /**
     * traverse the current circular linked list
     */
    public void showBoy() {
        if (first == null) {
            System.out.println("没有任何小孩~~");
            return;
        }
        Boy curBoy = first;
        while (true) {
            System.out.printf("小孩的编号 %d \n", curBoy.getNo());
            if (curBoy.getNext() == first) {
                // traversal completed
                break;
            }
            // move back
            curBoy = curBoy.getNext();
        }
    }

    /**
     * According to the user's input
     * calculate the order in which the children go out of the circle
     *
     * @param startNo  表示从第几个小孩开始数数
     * @param countNum 表示数几下
     * @param nums     表示最初有多少小孩在圈中
     */
    public void countBoy(int startNo, int countNum, int nums) {
        // check data
        if (first == null || startNo < 1 || startNo > nums) {
            System.out.println("参数输入有误， 请重新输入");
            return;
        }
        // 创建一个辅助指针(变量) helper
        // 指向环形链表的最后这个节点
        Boy helper = first;
        while (helper.getNext() != first) {
            helper = helper.getNext();
        }
        for (int j = 0; j < startNo - 1; j++) {
            first = first.getNext();
            helper = helper.getNext();
        }
        while (helper != first) {
            for (int j = 0; j < countNum - 1; j++) {
                first = first.getNext();
                helper = helper.getNext();
            }
            System.out.printf("小孩%d出圈\n", first.getNo());
            first = first.getNext();
            helper.setNext(first);
        }
        System.out.printf("最后留在圈中的小孩编号%d \n", first.getNo());
    }
}

/**
 * node
 */
@Data
class Boy {

    private int no;
    private Boy next;

    public Boy(int no) {
        this.no = no;
    }

}
