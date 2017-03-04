package com.algo_ds.string;

import com.lqw.common.WebPath;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class KMP {

    /**
     * 计算模式串右划的最大距离的改进算法
     *
     * @param t
     */
    private static int[] get_nextval(String t) {
        int[] nextval = new int[t.length()];
        nextval[0] = -1;
        int i = 0, j = -1;
        while (i < t.length() - 1) {
            if (j == -1 || t.charAt(i) == t.charAt(j)) {
                i++;
                j++;
                if (t.charAt(i) != t.charAt(j))
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
     * @param t
     */
    private static int[] get_next(String t) {
        int[] next = new int[t.length()];
        next[0] = -1;
        int i = 0, j = -1;
        while (i < t.length() - 1) {
            if (j == -1 || t.charAt(i) == t.charAt(j)) {
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
     * @param s        源串
     * @param t        模式串
     * @param position 起始匹配位置
     * @return 匹配到的位置
     */
    public static int kmp_index(String s, String t, int position) {
        //get the next or nextval of the template string
        int[] nextval = get_nextval(t);

        //match and search
        int i = position, j = 0;
        while (i < s.length() && j < t.length()) {
            if (j == -1 || s.charAt(i) == t.charAt(j)) {
                i++;
                j++;
            } else j = nextval[j];
        }

        if (j >= t.length()) return i - t.length();
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
        for (int i = 0; i< nextval.length; i++)
            System.out.print(nextval[i] + " ");

        //resolve

        //output
        sc.close();
    }
}
