package com.leetcode;

import com.lqw.common.WebPath;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Power {

    /**
     * SPCS, power operation.
     * Russian peasant algo
     * O(logn)
     * AC
     *
     * @param x
     * @param n
     * @return
     */
    public double myPow(double x, int n) {
        boolean negtive = n < 0;
        long nn = (long) n;
        nn = n < 0 ? -nn : nn;
        double ans = 1;
        double cur = x;
        while (nn > 0) {
            if (nn % 2 == 1) {
                ans *= cur;
            }
            cur = cur * cur;
            nn >>>= 1;
        }
        return negtive ? 1 / ans : ans;
    }

    public static void main(String[] argv) throws FileNotFoundException {
        long begin = System.currentTimeMillis();
        Scanner sc = new Scanner(new File(WebPath.getAbsolutePathWithClass("/input.txt").getPath()));
        //        Scanner sc = new Scanner(new BufferedInputStream(System.in));
        //        Scanner sc = new Scanner(System.in);
        //        System.setOut(new PrintStream(new FileOutputStream(new File(WebPath.getAbsolutePathWithClass().getPath() + "output.txt"))));
        //presolve
        //input
        System.out.println(new Power().myPow(2.0, -2147483648));

        //resolve

        //output
        sc.close();
        System.err.println("use time: " + (System.currentTimeMillis() - begin) / 1000.0 + " seconds.");
    }
}
