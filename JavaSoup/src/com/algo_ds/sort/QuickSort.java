package com.algo_ds.sort;

import java.util.Arrays;

/**
 * Created by Qingwei on 2017/6/7.
 */
public class QuickSort {

    /**
     * Entry for the quick sort algo, this is just an example for the integer arrays.
     * <p>
     * base algo, divide & conqer, the second recurseive diide conqer method.
     * <p>
     * Partition the array into two parts with respect to a pivot selected as the first element of the array.
     * left ones are not bigger than the pivot, while the right ones are not smaller.
     * Recursively partiton the left part and right part will generate the final solution.
     * while the current array has no or only one elements exit the recursive bottom.
     * <p>
     * O(nlog(n))
     * AC
     *
     * @param nums
     */
    public static void sort(int[] nums) {
        int n = nums.length;
        sort_in_range(nums, 0, n - 1);
    }

    /**
     * Partition the array nums into 2 parts, first is smaller ones and second or right is bigger ones.
     * <p>
     * SPCS, 2 pointers
     * <p>
     * Set the first element to be the pivot.
     * Select a smaller element than pivot from the end side, put it in the start redundant location, which makes the end redundant.
     * this operation gurantees the smaller is on the right, that is when index <= start, then ele <= pivot.
     * then select a bigger elementt from the start side, put it in the end redundant location, which makes the start redundant again.
     * this operation gurantees the bigger is on the right, that is when index >= end, then ele >= pivot.
     * repeated this process until the start meets the end and the element in start is redundant before and after a cycle,
     * pivot is missed in the array.
     * put back the pivot onto start redundant location.
     * <p>
     * O(n) n = start - end
     * AC
     *
     * @param nums  array to partition
     * @param start start location
     * @param end   end location
     * @return location of the pivot or the boundary of the two parts.
     */
    protected static int partition(int[] nums, int start, int end) {
        int pivot = nums[start];
        while (start < end) { // the element in start is redundant.
            //get th first litter than pivot
            while (start < end && nums[end] >= pivot) end--;
            nums[start] = nums[end];

            //get the first bigger that
            while (start < end && nums[start] <= pivot) start++;
            nums[end] = nums[start];
        }
        nums[start] = pivot;//start is redundant, and pivot is not in the array, so put it here, left is small and right is large.
        return start;
    }


    /**
     * Sort specific range of the the array recursively.
     *
     * @param nums
     * @param start
     * @param end
     */
    protected static void sort_in_range(int[] nums, int start, int end) {
        if (start < end) {
            int piv = partition(nums, start, end);
            sort_in_range(nums, start, piv - 1);
            sort_in_range(nums, piv + 1, end);
        }
    }
}
