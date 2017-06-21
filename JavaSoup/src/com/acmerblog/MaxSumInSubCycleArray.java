package com.acmerblog;

import com.lqw.common.WebPath;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by Qingwei on 2017/3/10.
 */
public class MaxSumInSubCycleArray {

    //idx in the subarray, may be larger than the legal value,
    // mod them with the len when output use the idx.
    private static int max_value_idx_subarray_start;
    private static int max_value_idx_subarray_end;

    /**
     * 扩展问题
     * <p/>
     * 如果一维数组首尾相连，如何计算？
     * <p/>
     * 可以先考虑一维数组首位相连的问题。
     * 一个方法是把原来的数组进行扩充，例如对于 arr[0 ... n-1 ] 可以看成是  arr[0 ... n-1, 0, 1, ....n-2]。
     * 即原数组的长度由原来的n，变为了 2*n-1。实际中计算中没有必要扩充，求余即可。
     * 需要注意的是，如果全部都是非负的，
     * 我们需要加一个判断，防止计算结果超出数组的总和。当然还有其他的计算方法，大家可以参考编程之美一书。
     * <p/>
     * 还要防止循环发生的可能，处理方法，为了遍历所有可能的数组，只能去除前者，往后找到新的起始位置。
     * 方法是从start+1开始向后累加，负则更正新的start, 直到累加到end-1，或者start越界。详见代码。
     *
     * @param arr
     * @param n
     * @return
     */
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
            if (t_sum > max_sum) { //could remember the index here
                max_value_idx_subarray_start = start;
                max_value_idx_subarray_end = end;
            }
            max_sum = Math.max(max_sum, t_sum);
            if (t_sum < 0) {
                t_sum = 0;
                start = end + 1;
            }
            end++;
        }
        return max_sum;
    }

    public static void main(String[] args) {
//        int a[] = {1, -2, 3, 10, -4, 7, 2, -5};
        int a[] = {1, 2, 3, -1, -3, -3, -4, -5, 6};
        int res = maxSumLinked(a, a.length);
        System.out.println(res);
        System.out.println(max_value_idx_subarray_start + " " + max_value_idx_subarray_end);
    }

    public static void main1(String[] argv) throws FileNotFoundException {
        long begin = System.currentTimeMillis();
        Scanner sc = new Scanner(new File(WebPath.getAbsolutePathWithClass("/input.txt").getPath()));
        //        Scanner sc = new Scanner(new BufferedInputStream(System.in));
        //        Scanner sc = new Scanner(System.in);
        //presolve
        //input
        while (sc.hasNext()) {
            int n = sc.nextInt();
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = sc.nextInt();
            }

            System.out.println(maxSumLinked(arr, n));
        }

        //resolve

        //output
        sc.close();
        System.err.println("use time: " + (System.currentTimeMillis() - begin) / 1000.0 + " seconds.");
    }
}
