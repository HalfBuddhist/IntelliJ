package com.leetcode;

import com.lqw.common.WebPath;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegularExpressionMatching {
    public String s;
    public String p;
    boolean[][] is_prefix_match;
    boolean[][] is_prefix_match_set;

    /**
     * DP, base algo.
     * f(i,j) 表示以i,j为前缀的两个子串是否匹配，是直接分解，递推关系如下：
     * 在模式串p中从后往前找到一个单元 u，这个单元包括一个*，因为只有这个时候才会导致多种区配结果，没有则可以直接匹配完全串。
     * 用u匹配一个在源串的可变区间 k，(m,n)
     * f(i,j)
     * = false if k does not exist else
     * = f(m-1, j-u) || f(m, j-u) || f(m+1, j-u) || .... || f(n, j-u)
     * <p/>
     * TC: O(m*n)
     * AC
     *
     * @param s_idx
     * @param p_idx
     * @return
     */
    public boolean isPrefixMatch(int s_idx, int p_idx) {
        if (s_idx < 0) return false;
        if (is_prefix_match_set[s_idx][p_idx]) {
            return is_prefix_match[s_idx][p_idx];
        } else {
            //get a unit from p (start_idx, p_idx-1), which include a *, if no should be the entire pattern
            int start_idx = p_idx - 1, end_idx = p_idx - 1;
            while (start_idx >= 0 && p.charAt(start_idx) != '*') {
                start_idx--;
            }
            if (start_idx >= 1)
                start_idx--;
            else start_idx = 0;//set to 0, no*

            //get a len in s of which the string match;
            int spec_idx = s_idx - 1;//should >=0, s_idx = 0?????
            for (int i = end_idx; i >= start_idx; ) {
                char c = p.charAt(i);
                switch (c) {
                    case '.': {
                        if (spec_idx >= 0) {
                            i--;
                            spec_idx--;
                            break;
                        } else {
                            is_prefix_match_set[s_idx][p_idx] = true;
                            is_prefix_match[s_idx][p_idx] = false;
                            if (spec_idx + 1 >= 0) {
                                is_prefix_match_set[spec_idx + 1][i + 1] = true;
                                is_prefix_match[spec_idx + 1][i + 1] = false;
                            }
                            return false;
                        }
                    }
                    case '*': {
                        int len;
                        if (p.charAt(i - 1) == '.') {
                            len = spec_idx + 1;
                        } else {
                            int idx = spec_idx;
                            while (idx >= 0 && s.charAt(idx) == p.charAt(i - 1)) {
                                idx--;
                            }
                            len = spec_idx - idx;
                        }

                        boolean result = false;
                        for (int j = 0; j <= len; j++) {
                            result = result || (isPrefixMatch(spec_idx + 1 - j, start_idx));
                        }
                        is_prefix_match_set[s_idx][p_idx] = true;
                        is_prefix_match[s_idx][p_idx] = result;
                        return result;
                    }
                    default: {
                        if (spec_idx >= 0 && c == s.charAt(spec_idx)) {
                            i--;
                            spec_idx--;
                            break;
                        } else {
                            is_prefix_match_set[s_idx][p_idx] = true;
                            is_prefix_match[s_idx][p_idx] = false;
                            if (spec_idx + 1 >= 0) {
                                is_prefix_match_set[spec_idx + 1][i + 1] = true;
                                is_prefix_match[spec_idx + 1][i + 1] = false;
                            }
                            return false;
                        }
                    }
                }
            }
            // no *, must be from start
            boolean res = spec_idx == -1;
            is_prefix_match_set[s_idx][p_idx] = true;
            is_prefix_match[s_idx][p_idx] = res;
            return res;
        }
    }


    public boolean isMatch(String s, String p) {
        this.s = s;
        this.p = p;
        is_prefix_match = new boolean[s.length() + 1][p.length() + 1];
        is_prefix_match_set = new boolean[s.length() + 1][p.length() + 1];

        for (int i = 0; i < s.length() + 1; i++) {
            is_prefix_match[i][0] = false;
            is_prefix_match_set[i][0] = true;
        }
        is_prefix_match[0][0] = true;
        is_prefix_match_set[0][0] = true;

        //calc
        return isPrefixMatch(s.length(), p.length());
    }

    public boolean isMatch_use_pattern(String s, String p) {

        Pattern pattern = Pattern.compile(p);
        Matcher matcher = pattern.matcher(s);
        return matcher.matches();
    }


    public static void main(String[] argv) throws FileNotFoundException {
        long begin = System.currentTimeMillis();
        Scanner sc = new Scanner(new File(WebPath.getAbsolutePathWithClass("/input.txt").getPath()));
        //        Scanner sc = new Scanner(new BufferedInputStream(System.in));
        //        Scanner sc = new Scanner(System.in);
        //presolve
        //input
        String a = sc.next();
        String b = sc.next();
        System.out.println(new RegularExpressionMatching().isMatch(a, b));

        //resolve

        //output
        sc.close();
        System.err.println("use time: " + (System.currentTimeMillis() - begin) / 1000.0 + " seconds.");
    }
}
