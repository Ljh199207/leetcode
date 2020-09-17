package com.ljh.suanfa.sort;

/**
 * @author ljh 希尔排序
 * @date 2019-11-08 16:41
 */
public class ShellSort {
    public static void main(String[] args) {
      /*  int[] src = {49, 38, 65, 97, 76, 13, 27, 49, 78, 34, 12, 64, 5, 4, 62, 99, 98, 54, 56, 17, 18, 23, 34, 15, 35,
                25, 53, 51};
        System.out.println("原始数组排序：");
        shellSort2(src);
        QuickSort.saymsg(src);*/
        int src[] = new int[80000];
        for (int i = 0; i < 80000; i++) {
            src[i] = (int) (Math.random() * 80000);
        }
        long start = System.currentTimeMillis();
        shellSort(src);
        long end = System.currentTimeMillis();
        System.out.println("用时：" + (end - start));
        QuickSort.saymsg(src);
    }

    public static void shellSort(int[] src) {
        double d1 = src.length;
        int temp = 0;
        int index = 1;
        while (true) {
            d1 = Math.ceil(d1 / 2);
            int d = (int) d1;
            for (int x = 0; x < d; x++) {
                for (int i = x + d; i < src.length; i += d) {
                    int j = i - d;
                    temp = src[i];
                    for (; j >= 0 && temp < src[j]; j -= d) {
                        src[j + d] = src[j];
                    }
                    src[j + d] = temp;
                }
            }
            if (d == 1) {
                break;
            }
        }
    }

    //交换法
    public static void shellSort2(int[] src) {
        int temp = 0;
        for (int d = src.length / 2; d > 0; d /= 2) {
            for (int i = d; i < src.length; i++) {
                for (int j = i - d; j >= 0; j -= d) {
                    if (src[j] > src[j + d]) {
                        temp = src[j + d];
                        src[j + d] = src[j];
                        src[j] = temp;
                    }
                }
            }
        }
    }

    //移位法
    public static void shellSort3(int[] src) {
        int temp = 0;
        for (int d = src.length / 2; d > 0; d /= 2) {
            //从d开始 逐个对其组进行插入排序
            for (int i = d; i <src.length ; i++) {

            }
        }
    }

}
