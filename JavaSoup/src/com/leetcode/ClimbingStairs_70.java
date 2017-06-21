package com.leetcode;

import com.lqw.common.WebPath;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ClimbingStairs_70 {


    /**
     * base algo, dp
     * bottom-up, iteratively.
     * f(n) = f(n-1) + f(n-2)
     * O(n)
     * AC
     *
     * @param n
     * @return
     */
    public int climbStairs(int n) {
        if (n == 1) return 1;
        else if (n == 2) return 2;
        else {
            int ll = 1, l = 2, cur = 3;
            int times = n - 3;
            while (times >= 0) {
                cur = ll + l;
                //next
                ll = l;
                l = cur;
                times--;
            }
            return cur;
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
