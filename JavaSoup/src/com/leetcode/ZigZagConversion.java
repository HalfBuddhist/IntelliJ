package com.leetcode;

import com.lqw.common.WebPath;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ZigZagConversion {

    //time complication should be O(n)
    public String convert(String s, int numRows) {
        if (1 == numRows) return s;
        StringBuffer sb = new StringBuffer("");
        for (int i = 1; i <= numRows; i++) {
            int j = 0;
            while (true) {
                int later = i + j * (2 + 2 * (numRows - 2)) - 1;
                int former = later - 2 * (i - 1);
                if (i != 1 && i != numRows && (later + 1 > numRows) && former < s.length())
                    sb.append(s.charAt(former));
                if (later < s.length())
                    sb.append(s.charAt(later));
                if (former >= s.length() || later >= s.length())
                    break;
                j++;
            }
        }
        return sb.toString();
    }

    public static void main(String[] argv) throws FileNotFoundException {
        long begin = System.currentTimeMillis();
        Scanner sc = new Scanner(new File(WebPath.getAbsolutePathWithClass("/input.txt").getPath()));
        //        Scanner sc = new Scanner(new BufferedInputStream(System.in));
        //        Scanner sc = new Scanner(System.in);
        //presolve
        //input
        System.out.println(new ZigZagConversion().convert("abcdef", 2));

        //resolve

        //output
        sc.close();
        System.err.println("use time: " + (System.currentTimeMillis() - begin) / 1000.0 + " seconds.");
    }
}
