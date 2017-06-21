package com.leetcode;

import com.lqw.common.WebPath;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class FourSum {


    /**
     * user 2pointers
     * O(n^3 + nlogn)
     *
     * @param nums
     * @param target
     * @return
     */
    public List<List<Integer>> fourSum(int[] nums, int target) {
        int n = nums.length;
        ArrayList<List<Integer>> ans = new ArrayList<List<Integer>>();
        Arrays.sort(nums);
        for (int i = 0; i < n - 3; i++) {
            if (i >= 1 && nums[i] == nums[i - 1]) continue;
            for (int j = i + 1; j < n - 2; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) continue;
                int start = j + 1, end = n - 1;
                while (start < end) {
                    int sum = nums[i] + nums[j] + nums[start] + nums[end];
                    if (sum < target) {
                        while (++start < n && nums[start] == nums[start - 1]) ;
                    } else if (sum == target) {
                        //record
                        ans.add(Arrays.asList(nums[i], nums[j], nums[start], nums[end]));
                        while (++start < n && nums[start] == nums[start - 1]) ;
                    } else {
                        while (--end > 0 && nums[end] == nums[end + 1]) ;
                    }
                }
            }
        }
        return ans;
    }

    public static void main(String[] argv) throws FileNotFoundException {
        long begin = System.currentTimeMillis();
        Scanner sc = new Scanner(new File(WebPath.getAbsolutePathWithClass("/input.txt").getPath()));
        //        Scanner sc = new Scanner(new BufferedInputStream(System.in));
        //        Scanner sc = new Scanner(System.in);
        //        System.setOut(new PrintStream(new FileOutputStream(new File(WebPath.getAbsolutePathWithClass().getPath() + "output.txt"))));
        //presolve
        //input
        String[] str_arr = sc.nextLine().split(",");
        int target = sc.nextInt();
        int n[] = new int[str_arr.length];
        int idx = 0;
        for (String str : str_arr) {
            n[idx++] = Integer.parseInt(str);
        }

        //resolve
        List<List<Integer>> list = new FourSum().fourSum(n, target);
        for (List<Integer> list1 : list) {
            for (Integer intt : list1) {
                System.out.print(intt + "  ");
            }
            System.out.println();
        }

        //output
        sc.close();
        System.err.println("use time: " + (System.currentTimeMillis() - begin) / 1000.0 + " seconds.");
    }
}
