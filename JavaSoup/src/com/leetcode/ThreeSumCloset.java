package com.leetcode;

import com.lqw.common.WebPath;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class ThreeSumCloset {

    // brute force, O(n^3)
    public int threeSumClosest_bf(int[] nums, int target) {
        int dis = Integer.MAX_VALUE;
        int ans_sum = 0;
        int n = nums.length;
        int ans_1 = 0, ans_2 = 0, ans_3 = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                for (int k = j + 1; k < n; k++) {
                    int dis_t = nums[i] + nums[j] + nums[k] - target;
                    if (Math.abs(dis_t) < dis) {
                        dis = Math.abs(dis_t);
                        ans_sum = nums[i] + nums[j] + nums[k];
                        ans_1 = nums[i];
                        ans_2 = nums[j];
                        ans_3 = nums[k];
                    }
                }
            }
        }

        System.err.println(ans_1 + " " + ans_2 + " " + ans_3);
        return ans_sum;
    }


    //2 pointers, O(n^2 + nlogn)
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int cha = Integer.MAX_VALUE;
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            int close_to = target - nums[i];
            int start = i + 1, end = nums.length - 1;
            while (start < end) {
                int c_cha = close_to - (nums[start] + nums[end]);
                if (Math.abs(c_cha) < cha) {
                    cha = Math.abs(c_cha);
                    sum = (nums[start] + nums[end] + nums[i]);
                }

                if (c_cha < 0) {
                    end--;
                } else if (c_cha > 0) {
                    start++;
                } else {
                    //==0, should return
                    return sum;
                }

            }
        }

        return sum;
    }

    public static void main(String[] argv) throws FileNotFoundException {
        long begin = System.currentTimeMillis();
        Scanner sc = new Scanner(new File(WebPath.getAbsolutePathWithClass("/input.txt").getPath()));
        //        Scanner sc = new Scanner(new BufferedInputStream(System.in));
        //        Scanner sc = new Scanner(System.in);
        //        System.setOut(new PrintStream(new FileOutputStream(new File(WebPath.getAbsolutePathWithClass().getPath() + "output.txt"))));
        //presolve
        //input
        int t = sc.nextInt();
        sc.nextLine();
        while (t-- > 0) {
            String[] str_arr = sc.nextLine().split(" ");
            System.err.println(str_arr.length);
            int nums[] = new int[str_arr.length];
            int idx = 0;
            for (String str : str_arr) {
                nums[idx++] = Integer.parseInt(str);
            }
            int target = sc.nextInt();
            sc.nextLine();
            int ans = new ThreeSumCloset().threeSumClosest(nums, target);
            System.out.println(ans);
        }

        //resolve

        //output
        sc.close();
        System.err.println("use time: " + (System.currentTimeMillis() - begin) / 1000.0 + " seconds.");
    }
}
