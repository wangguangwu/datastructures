package com.wangguangwu.stack;

import java.rmi.ServerException;

/**
 * 使用栈实现计算器
 *
 * @author wangguangwu
 */
public class Calculator {

    public static void main(String[] args) {
        String expression = "3+2*6-4";
        // 创建两个栈
        // 数栈
        ArrayStack2 numStack = new ArrayStack2(10);
        // 符号栈
        ArrayStack2 operationStack = new ArrayStack2(10);
        // 用于扫描
        int index = 0;
        // 基本变量
        int num1;
        int num2;
        int operation;
        int res;
        // 将每次扫描到 char 保存到 ch
        char ch;
        // 拼接多位数
        StringBuilder keepNum = new StringBuilder();
        // 循环扫描 expression
        do {
            // 依次得到每一个字符
            ch = expression.substring(index, index + 1).charAt(0);
            // 判断 ch，做相应的处理
            if (operationStack.isOperation(ch)) {
                if (!operationStack.isEmpty()) {
                    // 如果符号栈中有操作符，进行比较
                    // 如果当前的操作符的优先级小于或者等于栈中的操作符
                    // 从数栈中 pop 出两个数，从符号栈中 pop 出一个符号
                    // 进行运算，得到结果后，将结果入数栈，将当前的操作符入符号栈
                    if (operationStack.priority(ch) <= operationStack.priority(operationStack.peek())) {
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        operation = operationStack.pop();
                        res = numStack.cal(num1, num2, operation);
                        // 把运算的结果加入数栈
                        numStack.push(res);
                        // 把当前的操作符加入符号栈
                        operationStack.push(ch);
                    } else {
                        // 当前操作符的优先级大于栈中的操作符，直接入符号栈
                        operationStack.push(ch);
                    }
                } else {
                    // 符号栈为空也直接入符号栈
                    operationStack.push(ch);
                }
            } else {
                // 如果是数，直接入数栈
                // numStack.push(ch - 48)
                // 分析思路
                // 1. 考虑到多位数场景，不能发现是一个数就立即入栈
                // 2. 在处理数时，需要向 expression 的表达式的 index 后再看一位，如果是数就进行扫描，如果是符号才入栈
                // 3. 定义一个变量字符串，用于拼接
                keepNum.append(ch);

                // 如果 ch 已经是 expression 的最后一位，就直接入栈
                if (index == expression.length() - 1) {
                    numStack.push(Integer.parseInt(keepNum.toString()));
                } else {
                    // 判断下一个字符是不是数字，如果是数字，就继续扫描，如果是运算符，则入栈
                    if (operationStack.isOperation(expression.substring(index + 1, index + 2).charAt(0))) {
                        // 如果后一位是运算符，则入栈 keepNum = "1" 或者 "123"
                        numStack.push(Integer.parseInt(keepNum.toString()));
                        // 清空 keepNum
                        keepNum = new StringBuilder();
                    }
                }
            }
            // index++，并判断是否扫描到 expression 结尾
            index++;
        } while (index < expression.length());

        // 当表达式扫描完毕，就按顺序从数栈和符号栈中 pop 出相应的数和符号，并运行
        while (!operationStack.isEmpty()) {
            // 如果符号栈为空，则计算到最后的结果，数栈中只有一个数字
            num1 = numStack.pop();
            num2 = numStack.pop();
            operation = operationStack.pop();
            res = numStack.cal(num1, num2, operation);
            // 计算结果入栈
            numStack.push(res);
        }
        // 数栈中最后只剩下一个数，这个数就是结果
        int res2 = numStack.pop();
        System.out.printf("表达式 %s = %d", expression, res2);
    }

}

class ArrayStack2 {

    /**
     * size of stack
     */
    private final int maxSize;

    /**
     * array
     */
    private final int[] stack;

    /**
     * the top of stack
     */
    private int top = -1;

    public ArrayStack2(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[this.maxSize];
    }

    public int peek() {
        return stack[top];
    }

    public boolean isFull() {
        return top == maxSize - 1;
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public void push(int value) {
        if (isFull()) {
            System.out.println("栈满");
            return;
        }
        top++;
        stack[top] = value;
    }

    public int pop() {
        if (isEmpty()) {
            try {
                throw new ServerException("栈空，没有数据");
            } catch (ServerException e) {
                e.printStackTrace();
            }
        }
        int value = stack[top];
        top--;
        return value;
    }

    public void list() {
        if (isEmpty()) {
            System.out.println("栈空，没有数据");
            return;
        }
        for (int i = top; i >= 0; i--) {
            System.out.printf("stack[%d]=%d\n", i, stack[i]);
        }
    }

    public int priority(int operation) {
        if (operation == '*' || operation == '/') {
            return 1;
        } else if (operation == '+' || operation == '-') {
            return 0;
        } else {
            return -1;
        }
    }

    public boolean isOperation(char val) {
        return val == '+' || val == '-' || val == '*' || val == '/';
    }

    public int cal(int num1, int num2, int operation) {
        // 计算结果
        int res = 0;
        switch (operation) {
            case '+':
                res = num1 + num2;
                break;
            case '-':
                res = num1 - num2;
                break;
            case '*':
                res = num1 * num2;
                break;
            case '/':
                res = num1 / num2;
                break;
            default:
                break;
        }
        return res;
    }

}


