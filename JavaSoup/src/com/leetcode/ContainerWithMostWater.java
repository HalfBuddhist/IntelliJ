package com.leetcode;

import com.lqw.common.WebPath;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ContainerWithMostWater {

    //use 2 pointers, O(n)
    public int maxArea1(int[] height) {
        int maxArea = Integer.MIN_VALUE;
        int i = 0, j = height.length - 1;
        while (i < j) {
            int area = (j - i) * (Math.min(height[i], height[j]));
            maxArea = Math.max(maxArea, area);
            if (height[i] < height[j]) {
                i++;
            } else {
                j--;
            }
        }
        return maxArea;
    }


    //brute force, O(n^2)
    public int maxArea(int[] height) {
        long max_r = Long.MIN_VALUE;
        int line1, line2;
        for (int i = 0; i < height.length; i++) {
            for (int j = i + 1; j < height.length; j++) {
                long r = (j - i) * (Integer.min(height[i], height[j]));
                if (r > max_r) {
                    max_r = r;
                    line1 = i;
                    line2 = j;
                }
            }
        }
        return (int) max_r;
    }

    public static void main(String[] argv) throws FileNotFoundException {
        long begin = System.currentTimeMillis();
        Scanner sc = new Scanner(new File(WebPath.getAbsolutePathWithClass("/input.txt").getPath()));
        //        Scanner sc = new Scanner(new BufferedInputStream(System.in));
        //        Scanner sc = new Scanner(System.in);
        //presolve
        //input
        String i = sc.next();
        String[] num_ar = i.split(",");
        int[] params = new int[num_ar.length];
        int idx = 0;
        for (String s : i.split(",")) {
            params[idx++] = Integer.parseInt(s);
        }

        System.out.println(new ContainerWithMostWater().maxArea(params));

        //resolve

        //output
        sc.close();
        System.err.println(num_ar.length);
        System.err.println("use time: " + (System.currentTimeMillis() - begin) / 1000.0 + " seconds.");
    }
}
