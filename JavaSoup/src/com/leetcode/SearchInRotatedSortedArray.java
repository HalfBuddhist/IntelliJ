package com.leetcode;

import com.lqw.common.WebPath;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class SearchInRotatedSortedArray {

    /**
     * SPCS, 修改的二分查找
     * only one rotation, continuous and ordered mainly，
     * maybe a modifiedy binary search could works.
     * O(logn)
     *
     * @param nums
     * @param target
     * @return
     */
    public int search(int[] nums, int target) {

        int low = 0, high = nums.length - 1, mid;
        while (low <= high) {
            mid = (low + high) >>> 1;
            if (nums[mid] == target) return mid;
            else if (nums[mid] > nums[high]) {
                //end half is bad
                if (target < nums[mid] && target >= nums[low]) {
                    int index = Arrays.binarySearch(nums, low, mid, target);
                    return index >= 0 ? index : -1;
                } else {
                    low = mid + 1;
                }
            } else if (nums[mid] < nums[low]) {
                //start half is bad
                if (target > nums[mid] && target <= nums[high]) {
                    int index = Arrays.binarySearch(nums, mid + 1, high + 1, target);
                    return index >= 0 ? index : -1;
                } else {
                    high = mid - 1;
                }
            } else {
                //this is a binary search normally
                int index = Arrays.binarySearch(nums, low, high + 1, target);
                return index >= 0 ? index : -1;
            }
        }

        return -1;
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
        while (t-- > 0) {
            int target = sc.nextInt();
            sc.nextLine();
            String[] strs = sc.nextLine().split(" ");
            int a[] = new int[strs.length];
            int idx = 0;
            for (String s : strs) {
                a[idx++] = Integer.parseInt(s);
            }
            System.out.print(new SearchInRotatedSortedArray().search(a, target));

        }

        //resolve

        //output
        sc.close();
        System.err.println("use time: " + (System.currentTimeMillis() - begin) / 1000.0 + " seconds.");
    }
}
