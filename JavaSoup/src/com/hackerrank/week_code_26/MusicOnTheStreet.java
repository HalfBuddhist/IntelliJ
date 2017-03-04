package com.hackerrank.week_code_26;

import com.lqw.common.WebPath;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


/**
 * 使用滑动窗口, o(n)
 * <p/>
 * 特殊情况：
 * 只有一个点情况，1 0 10 5 5
 * m=1的情况， 1，0 1 1 1
 */
public class MusicOnTheStreet {

    public static int min, max, m, a[], n;

    public static int getLen(int idx) {
        if (idx == 0 || idx == n) return max;
        return a[idx] - a[idx - 1];
    }

    public static void main(String[] argv) throws FileNotFoundException {
        long begin = System.currentTimeMillis();
        Scanner sc = new Scanner(new File(WebPath.getAbsolutePathWithClass("/input.txt").getPath()));
        //        Scanner sc = new Scanner(new BufferedInputStream(System.in));
        //        Scanner sc = new Scanner(System.in);
        //presolve
        //input
        n = sc.nextInt();
        a = new int[n + 5];
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }
        m = sc.nextInt();
        min = sc.nextInt();
        max = sc.nextInt();

        //resolve
        int start_p = 0, idx_start = 0, cur_miles = 0;
        boolean find_b = false;
        //find the start.
        while (!find_b && idx_start <= n) {
            if (getLen(idx_start) >= min) {
                //find the end - next
                int next = idx_start;
                while (!find_b && next <= n) {
                    if (getLen(next) < min) {
                        idx_start = next + 1; //retrait;
                        break;
                    } else {
                        //could be end?
                        int l = m - cur_miles;//left miles;
                        if (((idx_start != next) &&
                                (l >= 2 * min && l <= 2 * max && l <= getLen(idx_start) + getLen(next))) ||
                                ((idx_start == next) && (l >= min && l <= max && l <= getLen(idx_start)))) {
                            //y
                            if (idx_start != next) {
                                int min1 = Math.min(max, l - min);
                                start_p = a[idx_start] - Math.min(getLen(idx_start), min1);
                            } else {
                                //end in the same interval
                                start_p = a[idx_start] - l;
                            }
                            find_b = true;
                        } else {//n, should accumulate
                            if (getLen(next) <= max) {
                                //accumulate
                                if (next != idx_start)
                                    cur_miles += getLen(next);
                                next += 1;
                            } else {//retrait
                                idx_start = next + 1;
                                break;
                            }
                        }
                    }
                }
            } else {
                idx_start++;
            }
            cur_miles = 0;
        }

        //output
        System.out.println(start_p);
        sc.close();
        System.err.println("use time: " + (System.currentTimeMillis() - begin) / 1000.0 + " seconds.");
    }
}