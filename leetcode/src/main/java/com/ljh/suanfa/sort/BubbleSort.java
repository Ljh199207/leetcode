package com.ljh.suanfa.sort;

/**
 * @author ljh
 * @date 2019-11-08 15:01
 */
public class BubbleSort {

    public static void main(String[] args) {
        int[] src =new int[80000];

        for (int i = 0; i < 80000; i++) {
            src[i]= (int) (Math.random()*100);
        }
       long start = System.currentTimeMillis();
        System.out.println("原始数组排序：");
      //  saymsg(src);
        bubleSort(src);
        long end = System.currentTimeMillis();
        System.out.println("用时："+(end -start));
        saymsg(src);
    }

    public static void bubleSort(int[] src) {

        int temp = 0;
        for (int i = 0; i < src.length - 1; i++) {
            for (int j = 0; j < src.length - 1 - i; j++) {
                if (src[j] > src[j + 1]) {
                    temp = src[j];
                    src[j] = src[j + 1];
                    src[j + 1] = temp;
                }
            }
           // saymsg(src);
        }
       // saymsg(src);
    }


    //冒泡的优化
    public static void bubleSort2(int[] src) {
        boolean flag = false;
        int temp = 0;
        for (int i = 0; i < src.length - 1; i++) {
            for (int j = 0; j < src.length - 1 - i; j++) {
                if (src[j] > src[j + 1]) {
                    flag = true;
                    temp = src[j];
                    src[j] = src[j + 1];
                    src[j + 1] = temp;
                }
            }
            saymsg(src);
            //只要发生过一次没有交换过，就可以认为他是有序的
            if (!flag) {
                break;
            } else {
                flag = false;
            }
        }
        saymsg(src);
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
