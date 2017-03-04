package com.hackerrank.week_code_21;


import com.lqw.common.WebPath;

import java.io.File;
import java.util.HashSet;
import java.util.Scanner;

/**
 * resolve in a dp way,
 * 复杂度为O(2^n), 采用剪枝策略
 */

public class DemandingMoney3 {

    static class Result {
        public int max_value;
        public int max_cnt;

        Result(int max_value, int max_cnt) {
            this.max_value = max_value;
            this.max_cnt = max_cnt;
        }
    }

    public static int max_value = Integer.MIN_VALUE;
    public static int max_value_cnt = 0;

    public static Result calc(int[] bitmask1, int begin, int cur_val, int remain_val, boolean[][] road, int[] money, int n) {
        if (begin != -1) {
            //choose it
            int[] newbitmask = bitmask1.clone();
            int remain_val_new = remain_val;
            newbitmask[begin] = 1;
            remain_val_new -= money[begin];
            int newbegin = -1;
            for (int i = 1; i <= n; i++) {
                if (road[begin][i] && newbitmask[i] == 0) {
                    newbitmask[i] = -1;
                    remain_val_new -= money[i];
                }
                if (newbitmask[i] == 0) newbegin = i;
            }
            Result first_res;
            if (cur_val + money[begin] + remain_val_new >= max_value) {
                first_res = calc(newbitmask, newbegin, cur_val + money[begin], remain_val_new, road, money, n);
                first_res.max_value += money[begin];
            } else {
                first_res = new Result(Integer.MIN_VALUE, 0);
            }

            //not choose it
            if (remain_val == 16) {
                int iii = 0;
            }
            newbitmask = bitmask1.clone();
            remain_val_new = remain_val;
            newbitmask[begin] = -1;
            remain_val_new -= money[begin];
            newbegin = -1;
            for (int i = 1; i <= n; i++) {
                if (newbitmask[i] == 0) newbegin = i;
            }
            Result second_res;
            if (cur_val + remain_val_new >= max_value) {
                second_res = calc(newbitmask, newbegin, cur_val, remain_val_new, road, money, n);
            } else {
                second_res = new Result(Integer.MIN_VALUE, 0);
            }


            if (first_res.max_value > second_res.max_value) {
                return first_res;
            } else if (first_res.max_value < second_res.max_value) {
                return second_res;
            } else {
                if (first_res.max_value == Integer.MIN_VALUE) {
                    return new Result(Integer.MIN_VALUE, 0);
                } else {
                    first_res.max_cnt += second_res.max_cnt;
                    return first_res;
                }
            }
        } else {
            if (cur_val > max_value) {
                max_value = cur_val;
                max_value_cnt = 1;
            } else if (cur_val == max_value) {
                max_value_cnt += 1;
            }
            return new Result(0, 1);
        }
    }

    public static void main(String[] args) throws Exception {
        long begin = System.currentTimeMillis();
        Scanner sc = new Scanner(new File(WebPath.getAbsolutePathWithClass("/com/hackerrank/week_code_21/input.txt").getPath()));
        //Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[] c = new int[n + 1];
        int[] bitmask = new int[n + 1];
        boolean[][] road = new boolean[n + 1][n + 1];
        HashSet<Integer> heros = new HashSet<Integer>();
        int remain_val_max = 0;
        for (int i = 0; i < n; i++) {
            c[i + 1] = sc.nextInt();
            remain_val_max += c[i + 1];
            heros.add(i + 1);
        }
        for (int j = 0; j < m; j++) {
            int first = sc.nextInt();
            int second = sc.nextInt();
            road[first][second] = true;
            road[second][first] = true;
        }

        //resolve recirsove
        Result temp = calc(bitmask, 1, 0, remain_val_max, road, c, n);

        //output
        System.out.println(temp.max_value + " " + temp.max_cnt);
        System.out.println(max_value + " " + max_value_cnt);

        System.out.println("use time: " + (System.currentTimeMillis() - begin) / 1000.0);
    }
}

/*
test case

#1 the standard one ans: 8 2
3 2
6 8 2
1 2
3 2

#2 the distinct verticles | ans : 0 1
4 0
1 2 3 4

#3 the totally connected graph | ans: 6 1
3 3
4 5 6
1 2
3 2
1 3

#4 has zero condition | ans: 0 8
3 0
0 0 0

#5 the zero condition 2, answer: 2 6
4 1
2 0 0 0
2 3

#6 the zero condition 3, answer: 2 3
4 3
2 0 0 0
1 4
2 3
3 4

 */