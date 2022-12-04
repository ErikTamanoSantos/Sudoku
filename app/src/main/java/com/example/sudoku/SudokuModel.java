package com.example.sudoku;

import java.util.ArrayList;

public class SudokuModel {
    static int[][] matrix = new int[9][9];

    public static int getVal(int row, int col) {
        return matrix[row][col];
    }

    public static int setVal(int row, int col, int value) {
        try {
            matrix[row][col] = value;
            if (value != 0 && (!comprovaFila(row) || !comprovaCol(col) || comprovaQuad(row, col))) {
                return -1;
            }
            return value;
        } catch (Exception e) {
            return -1;
        }
    }

    public static boolean comprovaFila(int row) {
        ArrayList<Integer> vals = new ArrayList<Integer>();
        for (int col = 0; col < 9; col++) {
            if (vals.contains(matrix[row][col])) {
                return false;
            }
            vals.add(matrix[row][col]);
        }
        return true;
    }

    public static boolean comprovaCol(int col) {
        ArrayList<Integer> vals = new ArrayList<Integer>();
        for (int row = 0; row < 9; row++) {
            if (vals.contains(matrix[row][col])) {
                return false;
            }
            vals.add(matrix[row][col]);
        }
        return true;
    }

    public static boolean comprovaQuad(int row, int col) {
        ArrayList<Integer> vals = new ArrayList<Integer>();
        int quad_row = row/3;
        int quad_col = col/3;
        for (int i = quad_row; i < quad_row + 3; i++) {
            for (int j = quad_col; j < quad_col + 3; j++) {
                if (vals.contains(matrix[i][j])) {
                    return false;
                }
                vals.add(matrix[i][j]);
            }
        }
        return true;
    }

    public static void creaPartida() {
        int fixedValues = (int) (Math.random() * 40) +1;
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 0; col++) {
                if ((int) Math.random() == 1 && fixedValues > 0) {
                    int correctVal = 0;
                    while (true) {
                        correctVal = setVal(row, col, (int) (Math.random() * 8) + 1);
                        if (correctVal != -1) {
                            break;
                        }
                    }
                    fixedValues--;
                } else {
                    setVal(row, col, 0);
                }
            }
        }
    }

}
