package com.ljh.data.list;

/**
 * @author ljh
 * @date 2020-08-25 08:47
 * 单链表
 */
public class SingleLinkedList {

    //定义头节点
    public ListNode headNode = new ListNode(0, null);

    /**
     * 添加节点
     *
     * @param listNode
     */
    public void add(ListNode listNode) {
        ListNode tmp = headNode;
        while (tmp.next != null) {
            tmp = tmp.next;
        }
        tmp.next = listNode;
    }

    /**
     * 打印节点
     */
    public void list() {
        ListNode tmp = headNode;
        if (tmp.next == null) {
            System.out.println("链表为空");
            return;
        }
        while (tmp.next != null) {
            System.out.println(tmp.next.toString());
            tmp = tmp.next;
        }

    }

    /**
     * 按照no的顺序添加节点
     */
    public void addByOrder(ListNode listNode) {
        ListNode tmp = headNode;
        boolean flag = false;
        while (true) {
            if (tmp.next == null) {
                break;
            } else if (tmp.next.data > listNode.data) {
                break;
            } else if (tmp.next.data == listNode.data) {
                flag = true;
            }
            tmp = tmp.next;
        }
        if (!flag) {
            listNode.next = tmp.next;
            tmp.next = listNode;
        } else {
            System.out.println("no: " + listNode.data + " 已存在，插入失败");
        }
    }

    /**
     * 修改节点
     */
    public void update(ListNode listNode) {
        ListNode tmp = headNode;
        boolean flag = false;
        while (true) {
            if (tmp.next == null) {
                System.out.printf("链表为空");
                break;
            } else if (tmp.next.data == listNode.data) {
                flag = true;
                break;
            }
            tmp = tmp.next;
        }
        if (flag) {
            tmp.name = listNode.name;
        }
    }

    //删除节点

    public void delete(int data) {
        ListNode tmp = headNode;
        boolean flag = false;
        if (tmp.next == null) {
            System.out.printf("链表为空");
            return;
        }
        while (tmp.next != null) {
            if (tmp.next.data == data) {
                flag = true;
                break;
            }
            tmp = tmp.next;
        }
        if (flag) {
            tmp.next = tmp.next.next;
        } else {
            System.out.println("没有找到改节点");
        }
    }


    //第一个题  链表删除倒数第k个数据
    public void deleteByK(int k) {
        ListNode tmp = headNode;
        ListNode tmp2 = headNode;
        int num = 0;
        while (tmp.next != null) {
            num++;
            tmp = tmp.next;
        }
        if (k > num) {
            System.out.printf("删除数据超界了");
            return;
        } else {
            for (int i = 0; i < num - k; i++) {
                tmp2 = tmp2.next;
            }
            tmp2.next = tmp2.next.next;
        }
    }


    /**
     * 第一个题  链表删除倒数第k个数据  第二种方法
     * 设置两个节点p1和p2,假设链表长为N 先让p1针对k 则p1还有n-k个节点
     * 再让p1和p2同时移动p1到尾，则p2是倒数k的节点
     */
    public ListNode findKeyIntK(int k) {
        ListNode listNode1 = headNode;
        ListNode listNode2 = headNode;
        if (listNode1.next == null) {
            return null;
        }
        while (listNode1.next != null && k-- > 0) {
            listNode1 = listNode1.next;
        }
        if (k > 0) {
            return null;
        }
        while (listNode1 != null) {
            listNode1 = listNode1.next;
            listNode2 = listNode2.next;
        }
        return listNode2;
    }

    /**
     * 第二题，反转链表
     * 先定义 一个节点 ListNode  reverse= new ListNode();
     * 取出节点 遍历 将其取出放在reverse 的最前端
     * head.next = reverse.next
     */
    public void reversNode(ListNode headNode) {
        if (headNode.next == null || headNode.next.next == null) {
            System.out.println("一个或者空");
            return;
        }
        //辅助
        ListNode cur = headNode.next;
        ListNode next = null; //这个当前指向下一个节点
        ListNode reverse = new ListNode(0, "");
        while (cur != null) {
            next = cur.next; //暂时保存下一个节点
            cur.next = reverse.next;//将cur 的下一个节点放在最前端
            reverse.next = cur;
            cur = next;
        }
        headNode.next = reverse.next;
    }

    /**
     * 第二题，反转链表
     * 递归
     */
    public ListNode reversNode2(ListNode headNode) {
        if (headNode == null || headNode.next == null) {
            return headNode;
        }
        ListNode next = headNode.next;
        headNode.next = null;
        ListNode listNode = reversNode2(next);
        next.next = headNode;
        return listNode;
    }

    public void list(ListNode listNode) {
        if (listNode == null) {
            System.out.println("链表为空");
            return;
        }
        while (listNode != null) {
            System.out.println(listNode.toString());
            listNode = listNode.next;
        }
    }

    /**
     * 合并两个有序的链表
     * 直接循环
     *
     * @param listNode1
     * @param listNode2
     * @return
     */
    public ListNode merge(ListNode listNode1, ListNode listNode2) {
        ListNode cur = headNode;
        if (listNode1 == null) {
            return listNode2;
        }
        if (listNode2 == null) {
            return listNode1;
        }
        while (listNode1 != null && listNode2 != null) {
            if (listNode1.data < listNode2.data) {
                cur.next = listNode1;
                listNode1 = listNode1.next;
            } else {
                cur.next = listNode2;
                listNode2 = listNode2.next;
            }
            cur = cur.next;
        }
        if (listNode1 != null) {
            cur.next = listNode1;
        }
        if (listNode2 != null) {
            cur.next = listNode2;
        }
        return headNode.next;
    }

    /**
     * 递归
     *
     * @param listNode1
     * @param listNode2
     * @return
     */
    public ListNode merge2(ListNode listNode1, ListNode listNode2) {
        if (listNode1 == null) {
            return listNode2;
        }
        if (listNode2 == null) {
            return listNode1;
        }
        if (listNode1.data < listNode2.data) {
            listNode1.next = merge2(listNode1.next, listNode2);
            return listNode1;
        } else {
            listNode2.next = merge(listNode1, listNode2.next);
            return listNode2;
        }

    }
}
