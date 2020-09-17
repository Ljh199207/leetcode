package com.ljh.middling;

import java.util.ArrayList;
import java.util.List;

public class solution22 {


    public static char[] reverseString(char[] s) {
        swap(0, s.length, s);
        return s;
    }

    public static void swap(int start, int end, char[] s) {
        if (start >= end) {
            return;
        }
        char temp = s[start];
        s[start] = s[end];
        s[end] = temp;
        swap(start + 1, end - 1, s);
    }


    public ListNode swapPairs(ListNode head) {
        if (head == null) {
            return null;
        }
        if (head.next == null) {
            return head;
        }
        ListNode nnext = head.next.next;
        ListNode tmp = head;
        head = head.next;
        head.next = tmp;
        head.next.next = swapPairs(nnext);
        return head;
    }

    public static List<List<Integer>> generate(int numRows) {
        List<List<Integer>> lists = new ArrayList<>();
        for (int i = 1; i <= numRows; i++) {
            List<Integer> list = new ArrayList<>();
            for (int j = 1; j <= i; j++) {
                list.add(fun(i, j));
            }
            lists.add(list);
        }
        return lists;
    }

    //动态规划
    public static List<List<Integer>> generate2(int numRows) {
        List<List<Integer>> lists = new ArrayList<>();
        if (numRows == 0) {
            return lists;
        }
        lists.add(new ArrayList<>());
        //第一列都是1
        lists.get(0).add(1);
        for (int i = 1; i < numRows; i++) {
            List<Integer> row = new ArrayList<>();
            List<Integer> pre = lists.get(i - 1);
            row.add(1);
            for (int j = 1; j < i; j++) {
                row.add(pre.get(j - 1) + pre.get(j));
            }
            row.add(1);
            lists.add(row);
        }
        return lists;

    }

    //i 行 j列
    public static int fun(int i, int j) {
        if (i == j || j == 1) {
            return 1;
        } else {
            return fun(i - 1, j) + fun(i - 1, j - 1);
        }
    }

    public static void main(String[] args) {
        System.out.println(System.currentTimeMillis());
        List<List<Integer>> list = generate2(30);
        System.out.println(System.currentTimeMillis());
    }
}
