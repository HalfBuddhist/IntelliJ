package com.hackerrank.code30days;

import java.io.FileNotFoundException;
import java.util.Date;
import java.util.Random;

public class Testing {
    public static void main(String[] args) throws FileNotFoundException {
        Random random = new Random((new Date().getTime()));
        boolean[] n_flag = new boolean[201];
        StringBuffer res_str = new StringBuffer("");
        StringBuffer test_str = new StringBuffer("5\n");

        for (int i = 0; i < 5; i++) {
            int n;
            do {
                n = random.nextInt(200) + 1;
            } while (n_flag[n]);
            n_flag[n] = true;

            int k = random.nextInt(n) + 1;

            int a[] = new int[n];
            //add 3
            for (int j = 0; j < Math.min(3, n); j++) {
                if (j == 0)
                    a[j] = 0;
                else if (j == 1)
                    a[j] = random.nextInt(1000) + 1;
                else if (j == 2)
                    a[j] = random.nextInt(1000) - 1000;
            }

            //add others.
            for (int j = 3; j < n; j++) {
                a[j] = random.nextInt(2001) - 1000;
            }

            //output test case
            test_str.append(n + " " + k + "\n");
            int ontime_n = 0;
            for (int j = 0; j < a.length; j++) {
                if (j == 0) test_str.append(a[j]);
                else test_str.append(" " + a[j]);
                if (a[j] <= 0) ontime_n++;
            }
            test_str.append("\n");

            //calcu
            if (ontime_n < k) res_str.append("YES\n");
            else res_str.append("NO\n");
        }

        //output
        System.out.print(test_str);
//        System.out.print(res_str);
    }
}
