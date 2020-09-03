package com.ljh.data.array;

/**
 * @author ljh
 * @date 2020-08-18 08:40
 * 稀疏数组的实现
 */
public class SparseArray {

    public static void main(String[] args) {
        //创建一个原始的二维数组 11*11
        //0：表示没有棋子，1表示黑子，2表示蓝子
        int chessArr1[][] = new int[11][11];
        chessArr1[1][2] = 1;
        chessArr1[2][3] = 2;
        chessArr1[2][2] = 1;
        chessArr1[3][2] = 2;
        chessArr1[3][1] = 1;
        chessArr1[1][3] = 2;
        //输出原始的二维数组
        System.out.println("原始的二维数组：");
        for (int[] row : chessArr1) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }

        //将二维数组转成稀疏数组
        //1、遍历原始的二维数组，得到有效数组的个数sum
        int sum = 0;
        for (int i = 0; i < chessArr1.length; i++) {
            for (int j = 0; j < chessArr1.length; j++) {
                if (chessArr1[i][j] != 0) {
                    sum++;
                }
            }
        }

        int sparse[][] = new int[sum + 1][3];
        sparse[0][0] = 11;
        sparse[0][1] = 11;
        sparse[0][2] = sum;
        int count = 0;//用于记录是第几个非零数据
        for (int i = 0; i < chessArr1.length; i++) {
            for (int j = 0; j < chessArr1.length; j++) {
                if (chessArr1[i][j] != 0) {
                    count++;
                    sparse[count][0] = i;
                    sparse[count][1] = j;
                    sparse[count][2] = chessArr1[i][j];
                }
            }
        }

        //输出稀疏数组
        System.out.println();
        System.out.println("得到的稀疏数组为：");
        for (int i = 0; i < sparse.length; i++) {
            System.out.printf("%d\t%d\t%d\t\n", sparse[i][0], sparse[i][1], sparse[i][2]);
        }
        System.out.println();

        //稀疏数组转成二维数组
        int chessArr2[][] = new int[sparse[0][0]][sparse[0][1]];
        for (int i = 1; i < sparse.length; i++) {
            chessArr2[sparse[i][0]][sparse[i][1]] = sparse[i][2];
        }

        System.out.println("输出恢复后的二维数组：");
        for (int[] row : chessArr2) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }

    }

}
