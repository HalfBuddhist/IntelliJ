package com.leetcode;

import com.lqw.common.WebPath;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class AddBinary_67 {

    /**
     * brute force, defination.
     * simulatet the add operation in hand.
     * O(n)
     * AC
     *
     * @param a
     * @param b
     * @return
     */
    public String addBinary(String a, String b) {
        int jin = 0;
        int m = a.length();
        int n = b.length();
        StringBuilder sb = new StringBuilder("");
        int i = m - 1, j = n - 1;
        while (i >= 0 || j >= 0) {
            int he = (i >= 0 ? (a.charAt(i) - '0') : 0) + (j >= 0 ? (b.charAt(j) - '0') : 0) + jin;
            int cur;
            if (he >= 2) {
                jin = 1;
                cur = he - 2;
            } else {
                jin = 0;
                cur = he;
            }
            sb.append((char) (cur + '0'));

            //for next
            i--;
            j--;
        }

        if (jin == 1)
            sb.append(1);

        return sb.reverse().toString();
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
