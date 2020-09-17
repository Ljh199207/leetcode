package com.ljh.suanfa.serach;

import java.util.Arrays;

/**
 * @author ljh KMP算法  查找字符串
 * 失配时，模式串向右移动的位数为：已匹配字符数 - 失配字符的上一位字符所对应的最大长度值
 * @date 2019-11-11 11:27
 */
public class KmpSerach {
    public static void main(String[] args) {
       /* String ps ="ABCABCBEF";
        int a[] = getNext(ps);
        QuickSort.saymsg(a);*/


        String s = "000001000001";
        String t = "00001";
        String t1 = "AAAAAAA";
        int pos = kmp(s, t);
        System.out.println("pos:" + pos);
    }

    /**
     * 寻找最长前缀后缀
     * @param ps
     * @return
     */
    public static int[] getNext(String ps) {
        char[] p = ps.toCharArray();
        int[] next = new int[p.length];
        next[0] = -1;
        int j = 0;
        int k = -1;
        while (j < p.length - 1) {
            if (k == -1 || p[k] == p[j]) {
                next[++j] = ++k;
            } else {
                k = next[k];
            }
        }
        return next;
    }

    public static int kmp(String s, String t ){
        int i = 0;
        int j = 0;
        // 分析字串
        int[] next = getNext(t);
        System.out.println(Arrays.toString(next));
        while (i<s.length()&&j<t.length()){
            if(j==-1||s.charAt(i)==t.charAt(j)){
                i++;
                j++;
            }
            else {
                j=next[j];
            }
        }
        if(j == t.length()){
            return i-j;
        } else {
            return -1;
        }
    }
}
