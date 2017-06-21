package com.leetcode;

import com.algo_ds.tree.no_inherit.CntAVLTree;
import com.lqw.common.WebPath;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

public class PermutationSequence_60 {
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
     * O(nk) <= O(n*n!)
     * AC, but very slow.
     */
    public String getPermutation(int n, int k) {
        int[] arr = new int[n];
        for (int i = 1; i <= n; i++) {
            arr[i - 1] = i;
        }
        Permutation p = new Permutation();

        while (--k > 0) {
            p.nextPermutation(arr);
        }

        //form an string
        StringBuilder sb = new StringBuilder("");
        for (int i = 1; i <= n; i++) {
            sb.append(arr[i - 1]);
        }

        return sb.toString();
    }


    /**
     * brute force, defination.
     * decide every element one by one, the everty change in the ith position
     * has a factor of (i-1) permulations totally.
     * given the k, you could decide the every elements use the above fomula, then
     * k<-k%factor(i-1), process cyclely and get the solution.
     * <p/>
     * (n^2log(n))
     * AC
     *
     * @param n
     * @param k
     * @return
     */
    public String getPermutation2(int n, int k) {
        k = k - 1;
        //calc factor
        int factor[] = new int[n];
        factor[0] = 1;
        for (int i = 1; i < n; i++) {
            factor[i] = i * (factor[i - 1]);
        }

        int ans[] = new int[n];
        for (int i = 0; i < n; i++) {
            ans[i] = i + 1;
        }
        for (int i = 0; i < n; i++) {
            //decide every element one by one
            int cur = k / (factor[n - 1 - i]);
            k %= factor[n - 1 - i];

            int t = ans[n - 1 - i];
            ans[n - 1 - i] = ans[cur];
            ans[cur] = t;
            Arrays.sort(ans, 0, n - 1 - i);
        }

        //form an string
        StringBuilder sb = new StringBuilder("");
        for (int i = 1; i <= n; i++) {
            sb.append(ans[n - i]);
        }

        return sb.toString();
    }


    /**
     * brute force, defination.
     * decide every element one by one, the every change in the ith position
     * has a factor of (i-1) permulations totally.
     * given the k, you could decide the every elements use the above fomula, then
     * k<-k%factor(i-1), process cyclely and get the solution.
     * use a LikedList instead of the array
     * <p/>
     * O(n^2)
     * AC
     *
     * @param n
     * @param k
     * @return
     */
    public String getPermutation3(int n, int k) {
        k = k - 1;
        //calc factor
        int factor[] = new int[n];
        LinkedList<Integer> linkedList = new LinkedList<Integer>();
        factor[0] = 1;
        for (int i = 1; i < n; i++) {
            factor[i] = i * (factor[i - 1]);
            linkedList.add(i);
        }
        linkedList.add(n);

        //resolve
        StringBuilder sb = new StringBuilder("");
        for (int i = 0; i < n; i++) {
            //decide every element one by one
            int cur = k / (factor[n - 1 - i]);
            k %= factor[n - 1 - i];
            //get the current number
            Integer t = linkedList.remove(cur);
            sb.append(t);
        }

        return sb.toString();
    }


    /**
     * brute force, defination.
     * decide every element one by one, the every change in the ith position
     * has a factor of (i-1) permulations totally.
     * given the k, you could decide the every elements use the above fomula, then
     * k<-k%factor(i-1), process cyclely and get the solution.
     * use a avl tree instead of the linkedlist
     * <p/>
     * O(nlogn)
     * AC
     *
     * @param n
     * @param k
     * @return
     */
    public String getPermutation4(int n, int k) {
        k = k - 1;
        //calc factor
        int factor[] = new int[n];
        factor[0] = 1;
        CntAVLTree<Integer> tree = new CntAVLTree<Integer>();
        for (int i = 1; i < n; i++) {
            factor[i] = i * (factor[i - 1]);
            tree.insert(i);
        }
        tree.insert(n);

        //resolve
        StringBuilder sb = new StringBuilder("");
        for (int i = 0; i < n; i++) {
            //decide every element one by one
            int cur = k / (factor[n - 1 - i]);
            k %= factor[n - 1 - i];
            //get the cur'th number
            int t = tree.k_remove(cur+1);
            sb.append(t);
        }

        return sb.toString();
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
        String s = (new PermutationSequence_60()).getPermutation4(8, 100);
        System.out.println(s);

        //output

        sc.close();
        System.err.println("use time: " + (System.currentTimeMillis() - begin) / 1000.0 + " seconds.");
    }
}
