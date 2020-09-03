package com.ljh.data.list;

/**
 * @author ljh
 * @date 2020-08-25 09:24
 */
public class App {

    public static void main(String[] args) {
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        ListNode listNode1 = new ListNode(1, "张三");
        ListNode listNode2 = new ListNode(7, "李四");
        ListNode listNode3 = new ListNode(3, "王五");
        ListNode listNode4 = new ListNode(9, "孙六");
        ListNode listNode5 = new ListNode(5, "钱七");
/*

        singleLinkedList.add(listNode4);
        singleLinkedList.add(listNode2);
        singleLinkedList.add(listNode3);
        singleLinkedList.add(listNode1);
        singleLinkedList.add(listNode5);
*/

        //加入按顺序
        singleLinkedList.addByOrder(listNode4);
        singleLinkedList.addByOrder(listNode2);
        singleLinkedList.addByOrder(listNode3);
        singleLinkedList.addByOrder(listNode1);
        singleLinkedList.addByOrder(listNode5);

        ListNode listNode6 = new ListNode(4, "王八");
        singleLinkedList.update(listNode6);
     /*   System.out.println("----前");
        singleLinkedList.list();
        System.out.println("----后");*/
        //singleLinkedList.delete(7);
        singleLinkedList.list();


        //第一个题  链表删除倒数第k个数据
       /* System.out.println("----后");
        singleLinkedList.deleteByK(6);
        singleLinkedList.list();*/


      /*  System.out.println("----后");
        ListNode keyIntK = singleLinkedList.findKeyIntK(3);
        System.out.println("---" + keyIntK.toString());
        singleLinkedList.list();*/
/*
        System.out.println("----后");
        singleLinkedList.reversNode(singleLinkedList.headNode);
        singleLinkedList.list();*/

      /*  System.out.println("----后");
        ListNode listNode = singleLinkedList.reversNode2(singleLinkedList.headNode.next);
        singleLinkedList.list(listNode);*/

        SingleLinkedList singleLinkedList2 = new SingleLinkedList();

        ListNode listNode7 = new ListNode(8, "张三");
        ListNode listNode8 = new ListNode(2, "李四");
        ListNode listNode9 = new ListNode(6, "王五");
        ListNode listNode10 = new ListNode(4, "孙六");
        ListNode listNode11 = new ListNode(10, "钱七");

        singleLinkedList2.addByOrder(listNode7);
        singleLinkedList2.addByOrder(listNode8);
        singleLinkedList2.addByOrder(listNode9);
        singleLinkedList2.addByOrder(listNode10);
        singleLinkedList2.addByOrder(listNode11);
        System.out.println("合并后的");
       // ListNode merge = singleLinkedList.merge(singleLinkedList2.headNode.next, singleLinkedList.headNode.next);
         ListNode merge = singleLinkedList.merge2(singleLinkedList2.headNode.next, singleLinkedList.headNode.next);
        singleLinkedList.list(merge);
    }
}
