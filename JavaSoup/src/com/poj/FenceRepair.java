package com.poj;

import com.lqw.common.WebPath;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.PriorityQueue;
import java.util.Scanner;


/**
 * Imagination, visualization.
 * Huffman coding problem, Huffman tree.
 * Greedy, base algo.
 * select two min value from the leave node, merge into one new node and enroll the next selection.
 * iteratively stastics the cost of all.
 * O(nlogn)
 * AC
 */
public class FenceRepair {

    public static void mainn(String[] argv) throws FileNotFoundException {
        long begin = System.currentTimeMillis();
        Scanner sc = new Scanner(new File(WebPath.getAbsolutePathWithClass("/input.txt").getPath()));
        //        Scanner sc = new Scanner(new BufferedInputStream(System.in));
        //        Scanner sc = new Scanner(System.in);
        //        System.setOut(new PrintStream(new FileOutputStream(new File(WebPath.getAbsolutePathWithClass().getPath() + "output.txt"))));
        //presolve
        //input
        int n = sc.nextInt();
        int[] lengths = new int[n];
        PriorityQueue<Integer> queue = new PriorityQueue<Integer>();
        for (int i = 0; i < n; i++) {
            int t = sc.nextInt();
            lengths[i] = t;
            queue.add(lengths[i]);
        }

        //resolve
        long ans = 0;
        while (queue.size() >= 2) {
            Integer first = queue.poll();
            Integer sec = queue.poll();
            ans += first + sec;
            queue.add(new Integer(first + sec));
        }

        System.out.println(ans);

        //output
        sc.close();
        System.err.println("use time: " + (System.currentTimeMillis() - begin) / 1000.0 + " seconds.");
    }

    /**
     * O(n^2)
     * use travel to select the 2 min value instead the heap, that make the time complexity to N^2.
     *
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
        int[] lengths = new int[n];
        for (int i = 0; i < n; i++) {
            int t = sc.nextInt();
            lengths[i] = t;
        }

        //resolve
        long ans = 0;
        while (n > 1) {
            int min1 = 0, min2 = 1;
            if (lengths[min1] > lengths[min2]) {
                int t = min1;
                min1 = min2;
                min2 = t;
            }

            //get the 2 smallest elements.
            for (int i = 2; i < n; i++) {
                if (lengths[i] < lengths[min1]) {
                    min2 = min1;
                    min1 = i;
                } else if (lengths[i] < lengths[min2]) {
                    min2 = i;
                }
            }

            int t = lengths[min1] + lengths[min2];
            ans += t;

            if (min1 == n - 1) {
                int l = min1;
                min1 = min2;
                min2 = l;
            }
            lengths[min1] = t;
            lengths[min2] = lengths[n - 1];
            n--;
        }


        //output
        System.out.println(ans);
        sc.close();
        System.err.println("use time: " + (System.currentTimeMillis() - begin) / 1000.0 + " seconds.");
    }
}
