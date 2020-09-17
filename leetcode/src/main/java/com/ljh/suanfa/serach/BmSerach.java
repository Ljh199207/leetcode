package com.ljh.suanfa.serach;

/**
 * @author ljh BM 算法
 * @date 2019-11-12 08:22
 */
public class BmSerach {

    public static void main(String[] args) {
        String s = "faabc";
        String t = "abc";
        System.out.println("==========:" + index(s, t));
    }

    /**
     * @param c 主串（源串）中的字符
     * @param T 模式串（目标串）字符数组
     * @return 滑动距离
     */

    public static int dist(char c, char[] T) {
        int n = T.length;
        if (c == T[n - 1]) {
            return n;
        }
        for (int i = n; i > 0; i--) {
            if (T[i - 1] == c) {
                // i=max{i|t[i-1]且0<=i<=n-2
                return n - i;
            }
        }
        // c不出现在模式中时
        return n;
    }

    public static int index(String ps, String pt) {
        if (ps == null || pt == null) {
            return -2;
        }
        char[] s = ps.toCharArray();
        char[] t = pt.toCharArray();
        int sLen = s.length;
        int tLen = t.length;
        if (sLen < tLen) {
            return -1;
        }
        int i = tLen, j;
        while (i <= sLen) {
            j = tLen;
            // S[i-1]与T[j-1]若匹配，则进行下一组比较；反之离开循环。
            while (j > 0 && s[i - 1] == t[j - 1]) {
                i--;
                j--;
            }
            // j=0时，表示完美匹配，返回其开始匹配的位置
            if (0 == j) {
                return i;
            } else {
                // 把主串和模式串均向右滑动一段距离dist(s[i-1]).即跳过dist(s[i-1])个字符无需比较
                i = i + dist(s[i - 1], t);
            }
        }
        // 模式串与主串无法匹配
        return -1;
    }
}
