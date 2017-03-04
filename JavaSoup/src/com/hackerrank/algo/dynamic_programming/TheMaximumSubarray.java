package com.hackerrank.algo.dynamic_programming;

import com.lqw.common.WebPath;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * greedy.
 */
public class TheMaximumSubarray {
    public static void main(String[] argv) throws FileNotFoundException {
        Scanner sc = new Scanner(new File(WebPath.getAbsolutePathWithClass("/input2.txt").getPath()));
        //        Scanner sc = new Scanner(new BufferedInputStream(System.in));
        //        Scanner sc = new Scanner(System.in);
        //input
        int t = sc.nextInt();
        while (t-- > 0) {
            int n = sc.nextInt();
            int[] ar = new int[n];
            int max = Integer.MIN_VALUE;
            for (int i = 0; i < n; i++) {
                ar[i] = sc.nextInt();
                if (ar[i] > max) max = ar[i];
            }

            //resolve
            //find continue
            int s = 0, e = 0, sum = 0, cnt = 0, tmax = 0, cmax = Integer.MIN_VALUE;
            while (s < n) {
                tmax = 0;
                if (ar[s] <= 0) {
                    s++;
                    continue;
                } else {
                    //find the e
                    e = s;
                    while (e < n) {
                        tmax += ar[e];
                        if (tmax > cmax) {
                            cmax = tmax;
                        }
                        if (tmax < 0) {
                            break;
                        }
                        e++;
                    }
                    s = e + 1;
                }
            }
            if (cmax > Integer.MIN_VALUE) {
                System.out.print(cmax + " ");
            } else
                System.out.print(max + " ");

            //find the non continue;
            sum = 0;
            cnt = 0;
            for (int i = 0; i < n; i++) {
                if (ar[i] > 0) {
                    sum += ar[i];
                    cnt++;
                }
            }
            if (cnt > 0) {
                System.out.println(sum);
            } else
                System.out.println(max);

        }

        //resolve

        //output
        sc.close();
    }
}
