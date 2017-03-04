package com.hackerrank.algo.string;

import com.lqw.common.WebPath;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.TreeSet;

class Index2D implements Comparable {
    int i;
    int j;

    Index2D(int i, int j) {
        this.i = i;
        this.j = j;
    }

    @Override
    public boolean equals(Object obj) {
        Index2D p = (Index2D) obj;
        return this.i == p.i && this.j == p.j;
    }

    @Override
    public int compareTo(Object o) {
        Index2D p = (Index2D) o;
        return this.i - p.i != 0 ? (this.i - p.i) : (this.j - p.j);
    }
}


/**
 * 先预处理两个串，统计串中每个位置右边最大的回文长度。
 * 遍历两个串中的每一对元素，找到最大的相同部分后，再加上相邻位置的最大回文长度的串即可。
 * 最好的情况是n^2, 最坏情况下是n^3
 */
public class BuildPalindrome {

    /**
     * get the max length palindrome given the start index.
     * 最差的情况是n^2, 一般情况下是n
     *
     * @param first
     * @return
     */
    private static int[] preprocess(String first) {
        //presolve
        int[] max_len_plin = new int[first.length() + 1];
        int axis1 = 0, axis2 = 0;
        while (axis2 <= first.length() - 1) {
            //if in the middle
            boolean is_middle = axis1 == axis2;
            int base = is_middle ? 1 : 2;
            //find the palindrome
            int max_exp = Math.min(axis1 - 0, first.length() - 1 - axis2), exp = 0;
            for (exp = 0; exp <= max_exp; exp++) {
                if (first.charAt(axis1 - exp) != first.charAt(axis2 + exp)) {
                    break;
                } else {            //update index
                    max_len_plin[axis1 - exp] = Math.max(max_len_plin[axis1 - exp], base + 2 * exp);
                }
            }
            //shift right.
            if (axis1 == axis2) axis2++;
            else axis1++;
        }

        return max_len_plin;
    }


    public static void main(String[] argv) throws FileNotFoundException {
        Scanner sc = new Scanner(new File(WebPath.getAbsolutePathWithClass("/input.txt").getPath()));
        //        Scanner sc = new Scanner(new BufferedInputStream(System.in));
        //        Scanner sc = new Scanner(System.in);
        //input
        int q = sc.nextInt();
        while (q-- > 0) {
            String first = sc.next();
            String second = sc.next();
            String rev_second = new StringBuilder(second).reverse().toString();

            //presolve
            int[] max_len_plin_1 = preprocess(first);
            max_len_plin_1[first.length()] = -1;
            int[] max_len_plin_2 = preprocess(rev_second);
            max_len_plin_2[rev_second.length()] = -1;

            String cur_max_plin = null;
            TreeSet<Index2D> treeSet = new TreeSet<Index2D>();

            for (int i = 0; i < first.length(); i++) {
                for (int j = 0; j < rev_second.length(); j++) {
//                    if (treeSet.contains(new Index2D(i, j))) {
//                        treeSet.remove(new Index2D(i,j));
//                        continue;
//                    }

                    //calc
                    int end_idx_first = i, end_idx_second = j;
                    while (end_idx_first < first.length() && end_idx_second < rev_second.length() &&
                            first.charAt(end_idx_first) == rev_second.charAt(end_idx_second)) {
//                        treeSet.add(new Index2D(end_idx_first, end_idx_second));
                        end_idx_first++;
                        end_idx_second++;
                    }
                    if (end_idx_first > i) { //get at least one common character.
                        //get one two three
                        String one = first.substring(i, end_idx_first); //one and three
                        //two
                        String two = "";
                        //first comp
                        String first_comp = max_len_plin_1[end_idx_first] == -1 ? "" :
                                first.substring(end_idx_first, end_idx_first + max_len_plin_1[end_idx_first]);
                        //second comp
                        String second_cmp = max_len_plin_2[end_idx_second] == -1 ? "" :
                                rev_second.substring(end_idx_second, end_idx_second + max_len_plin_2[end_idx_second]);
                        //get two
                        if (first_comp.length() < second_cmp.length()) two += second_cmp;
                        else if (first_comp.length() > second_cmp.length()) two += first_comp;
                        else if (first_comp.compareTo(second_cmp) <= 0) two += first_comp;
                        else two += second_cmp;

                        String temp = one + two + (new StringBuilder(one).reverse().toString());

                        //update
                        if (cur_max_plin == null || cur_max_plin.length() < temp.length() || (cur_max_plin.length() == temp.length() && cur_max_plin.compareTo(temp) > 0)) {
                            cur_max_plin = temp;
                        }
                    }

//                    treeSet.remove(new Index2D(i, j));
                }
            }


            if (cur_max_plin == null) System.out.println(-1);
            else System.out.println(cur_max_plin);
        }
        //resolve
        //output
        sc.close();
    }
}
