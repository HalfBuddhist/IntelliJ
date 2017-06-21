package com.leetcode;

import com.lqw.common.WebPath;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class LongestPalindromicSubstring {

    //O(n^2)
    public String longestPalindrome(String s) {
        String ans = null;
        int size = Integer.MIN_VALUE;
        int axis = 0;
        while (axis / 2 < s.length()) {
            //prunning
            if (axis % 2 == 0) {
                int est_size = (s.length() - 1 - axis / 2) * 2 + 1;
                if (est_size <= size) {
                    break;
                }
            } else {
                int est_size = (s.length() - (axis / 2 + 1)) * 2;
                if (est_size <= size) {
                    break;
                }
            }


            if (axis % 2 == 0) {
                int new_axis = axis / 2;
                //find the palandrome
                int radius = 0;
                while ((new_axis - radius) >= 0 && (new_axis + radius) < s.length() && s.charAt(new_axis - radius) == s.charAt(radius + new_axis)) {
                    radius++;
                }
                radius--;
                int len = radius * 2 + 1;//record
                if (len > size) {
                    size = len;
                    ans = s.substring(new_axis - radius, new_axis + radius + 1);
                }
            } else {
                int new_axis1 = axis / 2;
                int new_axis2 = new_axis1 + 1;
                //find the palandrome
                int radius = 0;
                while ((new_axis1 - radius) >= 0 && (new_axis2 + radius) < s.length() && s.charAt(new_axis1 - radius) == s.charAt(new_axis2 + radius)) {
                    radius++;
                }
                radius--;
                int len = radius * 2 + 2;//record
                if (len > size) {
                    size = len;
                    ans = s.substring(new_axis1 - radius, new_axis2 + radius + 1);
                }
            }
            axis++;
        }
        return ans;
    }


    public static void main(String[] argv) throws FileNotFoundException {
        long begin = System.currentTimeMillis();
        Scanner sc = new Scanner(new File(WebPath.getAbsolutePathWithClass("/input.txt").getPath()));
        //        Scanner sc = new Scanner(new BufferedInputStream(System.in));
        //        Scanner sc = new Scanner(System.in);
        //presolve
        //input
        String s = sc.next();
        System.out.println(new LongestPalindromicSubstring().longestPalindrome(s.substring(1, s.length() - 1)));

        //resolve

        //output
        sc.close();
        System.err.println("use time: " + (System.currentTimeMillis() - begin) / 1000.0 + " seconds.");
    }
}
