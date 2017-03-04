package com.hackerrank.algo.string;

import com.lqw.common.WebPath;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class BearAndSteadyGene {

    public static boolean is_condition_satis(int[] cur_inc, int[] need_inc) {
        return (cur_inc['A' - 'A'] >= need_inc['A' - 'A'] &&
                cur_inc['G' - 'A'] >= need_inc['G' - 'A'] &&
                cur_inc['C' - 'A'] >= need_inc['C' - 'A'] &&
                cur_inc['T' - 'A'] >= need_inc['T' - 'A']);
    }

    public static void main(String[] argv) throws FileNotFoundException {
        Scanner sc = new Scanner(new File(WebPath.getAbsolutePathWithClass("/input.txt").getPath()));
        //        Scanner sc = new Scanner(new BufferedInputStream(System.in));
        //        Scanner sc = new Scanner(System.in);
        int len = sc.nextInt();
        String str = sc.next();

        //stat
        int[] agct_cnt = new int[26];
        for (char c : str.toCharArray()) {
            agct_cnt[c - 'A']++;
        }

        //2 pointer calc
        int[] need_inc = new int[26];
        int[] cur_inc = new int[26];
        boolean is_need_substitute = false;
        for (int i = 0; i < 26; i++) {
            if (agct_cnt[i] <= len / 4) {
                need_inc[i] = 0;
            } else {
                is_need_substitute = true;
                need_inc[i] = -len / 4 + agct_cnt[i];
            }
        }

        if (is_need_substitute) {
            int i = -1, j = 0, min_length = Integer.MAX_VALUE;
            while (j < len) {
                if (need_inc[str.charAt(j) - 'A'] == 0) {
                    j++;
                } else {
                    cur_inc[str.charAt(j) - 'A']++;
                    if (is_condition_satis(cur_inc, need_inc)) {
                        //update the min value
                        min_length = j - i >= min_length ? min_length : j - i;

                        //move i, untill the conditon not satisfied.
                        while (++i <= j) {
                            if (need_inc[str.charAt(i) - 'A'] == 0) {
                                min_length = j - i >= min_length ? min_length : j - i;
                            } else {
                                cur_inc[str.charAt(i) - 'A']--;
                                if (is_condition_satis(cur_inc, need_inc)) {
                                    //update length
                                    min_length = j - i >= min_length ? min_length : j - i;
                                } else {
                                    j++;//move j
                                    break;
                                }
                            }
                        }

                    } else {
                        j++;
                    }
                }
            }
            System.out.println(min_length);
        } else {
            System.out.println(0);
        }

        sc.close();
    }
}
