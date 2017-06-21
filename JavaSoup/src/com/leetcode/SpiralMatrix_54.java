package com.leetcode;

import com.lqw.common.WebPath;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SpiralMatrix_54 {

    /**
     * brute force, defination
     * set the 4 directions corresonding 4 scan segments.
     * set the up, down, start, end boundries for the 4 directions scan.
     * when the boundries cross, the scan ends.
     * <p/>
     * O(N^2)
     * AC
     *
     * @param matrix
     * @return
     */
    public List<Integer> spiralOrder(int[][] matrix) {
        ArrayList<Integer> ans = new ArrayList<Integer>();
        int m = matrix.length;
        int n = m > 0 ? matrix[0].length : 0;
        int row_up = 0, row_down = m - 1;
        int col_left = 0, col_right = n - 1;
        int cur_row = row_up, cur_col = col_left;
        int direction = 0;//123
        while (row_up <= row_down && col_left <= col_right) {
            switch (direction) {
                case 0: {
                    for (; cur_col <= col_right; cur_col++) {
                        ans.add(matrix[cur_row][cur_col]);
                    }
                    cur_col--;
                    row_up++;
                    cur_row++;
                    direction = (direction + 1) % 4;
                }
                break;
                case 1: {
                    for (; cur_row <= row_down; cur_row++) {
                        ans.add(matrix[cur_row][cur_col]);
                    }
                    cur_row--;
                    col_right--;
                    cur_col--;
                    direction = (direction + 1) % 4;
                }
                break;
                case 2: {
                    for (; cur_col >= col_left; cur_col--) {
                        ans.add(matrix[cur_row][cur_col]);
                    }
                    cur_col++;
                    row_down--;
                    cur_row--;
                    direction = (direction + 1) % 4;
                }
                break;
                case 3: {
                    for (; cur_row >= row_up; cur_row--) {
                        ans.add(matrix[cur_row][cur_col]);
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
        List<Integer> list = new SpiralMatrix_54().spiralOrder(new int[][]{
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        });

        //output
        for (Integer e : list) {
            System.out.print(e + " ");
        }

        sc.close();
        System.err.println("use time: " + (System.currentTimeMillis() - begin) / 1000.0 + " seconds.");
    }
}
