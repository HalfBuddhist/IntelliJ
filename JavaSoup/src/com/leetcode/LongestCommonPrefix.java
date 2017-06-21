package com.leetcode;

import com.lqw.common.WebPath;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class LongestCommonPrefix {

    public String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) return "";
        int len = -1;
        LOOP:
        while (true) {
            char c = ' ';
            len++;
            for (int i = 0; i < strs.length; i++) {
                if (strs[i].length() <= len) {
                    break LOOP;
                } else {
                    if (i == 0) {
                        c = strs[i].charAt(len);
                    } else {
                        if (strs[i].charAt(len) != c) {
                            break LOOP;
                        }
                    }
                }
            }
        }
        if (len == 0) return "";
        else
            return strs[0].substring(0, len);
    }

    public static void main(String[] argv) throws FileNotFoundException {
        long begin = System.currentTimeMillis();
        Scanner sc = new Scanner(new File(WebPath.getAbsolutePathWithClass("/input.txt").getPath()));
        //        Scanner sc = new Scanner(new BufferedInputStream(System.in));
        //        Scanner sc = new Scanner(System.in);
        //presolve
        //input
        System.out.println(new LongestCommonPrefix().longestCommonPrefix(new String[]{"abcdef"}));
        //resolve

        //output
        sc.close();
        System.err.println("use time: " + (System.currentTimeMillis() - begin) / 1000.0 + " seconds.");
    }
}
