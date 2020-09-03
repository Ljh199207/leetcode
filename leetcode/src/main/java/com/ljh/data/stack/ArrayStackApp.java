package com.ljh.data.stack;

/**
 * @author ljh
 * @date 2020-08-31 14:41
 */
public class ArrayStackApp {

    public static void main(String[] args) throws Exception {
        ArrayStack arrayStack = new ArrayStack(10);
        Node node = new Node(2);
        Node node1 = new Node(3);
        Node node2 = new Node(4);
        Node node3 = new Node(5);
        Node node4 = new Node(6);
        Node node5 = new Node(7);

        arrayStack.add(node);
        arrayStack.add(node1);
        arrayStack.add(node2);
        arrayStack.add(node4);
        arrayStack.add(node3);
        arrayStack.add(node5);

        arrayStack.list();

    }
}
