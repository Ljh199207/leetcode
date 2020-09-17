package com.ljh.simple;

/**
 * 回文数
 * 判断一个整数是否是回文数，回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数
 * 121 true   -121 false
 */
public class solution9 {

     public static boolean isParbmloon(int num){
         if(num<0){
             return false;
         }
         int rev =0;
         int a = num;
         while (num!=0){
             int pop = num%10;
             num /=10;

             rev = rev*10+pop;
         }
         if(rev==a){
             return true;
         }
         return false;
     }

    public static void main(String[] args) {
        int s = 0;
        System.out.println(isParbmloon(s));
    }
}
