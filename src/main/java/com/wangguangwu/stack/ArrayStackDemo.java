package com.wangguangwu.stack;

import lombok.Data;

import java.rmi.ServerException;
import java.util.Scanner;

/**
 * stack
 * <p>
 * FILO 的有序列表
 * 插入和删除都是在线性表的同一端进行
 * 允许插入和删除的一端，为变化的一端，称为栈顶（Top），另一端为固定的一端，称为栈底（Bottom）
 * <p>
 * 子程序的调用
 * 在调入子程序前，会先将下个指令的地址存到堆栈中，直到子程序执行完再将地址取出，以回到原来的程序中。
 *
 * @author wangguangwu
 */
public class ArrayStackDemo {

    public static void main(String[] args) {
        ArrayStack stack = new ArrayStack(4);
        String key;
        boolean loop = true;
        Scanner scanner = new Scanner(System.in);

        System.out.println("show: 表示显示栈");
        System.out.println("exit: 退出程序");
        System.out.println("push: 表示添加数据到栈(入栈)");
        System.out.println("pop: 表示从栈取出数据(出栈)");
        System.out.println("请输入你的选择");
        while (loop) {
            key = scanner.next();
            switch (key) {
                case "show":
                    stack.list();
                    break;
                case "push":
                    System.out.println("请输入一个数");
                    int value = scanner.nextInt();
                    stack.push(value);
                    break;
                case "pop":
                    int res = stack.pop();
                    System.out.printf("出栈的数据是 %d\n", res);
                    break;
                case "exit":
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;
            }
        }

    }

}

/**
 * Create a stack using an array
 */
@Data
class ArrayStack {

    /**
     * the size of stack
     */
    private int maxSize;

    /**
     * array
     */
    private int[] stack;

    /**
     * the top of stack
     */
    private int top = -1;

    public ArrayStack(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[this.maxSize];
    }


    /**
     * stack is full
     *
     * @return boolean
     */
    public boolean isFull() {
        return top == maxSize - 1;
    }

    /**
     * stack is empty
     *
     * @return boolean
     */
    public boolean isEmpty() {
        return top == -1;
    }

    /**
     * push
     *
     * @param value value
     */
    public void push(int value) {
        if (isFull()) {
            System.out.println("栈满");
            return;
        }
        top++;
        stack[top] = value;
    }

    /**
     * pop
     *
     * @return value
     */
    public int pop() {
        if (isEmpty()) {
            try {
                throw new ServerException("栈空，没有数据~");
            } catch (ServerException e) {
                e.printStackTrace();
            }
        }
        int value = stack[top];
        top--;
        return value;
    }

    /**
     * show data
     * from pop
     */
    public void list() {
        if (isEmpty()) {
            System.out.println("栈空，没有数据");
            return;
        }
        for (int i = top; i >= 0; i--) {
            System.out.printf("stack[%d]=%d\n", i, stack[i]);
        }
    }

}