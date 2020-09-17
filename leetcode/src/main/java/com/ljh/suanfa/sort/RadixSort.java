package com.ljh.suanfa.sort;

/**
 * @author ljh
 * @date 2020-09-17 09:36
 * 基数排序
 */
public class RadixSort {

    public static void main(String[] args) {
       /* int[] src = {49, 38, 65, 97, 76, 13, 27, 49, 78, 34, 12, 64, 5, 4, 62, 99, 98, 54, 56, 17, 18, 23, 34, 15, 35,
                25, 53, 51};
        System.out.println("原始数组排序：");
        radixSort(src);
        QuickSort.saymsg(src);*/

        int[] src = new int[8000000];
        for (int i = 0; i < 8000000; i++) {
            src[i] = (int) (Math.random() * 8000000);
        }
        int[] tmp = new int[src.length]; //中间数组，用来排序数组
        long start = System.currentTimeMillis();
        radixSort(src);
        long end = System.currentTimeMillis();
        System.out.println("用时：" + (end - start));
        //QuickSort.saymsg(src);
    }


    public static void radixSort(int[] src) {
        //1.选出高位数
        int max = src[0];
        for (int i = 0; i < src.length; i++) {
            if (max < src[i]) {
                max = src[i];
            }
        }
        //2.高位数的长度
        int length = (max + "").length();

        //3.循环
        for (int i = 0, n = 1; i < length; i++, n *= 10) {
            //设计桶
            int radix[][] = new int[10][src.length];
            //每个桶的索引指针
            int tem[] = new int[10];
            for (int j = 0; j < src.length; j++) {
                int result = src[j] / n % 10;
                radix[result][tem[result]++] = src[j];
            }
            int pos = 0;
            for (int k = 0; k < 10; k++) {
                for (int t = 0; t < tem[k]; t++) {
                    src[pos++] = radix[k][t];
                }

            }
        }
    }
}

