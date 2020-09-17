package com.ljh.suanfa.sort;

/**
 * @author ljh
 * @date 2020-09-14 17:25
 * 归并排序
 */
public class MergeSort {

    public static void main(String[] args) {

    /*    int[] sort = {5, 8, 3, 2, 6, 9};
        mergeSort(sort, 0, sort.length - 1);

        QuickSort.saymsg(sort);*/

        int src[] = new int[8000000];
        for (int i = 0; i < 8000000; i++) {
            src[i]= (int) (Math.random()*800000);
        }
        int[] tmp = new int[src.length]; //中间数组，用来排序数组
        long start = System.currentTimeMillis();
        mergeSort(src,0, src.length - 1,tmp);
        long end = System.currentTimeMillis();
        System.out.println("用时："+(end -start));
       // QuickSort.saymsg(src);

    }

    public static void mergeSort(int[] src, int start, int end,int[] temp) {
        if (start < end) {
            int middle = (start + end)/2;
            mergeSort(src, start, middle,temp);
            mergeSort(src, middle + 1, end,temp);
            merge(src, start, middle, end,temp);
        }
    }

    /**
     * 4 5
     *
     * @param src    原数组
     * @param start  数组合并开始的下标
     * @param middle 中轴
     * @param end    结束下标
     *               1
     */
    private static void merge(int[] src, int start, int middle, int end,int[] tmp) {
        int i = start; //左指针
        int j = middle + 1;//右指针
        int t = 0;//临时数组指针
        while (i <= middle && j <= end) {
            if (src[i] < src[j]) {
                tmp[t++] = src[i++];
            } else {
                tmp[t++] = src[j++];
            }
        }
        //左边没合完
        while (i <= middle) {
            tmp[t++] = src[i++];
        }
        while (j <= end) {
            tmp[t++] = src[j++];
        }
        t = 0;
        while (start <= end) {
            src[start++] = tmp[t++];
        }

    }
}
