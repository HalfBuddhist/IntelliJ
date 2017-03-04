package com.hackerrank.algo.string;

import com.lqw.common.WebPath;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;


class CharStats {
    char m_char;
    int cnt;

    CharStats(char m_char, int cnt) {
        this.m_char = m_char;
        this.cnt = cnt;
    }
}

public class YetAnotherKMPProblem {
    public static void main(String[] argv) throws FileNotFoundException {
        Scanner sc = new Scanner(new File(WebPath.getAbsolutePathWithClass("/input.txt").getPath()));
        //        Scanner sc = new Scanner(new BufferedInputStream(System.in));
        //        Scanner sc = new Scanner(System.in);
        //input
        int[] cnt_char = new int[26];
        int small = Integer.MAX_VALUE, small_idx = -1, sum_cnt = 0;
        for (int i = 0; i < 26; i++) {
            cnt_char[i] = sc.nextInt();
            //find the min and lexgical smallest
            if (cnt_char[i] != 0 && cnt_char[i] < small) {
                small = cnt_char[i];
                small_idx = i;
            }
            //get sum
            sum_cnt += cnt_char[i];
        }

        //resolve
        char[] res = new char[sum_cnt];
        //form the first element
        cnt_char[small_idx] -= 1;
        int res_idx = 0;
        res[res_idx++] = (char) (small_idx + 'a');
        //form the linkedlist
        LinkedList<CharStats> linkedlist = new LinkedList<CharStats>();
        for (int i = 0; i < 26; i++) {
            if (cnt_char[i] > 0) {
                linkedlist.add(new CharStats((char) ('a' + i), cnt_char[i]));
            }
        }

        while (res_idx < sum_cnt) {
            CharStats temp = null;
            if (res[res_idx - 1] == res[0]) {
                Iterator<CharStats> iter = linkedlist.iterator();
                //find the first unequal the second one.
                while (iter.hasNext()) {
                    temp = iter.next();
                    if (temp.m_char != res[1]) break;
                }
            } else {
                temp = linkedlist.getFirst();
            }
            res[res_idx++] = temp.m_char;
            temp.cnt--;
            if (temp.cnt == 0) linkedlist.removeFirstOccurrence(temp);
            //只有常数复杂度，因为要删除的不是第一个就是第二个元素
        }

        //output
        System.out.println(res);
        sc.close();
    }
}

//some test case
//2 2 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
//aabb

//0 0 0 0 0 0 0 0 0 0 0 0 0 499740 0 0 0 0 0 0 0 0 0 0 0 500260
//nnznznznznznznznznznznznznznznznznznznznznznznzn(zn)

//0 0 0 0 0 0 0 0 0 0 0 0 3 0 0 0 0 0 5 0 0 0 5 0 0 0
//mmsmsssswwwww
