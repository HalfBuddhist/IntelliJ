package com.leetcode;

import com.lqw.common.WebPath;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Roman2Integer {

    //O(n), length of the string.
    public int romanToInt(String s) {
        //preprocess
        String units = "IVXLCDM";
        int[] units_i = {1, 5, 10, 50, 100, 500, 1000};
        int map[] = new int[27];
        for (int i = 0; i < units.length(); i++) {
            map[units.charAt(i) - 'A'] = units_i[i];
        }

        //process
        StringBuilder sb = new StringBuilder(s);
        sb.reverse();
        int ans = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = sb.charAt(i);
            if (i + 1 < s.length() && map[sb.charAt(i + 1) - 'A'] < map[c - 'A']) {
                ans += map[c - 'A'] - map[sb.charAt(i + 1) - 'A'];
                i++;
            } else {
                ans += map[c - 'A'];
            }
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
        System.out.println(new Roman2Integer().romanToInt("DCXXI"));

        //resolve

        //output
        sc.close();
        System.err.println("use time: " + (System.currentTimeMillis() - begin) / 1000.0 + " seconds.");
    }
}
