package com.jobdu;

import com.lqw.common.WebPath;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * Created by Qingwei on 2017/3/10.
 */
public class MaxSumInCycleArray_1527 {

    public static int maxSumLinked(int arr[], int n) {
        int start = 0, end = 0;
        int max_sum = Integer.MIN_VALUE;
        int t_sum = 0;
        while (start < n && end < n * 2 - 1) {
            if (end >= n && start == end % n) {//has a cycle, move start
                t_sum -= arr[start++]; //move one more top let in from the other side.
                //ensure sum(start -> end-1) is positive
                int t = 0;
                int end_t = start;
                while (start < n && end_t < end) {
                    t += arr[end_t % n];
                    if (t < 0) {
                        t_sum -= t;
                        start = end_t + 1;
                        t = 0;
                    }
                    end_t++;
                }
                if (start >= n) break;//finished, avoid duplicate search
            }

            //accumulate
            t_sum += arr[end % n];
//            if (t_sum > max_sum) //could remember the index here
//                System.out.println(start + "\t" + end);
            max_sum = Math.max(max_sum, t_sum);
            if (t_sum < 0) {
                t_sum = 0;
                start = end + 1;
            }
            end++;
        }
        return max_sum;
    }


    public static void main(String[] argv) throws FileNotFoundException {
        long begin = System.currentTimeMillis();
        Scanner sc = new Scanner(new File(WebPath.getAbsolutePathWithClass("/input.txt").getPath()));

//        Scanner sc = new Scanner(new File(WebPath.getAbsolutePathWithClass("/test_1527.txt").getPath()));
        //        Scanner sc = new Scanner(new BufferedInputStream(System.in));
//        Scanner sc = new Scanner(System.in);

        System.setOut(new PrintStream(new FileOutputStream(
                new File(WebPath.getAbsolutePathWithClass().getPath() + "out_me.txt"))));

        //presolve
        //input
        while (sc.hasNext()) {
            int n = sc.nextInt();
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = sc.nextInt();
            }

            int res = maxSumLinked(arr, n);
            System.out.println(res >= 0 ? res : 0);
        }

        //resolve

        //output
        sc.close();
//        System.err.println("use time: " + (System.currentTimeMillis() - begin) / 1000.0 + " seconds.");
    }
}
