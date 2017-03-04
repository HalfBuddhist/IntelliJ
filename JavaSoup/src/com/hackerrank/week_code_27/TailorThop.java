package com.hackerrank.week_code_27;

import com.lqw.common.WebPath;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class TailorThop {
    public static void main(String[] argv) throws FileNotFoundException {
        long begin = System.currentTimeMillis();
        Scanner sc = new Scanner(new File(WebPath.getAbsolutePathWithClass("/input.txt").getPath()));
        //        Scanner sc = new Scanner(new BufferedInputStream(System.in));
        //        Scanner sc = new Scanner(System.in);
        //presolve
        //input
        int n = sc.nextInt();
        int p = sc.nextInt();
        int[] cost = new int[n];
        for (int i = 0; i < n; i++) {
            cost[i] = sc.nextInt();
        }

        //resolve
        Arrays.sort(cost);
        long least_btns = 0;
        long last_cnt = -1;
        for (int i = 0; i < n; i++) {
            long cur_cnt = cost[i] % p == 0 ? cost[i] / p : cost[i] / p + 1;
            if (cur_cnt > last_cnt) {
                least_btns += cur_cnt;
                last_cnt = cur_cnt;
            } else{
                least_btns += last_cnt + 1;
                last_cnt++;
            }
        }


        //output
        System.out.println(least_btns);
        sc.close();
        System.err.println("use time: " + (System.currentTimeMillis() - begin) / 1000.0 + " seconds.");
    }
}

// 6 2   3 4 4 4 4 5      => 27
// 5 5   4 5 5 5 6        => 15
// 8 5   4 5 5 5 6 6 6 20 => 36

// IF your code pass these test cases
// THEN your code will also pass all preliminary test cases
