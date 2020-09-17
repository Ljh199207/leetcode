package com.ljh.simple;

/**
 * 移除元素  数组是无序的
 */
public class solution27 {

    public static int removerArray(int[] a,int val){
        int i =0;
        for(int j =0;j<a.length;j++){
            if(a[j]!=val){
                a[i]=a[j];
                i++;
            }
        }
        return  i+1;
    }

    public static void main(String[] args) {
        int a[] ={0,0,0,1,1,2,3,5,};
        int i = removerArray(a, 1);
        for (int j = 0; j <i ; j++) {
            System.out.println(a[j]);
        }

        System.out.println(Integer.toBinaryString(8888));
        System.out.println(Integer.toBinaryString(8887));
    }
}
