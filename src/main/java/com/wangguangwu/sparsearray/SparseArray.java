package com.wangguangwu.sparsearray;


/**
 * 稀疏数组
 * <p>
 * 使用背景：
 * 当一个数组中大部分元素为０，或者为同一个值的数组时，可以使用稀疏数组来保存该数组。
 * <p>
 * 处理方法：
 * 1) 记录数组一共有几行几列，有多少个不同的值
 * 2) 把具有不同值的元素的行列及值记录在一个小规模的数组中，从而缩小程序的规模
 * <p>
 * 使用实例：
 * 用来保存棋盘、地图等
 *
 * @author wangguangwu
 */
public class SparseArray {

    private static final int SIZE = 11;

    private static final int[][] CHESS_ARRAY = new int[11][11];

    public static void main(String[] args) {
        initChessArray();
        printChessArray(CHESS_ARRAY);
        // 将二维数组转为稀疏数组
        // 1. 遍历二维数组，得到其中非 0 数据的个数
        int sum = 0;
        for (int[] row : CHESS_ARRAY) {
            for (int data : row) {
                if (data != 0) {
                    sum++;
                }
            }
        }

        // 2. 创建对应的稀疏数组
        int[][] sparseArray = new int[sum + 1][3];
        // 给稀疏数组赋值
        sparseArray[0][0] = 11;
        sparseArray[0][1] = 11;
        sparseArray[0][2] = sum;

        // 3. 遍历二维数组，将非 0 的值存到稀疏数组中
        // count 记录是第几个非 0 数据
        int count = 0;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (CHESS_ARRAY[i][j] != 0) {
                    count++;
                    sparseArray[count][0] = i;
                    sparseArray[count][1] = j;
                    sparseArray[count][2] = CHESS_ARRAY[i][j];
                }
            }
        }

        // 打印稀疏数组
        printSparseArray(sparseArray);

        // 将稀疏数组转为二维数组
        // 1. 先读取稀疏数组的第一行，根据第一行的数据，创建原始的二维数组
        int[][] chessArray = new int[sparseArray[0][0]][sparseArray[0][1]];

        // 2. 在读取稀疏数组后几行的数据，并赋给 原始的二维数组 即可.
        for (int i = 1; i < sparseArray.length; i++) {
            chessArray[sparseArray[i][0]][sparseArray[i][1]] = sparseArray[i][2];
        }
        printChessArray(chessArray);

    }

    private static void printSparseArray(int[][] sparseArray) {
        System.out.println("==================稀疏数组==================");
        for (int[] ints : sparseArray) {
            System.out.printf("%d\t%d\t%d\t\n", ints[0], ints[1], ints[2]);
        }
        System.out.println();
    }

    private static void printChessArray(int[][] chessArray) {
        System.out.println("==================二维数组==================");
        for (int[] row : chessArray) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }
        System.out.println();
    }

    private static void initChessArray() {
        // 0: 表示没有棋子， 1 表示 黑子 2 表示白子
        CHESS_ARRAY[1][2] = 1;
        CHESS_ARRAY[2][3] = 2;
        CHESS_ARRAY[3][4] = 2;
    }

}
