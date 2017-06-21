package com.leetcode;

import com.algo_ds.tree.RepeatedAVLTree;
import com.lqw.common.WebPath;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.PriorityQueue;
import java.util.Scanner;

class IDXJump implements Comparable<IDXJump> {
    int idx;
    int maxJump2;
    int leastJump;

    public IDXJump(int idx, int max, int leastJump) {
        this.idx = idx;
        this.maxJump2 = max;
        this.leastJump = leastJump;
    }

    @Override
    public int compareTo(IDXJump o) {
        return this.maxJump2 - o.maxJump2;
    }
}

public class JumpGameII_45 {

    public int jump(int[] nums) {
        PriorityQueue<IDXJump> priorityQueue = new PriorityQueue<IDXJump>();
        RepeatedAVLTree<Integer> repeatedAVLTree = new RepeatedAVLTree<Integer>();
        int n = nums.length;
        IDXJump[] jumps = new IDXJump[n];

        for (int i = 0; i < n; i++) {
            jumps[i] = new IDXJump(i, i + nums[i], -1);
            while (!priorityQueue.isEmpty() && priorityQueue.peek().maxJump2 < i) {
                IDXJump t = priorityQueue.poll();
                repeatedAVLTree.remove(t.leastJump);
            }

            //get f
            if (repeatedAVLTree.getmRoot() == null) jumps[i].leastJump = 0;//for the 0 situation
            else
                jumps[i].leastJump = 1 + (Integer) (repeatedAVLTree.minimum());

            //for next
            repeatedAVLTree.insert(jumps[i].leastJump);
            priorityQueue.add(jumps[i]);
        }

        return jumps[n - 1].leastJump;
    }

    public int dp(int[] nums, int[] f, int i) {
        if (f[i] != Integer.MAX_VALUE) return f[i];
        //calc
        int minn = Integer.MAX_VALUE - 1;
        for (int j = 0; j <= i - 1; j++) {
            int cha = i - j;
            if (cha <= nums[j])
                minn = Math.min(minn, dp(nums, f, j) + 1);
            else {
                continue;
            }
        }
        f[i] = minn;
        return minn;
    }

    /**
     * stack overflow,
     * O(n^2)
     *
     * @param nums
     * @return
     */
    public int jump_dp_recursive(int[] nums) {
        int n = nums.length;
        int[] f = new int[n];
        for (int i = 0; i < n; i++) {
            f[i] = Integer.MAX_VALUE;
        }

        if (n > 0) f[0] = 0;
        return dp(nums, f, n - 1);
    }


    /**
     * Brute force, defination
     * enumerate every jump from the beginning, the jump number is the current jump plusing one.
     * for every point picking the least jump numbers.
     * O(n*max(mi))
     * TLE
     *
     * @param nums
     * @return
     */
    public int jump_bf(int[] nums) {
        int n = nums.length;
        int[] f = new int[n];
        for (int i = 0; i < n; i++) {
            f[i] = Integer.MAX_VALUE;
        }

        if (n > 0) f[0] = 0;

        for (int i = 0; i < n; i++) {
            int max_jump = nums[i];
            for (int j = 0; j <= max_jump; j++) {
                if (i + j < n)
                    f[i + j] = Math.min(f[i + j], f[i] + 1);
                else {
                    break;
                }
            }
        }

        return f[n - 1];
    }


    public static void main(String[] argv) throws FileNotFoundException {
        long begin = System.currentTimeMillis();
        Scanner sc = new Scanner(new File(WebPath.getAbsolutePathWithClass("/input2.txt").getPath()));
        //        Scanner sc = new Scanner(new BufferedInputStream(System.in));
        //        Scanner sc = new Scanner(System.in);
        System.setOut(new PrintStream(new FileOutputStream(new File(WebPath.getAbsolutePathWithClass().getPath() + "output.txt"))));
        //presolve
        //input
        while (sc.hasNext()) {
            int n = sc.nextInt();
            int ar[] = new int[n];
            for (int i = 0; i < n; i++) {
                ar[i] = sc.nextInt();
            }
            System.out.println((new JumpGameII_45()).jump(ar));
        }


//
//        int n = sc.nextInt();
//        int ar[] = new int[n];
//        for (int i = 0; i < n; i++) {
//            ar[i] = sc.nextInt();
//        }
//        System.out.println((new JumpGameII_45()).jump(ar));

//        System.out.println((new JumpGameII_45()).jump(new int[]{2, 3, 1, 1, 4}));

        //resolve

        //output
        sc.close();
        System.err.println("use time: " + (System.currentTimeMillis() - begin) / 1000.0 + " seconds.");
    }
}
