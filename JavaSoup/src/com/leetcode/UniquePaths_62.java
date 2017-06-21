package com.leetcode;

import com.lqw.common.WebPath;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class UniquePaths_62 {

    /**
     * dp, base algo
     * f(i,j) = f(i+1, j) + f(i, j+1)
     * iterative way with the Recurrence ralation.
     * use the rolling table(one dimensional, one array) instead of the two dimensional table.
     * O(m*n)
     * AC
     *
     * @param m
     * @param n
     * @return
     */
    public int uniquePaths(int m, int n) {
        int rows[] = new int[m];
        Arrays.setAll(rows, i -> 1);
        for (int i = n - 2; i >= 0; i--) {
            for (int j = m - 2; j >= 0; j--) {
                rows[j] += rows[j + 1];
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
        System.out.print(new UniquePaths_62().uniquePaths(1, 2));

        //resolve

        //output

        sc.close();
        System.err.println("use time: " + (System.currentTimeMillis() - begin) / 1000.0 + " seconds.");
    }
}
