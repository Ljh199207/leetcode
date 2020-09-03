package com.ljh.data.list;


/**
 * @author ljh
 * @date 2020-08-25 08:41
 * 单链表 //节点类
 */
public class ListNode {

    //链表数据
    public int data;
    public String name;

    //当前链表指向下一个节点
    public ListNode next;


    public ListNode(int data, String name) {
        this.data = data;
        this.name = name;
    }

    public ListNode() {

    }

    @Override
    public String toString() {
        return "ListNode{" +
                "data=" + data +
                ", name='" + name + '\'' +
                '}';
    }
}
