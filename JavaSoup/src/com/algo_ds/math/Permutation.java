package com.algo_ds.math;


import org.apache.commons.lang3.ArrayUtils;

import java.util.Arrays;
import java.util.function.Consumer;
import java.util.function.IntUnaryOperator;

/**
 * 排列组合相关算法
 */
public class Permutation {

    public static boolean isFirstCall = true; /* indicate if the method first called.*/
    private static int[] factorial;

    /**
     * set the factorial array of n
     *
     * @param n
     */
    public static void setFactorial(int n) {
        factorial = new int[n + 1];
        factorial[0] = 1;
        for (int i = 1; i <= n; i++) {
            factorial[i] = i * factorial[i - 1];
        }
    }

    /**
     * Find the next permutation in the natural order(that is LexicographicalOrder).
     * also works when duplicated elements exist.
     * <p>
     * Brute force
     * find from the end, until find the invert to an increasing order,
     * replace it with the larger elements, and reverse the others to and decreasing order by swap.
     * O(logn + n) = O(n)
     * <p>
     * Note set the isFirstCall = true when call to generate the whole permutations.
     * if just want a next permu of the current nums, set the isFirstCall = false;
     *
     * @param nums - integer array.
     * @return is come to the end.
     */
    public static boolean nextPermutation_Lexical(int[] nums) {
        if (isFirstCall) {
            Arrays.setAll(nums, i -> i + 1);
            isFirstCall = false;
            return true;
        }
        //find the first invert
        int n = nums.length;
        boolean ans = true;
        int i = n - 1;
        if (i - 1 < 0) return false; //only one element
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
            ans = false;
            isFirstCall = true;
        }
        //reverse the others
        while (f < e) {
            int tt = nums[f];
            nums[f] = nums[e];
            nums[e] = tt;
            f++;
            e--;
        }
        return ans;
    }

    /**
     * get the next k'th permutation in lexical order of the current permu.
     * effecitve only when the element is unique.
     * better not to repeatly call to generation the whole permutation.
     * <p>
     * O(nlogn)
     * AC
     *
     * @param nums
     * @param k
     */
    public static void nextKthLexicalPermutation(int[] nums, int k) {
        //get the current Intermediary number of the permu
        IntermediaryItf intermediary = new LexicalIntermediary(nums);
//        IntermediaryItf intermediary = new LexicalIntermediary_LL(nums);

        //Intermediary number calc
        intermediary.add(k);

        //restore permu from the Intermediary number caled.
        intermediary.restorePermu(nums);
    }

    /**
     * get the next k'th permutation in ordinal order of the current permu.
     * effecitve only when the element is unique.
     * better not to repeatly call to generation the whole permutation.
     * O(n^2)
     *
     * @param nums
     * @param k
     */
    public static void nextKthOrdinalPermutation(int[] nums, int k) {
        //get the current Intermediary number of the permu
        IntermediaryItf intermediary = new OrdinalIntermediary(nums);

        //Intermediary number calc
        intermediary.add(k);

        //restore permu from the Intermediary number caled.
        intermediary.restorePermu(nums);
    }

    /**
     * get the next k'th permutation in ordinal order of the current permu.
     * effecitve only when the element is unique.
     * provided to repeatly call to generation the whole permutation if have to,
     * because this method is less effective.
     * O(n^2)
     *
     * @param nums
     * @param intermediary
     * @param k
     */
    public static void nextKthOrdinalPermutation(int[] nums, OrdinalIntermediary intermediary, int k) {
        //Intermediary number calc
        intermediary.add(k);

        //restore permu from the Intermediary number caled.
        intermediary.restorePermu(nums);
    }

    // local variable in adjacent exchange method.
    private static int[] D;
    private static boolean[] E;


    /**
     * Get the next permutation use the adjexchange method.
     * <p>
     * One feature of this method is that the modificaton of every two adjacent permutations is
     * just one exchange of two adjacent elements.
     * <p>
     * This is the most effective method to generate the whole permuation with uqiue elements.
     * see the combinatorics for reference.
     * this method must be called from the start not the middle, and set the isFirstCall = true.
     * <p>
     * O(1) most of time
     * although there is a for cycle, but most of the time, the cycle is break at k=n once.
     * only O(n) when generate every n permutations, so average is O(1).
     *
     * @return if come to the end
     */
    public static boolean nextPermutation_AdjExchange(int[] A) {
        int n = A.length;
        if (isFirstCall) {
            D = new int[n + 1];
            E = new boolean[n + 1]; //false is the start
            Arrays.setAll(D, i -> i);
            Arrays.setAll(A, i -> i + 1);
            isFirstCall = false;
            return true;
        } else {
            int q = 0, p;
            for (int k = n; k >= 2; k--) {
                D[k] += (E[k] ? 1 : -1);
                p = D[k];
                if (p == k) {
                    E[k] = false;
                } else if (p == 0) {
                    E[k] = true;
                    q++;
                } else {
                    p += q;
                    int t = A[p - 1];
                    A[p - 1] = A[p];
                    A[p] = t;
                    return true;
                }
            }
            isFirstCall = true;
            return false;
        }
    }

    /**
     * An simple recurseive method to generate all permutations.
     * <p>
     * the permuations generation algo, this is completed by swap the current ele with the later element  consecutively
     * and merge this locatoin with the whole permuations of the later elemnent. Buth the complexity is also the n*n!.
     * if youd don't remember this youd could also use the other permutations algos depicted above.
     * But the prunning process will be not so intuitionistic as this mehtod.
     * <p>
     * one advantage of this method is easy to pruning some branches of the permutations
     * <p>
     * O(n * n!)
     * n is the time complexity of the backtracking operation.
     *
     * @param nums the uninitialized array of integer
     * @param c    consumer of the permutation.
     */
    public static void traversal_permutation_recursive(int[] nums, Consumer<int[]> c) {
        Arrays.setAll(nums, i -> i + 1);
        traversal_permutation_from(0, nums, c);
    }

    /**
     * An auxiliary func to 'traversal_permulation_recursive'
     * traversal the permutations with the interval start from (start).
     *
     * @param start
     * @param n
     * @param c
     */
    private static void traversal_permutation_from(int start, int[] nums, Consumer<int[]> c) {
        if (start == nums.length) {//get an permutation.
            c.accept(nums);
            return;
        }

        //generate all the permutation
        int n = nums.length;
        for (int i = start; i < n; i++) {
            //if (...) { // some prunning work.
            ArrayUtils.swap(nums, start, i);
            traversal_permutation_from(start + 1, nums, c);
            ArrayUtils.swap(nums, start, i);
        }
    }


    /**
     * find in a descending array.
     * auxiliary fucntion of nextPermutation_Lexical
     *
     * @param a
     * @param from included
     * @param to   excluded
     * @param key
     * @return
     */
    private static int binarySearch(int[] a, int from, int to, int key) {
        if (from >= to) throw new IllegalArgumentException("Bad search scope!");
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
