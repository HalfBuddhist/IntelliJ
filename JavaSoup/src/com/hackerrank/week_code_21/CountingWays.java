package com.hackerrank.week_code_21;

import com.lqw.common.WebPath;

import java.io.File;
import java.util.Scanner;


/**
 * 只用于计算小于1400100的情况。
 */

public class CountingWays {

    public static void main(String[] args) throws Exception {
        long begin = System.currentTimeMillis();
        Scanner sc = new Scanner(new File(WebPath.getAbsolutePathWithClass("/com/hackerrank/week_code_21/input.txt").getPath()));
        //Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }
        int L = sc.nextInt();
        int R = sc.nextInt();
        long mod = (long) 1E9 + 7;
        // maxx = long(1E17 + 5)
        int maxx = 1400100;
//        int maxx = 10;
        long[][] f = new long[maxx][n];

        for (int i = 0; i < n; i++) {
            f[0][i] = 1;
        }

        for (int i = 1; i < maxx; i++) {
            for (int j = 0; j < n; j++) {
                if (j > 0) {
                    f[i][j] = f[i][j - 1];
                }
                if (i >= a[j]) {
                    f[i][j] += f[i - a[j]][j];
                    if (f[i][j] >= mod) f[i][j] -= mod;
                }
            }
        }

        int res = 0;
        for (int i = L; i <= R; i++) {
            res += f[i][n - 1];
            if (res >= mod) res -= mod;
        }

        System.out.println(res);

        sc.close();
        System.out.println("use time: " + (System.currentTimeMillis() - begin) / 1000.0 + " seconds.");
    }

}
