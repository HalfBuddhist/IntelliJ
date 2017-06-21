package com.leetcode;

import com.lqw.common.WebPath;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Integer2Roman {

    //O(n), length of the number in string
    public String intToRoman(int num) {
        String units = "IVXLCDM";
        StringBuilder result = new StringBuilder("");
        String num_str_rev = new StringBuilder(String.valueOf(num)).reverse().toString();
        int i = 0;
        for (char c : num_str_rev.toCharArray()) {
            StringBuilder sb = new StringBuilder("");
            switch (c) {
                case '0':
                    break;
                case '1':
                case '2':
                case '3':
                    for (int j = 0; j < c - '0'; j++) {
                        sb.append(units.charAt(i));
                    }
                    break;
                case '4':
                case '5':
                case '6':
                case '7':
                case '8':
                    StringBuilder sbt = new StringBuilder(String.valueOf(units.charAt(i + 1)));
                    for (int j = 0; j < Math.abs(c - '5'); j++) {
                        sbt.append(units.charAt(i));
                    }
                    if (c >= '5') {
                        sb.append(sbt.toString());
                    } else {
                        sb.append(sbt.reverse().toString());
                    }
                    break;
                case '9':
                    sb.append(String.valueOf(units.charAt(i)) + String.valueOf(units.charAt(i + 2)));
                default:
                    break;
            }
            result.append(sb.reverse());
            i += 2;
        }
        return result.reverse().toString();
    }

    public static void main(String[] argv) throws FileNotFoundException {
        long begin = System.currentTimeMillis();
        Scanner sc = new Scanner(new File(WebPath.getAbsolutePathWithClass("/input.txt").getPath()));
        //        Scanner sc = new Scanner(new BufferedInputStream(System.in));
        //        Scanner sc = new Scanner(System.in);
        //presolve
        //input


        System.out.println(new Integer2Roman().intToRoman(4));

        //resolve

        //output
        sc.close();
        System.err.println("use time: " + (System.currentTimeMillis() - begin) / 1000.0 + " seconds.");
    }
}
