package com.hackerrank.week_code_26;

import com.lqw.common.WebPath;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * brute force
 * O(n^2)
 * should be time out.
 * <p/>
 * test case:
 * 3 / 2.524412954
 * 16700 / 2.876370614
 */
public class HardHomework2 {
    public static void main(String[] argv) throws FileNotFoundException {
        long begin = System.currentTimeMillis();
        Scanner sc = new Scanner(new File(WebPath.getAbsolutePathWithClass("/input.txt").getPath()));
        //        Scanner sc = new Scanner(new BufferedInputStream(System.in));
        //        Scanner sc = new Scanner(System.in);
        //presolve
        //input
        int n = sc.nextInt();
        double max = Double.MIN_VALUE;

        //resolve
        for (int x = 1; x <= n - 2; x++) {
            for (int y = 1; y <= x; y++) {
                int z = n - x - y;
                double temp = Math.sin(x) + Math.sin(y) + Math.sin(z);
                if (temp > max) {
                    max = temp;
                }
            }
        }

        //output
        System.out.printf("%.9f\n", max);
        sc.close();
        System.err.println("use time: " + (System.currentTimeMillis() - begin) / 1000.0 + " seconds.");
    }
}
