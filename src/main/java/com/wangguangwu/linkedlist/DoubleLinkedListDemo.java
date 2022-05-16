package com.wangguangwu.linkedlist;

/**
 * Doubly linked list
 * <p>
 * 1. 单向链表查找只能是一个方向，双向链表可以向前或者向后查找
 * 2. 单向链表不能自我删除，需要依赖辅助节点，而双向链表可以自我删除
 *
 * @author wangguangwu
 */
public class DoubleLinkedListDemo {

    public static void main(String[] args) {
        // 测试
        System.out.println("双向链表的测试");
        // 先创建节点
        HeroNode2 hero1 = new HeroNode2(1, "宋江", "及时雨");
        HeroNode2 hero2 = new HeroNode2(2, "卢俊义", "玉麒麟");
        HeroNode2 hero3 = new HeroNode2(3, "吴用", "智多星");
        HeroNode2 hero4 = new HeroNode2(4, "林冲", "豹子头");

        // create a doubly linked list
        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
        doubleLinkedList.add(hero1);
        doubleLinkedList.add(hero2);
        doubleLinkedList.add(hero3);
        doubleLinkedList.add(hero4);

        doubleLinkedList.list();

        // update
        HeroNode2 updateNode = new HeroNode2(3, "汪光武", "小救星小汪");
        doubleLinkedList.update(updateNode);
        System.out.println("修改后的链表情况");
        doubleLinkedList.list();

        // delete
        doubleLinkedList.delete(4);
        System.out.println("删除后的链表情况");
        doubleLinkedList.list();
    }

}

@SuppressWarnings("all")
class DoubleLinkedList {

    /**
     * head node
     */
    private final HeroNode2 head = new HeroNode2(0, "", "");

    /**
     * traverse doubly linked list
     */
    public void list() {
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        // do not traverse the head node
        HeroNode2 cur = head.next;
        while (cur != null) {
            System.out.println(cur);
            // cur move back
            cur = cur.next;
        }
    }

    /**
     * add node to the end of linked list
     *
     * @param node node
     */
    public void add(HeroNode2 node) {
        HeroNode2 cur = head;
        // find the end of the linked list
        while (cur.next != null) {
            // cur move back
            cur = cur.next;
        }
        // add
        cur.next = node;
        node.pre = cur;
    }

    /**
     * update node
     *
     * @param node node
     */
    public void update(HeroNode2 node) {
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        // find the node which needs to be updated
        HeroNode2 cur = head.next;
        boolean flag = false;
        while (true) {
            if (cur == null) {
                break;
            }
            if (cur.no == node.no) {
                // find
                flag = true;
                break;
            }
            cur = cur.next;
        }
        if (flag) {
            cur.name = node.name;
            cur.nickname = node.nickname;
        } else {
            System.out.printf("没有找到 编号 %d 的节点，不能修改\n", node.no);
        }
    }

    /**
     * remove node from doubly linked list
     *
     * @param no no
     */
    public void delete(int no) {
        // empty linked list
        if (head.next == null) {
            System.out.println("链表为空，无法删除");
            return;
        }

        HeroNode2 cur = head.next;
        boolean flag = false;
        while (true) {
            if (cur == null) {
                break;
            }
            if (cur.no == no) {
                // find the node
                flag = true;
                break;
            }
            cur = cur.next;
        }
        if (flag) {
            cur.pre.next = cur.next;
            // prevent the situation: the node is at last
            if (cur.next != null) {
                cur.next.pre = cur.pre;
            }
        } else {
            System.out.printf("要删除的 %d 节点不存在\n", no);
        }
    }

}

/**
 * node
 */
@SuppressWarnings("all")
class HeroNode2 {

    public int no;
    public String name;
    public String nickname;

    /**
     * points to the pre node, default to null
     */
    public HeroNode2 pre;

    /**
     * points to the next node, default to null
     */
    public HeroNode2 next;

    public HeroNode2(int no, String name, String nickName) {
        this.no = no;
        this.name = name;
        this.nickname = nickName;
    }

    @Override
    public String toString() {
        return "HeroNode [no=" + no + ", name=" + name + ", nickName=" + nickname + "]";
    }
}