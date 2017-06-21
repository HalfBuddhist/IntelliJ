package com.poj;

import com.lqw.common.WebPath;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class SlidingWindow_2823_MonotoneQueue_OPT {
    /**
     * Monotone Queue, 单调队列
     * O(N)
     * 优化： 最后的出队列操作改为二分查找。
     * 但是从实际运行效果看，运行速度还没有优化前的好，应该使用的二分查找引入了函数操作，而增加了循环体的复杂度，
     * 抵消了算法的优势。
     *
     * @param argv
     * @throws java.io.FileNotFoundException
     */
    static int nums[] = new int[1000005];
    static Integer qmax[] = new Integer[1000005];
    static Integer qmin[] = new Integer[1000005];
    static int[] ans_max = new int[1000005];
    static int[] ans_min = new int[1000005];

    public static void main(String[] argv) throws FileNotFoundException {
        long begin = System.currentTimeMillis();
        Scanner sc = new Scanner(new File(WebPath.getAbsolutePathWithClass("/input.txt").getPath()));
        //        Scanner sc = new Scanner(new BufferedInputStream(System.in));
        //        Scanner sc = new Scanner(System.in);
        //        System.setOut(new PrintStream(new FileOutputStream(new File(WebPath.getAbsolutePathWithClass().getPath() + "output.txt"))));
        //presolve
        //input
        int n = sc.nextInt();
        int k = sc.nextInt();
        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }

        int max_front = 0, max_tail = 0, min_front = 0, min_tail = 0;
        int idx_max = 0, idx_min = 0;

        for (int i = 0; i < n; i++) {
            // for min q
            if (min_front != min_tail && i - qmin[min_front] >= k)
                min_front++;
            //binary search place in the queue
            int t = Arrays.binarySearch(qmin, min_front, min_tail, i, new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    return (nums[o1] - nums[o2]);
                }
            });
            if (t >= 0) {
                min_tail = t;
            } else {
                min_tail = -t - 1;
            }
            qmin[min_tail++] = i;

            // for mq
            if (max_front != max_tail && i - qmax[max_front] >= k)
                max_front++;
            //binary search place in the queue
            t = Arrays.binarySearch(qmax, max_front, max_tail, i, new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    return (nums[o2] - nums[o1]);
                }
            });
            if (t >= 0) {
                max_tail = t;
            } else {
                max_tail = -t - 1;
            }
            qmax[max_tail++] = i;

            //rememeber the min and max
            if (i >= k - 1) {
                ans_max[idx_max++] = nums[qmax[max_front]];
                ans_min[idx_min++] = nums[qmin[min_front]];
            }
        }


        //out
        for (int i = 0; i <= n - k; i++) {
            System.out.print(ans_min[i] + " ");
        }
        System.out.println();
        for (int i = 0; i <= n - k; i++) {
            System.out.print(ans_max[i] + " ");
        }
        System.out.println();

        sc.close();
        System.err.println("use time: " + (System.currentTimeMillis() - begin) / 1000.0 + " seconds.");
    }
}

