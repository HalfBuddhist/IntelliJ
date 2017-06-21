package com.algo_ds.string;

import com.lqw.common.WebPath;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class KMP {

    /**
     * 计算模式串右划的最大距离的改进算法
     * Faster than get_next in matching
     *
     * @param pattern
     */
    private static int[] get_nextval(String pattern) {
        int[] nextval = new int[pattern.length()];
        if (pattern.length() == 0) return nextval; // boundary value for "" pattern
        nextval[0] = -1;
        int i = 0, j = -1;
        while (i < pattern.length() - 1) {
            if (j == -1 || pattern.charAt(i) == pattern.charAt(j)) {
                i++;
                j++;
                if (pattern.charAt(i) != pattern.charAt(j))
                    nextval[i] = j;
                else nextval[i] = nextval[j];
            } else j = nextval[j];
        }
        return nextval;
    }


    /**
     * 计算模式串右划的最大距离
     * 由于string 本身有长度，所以与书中不同的是以-1为初始值代表需要移动主串指针。
     *
     * @param pattern
     */
    private static int[] get_next(String pattern) {
        int[] next = new int[pattern.length()];
        if (pattern.length() == 0) return next; // boundary value for "" pattern
        next[0] = -1;
        int i = 0, j = -1;
        while (i < pattern.length() - 1) {
            if (j == -1 || pattern.charAt(i) == pattern.charAt(j)) {
                i++;
                j++;
                next[i] = j;
            } else j = next[j];
        }
        return next;
    }


    /**
     * kmp算法
     *
     * @param source   源串
     * @param pattern  模式串
     * @param position 起始匹配位置
     * @return 匹配到的位置
     */
    public static int kmp_index(String source, String pattern, int position) {
        //get the next or nextval of the template string
        int[] nextval = get_nextval(pattern);

        //match and search
        int i = position, j = 0;
        while (i < source.length() && j < pattern.length()) {
            if (j == -1 || source.charAt(i) == pattern.charAt(j)) {
                i++;
                j++;
            } else j = nextval[j];
        }

        if (j >= pattern.length()) return i - pattern.length();
        else return -1;
    }

    public static void main(String[] argv) throws FileNotFoundException {
        Scanner sc = new Scanner(new File(WebPath.getAbsolutePathWithClass("/input.txt").getPath()));
        //        Scanner sc = new Scanner(new BufferedInputStream(System.in));
        //        Scanner sc = new Scanner(System.in);
        //input
        String s = sc.next();
        String t = sc.next();
        System.out.println(kmp_index(s, t, 9));


        int[] nextval = get_next(t);
        for (int i = 0; i < nextval.length; i++)
            System.out.print(nextval[i] + " ");

        //resolve

        //output
        sc.close();
    }
}
