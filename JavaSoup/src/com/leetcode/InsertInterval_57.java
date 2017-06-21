package com.leetcode;

import com.algo_ds.tree.Interval;
import com.lqw.common.WebPath;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InsertInterval_57 {

    /**
     * Brute force, defination
     * resolve the new interval with the old ones one by one.
     * O(n)
     * AC
     *
     * @param intervals
     * @param newInterval
     * @return
     */
    public List<Interval> insert(List<Interval> intervals, Interval newInterval1) {
        ArrayList<Interval> ans = new ArrayList<>();
        ArrayList<Interval> t = new ArrayList<>();
        t.add(newInterval1);

        intervals.forEach(interval -> {
            if (t.size() == 1) {
                Interval newInterval = t.get(0);
                if (newInterval.start > interval.end) {
                    ans.add(interval);
                } else if (newInterval.start == interval.end) {
                    newInterval.start = interval.start;
                } else {
                    if (newInterval.end < interval.start) {
                        ans.add(newInterval);
                        ans.add(interval);
                        t.remove(0);
                    } else { //merge
                        newInterval.start = Math.min(newInterval.start, interval.start);
                        newInterval.end = Math.max(newInterval.end, interval.end);
                    }
                }
            } else {
                ans.add(interval);
            }
        });

        if (t.size() > 0) {
            ans.add(t.get(0));
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
        int n = sc.nextInt();
        ArrayList<Interval> a = new ArrayList<>();
        while (n-- > 0) {
            int s = sc.nextInt();
            int e = sc.nextInt();
            a.add(new Interval(s, e));
        }
        List<Interval> ans = (new InsertInterval_57()).insert(a, new Interval(sc.nextInt(), sc.nextInt()));
        ans.forEach(i -> {
            System.out.println(i.start + " " + i.end);
        });

        //resolve

        //output

        sc.close();
        System.err.println("use time: " + (System.currentTimeMillis() - begin) / 1000.0 + " seconds.");
    }
}
