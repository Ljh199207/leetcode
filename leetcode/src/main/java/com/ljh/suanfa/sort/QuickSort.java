package com.ljh.suanfa.sort;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * @author ljh 分治法  该排序方法被认为是目前最好的一种内部排序方法
 * @date 2019-11-08 15:15
 */
public class QuickSort {

    public static void main(String[] args) {
        int[] src = {49, 38, 65, 97, 76, 13, 27, 49, 78, 34, 12, 64, 5, 4, 62, 99, 98, 54, 56, 17, 18, 23, 34, 15, 35, 25, 53, 51};
        System.out.println("原始数组排序：");
        saymsg(src);
        if (src.length > 0) {
          //  quickSort(src, 0, src.length - 1);
            withStack(src, 0, src.length - 1);
        }
    }

    /**
     * 这个是挖坑法
     *
     * @param src
     * @param start
     * @param end
     * @return
     */

    public static int getMiddel(int[] src, int start, int end) {
        // 数组的第一个作为中轴
        int tmp = src[start];
        while (start < end) {
            while (start < end && src[end] >= tmp) {
                end--;
            }
            // 比中轴小的记录移到低端
            src[start] = src[end];
            saymsg(src);
            while (start < end && src[start] <= tmp) {
                start++;
            }
            // 比中轴大的记录移到高端
            src[end] = src[start];

        }
        // 中轴记录到尾
        src[start] = tmp;
        saymsg(src);
        return start;
    }

    public static void quickSort(int[] src, int start, int end) {
        if (start < end) {
            // int middle = getMiddel(src, start, end);
            int middle = partition(src, start, end);
            quickSort(src, start, middle - 1);
            quickSort(src, middle + 1, end);
        }
    }

    /**
     * 指针法
     *
     * @param src
     * @param start
     * @param end
     * @return
     */

    public static int partition(int[] src, int start, int end) {
        // 数组的第一个作为中轴
        int tmp = src[start];
        int left = start;
        int right = end;
        while (left != right) {
            while (left < right && src[right] >= tmp) {
                right--;
            }
            while (left < right && src[left] <= tmp) {
                left++;
            }
            int t = src[right];
            src[right] = src[left];
            src[left] = t;
        }
        // 中轴记录到尾
        int p = src[start];
        src[start] = src[left];
        src[left] = p;
        saymsg(src);
        return left;
    }

    /**
     * 栈
     *
     * @param src
     * @param start
     * @param end
     * @return
     */

    public static int withStack(int[] src, int start, int end) {
        // 用一个集合栈来代替递归的函数栈
        Stack<Map<String, Integer>> quickWithStack = new Stack<Map<String, Integer>>();
        //整个入栈
        Map map = new HashMap();
        map.put("startIndex", start);
        map.put("endIndex", end);
        quickWithStack.push(map);
        while (!quickWithStack.isEmpty()) {
            // 栈顶元素出栈，得到起止下标
            Map<String, Integer> param = quickWithStack.pop();
            int pivotIndex = partition(src, param.get("startIndex"), param.get("endIndex"));
            // 根据基准元素分成两部分, 把每一部分的起止下标入栈
            if (param.get("startIndex") < pivotIndex - 1) {
                Map<String, Integer> leftParam = new HashMap<>();
                leftParam.put("startIndex", param.get("startIndex"));
                leftParam.put("endIndex", pivotIndex - 1);
                quickWithStack.push(leftParam);
            }
            if (param.get("endIndex") > pivotIndex + 1) {
                Map<String, Integer> rightParam = new HashMap<>();
                rightParam.put("startIndex", pivotIndex + 1);
                rightParam.put("endIndex", param.get("endIndex"));
                quickWithStack.push(rightParam);
            }
        }


        return 0;
    }


    /**
     * 打印数组内容
     *
     * @param src
     */
    public static void saymsg(int[] src) {
        for (int i = 0; i < src.length; i++) {
            System.out.print(src[i]);
            System.out.print("  ");
        }
        System.out.println();
    }
}
