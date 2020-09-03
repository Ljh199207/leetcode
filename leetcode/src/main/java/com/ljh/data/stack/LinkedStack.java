package com.ljh.data.stack;

import com.ljh.data.list.ListNode;

/**
 * @author ljh
 * @date 2020-08-31 15:10
 */
public class LinkedStack {
    ListNode head = null;

    public LinkedStack() {
        head = new ListNode();
    }

    public boolean isEmpty() {//判断是否为空的
        return head == null;
    }

    public void add(ListNode listNode) {
        if (head != null) {
            listNode.next = head;
        }
        head = listNode;
    }

    public ListNode pop() {
        if (isEmpty()) {
            throw new RuntimeException("栈为空");
        }
        ListNode tmp = head;
        head = head.next;
        return tmp;
    }

    public ListNode peek() {
        if (isEmpty()) {
            throw new RuntimeException("栈为空");
        }
        return head;
    }

    public void list() {
        while (true) {
            if (head == null) {
                break;
            }
            System.out.println(head.toString());
            head = head.next;
        }
    }

}
