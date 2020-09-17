package com.ljh.suanfa.serach;

import java.util.Arrays;

/**
 * @author ljh
 * @date 2019-11-12 16:16
 */
public class BmSerach2 {

    public static int[] suffixes(char[] c) {
        int m = c.length;
        int[] suff = new int[m];
        suff[m - 1] = m;
        for (int i = m - 2; i >= 0; i--) {
            int q = i;
            while (q >= 0 && c[q] == c[m - 1 - i + q]) {
                q--;
            }
            suff[i] = i - q;
        }
        //返回后缀数据
        return suff;
    }

    public static int[] bmBc(char[] p) {
        int[] bmBc = new int[65536];
        Arrays.fill(bmBc, -1);
        int m = p.length;
        for (int i = 0; i < m; i++) {
            bmBc[p[i]] = m - 1 - i;
        }
        return bmBc;
    }


    public static void bmGs(char[] p) {
        int m = p.length;
        int[] bmGs = new int[m];
        int[] sufffix = suffixes(p);
        Arrays.fill(sufffix, m);
        for (int i = m - 1; i >= 0; i--) {
            if (sufffix[i] == i + 1) {
                for (int j = 0; j < m - 1 - i; j++) {
                    if (bmGs[j] == m) {
                        bmGs[j] = m - 1 - i;
                    }
                }

            }
        }
    }
}
