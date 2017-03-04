package com.hackerrank.Ad_infinitum_16_first_timer;

import com.lqw.common.WebPath;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class LeonardosPrimeFactors {
    public static void main(String[] argv) throws FileNotFoundException {
        Scanner sc = new Scanner(new File(WebPath.getAbsolutePathWithClass("/input2.txt").getPath()));
        //        Scanner sc = new Scanner(new BufferedInputStream(System.in));
        //        Scanner sc = new Scanner(System.in);
        //presolve
        ArrayList<Integer> primes = new ArrayList<Integer>();//100以内的素数, 使用筛法
        int prime_ar[] = new int[105];
        for (int i = 2; i < 11; i++) {
            if (prime_ar[i] == -1) {
                continue;
            } else if (prime_ar[i] == 0) {
                int start = 2;
                while (start * i <= 100) {
                    prime_ar[start * i] = -1;
                    start++;
                }
            }
        }
        for (int i = 2; i < 101; i++) {
            if (prime_ar[i] == 0) primes.add(i);
        }

        //input
        int q = sc.nextInt();
        while (q-- > 0) {
            long n = sc.nextLong();
            long last_m = -1;
            long m = 1;
            int cur_num = 0;
            while (m <= n) {
                m *= primes.get(cur_num++);
                if (m < last_m) break;
                last_m = m;
            }
            System.out.println(cur_num - 1);
        }

        //resolve

        //output
        sc.close();
    }
}
