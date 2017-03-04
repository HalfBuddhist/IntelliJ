package com.hackerrank.algo.string;

import com.lqw.common.WebPath;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CommonChild {
    static int[][] dp;
    static boolean[][] dp_flags;
    static String str1, str2;

    public static int max_common_child(int m, int n) {
        if (dp_flags[m][n]) return dp[m][n];
        int t;
        if (str1.charAt(m) == str2.charAt(n)) {
            t = max_common_child(m - 1, n - 1) + 1;

        } else {
            t = Math.max(max_common_child(m - 1, n), max_common_child(m, n - 1));
        }
        dp[m][n] = t;
        dp_flags[m][n] = true;
        return t;
    }

    public static void main(String[] argv) throws FileNotFoundException {
        Scanner sc = new Scanner(new File(WebPath.getAbsolutePathWithClass("/input.txt").getPath()));
        //        Scanner sc = new Scanner(new BufferedInputStream(System.in));
        //        Scanner sc = new Scanner(System.in);
        //input
        str1 = " " + sc.next();
        str2 = " " + sc.next();
        int n = str1.length() - 1;
        if (n == 0) System.out.println(0);

        //dp and member func
        dp = new int[n + 1][n + 1];
        dp_flags = new boolean[n + 1][n + 1];
        for (int i = 0; i < n + 1; i++) {
            dp[0][i] = 0;
            dp[i][0] = 0;
            dp_flags[0][i] = true;
            dp_flags[i][0] = true;
        }

        int max_comm = max_common_child(n, n);

        //output
        System.out.println(max_comm);
        sc.close();
    }
}
