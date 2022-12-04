package com.example.sudoku;

import java.util.ArrayList;

public class SudokuModel {
    static int[][] matrix = new int[9][9];

    public static int getVal(int row, int col) {
        return matrix[row][col];
    }

    public static int setVal(int row, int col, int value) {
        int ogValue = getVal(row, col);
        matrix[row][col] = value;
        if (value != 0 && (!comprovaFila(row) || !comprovaCol(col) || !comprovaQuad(row, col))) {
            matrix[row][col] = ogValue;
            return -1;
        }
        return value;
    }

    public static boolean comprovaFila(int row) {
        ArrayList<Integer> vals = new ArrayList<Integer>();
        for (int col = 0; col < 9; col++) {
            if(matrix[row][col] != 0) {
                if (vals.contains(matrix[row][col])) {
                    return false;
                }
                vals.add(matrix[row][col]);
            }
        }
        return true;
    }

    public static boolean comprovaCol(int col) {
        ArrayList<Integer> vals = new ArrayList<Integer>();
        for (int row = 0; row < 9; row++) {
            if(matrix[row][col] != 0) {
                if (vals.contains(matrix[row][col])) {
                    return false;
                }
                vals.add(matrix[row][col]);
            }
        }
        return true;
    }

    public static boolean comprovaQuad(int row, int col) {
        ArrayList<Integer> vals = new ArrayList<Integer>();
        int row_min;
        int row_max;
        int col_min;
        int col_max;
        if (row < 3) {
            row_min = 0;
            row_max = 3;
        } else if (row < 6) {
            row_min = 3;
            row_max = 6;
        } else {
            row_min = 6;
            row_max = 9;
        }
        if (col < 3) {
            col_min = 0;
            col_max = 3;
        } else if (col < 6) {
            col_min = 3;
            col_max = 6;
        } else {
            col_min = 6;
            col_max = 9;
        }
        for (int i = row_min; i < row_max; i++) {
            for (int j = col_min; j < col_max; j++) {
                if(matrix[i][j] != 0) {
                    if (vals.contains(matrix[i][j])) {
                        return false;
                    }
                    vals.add(matrix[i][j]);
                }
            }
        }
        return true;
    }

    public static void creaPartida() {
        for (int row = 0; row < 9; row ++) {
            for (int col = 0; col < 9; col++) {
                setVal(row, col, 0);
            }
        }
        int fixedValues = (int) (Math.random() * 20) + 30;
        while (fixedValues > 0) {
            int row = (int) (Math.random() * 9);
            int col = (int) (Math.random() *9);
            if (getVal(row, col) == 0) {
                setVal(row, col, (int) (Math.random() * 8) + 1);
                fixedValues--;
            }
        }
    }

    public static boolean checkCompleted() {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (getVal(row, col) == 0) {
                    return false;
                }
            }
        }
        return true;
    }
}
