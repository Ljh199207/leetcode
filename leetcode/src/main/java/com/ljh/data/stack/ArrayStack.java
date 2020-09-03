package com.ljh.data.stack;

/**
 * @author ljh
 * @date 2020-08-31 14:38
 * 数组实现的栈
 */
public class ArrayStack {

    private int macSize;//栈的大小
    private int[] elementData;
    private int top = -1;//top表示栈顶，初始化为-1

    public ArrayStack(int macSize) {
        this.macSize = macSize;
        elementData = new int[macSize];
    }

    //判断是不是栈满
    public boolean isFull() {
        return top == macSize - 1;
    }

    //判断是不是为空
    public boolean isEmpty() {
        return top == -1;
    }

    //添加
    public void add(Node node) {
        if (isFull()) {
            System.out.println("栈满");
            return;
        }
        top++;
        elementData[top] = node.data;
    }

    //取出
    public Integer pop() throws Exception {
        if (isEmpty()) {
            throw new RuntimeException("栈为空");
        }
        int value = elementData[top];
        top--;
        return value;
    }

    //查询栈顶的元素
    public Integer peek() throws Exception {
        if (isEmpty()) {
            throw new RuntimeException("栈为空");
        }
        int value = elementData[top];
        return value;
    }

    public void list() {
        if (isEmpty()) {
            throw new RuntimeException("栈为空");
        }
        for (int i = top; i >= 0; i--) {
            System.out.println("出栈：" + elementData[i]);
        }
    }
}

class Node {
    public Node(int data) {
        this.data = data;
    }

    int data;
}
