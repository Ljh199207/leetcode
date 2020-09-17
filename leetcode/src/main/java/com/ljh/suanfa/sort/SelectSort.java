package com.ljh.suanfa.sort;

/**
 * @author ljh 选择排序
 * @date 2019-11-11 09:34
 */
public class SelectSort {

    public static void main(String[] args) {
        int[] src = { 49, 38, 65, 97, 76, 13, 27, 49, 78, 34, 12, 64, 5, 4, 62, 99, 98, 54, 56, 17, 18, 23, 34, 15, 35,
                25, 53, 51 };
        System.out.println("原始数组排序：");
        selectSort(src);
    }

    public static void selectSort(int[] src) {
        int index = 0;
        for (int i = 0; i < src.length; i++) {
            int low = src[i];
             index =i;
            for (int j = i + 1; j < src.length; j++) {
                if(src[j]<low){
                    low = src[j];
                    index=j;
                }
            }
            src[index] = src[i];
            src[i] = low;
            QuickSort.saymsg(src);
        }
    }
}
