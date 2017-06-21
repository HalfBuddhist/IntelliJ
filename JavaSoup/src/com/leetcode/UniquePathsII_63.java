package com.leetcode;

import com.lqw.common.WebPath;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class UniquePathsII_63 {

    /**
     * dp, base algo
     * f(i,j) = f(i+1, j) + f(i, j+1) if obs[i][j] == 0 else 0
     * iterative way with the Recurrence ralation.
     * use the rolling table(one dimensional, one array) instead of the two dimensional table.
     * O(m*n)
     * AC
     *
     * @param obstacleGrid
     * @return
     */
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = m > 0 ? obstacleGrid[0].length : 0;

        //set intitial value.
        int rows[] = new int[m];
        boolean hasObs = false;
        for (int i = m - 1; i >= 0; i--) {
            if (obstacleGrid[i][n - 1] == 1)
                hasObs = true;
            if (hasObs) rows[i] = 0;
            else rows[i] = 1;
        }

        //iterative add
        hasObs = rows[m - 1] != 1;
        for (int i = n - 2; i >= 0; i--) {
            if (obstacleGrid[m - 1][i] == 1)
                hasObs = true;
            if (hasObs) rows[m - 1] = 0;
            else rows[m - 1] = 1;

            for (int j = m - 2; j >= 0; j--) {
                rows[j] += rows[j + 1];
                if (obstacleGrid[j][i] == 1)
                    rows[j] = 0;
            }
        }
        return rows[0];
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
