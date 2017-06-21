package com.leetcode;

import com.lqw.common.WebPath;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MedianOfTwoSortedArrays2 {

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length, n = nums2.length;
        if (n < m) {
            return findMedianSortedArrays(nums2, nums1);
        } else {
            int imin = 0, imax = m, i = 0, j = 0;
            while (true) {
                i = (imin + imax) / 2;
                j = (m + n + 1) / 2 - i;
                if ((i == 0 || j == n || nums1[i - 1] <= nums2[j]) &&
                        (j == 0 || i == m || nums2[j - 1] <= nums1[i])) {
                    break;
                } else if (i < m && nums2[j - 1] > nums1[i]) {
                    imin = i + 1;
                } else if (i > 0 && nums1[i - 1] > nums2[j]) {
                    imax = i - 1;
                }
            }

            if ((m + n) % 2 != 0) {
                if ((i + j) > (m - i + n - j)) {
                    return Math.max(i > 0 ? nums1[i - 1] : Integer.MIN_VALUE, j > 0 ? nums2[j - 1] : Integer.MIN_VALUE);
                } else
                    return Math.min(i < m ? nums1[i] : Integer.MAX_VALUE, j < n ? nums2[j] : Integer.MAX_VALUE);
            } else {
                return (Math.max(i > 0 ? nums1[i - 1] : Integer.MIN_VALUE, j > 0 ? nums2[j - 1] : Integer.MIN_VALUE) + +
                        Math.min(i < m ? nums1[i] : Integer.MAX_VALUE, j < n ? nums2[j] : Integer.MAX_VALUE)) / 2.0;
            }
        }
    }

    public static void main(String[] argv) throws FileNotFoundException {
        long begin = System.currentTimeMillis();
        Scanner sc = new Scanner(new File(WebPath.getAbsolutePathWithClass("/input.txt").getPath()));
        //        Scanner sc = new Scanner(new BufferedInputStream(System.in));
        //        Scanner sc = new Scanner(System.in);
        //presolve
        //input
        String[] ar1 = sc.nextLine().split(",");
        int[] list1 = new int[ar1.length];
        int i =0;
        for (String s : ar1){
            list1[i++] = Integer.parseInt(s);
        }

        String[] ar2 = sc.nextLine().split(",");
        int[] list2 = new int[ar2.length];
        i =0;
        for (String s : ar2){
            list2[i++] = Integer.parseInt(s);
        }

//        int[] a = ;
//        int[] b = {1};
        System.out.println(new MedianOfTwoSortedArrays().findMedianSortedArrays(list1, list2));

        //resolve

        //output
        sc.close();
        System.err.println("use time: " + (System.currentTimeMillis() - begin) / 1000.0 + " seconds.");
    }
}
