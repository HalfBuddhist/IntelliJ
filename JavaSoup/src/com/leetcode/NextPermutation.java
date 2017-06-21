package com.leetcode;

import com.lqw.common.WebPath;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.lang.IllegalArgumentException;

public class NextPermutation {
    /**
     * Brute force
     * find from the end, until find the invert to an increasing order,
     * replace it with the larger elements, and reverse the others to and decreasing order by swap.
     * O(logn + n) = O(n)
     * <p/>
     * Note the elements in the array may be the same.
     *
     * @param nums
     */
    public void nextPermutation(int[] nums) {
        //find the first invert
        int n = nums.length;
        int i = n - 1;
        if (i - 1 < 0) return; //only one element
        while (i - 1 >= 0) {
            if (nums[i] > nums[i - 1])
                break;
            i--;
        }
        int f, e;
        if (i - 1 >= 0) {
            //binarsy search the location to swap
            int t = binarySearch(nums, i, n, nums[i - 1]);       //same with the inherent method.
            int pos_swap;
            if (t < 0) {
                pos_swap = -t - 1 - 1;
                //-t-1 is the insertion point, so current ele in the pos is smaller, bigger is in
                //front of it, so it is -t-1-1 not -t-1.
            } else {//has the same ele, found nums[i-1]
                while (nums[t] == nums[i - 1]) t--;
                pos_swap = t;
            }
            //swap
            int tt = nums[i - 1];
            nums[i - 1] = nums[pos_swap];
            nums[pos_swap] = tt;
            //reverse the others
            f = i;
            e = n - 1;
        } else {//no found return the full reverse
            f = 0;
            e = n - 1;
        }
        //reverse the others
        while (f < e) {
            int tt = nums[f];
            nums[f] = nums[e];
            nums[e] = tt;
            f++;
            e--;
        }
    }


    public int binarySearch(int[] a, int from, int to, int key) {
        if (from > to) throw new IllegalArgumentException("Bad search scope!");
        to--; // to is excluded.
        while (from <= to) {
            int mid = (from + to) >>> 1;
            if (a[mid] > key) { // a sorted in descending order.
                from = mid + 1;
            } else if (a[mid] < key) {
                to = mid - 1;
            } else {
                return mid;
            }
        }

        //not found, must be negtive value to return
        //to is small than from, to = from -1;
        return -from - 1;
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
            String strs[] = sc.nextLine().split(" ");
            int a[] = new int[strs.length];
            int idx = 0;
            for (String str : strs) {
                a[idx++] = Integer.parseInt(str);
            }

            new NextPermutation().nextPermutation(a);
            for (int i : a) {
                System.out.print(i + " ");

            }
            System.out.println();
        }

        //resolve

        //output
        sc.close();
        System.err.println("use time: " + (System.currentTimeMillis() - begin) / 1000.0 + " seconds.");
    }
}
