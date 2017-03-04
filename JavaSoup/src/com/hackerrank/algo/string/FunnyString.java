package com.hackerrank.algo.string;

import com.lqw.common.WebPath;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FunnyString {
    public static void main(String[] argv) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(WebPath.getAbsolutePathWithClass("/input.txt").getPath()));
        //        Scanner scanner = new Scanner(new BufferedInputStream(System.in));
        //        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        while (t-- > 0) {
            char[] chars = scanner.next().toCharArray();
            int len = chars.length;
            boolean funny = true;
            for (int i = 1; i < len; i++) {
                if (Math.abs(chars[i] - chars[i - 1]) !=
                        Math.abs(chars[len - i - 1] - chars[len - i])) {
                    funny = false;
                    break;
                }
            }
            if (funny) System.out.println("Funny");
            else System.out.println("Not Funny");
        }
        scanner.close();
    }
}
