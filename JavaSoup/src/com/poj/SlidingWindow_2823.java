package com.poj;

import com.lqw.common.WebPath;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class SlidingWindow_2823 {
    /**
     * priority queue, 因为在堆中查找元素不变，所以复杂度仍为NK, 与暴力法相同
     * TLE
     * @param argv
     * @throws FileNotFoundException
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
        int nums[] = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }
        PriorityQueue<Integer> maxPQueue = new PriorityQueue<Integer>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2.intValue() - o1.byteValue();
            }
        });
        PriorityQueue<Integer> minPQueue = new PriorityQueue<Integer>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.intValue() - o2.byteValue();
            }
        });

        //resolve
        ArrayList<Integer> ans_min = new ArrayList<Integer>();
        ArrayList<Integer> ans_max = new ArrayList<Integer>();

        // cycle do the same
        for (int i = 0; i < n; i++) {
            maxPQueue.add(nums[i]);
            minPQueue.add(nums[i]);
            if (maxPQueue.size() >= k) {
                //output the max min
                ans_min.add(minPQueue.peek());
                ans_max.add(maxPQueue.peek());

                //next in and out
                minPQueue.remove(nums[i - k + 1]);
                maxPQueue.remove(nums[i - k + 1]);
            }
        }

        //output
        for (int i = 0; i < ans_min.size() - 1; i++) {
            System.out.print(ans_min.get(i) + " ");
        }
        if (ans_min.size() - 1 >= 0) System.out.println(ans_min.get(ans_min.size() - 1));
        for (int i = 0; i < ans_max.size() - 1; i++) {
            System.out.print(ans_max.get(i) + " ");
        }
        if (ans_max.size() - 1 >= 0) System.out.println(ans_max.get(ans_max.size() - 1));

        sc.close();
        System.err.println("use time: " + (System.currentTimeMillis() - begin) / 1000.0 + " seconds.");
    }
}

