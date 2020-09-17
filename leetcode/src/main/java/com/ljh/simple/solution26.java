package com.ljh.simple;

/**
 *  合并排序数组的重复项
 */
public class solution26 {

    public static  int  meragerArry(int[] a ){
        int i = 0;
        for(int j =1;j<a.length;j++){
            if(a[i]!=a[j]){
                i++;
                a[i]=a[j];
            }
        }
        return i+1;
    }

    public static void main(String[] args) {

        int[] a ={0,0,0,1,1,2,2,3,4};
        int ints = meragerArry(a);
        for(int i =0;i<ints;i++){
            System.out.println(a[i]);
        }
    }

}
