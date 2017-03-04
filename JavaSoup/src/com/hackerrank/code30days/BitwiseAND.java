package com.hackerrank.code30days;

import com.lqw.common.WebPath;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


/**
 * O(n^2)
 */
public class BitwiseAND {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File(WebPath.getAbsolutePathWithClass("/com/hackerrank/code30days/input.txt").getPath()));
//        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while (t-- > 0) {
            int n = sc.nextInt();
            int k = sc.nextInt();

            int maxx = -1;
            for (int i = 1; i <= n; i++) {
                for (int j = i + 1; j <= n; j++) {
                    int temp = i & j;
                    if (temp < k && temp > maxx) {
                        maxx = temp;
                    }
                }
            }

            System.out.println(maxx);
        }
    }
}
