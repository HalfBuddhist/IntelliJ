package com.hackerrank.week_code_21;


import com.lqw.common.WebPath;

import java.io.File;
import java.util.HashSet;
import java.util.Scanner;

/**
 * resolve in a recursive way
 * 现提交的三份代码的差别为，空集的终止条件是否单列，输出的时候如果为0空集是否可以被包含。
 * 复杂度为O(n!)
 */

public class DemandingMoney {

    static class Result {
        public int max_value;
        public HashSet<HashSet<Integer>> max_set;

        Result(int max_value, HashSet<HashSet<Integer>> max_set) {
            this.max_value = max_value;
            this.max_set = max_set;
            max_set.add(new HashSet<Integer>());
        }

        public int mf_max_set_len() {
            if (max_set == null) return 0;
            else {
                int len = 0;
                for (HashSet<Integer> hashSet : max_set) {
                    if (!hashSet.isEmpty()) len++;
                }
                return len;
            }
        }
    }

    public static Result calc(HashSet<Integer> heros, boolean[][] road, int[] money, int n) {
        Result cur_res = new Result(0, new HashSet<HashSet<Integer>>());
        for (int hero : heros) {
            //sub the connected nodes.
            HashSet<Integer> sub_heros = (HashSet<Integer>) heros.clone();
            for (int i = 0; i < n; i++) {
                if (road[hero][i + 1]) {
                    sub_heros.remove(i + 1);
                }
            }
            sub_heros.remove(hero);


            //calc the remain max.
            Result t_res = calc(sub_heros, road, money, n);

            if (t_res.max_value + money[hero] > cur_res.max_value) {
                cur_res.max_value = t_res.max_value + money[hero];
                cur_res.max_set.clear();
                for (HashSet e : t_res.max_set) {
                    if (money[hero] == 0) {
                        cur_res.max_set.add((HashSet) (e.clone()));
                    }
                    e.add(hero);
                    cur_res.max_set.add(e);
                }
            } else if (t_res.max_value + money[hero] == cur_res.max_value) {
                for (HashSet e : t_res.max_set) {
                    if (money[hero] == 0) {
                        cur_res.max_set.add((HashSet) (e.clone()));
                    }
                    e.add(hero);
                    cur_res.max_set.add(e);
                }
            }
        }
        return cur_res;
    }

    public static void main(String[] args) throws Exception {
        long begin = System.currentTimeMillis();
        Scanner sc = new Scanner(new File(WebPath.getAbsolutePathWithClass("/com/hackerrank/week_code_21/input.txt").getPath()));
        //Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[] c = new int[n + 1];
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
        Result res = calc(heros, road, c, n);

        //output
        System.out.println(res.max_value + " " + res.mf_max_set_len());
//        System.out.println(res.max_value + " " + res.max_set.size());

        System.out.println( "use time: " + (System.currentTimeMillis() - begin)/100.0);

    }
}