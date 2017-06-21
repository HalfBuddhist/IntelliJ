package com.jobdu;

import com.acmerblog.MaxSumInSubArray;
import com.lqw.common.WebPath;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by Qingwei on 2017/3/10.
 */
public class MaxSumInSubArray_1372 {


    public static void main(String[] argv) throws FileNotFoundException {
        long begin = System.currentTimeMillis();
        Scanner sc = new Scanner(new File(WebPath.getAbsolutePathWithClass("/input.txt").getPath()));
        //        Scanner sc = new Scanner(new BufferedInputStream(System.in));
        //        Scanner sc = new Scanner(System.in);
        //presolve
        //input
        while (true) {
            int n = sc.nextInt();
            if (n == 0) break;
            int arr[] = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = sc.nextInt();
            }

            MaxSumInSubArray.Result max = MaxSumInSubArray.maxSum2Pointer(arr, n);
            System.out.println(max.sum + " " + max.start + " " + max.end);
        }
        //resolve

        //output
        sc.close();
        System.err.println("use time: " + (System.currentTimeMillis() - begin) / 1000.0 + " seconds.");
    }
}
