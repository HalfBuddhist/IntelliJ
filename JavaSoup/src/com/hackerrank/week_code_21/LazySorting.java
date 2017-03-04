package com.hackerrank.week_code_21;


import com.algo_ds.math.Factorial;
import com.lqw.common.WebPath;

import java.io.File;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Scanner;

/**
 * 这是一个闭型问题 closed-form
 * 通过数列的极限运算可知平均的用时为1/p, 其中p为一个序列有序的概率：
 * p = m1!m2!...mk!/(n!), 其中n为元素的个数， mi为不同元素重复的次数，sum(m1,m2,...,mk) = n
 *
 * 方法二：其实完全可以从概率的定义来思考，如果概率为p，则做n次实验，期望发生的次数为np;
 * 反之思考，如果发生一次，期望做的实验次数为1/p;
 */
public class LazySorting {

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(new File(WebPath.getAbsolutePathWithClass("/com/hackerrank/week_code_21/input.txt").getPath()));
        //Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] r = new int[105]; //record the repeate number.
        int[] a = new int[n];
        boolean isorder = true;
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
            r[a[i]] += 1;
            if (i > 0 && a[i] < a[i - 1]) {
                isorder = false;
            }
        }

        if (isorder) {
            System.out.printf("%.6f", 0.0);
        } else {
            //calcute the p
            BigInteger fenzi = BigInteger.ONE;
            for (int t : r) {
                fenzi = fenzi.multiply(Factorial.iterative_factorial(BigInteger.valueOf(t)));
            }
            BigDecimal answer = new BigDecimal(Factorial.iterative_factorial(BigInteger.valueOf(n))).divide(new BigDecimal(fenzi));

            System.out.printf("%.6f", answer.doubleValue());
        }
    }
}
