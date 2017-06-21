package com.poj;

import com.lqw.common.WebPath;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;
import java.io.BufferedInputStream;

public class SlidingWindow_2823_MonotoneQueue {

    static int nums[] = new int[1000005];
    static Deque<Integer> maxMQ = new LinkedList<Integer>();
    static Deque<Integer> minMQ = new LinkedList<Integer>();
    static ArrayList<Integer> ans_max = new ArrayList<Integer>();
    static ArrayList<Integer> ans_min = new ArrayList<Integer>();

    /**
     * monotone queue, 单调队列
     * O(N)
     * 可以再用二分删除队尾元素来优化
     *
     * @param argv
     * @throws java.io.FileNotFoundException
     */
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


        maxMQ.clear();
        minMQ.clear();
        ans_max.clear();
        ans_min.clear();

        for (int i = 0; i < n; i++) {
            // for min q
            if (!minMQ.isEmpty() && i - minMQ.peekFirst() >= k)
                minMQ.pollFirst();
            while (!minMQ.isEmpty() && nums[minMQ.peekLast()] >= nums[i])
                minMQ.pollLast();
            minMQ.addLast(i);

            // for mq
            if (!maxMQ.isEmpty() && i - maxMQ.peekFirst() >= k)
                maxMQ.pollFirst();
            while (!maxMQ.isEmpty() && nums[maxMQ.peekLast()] <= nums[i])
                maxMQ.pollLast();
            maxMQ.addLast(i);

            if (i >= k - 1) {
                ans_max.add(nums[maxMQ.peekFirst()]);
                ans_min.add(nums[minMQ.peekFirst()]);
            }
        }


        //output
        //out
//        for (int i = 0; i <= n - k; i++) {
//            System.out.print(ans_min.get(i) + " ");
//        }
//        System.out.println();
//        for (int i = 0; i <= n - k; i++) {
//            System.out.print(ans_max.get(i)+" ");
//        }
//        System.out.println();

        int l = ans_min.size();
        for (int i = 0; i < l - 1; i++) {
            System.out.print(ans_min.get(i) + " ");
        }
        if (l - 1 >= 0) System.out.println(ans_min.get(l - 1));
        for (int i = 0; i < l - 1; i++) {
            System.out.print(ans_max.get(i) + " ");
        }
        if (l - 1 >= 0) System.out.println(ans_max.get(l - 1));

        sc.close();
        System.err.println("use time: " + (System.currentTimeMillis() - begin) / 1000.0 + " seconds.");
    }
}

