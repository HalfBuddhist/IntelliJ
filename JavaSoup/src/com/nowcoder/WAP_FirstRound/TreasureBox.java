package com.nowcoder.WAP_FirstRound;

import com.lqw.common.WebPath;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class TreasureBox {
    /**
     * Question:
     * an integer arrays, length is n.
     * take at lease one of the integers from the array, get a sum.
     * Ask if possible the sum is divided by m, another integer.
     * <p>
     * base algo, dp, bottom-up manner, iteratively.
     * f(i,r), maximum attendance in the sum of some from the first i intergers,
     * and the sum is r remainder by modluo of m.
     * 0 -  indicate no attendance, forbiddent in this situation.
     * -1 - indicate not possible for this situation.
     * then we get the following recurrence relation.
     * f(i,r) = max(f(i-1, r), f(i-1, (m+r-a[i]%m)mod(m)) + 1)
     * And duration the update of the memorization table, only involving the two adjacent rows,
     * so the rolloing array should be used. And becase the update direction is undeterminate,
     * so we shold use two such arrays.
     * <p>
     * O(n*m)
     * AC
     *
     * @param argv
     * @throws FileNotFoundException
     */
    public static void main(String[] argv) throws FileNotFoundException {
        long begin = System.currentTimeMillis();
        Scanner sc = new Scanner(new File(WebPath.getAbsolutePathWithClass("/input.txt").getPath()));
        //        Scanner sc = new Scanner(new BufferedInputStream(System.in));
        //        Scanner sc = new Scanner(System.in);
        //        System.setOut(new PrintStream(new FileOutputStream(new File(WebPath.getAbsolutePathWithClass().getPath() + "output.txt"))));
        //presolve
        //input
        int n = sc.nextInt();
        int m = sc.nextInt();
        long[] a = new long[n];
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }

        //resolve
        //initialize
        int[][] dp = new int[2][m];
        for (int i = 0; i < m; i++) {
            dp[0][i] = -1;
        }
        dp[0][0] = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < m; j++) {
                int cur_row = i & 1;//1 is odd.
                int last = 1 - cur_row;
                int col = j - (int) ((a[i - 1]) % m);
                col = (col >= 0 ? col : col + m);
                dp[cur_row][j] = Math.max(dp[last][j], dp[last][col] >= 0 ? dp[last][col] + 1 : -1);
            }
        }


        //output
        System.out.println(dp[n & 1][0] > 0 ? "Yes" : "No");

        sc.close();
        System.err.println("use time: " + (System.currentTimeMillis() - begin) / 1000.0 + " seconds.");
    }
}
