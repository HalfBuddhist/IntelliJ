package com.hackerrank.week_code_21;


import com.lqw.common.WebPath;

import java.io.File;
import java.util.HashSet;
import java.util.Scanner;

/**
 * resolve in a dp way
 * 复杂度为O(2^n)
 */

public class DemandingMoney2 {

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

    public static Result calc(int[] bitmask1, int begine, boolean[][] road, int[] money, int n) {
        if (begine != -1) {
            //choose it
            int[] newbitmask = bitmask1.clone();
            newbitmask[begine] = 1;
            int newbegin = -1;
            for (int i = 1; i <= n; i++) {
                if (road[begine][i]) newbitmask[i] = -1;
                if (newbitmask[i] == 0) newbegin = i;
            }
            Result first_res = calc(newbitmask, newbegin, road, money, n);
            first_res.max_value += money[begine];

            //not choose it
            newbitmask = bitmask1.clone();
            newbitmask[begine] = -1;
            newbegin = -1;
            for (int i = 1; i <= n; i++) {
                if (newbitmask[i] == 0) newbegin = i;
            }
            Result second_res = calc(newbitmask, newbegin, road, money, n);

            if (first_res.max_value > second_res.max_value) {
                return first_res;
            } else if (first_res.max_value < second_res.max_value) {
                return second_res;
            } else {
                first_res.max_cnt += second_res.max_cnt;
                return first_res;
            }
        } else {
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
        int posible_max = 0;
        for (int i = 0; i < n; i++) {
            c[i + 1] = sc.nextInt();
            posible_max += c[i + 1];
            heros.add(i + 1);
        }
        for (int j = 0; j < m; j++) {
            int first = sc.nextInt();
            int second = sc.nextInt();
            road[first][second] = true;
            road[second][first] = true;
        }

        //resolve recirsove
        Result temp = calc(bitmask, 1, road, c, n);

        //output
        System.out.println(temp.max_value + " " + temp.max_cnt);
        System.out.println("use time: " + (System.currentTimeMillis() - begin) / 1000.0);
    }
}

/*
test case

#1 the standard one
3 2
6 8 2
1 2
3 2

#2 the distinct verticles
4 0
1 2 3 4

#3 the totally connected graph
3 3
4 5 6
1 2
3 2
1 3

#4 has zero condition
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