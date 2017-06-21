package com.leetcode;

import com.lqw.common.WebPath;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class PermutationsII {
    class Permutation {
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


        //find in a descending array.
        private int binarySearch(int[] a, int from, int to, int key) {
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
    }

    /**
     * Profession algo, permutation
     * use the next permutation to get all permutations one by one.
     * generation ends when the next permutations is the same with the original one.
     * O(n*n!)
     * AC
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> permuteUnique(int[] nums) {
        int origin[] = Arrays.copyOf(nums, nums.length);
        Permutation p = new Permutation();
        ArrayList<List<Integer>> ans = new ArrayList<List<Integer>>();

        do {
            ArrayList<Integer> templist = new ArrayList<Integer>();
            for (int ele : nums) {
                templist.add(ele);
            }
            ans.add(templist);
            p.nextPermutation(nums);

            //check if the same
            boolean hasCycled = true;
            for (int i = 0; i < nums.length; i++) {
                if (origin[i] != nums[i]) {
                    hasCycled = false;
                    break;
                }
            }
            if (hasCycled) break;
        } while (true);
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

        //resolve

        //output
        sc.close();
        System.err.println("use time: " + (System.currentTimeMillis() - begin) / 1000.0 + " seconds.");
    }
}
