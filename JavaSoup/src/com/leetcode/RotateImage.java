package com.leetcode;

import com.lqw.common.WebPath;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class RotateImage {

    /**
     * brute force, pattern recognization of defination
     * after rotation, (i,j) is rotated to (j, n-1-i), and the 4 rotations form a cycle.
     * In place requirements makes us rotate the targe immediately after a rotation.
     * only 1/4 matrix such as the start-up corner rotate is needed under this rotate operation.
     * The odd and even number of n is a litter differnet, the 1/4 only including 1/4 of the
     * four middle axis-like lines.
     * <p/>
     * O(n)
     * AC
     *
     * @param matrix
     */
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        int ro_matrix = n / 2;
        for (int i = 0; i < ro_matrix; i++) {
            int ro_col = n % 2 == 0 ? ro_matrix : ro_matrix + 1;//need to rotate the axis line
            for (int j = 0; j < ro_col; j++) {
                //rotate the small matrix
                int t = matrix[i][j];
                matrix[i][j] = matrix[n - 1 - j][i];
                matrix[n - 1 - j][i] = matrix[n - 1 - i][n - 1 - j];
                matrix[n - 1 - i][n - 1 - j] = matrix[j][n - 1 - i];
                matrix[j][n - 1 - i] = t;
            }
        }
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

        //output
        sc.close();
        System.err.println("use time: " + (System.currentTimeMillis() - begin) / 1000.0 + " seconds.");
    }
}
