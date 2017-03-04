package com.hackerrank.algo.string;

import com.lqw.common.WebPath;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TheLoveLetterMystery {
    public static void main(String[] argv) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(WebPath.getAbsolutePathWithClass("/input.txt").getPath()));
        //        Scanner scanner = new Scanner(new BufferedInputStream(System.in));
        //        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        while (t-- > 0) {
            String str = scanner.next();
            int i = 0, j = str.length() - 1, cnt = 0;
            while (i < j) {
                cnt += Math.abs((int) (str.charAt(i) - str.charAt(j)));
                i++;
                j--;
            }
            System.out.println(cnt);
        }
        scanner.close();
    }
}
