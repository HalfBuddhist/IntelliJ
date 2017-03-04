package com.algo_ds.math;

public class Fibonacci {

    /**
     * calculate the fibonacci of n recursively,
     * too shlow, discarded.
     *
     * @param end the end number of the array of the fibonacci
     * @return the sum of the
     */
    public static long fibonacciRecursively(long end) {
        if (end < 2)
            return 1;
        return Fibonacci.fibonacciRecursively(end - 1) + Fibonacci.fibonacciRecursively(end - 2);
    }


    /**
     * calculate the fibonacci of n bottom up method, more efficient.
     * @param end
     * @return
     */
    public static long fibonacciBottomUp(long end) {
        long idx = 2, first = 1, second = 1;
        while (idx < end) {
            long temp = first + second;
            first = second;
            second = temp;
            idx++;
        }
        return second;
    }

    public static void main(String[] args) {
        System.out.println(Fibonacci.fibonacciBottomUp(50));
    }
}
