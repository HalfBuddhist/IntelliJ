package com.algo_ds.math;


import java.math.BigInteger;

/**
 * 阶乘
 */
public class Factorial {

    /**
     * user big integer class
     *
     * @param p
     * @return
     * @throws Exception
     */
    public static BigInteger iterative_factorial(BigInteger p) throws Exception {
        int cmp = p.compareTo(BigInteger.ZERO);
        if (cmp < 0)
            throw new Exception("parameter should be non-negtive.");
        else {
            BigInteger ini = BigInteger.ONE;
            BigInteger idx = BigInteger.ONE;
            int cmp1 = idx.compareTo(p);
            while (cmp1 <= 0) {
                ini = ini.multiply(idx);
                idx = idx.add(BigInteger.ONE);
                cmp1 = idx.compareTo(p);
            }
            return ini;
        }
    }

    /**
     * use int.
     *
     * @param p
     * @return
     * @throws Exception
     */
    public static int iterative_factorial(int p) throws Exception {
        if (p < 0)
            throw new Exception("parameter should be non-negtive.");
        else {
            int accumulate = 1;
            int cur = 1;
            while (cur <= p) {
                accumulate *= cur;
                cur++;
            }
            return accumulate;
        }
    }
}
