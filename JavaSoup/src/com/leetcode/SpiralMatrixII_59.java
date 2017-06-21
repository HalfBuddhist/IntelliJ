package com.leetcode;

import com.lqw.common.WebPath;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SpiralMatrixII_59 {

    /**
     * brute force, defination
     * set the 4 directions corresonding 4 scan segments.
     * set the up, down, start, end boundries for the 4 directions scan.
     * when the boundries cross, the scan ends.
     * <p/>
     * O(N^2)
     * AC
     *
     * @param n
     * @return
     */
    public int[][] generateMatrix(int n) {
        int ans[][] = new int[n][n];
        int cur_ele = 1;
        int row_up = 0, row_down = n - 1;
        int col_left = 0, col_right = n - 1;
        int cur_row = row_up, cur_col = col_left;
        int direction = 0;//123
        while (row_up <= row_down && col_left <= col_right) {
            switch (direction) {
                case 0: {
                    for (; cur_col <= col_right; cur_col++) {
                        ans[cur_row][cur_col] = cur_ele++;
                    }
                    cur_col--;
                    row_up++;
                    cur_row++;
                    direction = (direction + 1) % 4;
                }
                break;
                case 1: {
                    for (; cur_row <= row_down; cur_row++) {
                        ans[cur_row][cur_col] = cur_ele++;
                    }
                    cur_row--;
                    col_right--;
                    cur_col--;
                    direction = (direction + 1) % 4;
                }
                break;
                case 2: {
                    for (; cur_col >= col_left; cur_col--) {
                        ans[cur_row][cur_col] = cur_ele++;
                    }
                    cur_col++;
                    row_down--;
                    cur_row--;
                    direction = (direction + 1) % 4;
                }
                break;
                case 3: {
                    for (; cur_row >= row_up; cur_row--) {
                        ans[cur_row][cur_col] = cur_ele++;
                    }
                    cur_row++;
                    col_left++;
                    cur_col++;
                    direction = (direction + 1) % 4;
                }
                break;
            }
        }
        return ans;
    }

    public static void main(String[] argv) throws FileNotFoundException {
        long begin = System.currentTimeMillis();
        Scanner sc = new Scanner(new File(WebPath.getAbsolutePathWithClass("/input.txt").getPath()));
        //        Scanner sc = new Scanner(new BufferedInputStream(System.in));
        //        Scanner sc = new Scanner(System.in);
        //        System.setOut(new PrintStream(new FileOutputStream(new File(WebPath.getAbsolutePathWithClass().getPath() + "output.txt"))));
        //presolve
        //input
        //resolve
        int[][] ans = (new SpiralMatrixII_59()).generateMatrix(3);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(" " + ans[i][j]);
            }
            System.out.println();
        }

        //output

        sc.close();
        System.err.println("use time: " + (System.currentTimeMillis() - begin) / 1000.0 + " seconds.");
    }
}
