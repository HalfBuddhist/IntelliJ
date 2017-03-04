package com.hackerrank.week_code_27;

import com.lqw.common.WebPath;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class HackonacciMatrixRotations {
    static int[] odditty = {1, 0, 1, 0, 0, 1, 1};

    /**
     * odd is true
     *
     * @param num
     * @return
     */
    public static int getHacks(long num) {
        int idx = (int) (num % 7);
        return idx == 0 ? odditty[6] : odditty[idx - 1];
    }

    public static void main(String[] argv) throws FileNotFoundException {
        long begin = System.currentTimeMillis();
        Scanner sc = new Scanner(new File(WebPath.getAbsolutePathWithClass("/input.txt").getPath()));
        //        Scanner sc = new Scanner(new BufferedInputStream(System.in));
        //        Scanner sc = new Scanner(System.in);
        //presolve

        //input
        int n = sc.nextInt();
        int q = sc.nextInt();

        //resolve
        int[][] matricks = new int[n + 1][n + 1];

        //fill the matrics
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                matricks[i][j] = getHacks((((long) i) * j) * (((long) i) * j));
            }
        }

        //fill the other three matricks
        int[] diffs = new int[4];
        diffs[0] = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (matricks[i][j] != matricks[j][n + 1 - i])
                    diffs[1]++;
                if (matricks[i][j] != matricks[n + 1 - i][n + 1 - j])
                    diffs[2]++;
                if (matricks[i][j] != matricks[n + 1 - j][i])
                    diffs[3]++;
            }
        }

        for (int p = 0; p < q; p++) {
            int tt = sc.nextInt();
            int normal_a = tt % 360;
            System.out.println(diffs[normal_a / 90]);
        }


        //output
        sc.close();
        System.err.println("use time: " + (System.currentTimeMillis() - begin) / 1000.0 + " seconds.");
    }
}
