package com.hackerrank.algo.search;

import com.lqw.common.WebPath;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SherlockAndArray {
    public static void main(String[] argv) throws FileNotFoundException {
        Scanner sc = new Scanner(new File(WebPath.getAbsolutePathWithClass("/input.txt").getPath()));
        //        Scanner sc = new Scanner(new BufferedInputStream(System.in));
        //        Scanner sc = new Scanner(System.in);
        //presolve
        //input
        int t = sc.nextInt();
        while (t-- > 0) {
            int n = sc.nextInt();
            int sum = 0;
            int[] numbers = new int[n];
            for (int i = 0; i < n; i++) {
                numbers[i] = sc.nextInt();
                sum += numbers[i];
            }

            boolean found = false;
            int left_sum = 0, right_sum = sum;
            for (int i = 0; i < n; i++) {
                right_sum -= numbers[i];
                if (i - 1 >= 0) left_sum += numbers[i - 1];
                if (left_sum == right_sum) {
                    found = true;
                    break;
                }
                if (left_sum >= right_sum) break;
            }

            if (found) System.out.println("YES");
            else System.out.println("NO");
        }

        //resolve

        //output
        sc.close();
    }
}
