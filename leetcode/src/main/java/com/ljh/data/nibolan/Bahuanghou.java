package com.ljh.data.nibolan;

/**
 * @author ljh
 * @date 2020-09-07 17:02
 * 八皇后问题
 * 任意两个皇后都不能处于同一行 ，同一列或同一斜线上
 */
public class Bahuanghou {

    static int src[] = new int[8];

    public static void main(String[] args) {
        Setting(0);
    }


    public static void Setting(int n) {
        if (n == 8) {
            print();
            return;
        }

        for (int i = 0; i < 8; i++) {
            src[n] = i;
            if (jugue(n)) {
                Setting(n + 1);
            }
        }

    }

    public static boolean jugue(int n) {
        for (int i = 0; i < n; i++) {
            if (src[i] == src[n] || Math.abs(n - i) == Math.abs(src[n] - src[i])) {
                return false;
            }
        }
        return true;
    }
    private static void print()  {
        for (int i = 0; i < src.length; i++) {
            System.out.print(src[i] + 1 + " ");
        }
        System.out.println();
    }

}
