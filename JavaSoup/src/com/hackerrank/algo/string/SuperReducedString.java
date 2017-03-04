package com.hackerrank.algo.string;

import com.lqw.common.WebPath;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * 使用分治算法
 */
public class SuperReducedString {

    public static String divide_couquer(String str) {

        if (str == null || str.equals("")) {
            return "";
        } else if (str.length() == 1) {
            return str;
        } else {
            int n = str.length();
            String str1 = divide_couquer(str.substring(0, n / 2));
            String str2 = divide_couquer(str.substring(n / 2));
            int i = str1.length() - 1;
            int j = 0;
            while (i >= 0 && j < str2.length()) {
                if (str1.charAt(i) == str2.charAt(j)) {
                    i--;
                    j++;
                } else break;
            }
            String firstHalf, secondHalf;
            if (i < 0) {
                firstHalf = "";
            } else {
                firstHalf = str1.substring(0, i + 1);
            }

            if (j < str2.length()) {
                secondHalf = str2.substring(j);
            } else {
                secondHalf = "";
            }

            return firstHalf + secondHalf;
        }

    }

    public static void main(String[] argv) throws FileNotFoundException {

        Scanner scanner = new Scanner(new File(WebPath.getAbsolutePathWithClass("/input.txt").getPath()));
        //        Scanner scanner = new Scanner(new BufferedInputStream(System.in));
        String source = scanner.next();
        String result = divide_couquer(source);
        if (result == null || result.equals("")) {
            System.out.println("Empty String");
        } else {
            System.out.println(result);
        }
        scanner.close();
    }
}
