package com.poj;

import com.lqw.common.WebPath;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;


/**
 * bad greedy
 * average chop the wood as much, under a sorted blocks of target.
 * <p/>
 * WA
 */
public class FenceRepairWA {
    /**
     * min cost to cut from the start to end
     *
     * @param sums
     * @param indexes
     * @param start,  not included
     * @param end,    included
     * @return min cost
     */
    public static long mincost(final int[] sums, Integer[] indexes, final int start, final int end) {
        if (start + 1 == end) {
            return 0;
        }

        int index = Arrays.binarySearch(indexes, start + 1, end + 1, 0, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                //negtive to search the back half,
                //o2 is the target, that is 0, o1 is the middle value
                return (sums[o1] - sums[start]) - (sums[end] - sums[o1]);
            }
        });
        //index is in the first half.
        if (index >= 0) {
            long cost = sums[end] - sums[start];
            return cost + mincost(sums, indexes, start, index) + mincost(sums, indexes, index, end);
        } else {
            index = -index - 1;
            //justice
            long cost = sums[end] - sums[start];
            int first = Math.abs((sums[index] - sums[start]) - (sums[end] - sums[index]));
            int front = Math.abs((sums[index - 1] - sums[start]) - (sums[end] - sums[index - 1]));
            if (first <= front) {
                return cost + mincost(sums, indexes, start, index) + mincost(sums, indexes, index, end);
            } else {
                return cost + mincost(sums, indexes, start, index - 1) + mincost(sums, indexes, index - 1, end);
            }
        }
    }

    public static void main(String[] argv) throws FileNotFoundException {
        long begin = System.currentTimeMillis();
        Scanner sc = new Scanner(new File(WebPath.getAbsolutePathWithClass("/input.txt").getPath()));
        //        Scanner sc = new Scanner(new BufferedInputStream(System.in));
        //        Scanner sc = new Scanner(System.in);
        //        System.setOut(new PrintStream(new FileOutputStream(new File(WebPath.getAbsolutePathWithClass().getPath() + "output.txt"))));
        //presolve
        //input
        int n = sc.nextInt();
        int[] lengths = new int[n + 1];
        Integer[] indexes = new Integer[n + 1];
        for (int i = 1; i <= n; i++) {
            int t = sc.nextInt();
            lengths[i] = t;
            indexes[i] = i;
        }

        //resolve
        Arrays.sort(lengths, 1, n + 1);
        int[] sums = new int[n + 1];
        int sum = 0;
        for (int i = 1; i <= n; i++) {
            sum += lengths[i];
            sums[i] = sum;
        }

        long ans = mincost(sums, indexes, 0, n);
        System.out.println(ans);

        //output
        sc.close();
        System.err.println("use time: " + (System.currentTimeMillis() - begin) / 1000.0 + " seconds.");
    }
}
