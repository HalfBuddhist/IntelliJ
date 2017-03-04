package com.hackerrank.code30days;

import com.lqw.common.WebPath;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Sorting {
    public static void main(String[] argh) throws FileNotFoundException {
        Scanner scan = new Scanner(new File(WebPath.getAbsolutePathWithClass("/com/hackerrank/code30days/input.txt").getPath()));
//        Scanner in = new Scanner(System.in);
        int n = scan.nextInt();
        int[] a = new int[n];
        for (int i = 0; i< n; i++){
            a[i] = scan.nextInt();
        }
        scan.close();

        int sum_cnt = 0;
        for (int i = 0; i < n; i++) {
            int numberOfSwaps = 0;

            for (int j = 0; j < n - 1; j++) {
                if (a[j] > a[j + 1]) {
                    int t = a[j+1];
                    a[j+1] = a [j];
                    a[j] = t;
                    numberOfSwaps++;
                }
            }

            sum_cnt += numberOfSwaps;
            if (numberOfSwaps == 0) {
                break;
            }
        }

        System.out.println("Array is sorted in " + sum_cnt +" swaps.");
        System.out.println("First Element: " + a[0]);
        System.out.println("Last Element: " + a[a.length-1]);
    }
}