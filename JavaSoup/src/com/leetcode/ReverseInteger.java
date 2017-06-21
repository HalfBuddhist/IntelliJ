package com.leetcode;

import com.lqw.common.WebPath;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ReverseInteger {


    //O(1)
    public int reverse(int x) {
        String min_str = Integer.toString(Integer.MIN_VALUE);
        String max_str = Integer.toString(Integer.MAX_VALUE);

        StringBuffer sb = new StringBuffer(Integer.toString(x >= 0 ? x : -1 * x));
        sb.reverse();
        //complement zeros
        int zeros = 10 - sb.length();
        while (zeros-->0){
            sb.insert(0, '0');
        }

        if (x >= 0) {
            int cmp = sb.toString().compareTo(max_str);
            if (cmp > 0) return 0;
            else {
                return Integer.parseInt(sb.toString());
            }
        } else {
            int cmp = (sb.insert(0, '-').toString()).compareTo(min_str);
            if (cmp > 0) return 0;
            else {
                return Integer.parseInt(sb.toString());
            }
        }
    }

    public static void main(String[] argv) throws FileNotFoundException {
        long begin = System.currentTimeMillis();
        Scanner sc = new Scanner(new File(WebPath.getAbsolutePathWithClass("/input.txt").getPath()));
        //        Scanner sc = new Scanner(new BufferedInputStream(System.in));
        //        Scanner sc = new Scanner(System.in);
        //presolve
        //input
        System.out.println((new ReverseInteger()).reverse(-123));

        //resolve

        //output
        sc.close();
        System.err.println("use time: " + (System.currentTimeMillis() - begin) / 1000.0 + " seconds.");
    }
}