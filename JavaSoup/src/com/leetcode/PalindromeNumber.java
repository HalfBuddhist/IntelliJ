package com.leetcode;

import com.lqw.common.WebPath;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class PalindromeNumber {

    public boolean isPalindrome(int x) {
        if (x < 0) return false;
        if (x == 0) return true;
        int div = 1000000000;//get length
        while (true) {
            if (x / div > 0) break;
            div /= 10;
        }

        //test palndrome
        int div1 = div;
        while (div1 > 0) {
            if (x / div1 == x % 10) {
                x = x % div1;
                x = x / 10;
                div1 /= 100;
            } else {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] argv) throws FileNotFoundException {
        long begin = System.currentTimeMillis();
        Scanner sc = new Scanner(new File(WebPath.getAbsolutePathWithClass("/input.txt").getPath()));
        //        Scanner sc = new Scanner(new BufferedInputStream(System.in));
        //        Scanner sc = new Scanner(System.in);
        //presolve
        //input
        System.out.println(new PalindromeNumber().isPalindrome(1));

        //resolve

        //output
        sc.close();
        System.err.println("use time: " + (System.currentTimeMillis() - begin) / 1000.0 + " seconds.");
    }
}
