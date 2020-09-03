package com.ljh.data.stack;

import com.ljh.data.list.ListNode;

/**
 * @author ljh
 * @date 2020-08-31 15:10
 */
public class LinkedStackApp {
    public static void main(String[] args) {
        ListNode listNode1 = new ListNode(8, "张三");
        ListNode listNode2 = new ListNode(2, "李四");
        ListNode listNode3 = new ListNode(6, "王五");
        ListNode listNode4 = new ListNode(4, "孙六");
        ListNode listNode5 = new ListNode(10, "钱七");

        LinkedStack linkedStack = new LinkedStack();

        linkedStack.add(listNode1);
        linkedStack.add(listNode2);
        linkedStack.add(listNode3);
        linkedStack.add(listNode4);
        linkedStack.add(listNode5);


        System.out.println(linkedStack.pop());
        System.out.println(linkedStack.pop());
        System.out.println(linkedStack.pop());
        System.out.println(linkedStack.pop());

        linkedStack.add(listNode4);
        linkedStack.add(listNode5);
        linkedStack.list();
    }
}
