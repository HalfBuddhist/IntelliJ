package com.leetcode;

import com.lqw.common.WebPath;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class WildcardMatching_44 {
    /**
     * match the prefix.
     *
     * @param fmatch
     * @param i
     * @param j
     * @param s
     * @param p
     * @param sta
     * @return
     */
    private boolean isPrefixMatch(int[][] fmatch, int i, int j, String s, String p, int sta[]) {
        if (j < 0)
            return i < 0;
        else if (i < 0) {
            if (p.charAt(j) == '*') return isPrefixMatch(fmatch, i, j - 1, s, p, sta);
            else return false;
        }

        //check cache
        if (fmatch[i][j] != 0) return fmatch[i][j] == 1;

        //calc
        boolean ans = false;
        char c = p.charAt(j);
        if (c == '?') {
            ans = isPrefixMatch(fmatch, i - 1, j - 1, s, p, sta);
        } else if (c == '*') {
            for (int t = sta[j] - 1; t <= i; t++) {
                boolean bl = isPrefixMatch(fmatch, t, j - 1, s, p, sta);
                if (bl) {
                    ans = true;
                    break;
                }
            }
        } else {
            if (c == s.charAt(i)) {
                ans = isPrefixMatch(fmatch, i - 1, j - 1, s, p, sta);
            } else {
                ans = false;
            }
        }

        //return
        fmatch[i][j] = ans ? 1 : 2;
        return ans;
    }

    /**
     * DP, base algo. in recursive.
     * f(i,j), if first i of s match the first j of p;
     * f(i,j) = true if f(i-1, j-1) and s[i] == p[j]
     * or p[j] = '*', one of the s[:x] match p[j-1], x:0<i;
     * else = false.
     * O(m*n); m, m length of the strings respectively.
     * AC, but slow.
     *
     * @param s
     * @param p
     * @return
     */
    public boolean isMatch_dp(String s, String p) {
        int m = s.length();
        int n = p.length();
        int fmatch[][] = new int[m][n];
        int sta[] = new int[n];
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            if (p.charAt(i) != '*') {
                cnt++;
            }
            sta[i] = cnt;
        }
        return isPrefixMatch(fmatch, m - 1, n - 1, s, p, sta);
    }

    /**
     * Backtracking, base algo;
     * Two pointers for each s and p to indicatet the compare point.
     * encounter ? and alpha, compare and do the corresponding return.
     * when *, only increase the pattern, and record the current state, the * currently match null string.
     * after conflict occure, check if * has been encoutered befer, backtracking and adjust the match string of *.
     * re-begine until reach the end of the source string.
     * O(m*n)； m, m length of the strings respectively.
     * AC， efficient.
     * the diff between above dp method, is the iterative way exploited which is more efficient that recursive way above,
     * because the stack operation in function calling is eliminated.
     *
     * @param str
     * @param pattern
     * @return
     */
    public boolean isMatch(String str, String pattern) {
        int strIDX = 0, patternIDX = 0, matchStrIDX = 0, starIDX = -1;
        while (strIDX < str.length()) {
            // advancing both pointers
            if (patternIDX < pattern.length() && (pattern.charAt(patternIDX) == '?' || str.charAt(strIDX) == pattern.charAt(patternIDX))) {
                strIDX++;
                patternIDX++;
            }
            // * found, only advancing pattern pointer
            else if (patternIDX < pattern.length() && pattern.charAt(patternIDX) == '*') {
                starIDX = patternIDX;
                matchStrIDX = strIDX;
                patternIDX++;
            }
            // last pattern pointer was *, advancing string pointer
            else if (starIDX != -1) {
                patternIDX = starIDX + 1;
                matchStrIDX++;
                strIDX = matchStrIDX;
            }
            //current pattern pointer is not star, last patter pointer was not *
            //characters do not match
            else return false;
        }

        //check for remaining characters in pattern
        while (patternIDX < pattern.length() && pattern.charAt(patternIDX) == '*')
            patternIDX++;

        return patternIDX == pattern.length();
    }

    public static void main(String[] argv) throws FileNotFoundException {
        long begin = System.currentTimeMillis();
        Scanner sc = new Scanner(new File(WebPath.getAbsolutePathWithClass("/input.txt").getPath()));
        //        Scanner sc = new Scanner(new BufferedInputStream(System.in));
        //        Scanner sc = new Scanner(System.in);
        //        System.setOut(new PrintStream(new FileOutputStream(new File(WebPath.getAbsolutePathWithClass().getPath() + "output.txt"))));
        //presolve
        //input
        String s = sc.next();
        String p = sc.next();
        System.out.println(new WildcardMatching_44().isMatch(s, p));

        //resolve

        //output
        sc.close();
        System.err.println("use time: " + (System.currentTimeMillis() - begin) / 1000.0 + " seconds.");
    }
}
