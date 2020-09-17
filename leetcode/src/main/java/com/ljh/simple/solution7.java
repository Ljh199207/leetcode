package com.ljh.simple;

/**
 * 反转整数
 * 给定一个32位有符号整数，将整数中的数字进行反转
 */

public class solution7 {

    public static int reversInt(int num) {

        int rev =0;
        while (num!=0){
            int pop = num%10;
            num /=10;
            if(rev>Integer.MAX_VALUE/10||(rev==Integer.MAX_VALUE/10&&pop>7)){
                return 0;
            }
            if(rev<Integer.MIN_VALUE/10||(rev==Integer.MIN_VALUE/10&&pop<-8)){
                return 0;
            }
            rev = rev*10+pop;
        }
        return rev;
    }


    public static void main(String[] args) {
        int num = 32435789;

        System.out.println(reversInt(num));
    }
}
