package com.hackerrank.week_code_26;

import com.algo_ds.math.Primality;
import com.lqw.common.WebPath;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


/**
 * 使用优化的开方复杂度的素性检验
 * 整体复杂度为(m-n)n^(1/2), 当前最坏情况为10^(10.5)
 * 预计会超复杂度时间限制
 *
 */
public class Twins {
    public static void main(String[] argv) throws FileNotFoundException {
        long begin = System.currentTimeMillis();
        Scanner sc = new Scanner(new File(WebPath.getAbsolutePathWithClass("/input.txt").getPath()));
        //        Scanner sc = new Scanner(new BufferedInputStream(System.in));
        //        Scanner sc = new Scanner(System.in);
        //presolve
        //input
        int n = sc.nextInt();
        int m = sc.nextInt();
        int cnt = 0;
        int[] prime_flag = new int[m - n + 5];

        //resolve
        for (int i = n; i <= m - 2; i++) {
            boolean primility_c = false;
            if (prime_flag[i - n] == 0) {
                //check
                prime_flag[i - n] = Primality.isPrimeBest(i) ? 1 : -1;
            }
            primility_c = prime_flag[i - n] == 1;

            boolean primality_n = false;
            if (prime_flag[i - n + 2] == 0) {
                //check
                prime_flag[i - n + 2] = Primality.isPrimeBest(i + 2) ? 1 : -1;
            }
            primality_n = prime_flag[i - n + 2] == 1;

            if (primality_n && primility_c) cnt++;
        }

        //output
        System.out.println(cnt);
        sc.close();
        System.err.println("use time: " + (System.currentTimeMillis() - begin) / 1000.0 + " seconds.");
    }
}
