package com.leetcode;

import com.lqw.common.WebPath;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;


class Tuple {
    public int first, second, third;

    public Tuple(int i, int j, int k) {
        first = i;
        second = j;
        third = k;
    }

    public List<Integer> toList() {
        ArrayList res = new ArrayList();
        res.add(this.first);
        res.add(this.second);
        res.add(this.third);
        return res;
    }

    @Override
    public boolean equals(Object object) {
        Tuple tuple = (Tuple) object;
        if (first == tuple.first && second == tuple.second && third == tuple.third) {
            return true;
        } else return false;
    }

    @Override
    public int hashCode() {
        return new Integer(-first + second + third).hashCode();
    }
}

public class ThreeSum {

    //standard solution, O(n^2), 2 pointers
    public List<List<Integer>> threeSum_sol(int[] num) {
        Arrays.sort(num);
        List<List<Integer>> res = new LinkedList();
        for (int i = 0; i < num.length - 2; i++) {
            if (i == 0 || (i > 0 && num[i] != num[i - 1])) {
                int lo = i + 1, hi = num.length - 1, sum = 0 - num[i];
                while (lo < hi) {
                    if (num[lo] + num[hi] == sum) {
                        res.add(Arrays.asList(num[i], num[lo], num[hi]));
                        while (lo < hi && num[lo] == num[lo + 1]) lo++;
                        while (lo < hi && num[hi] == num[hi - 1]) hi--;
                        lo++;
                        hi--;
                    } else if (num[lo] + num[hi] < sum) lo++;
                    else hi--;
                }
            }
        }
        return res;
    }

    //O(n^2logn) and optimization
    public List<List<Integer>> threeSum(int[] nums) {
        //sort for the duplicat
        Arrays.sort(nums);

        //find in  binarysearch
        HashSet hs = new HashSet();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) break;
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] > 0) break;
                int target = 0 - nums[i] - nums[j];
                int res = Arrays.binarySearch(nums, j + 1, nums.length, target);
                if (res >= 0) {
                    hs.add(new Tuple(nums[i], nums[j], nums[res]));
                }
            }
        }

        //for the list
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        Iterator iter = hs.iterator();
        while (iter.hasNext()) {
            res.add(((Tuple) iter.next()).toList());
        }
        return res;
    }


    //O(n^3), n: number of nums, m: the target length.
    public List<List<Integer>> threeSum1(int[] nums) {
        //sort for the duplicat
        Arrays.sort(nums);

        HashSet hs = new HashSet();
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                for (int k = j + 1; k < nums.length; k++) {
                    if (nums[i] + nums[j] + nums[k] == 0) {
                        //remember
                        hs.add(new Tuple(nums[i], nums[j], nums[k]));
                    }
                }
            }
        }

        //for the list
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        Iterator iter = hs.iterator();
        while (iter.hasNext()) {
            res.add(((Tuple) iter.next()).toList());
        }
        return res;

    }

    public static void main(String[] argv) throws FileNotFoundException {
        long begin = System.currentTimeMillis();
        Scanner sc = new Scanner(new File(WebPath.getAbsolutePathWithClass("/input.txt").getPath()));
        //        Scanner sc = new Scanner(new BufferedInputStream(System.in));
        //        Scanner sc = new Scanner(System.in);
        //presolve
        //input
        String[] array = sc.next().split(",");
        int[] p = new int[array.length];
        int idx = 0;
        for (String str : array) {
            p[idx++] = Integer.parseInt(str);
        }
        List<List<Integer>> list = new ThreeSum().threeSum(p);
        for (List<Integer> l : list) {
            for (Integer in : l) {
                System.out.print(in + "\t");
            }
            System.out.println();
        }

        //resolve

        //output
        sc.close();
        System.err.println(p.length);
        System.err.println("use time: " + (System.currentTimeMillis() - begin) / 1000.0 + " seconds.");
    }
}
