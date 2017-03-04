package com.hackerrank.week_code_21;

import com.lqw.common.WebPath;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;


public class LuckBalance {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File(WebPath.getAbsolutePathWithClass("/com/hackerrank/week_code_21/input.txt").getPath()));
//        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int a[] = new int[105];
        int num = 0, sum = 0;
        for (int i = 0; i < n; i++) {
            int l = sc.nextInt();
            int imp = sc.nextInt();
            sum += l;
            if (imp == 1) {
                a[num++] = l;
            }
        }

        //sort the imp
        Arrays.sort(a, 0, num);

        //
        int sub = 0;
        for (int i = 0; i < num - k; i++) {
            sub += a[i];
        }
        sum -= sub*2;

        //output
        System.out.println(sum);
    }
}
