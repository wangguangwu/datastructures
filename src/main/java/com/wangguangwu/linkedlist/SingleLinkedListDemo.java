package com.wangguangwu.linkedlist;


import java.util.Stack;

/**
 * @author wangguangwu
 */
public class SingleLinkedListDemo {

    public static void main(String[] args) {
        // create nodes
        HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode hero2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
        HeroNode hero4 = new HeroNode(4, "林冲", "豹子头");

        // create linked list
        SingleLinkedList singleLinkedList = new SingleLinkedList();

        // add node
        singleLinkedList.add(hero1);
        singleLinkedList.add(hero2);
        singleLinkedList.add(hero3);
        singleLinkedList.add(hero4);

        // show the single list
        System.out.println("====================链表====================");
        singleLinkedList.list();

        System.out.println("====================反转链表====================");
        reverseList(singleLinkedList.getHead());
        singleLinkedList.list();

        System.out.println("====================反转链表打印====================");
        reversePrint(singleLinkedList.getHead());

        System.out.println("====================find last index node====================");
        HeroNode lastIndexNode = findLastIndexNode(singleLinkedList.getHead(), 3);
        System.out.println(lastIndexNode);

        System.out.println("=====================update====================");
        HeroNode heroNode = new HeroNode(3, "汪光武", "小救星小汪");
        singleLinkedList.update(heroNode);
        singleLinkedList.list();

        System.out.println("=====================delete====================");
        singleLinkedList.delete(4);
        singleLinkedList.list();

    }

    /**
     * reverse order printing.
     *
     * @param head head node
     */
    @SuppressWarnings("all")
    public static void reversePrint(HeroNode head) {
        if (head.next == null) {
            // empty linked list
            return;
        }
        // create a stack
        // push each node onto the stack
        Stack<HeroNode> stack = new Stack<>();
        HeroNode cur = head.next;
        // push each node onto the stack
        while (cur != null) {
            stack.push(cur);
            // cur move back
            cur = cur.next;
        }
        while (!stack.isEmpty()) {
            System.out.println(stack.pop());
        }
    }

    /**
     * reverse single linked list
     *
     * @param head head node
     */
    public static void reverseList(HeroNode head) {
        if (head.next == null || head.next.next == null) {
            return;
        }

        // reverse linked list head node
        HeroNode reverseHead = new HeroNode(0, "", "");
        HeroNode cur = head.next;
        HeroNode next;
        while (cur != null) {
            // point to current node's next node
            next = cur.next;
            cur.next = reverseHead.next;
            reverseHead.next = cur;
            cur = next;
        }
        head.next = reverseHead.next;
    }

    /**
     * find last index node
     *
     * @param head  head node
     * @param index index
     * @return node
     */
    public static HeroNode findLastIndexNode(HeroNode head, int index) {
        if (head.next == null) {
            return null;
        }
        // get linked list length
        int size = getLength(head);
        if (index <= 0 || index > size) {
            return null;
        }
        HeroNode cur = head.next;
        int length = size - index;
        int i = 0;
        while (i < length) {
            cur = cur.next;
            i++;
        }
        return cur;
    }

    /**
     * get node size
     * if it is a linked list with a head node, the head node is not required to be counted
     *
     * @param head head node
     * @return linked list size
     */
    public static int getLength(HeroNode head) {
        if (head.next == null) {
            return 0;
        }
        int length = 0;
        // don't count head node
        HeroNode cur = head.next;
        while (cur != null) {
            length++;
            // cur move back
            cur = cur.next;
        }
        return length;
    }

}

/**
 * Linked list
 */
class SingleLinkedList {

    /**
     * head node
     */
    private final HeroNode head = new HeroNode(0, "", "");

    public HeroNode getHead() {
        return head;
    }

    /**
     * add node to the end of the single linked list
     * <p>
     * 1. Find the last node of the current linked list
     * 2. Point the next attribute of the last node to the new node
     *
     * @param heroNode node
     */
    public void add(HeroNode heroNode) {
        // we can't move the head node, so wo create an assist variable to traverse
        HeroNode temp = head;
        // traverse the linked list and find the last
        // the end of the linked list
        while (temp.next != null) {
            // temp move back
            temp = temp.next;
        }
        // point current node's next attribute to the new node
        temp.next = heroNode;
    }

    /**
     * add node to the single linked list by order
     *
     * @param heroNode node
     */
    @SuppressWarnings("all")
    public void addByOrder(HeroNode heroNode) {
        // create an assist variable to traverse
        HeroNode temp = head;
        // express whether the order exists
        boolean flag = false;
        while (true) {
            // the endOfLoop of the single linked list
            // find the place
            if (temp.next == null || temp.next.no > heroNode.no) {
                break;
            } else if (temp.next.no == heroNode.no) {
                // the order exists
                flag = true;
                break;
            }
            // node move back
            temp = temp.next;
        }
        // determine the value of flag
        if (flag) {
            System.out.printf("准备插入的英雄的编号 %d 已经存在了, 不能加入\n", heroNode.no);
        } else {
            // add the node
            heroNode.next = temp.next;
            temp.next = heroNode;
        }
    }

    /**
     * update the message of the node by the no attribute
     * cannot change the no attribute
     *
     * @param newHeroNode node
     */
    public void update(HeroNode newHeroNode) {
        // determine whether it is empty
        if (head.next == null) {
            System.out.println("链表为空~");
            return;
        }
        // find the node by the no attribute
        // define an auxiliary variable
        HeroNode temp = head.next;
        // indicates whether the node is found
        boolean flag = false;
        while (true) {
            // the linked list has been traversed
            if (temp == null) {
                break;
            }
            if (temp.no == newHeroNode.no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            temp.name = newHeroNode.name;
            temp.nickname = newHeroNode.nickname;
        } else {
            System.out.printf("没有找到 编号 %d 的节点，不能修改\n", newHeroNode.no);
        }
    }

    /**
     * delete node
     * find the previous node of the node to be deleted
     *
     * @param no the no of the node to be deleted
     */
    public void delete(int no) {
        HeroNode temp = head;
        // indicates whether the node is found
        boolean flag = false;
        while (true) {
            // the end of the single linked list
            if (temp.next == null) {
                break;
            }
            // find the previous node of the node to be deleted
            if (temp.next.no == no) {
                flag = true;
                break;
            }
            // node move back
            temp = temp.next;
        }
        if (flag) {
            temp.next = temp.next.next;
        } else {
            System.out.printf("要删除的 %d 节点不存在\n", no);
        }
    }

    /**
     * show the list
     */
    public void list() {
        HeroNode temp = head.next;
        if (temp == null) {
            System.out.println("链表为空");
            return;
        }
        while (temp != null) {
            // print the node
            System.out.println(temp);
            // node move back
            temp = temp.next;
        }
    }

}

/**
 * Node
 */
@SuppressWarnings("all")
class HeroNode {
    public final int no;
    public String name;
    public String nickname;
    public HeroNode next;

    public HeroNode(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "HeroNode [no=" + no + ", name=" + name + ", nickname=" + nickname + "]";
    }

}