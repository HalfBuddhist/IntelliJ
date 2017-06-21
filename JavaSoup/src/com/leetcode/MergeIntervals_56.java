package com.leetcode;

import com.algo_ds.tree.*;
import com.lqw.common.WebPath;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class MergeIntervals_56 {


    /**
     * SPCS, interval tree for interval
     * build an interval tree for the non-single interval, the value indicates if the interval has covered
     * totally not partially.
     * a preorder traversal will get all the covered interval(mege if you can) in the order of ascending.
     * finally merge as the order with the single-element intervals.
     * <p>
     * O(n + nlogn + nlogM),
     * n, number of the intervals. M the length of the interval
     * AC
     *
     * @param intervals
     * @return
     */
    public List<Interval> merge(List<Interval> intervals) {
        //get the min and max
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        ArrayList<Interval> longIntervals = new ArrayList<>();
        ArrayList<Integer> singles = new ArrayList<>();
        for (Interval i : intervals) {
            if (i.isSinglePoint()) {
                singles.add(i.start);
            } else {
                min = Math.min(min, i.start);
                max = Math.max(max, i.end);
                longIntervals.add(i);
            }
        }
        //sort
        singles.sort(null);
        LinkedList<Integer> ll = new LinkedList<Integer>();
        Integer last = null;
        for (Integer i : singles) {//deduplicate
            if (last == null || !i.equals(last)) {
                ll.add(i);
            }
            last = i;
        }

        //form
        IntervalTree intervalTree = new IntervalTree(new Interval(min, max));
        longIntervals.forEach(intervalTree::add);

        ArrayList<Interval> ans = new ArrayList<Interval>();
        //traversal
        intervalTree.preOrder(node -> {
            IntervalTreeNode n = (IntervalTreeNode) node;
            //add the singles
            while (!ll.isEmpty()) {
                int top = ll.peek();
                int cmp = n.interval.where(top);
                if (cmp < 0) {
                    ans.add(new Interval(top, top));
                    ll.pollFirst();
                } else if (cmp == 0) {
                    ll.pollFirst();
                } else {
                    break;
                }
            }

            //merge
            if (ans.size() == 0) {
                ans.add(n.interval);
            } else {
                Interval i = ans.get(ans.size() - 1);
                if (i.end == n.interval.start) {
                    i.end = n.interval.end;
                } else {
                    ans.add(n.interval);
                }
            }
        });

        //add all the singles left.
        ll.forEach(i -> ans.add(new Interval(i, i)));

        return ans;
    }


    /**
     * Brute force, defination
     * sort the array as the start and end coordinates
     * merge the interval one by one.
     * O(n+nlogn)
     * AC
     */
    public List<Interval> merge2(List<Interval> intervals) {
        //sort
        intervals.sort((i, j) -> {
            if (i.start != j.start)
                return i.start - j.start;
            else
                return i.end - j.end;
        });

        //merge one by one
        LinkedList<Interval> ans = new LinkedList<Interval>();
        intervals.forEach(i -> {
            if (ans.size() == 0) {
                ans.add(i);
            } else {
                Interval last = ans.peekLast();
                if (i.start > last.end) {
                    ans.add(i);
                } else if (i.start == last.end) {
                    last.end = i.end;
                } else { // cross
                    if (i.end > last.end) {
                        last.end = i.end;
                    }
                }
            }
        });

        //return
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
        int n = sc.nextInt();
        ArrayList<Interval> arr = new ArrayList<Interval>();
        while (n-- > 0) {
            int s = sc.nextInt();
            int e = sc.nextInt();
            arr.add(new Interval(s, e));
        }

        //resolve
        List<Interval> ans = (new MergeIntervals_56()).merge(arr);
        for (Interval i : ans) {
            System.out.println(i.start + " " + i.end);
        }
        //output

        sc.close();
        System.err.println("use time: " + (System.currentTimeMillis() - begin) / 1000.0 + " seconds.");
    }
}
