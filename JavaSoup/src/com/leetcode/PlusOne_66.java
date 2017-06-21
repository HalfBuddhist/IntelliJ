package com.leetcode;

import com.lqw.common.WebPath;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class PlusOne_66 {

    /**
     * brute force, defination
     * simulate the add operation in hand calculation.
     * O(n)
     * AC
     *
     * @param digits
     * @return
     */
    public int[] plusOne(int[] digits) {
        int n = digits.length;
        int jin = 1;

        for (int i = n - 1; i >= 0; i--) {
            int he = digits[i] + jin;
            digits[i] = he % 10;
            jin = he / 10;

            if (jin == 0) break;
        }

        if (jin == 1) {
            int[] ans = new int[n + 1];
            Arrays.setAll(ans, i -> {
                if (i == 0)
                    return 1;
                else
                    return digits[i - 1];
            });
            return ans;
        } else {
            return digits;
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
