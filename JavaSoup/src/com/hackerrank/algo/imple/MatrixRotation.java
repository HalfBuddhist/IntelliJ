package com.hackerrank.algo.imple;

import com.lqw.common.WebPath;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class MatrixRotation {

    public static int newrow;
    public static int newcolumn;

    public static int position2number(int row, int col, int endrow, int endcol) throws Exception {
        if (col == 0)
            return row;
        else if (row == endrow)
            return col + endrow;
        else if (col == endcol)
            return endrow + endcol + (endrow - row);
        else if (row == 0)
            return 2 * endrow + +endcol + (endcol - col);
        else {
            throw new Exception("wrong row and col.");
        }
    }

    public static int[] number2position(int number, int endrow, int endcol) throws Exception {
        int[] pos = new int[2];
        if (number >= (2 * endcol + 2 * endrow)) {
            throw new Exception("too large number.");
        } else if (number > (2 * endrow + endcol)) {
            pos[0] = 0;
            pos[1] = endcol - (number - (2 * endrow + endcol));
        } else if (number > endrow + endcol) {
            pos[0] = endrow - (number - endcol - endrow);
            pos[1] = endcol;
        } else if (number > endrow) {
            pos[0] = endrow;
            pos[1] = number - endrow;
        } else if (number >= 0) {
            pos[0] = number;
            pos[1] = 0;
        } else {
            throw new Exception("negtive number");
        }
        return pos;
    }

    public static void updateRowColumn(int r, int wrect, int m, int n, int row, int col) throws Exception {
        //translate to number
        int number = position2number(row - wrect, col - wrect, m - 1 - 2*wrect, n - 1 - 2*wrect);

        //number mod arith
        int mod = 2 * (m - 1 - 2 * wrect) + 2 * (n - 1 - 2 * wrect);
        int afternumber = (number + r) % mod;

        //number to row and col
        int pos[] = number2position(afternumber, m - 1 - 2*wrect, n - 1 - 2*wrect);
        newrow = pos[0] + wrect;
        newcolumn = pos[1] + wrect;
    }

    public static void main(String[] argv) throws Exception {
        Scanner scanner = new Scanner(new File(WebPath.getAbsolutePathWithClass("/com/hackerrank/algo/imple/input.txt").getPath()));
//                Scanner scanner = new Scanner(new BufferedInputStream(System.in));
        int m = scanner.nextInt();
        int n = scanner.nextInt();
        int r = scanner.nextInt();
        int[][] a = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int temp = scanner.nextInt();
                int c = m - i - 1 > i ? i : m - i - 1;
                int b = n - j - 1 > j ? j : n - j - 1;
                int wrect = c > b ? b : c;

                updateRowColumn(r, wrect, m, n, i, j);
                a[newrow][newcolumn] = temp;
            }
        }

        //output
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (j == 0)
                    System.out.print(a[i][j]);
                else
                    System.out.print(" " + a[i][j]);
            }
            System.out.println();
        }
    }
}
