package com.ljh.data.list;

/**
 * @author ljh
 * @date 2020-08-31 16:08
 * 1,先创建第一个节点 让first 指定该节点
 * 2，后面当我们创建一个新的节点，把加入到已有环形
 * 遍历
 * 先让一辅助变量curboy指向first
 * while 循环遍历，curboy.next =first 就结束了
 */
public class Josefu {

    Node first = null;

    public void count(int num) {
        if (num < 1) {
            System.out.println("不能小于1");
            return;
        }
        Node cur = null;
        for (int i = 1; i <= num; i++) {
            Node node = new Node(i);
            if (i == 1) {
                first = node;
                first.next = first;
                cur = first;
            } else {
                cur.next = node;
                node.next = first;
                cur = node;
            }
        }
    }

    /**
     * @param starNo   表示从第几个小孩开始数数
     * @param countNum 表示数几下
     * @param nums     表示最初有多少个小孩在去圈中
     */
    public void getCount(int starNo, int countNum, int nums) {
        //对数据校验
        if (starNo < 0 || countNum < 0 || starNo > nums) {
            throw new RuntimeException("数据有误");
        }
        //创建一个辅助指针helper 帮助小孩出圈
        Node helper = first;
        // 需要创建一个辅助指针（变量）helper 事先应该指向环形链表的最后这个节点
        while (true) {
            if (helper.next == first) {//说明help指向最后一个小孩子
                break;
            }
            helper = helper.next;
        }
        //报数前 要将first与helper定位
        for (int i = 0; i < starNo - 1; i++) {
            first = first.next;
            helper = helper.next;
        }
        while (true) {
            //直到圈中只有一个点
            if (helper == first) {
                break;
            }
            for (int i = 0; i < countNum - 1; i++) {
                first = first.next;
                helper = helper.next;
            }
            System.out.printf("小孩%d出圈 \n", first.data);
            first = first.next;
            helper.next = first;
        }
        System.out.printf("最后留在圈中小孩为%d \n", first.data);
    }

    public void show() {
        if (first == null) {
            System.out.println("链表为空");
            return;
        }
        //因为first不能动 所以 我们仍然使用一个辅助指针完成遍历
        Node curBoy = first;
        while (true) {
            System.out.printf("小孩的编号%d \n", curBoy.data);
            if (curBoy.next == first) {
                break;
            }
            curBoy = curBoy.next;
        }
    }


    public static void main(String[] args) {
        Josefu josefu = new Josefu();
        josefu.count(9);
        josefu.show();


        josefu.getCount(2,3,9);
    }
}


class Node {
    public Node(int data) {
        this.data = data;
    }


    public int data;
    public Node next;
}


