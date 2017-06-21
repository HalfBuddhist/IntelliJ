package com.leetcode;

import com.lqw.common.WebPath;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class DivideTwoIntegers {

    //use direct divide
    public int divide_direct(int dividend, int divisor) {
        if (divisor == 0 || (divisor == -1 && dividend == Integer.MIN_VALUE)) {
            return Integer.MAX_VALUE;
        } else {
            return dividend / divisor;
        }
    }

    // use the defination of divise,
    // time limit exceeding.
    public int divide(int dividend, int divisor) {
        if (divisor == 0) {
            return Integer.MAX_VALUE;
        } else {
            if (dividend == 0) {
                return 0;
            }
            int ans = 0;
            boolean a = dividend > 0;
            boolean b = divisor > 0;
            dividend = a ? dividend : -dividend;
            divisor = b ? divisor : -divisor;
            while (dividend >= 0) {
                dividend -= divisor;
                ans++;
            }
            ans--;
            if (ans < 0) return Integer.MAX_VALUE;
            return (a ^ b) && ans != 0 ? -ans : ans;
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
        System.out.println((new DivideTwoIntegers()).divide(16, 3));

        //resolve
        System.out.println(-300%-10 + " " + -300/-10);

        //output
        sc.close();
        System.err.println("use time: " + (System.currentTimeMillis() - begin) / 1000.0 + " seconds.");
    }
}
