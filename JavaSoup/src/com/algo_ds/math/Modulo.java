package com.algo_ds.math;

/**
 * Modulo operatoins class
 * Created by Qingwei on 2017/5/27.
 */
public class Modulo {

    /**
     * 求模mod下, x^n
     * <p>
     * use the Russian peasant algorithm
     * <p>
     * O(log(n))
     *
     * @param x
     * @param n
     * @param mod
     * @return
     */
    public static long power_mod(long x, long n, long mod) {
        long ans = 1;
        while (n > 0) {
            if (n % 2 == 1) ans = ans * x % mod;
            x = x * x % mod;
            n >>= 1;
        }
        return ans;
    }


    /**
     * Get the modular inverse of x ,in m
     * <p>
     * use the Fermat's little theorem.
     * simply as follow, m is the mod and prime, x is not the multiple to m;
     * (x^(m-1))mod(m) = 1
     * <p>
     * O(logmod)
     *
     * @param x
     * @param mod
     * @return
     */
    public static long modular_inverse_iter(long x, long mod) {
        return power_mod(x, mod - 2, mod);
    }
}
