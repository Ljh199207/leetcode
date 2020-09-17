package com.ljh.suanfa.sort;

/**
 * 插入排序
 *
 * @author ljh
 * @date 2019-11-08 16:08
 */
public class InsertSort {


    public static void main(String[] args) {
        int[] src = { 49, 38, 65, 97, 76, 13, 27, 49, 78, 34, 12, 64, 5, 4, 62, 99, 98, 54, 56, 17, 18, 23, 34, 15, 35,
                25, 53, 51 };
        System.out.println("原始数组排序：");
        BubbleSort.saymsg(src);
        insertSort(src);
    }

    public static void insertSort(int[] src) {
       int tem  = 0;
       for(int i=1;i<src.length-1;i++){
           int j = i - 1;
           tem = src[i];
           for (;j >= 0 && tem < src[j]; j--){
               src[j+1]=src[j];
           }
           src[j+1] = tem;
           System.out.println("第"+i+"次排序：");
           BubbleSort.saymsg(src);
       }
        BubbleSort.saymsg(src);
    }

}
