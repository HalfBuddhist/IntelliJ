package com.algo_ds.math;

import com.lqw.common.WebPath;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * 素数相关
 */
public class Primality {


    private static void printStats(int count, int n, boolean isPrime) {
        String caller = Thread.currentThread().getStackTrace()[2].getMethodName();
//        System.err.println(caller + " performed " + count + " checks, determined " + n
//                + ((isPrime) ? " is PRIME." : " is NOT PRIME."));
    }

    /**
     * Worst: O(n) algorithm
     * Checks if n is divisible by any number from 4 to n.
     *
     * @param n An integer to be checked for primality.
     * @return true if n is prime, false if n is not prime.
     */
    public static boolean isPrimeWorst(int n) {
        int count = 0;
        // 1 is not prime
        if (n == 1) {
            printStats(++count, n, false);
            return false;
        }

        // return false n is divisible by any i from 2 to n
        int i = 1;
        while (i++ < n - 1) {
            count++;
            if (n % i == 0) {
                printStats(++count, n, false);
                return false;
            }
        }

        // n is prime
        printStats(++count, n, true);
        return true;
    }

    /**
     * Better: O(n) algorithm
     * Checks if n is divisible by any number from 2 to n/2,
     * but is asymptotically the same as isPrimeWorst
     *
     * @param n An integer to be checked for primality.
     * @return true if n is prime, false if n is not prime.
     */
    public static boolean isPrimeLessWorst(int n) {
        int count = 0;
        // 1 is not prime
        if (n == 1) {
            printStats(++count, n, false);
            return false;
        }

        // return false n is divisible by any i from 2 to n
        int i = 1;
        while (i++ < n / 2) {
            count++;
            if (n % i == 0) {
                printStats(++count, n, false);
                return false;
            }
        }

        // n is prime
        printStats(++count, n, true);
        return true;
    }

    /**
     * O(n^(1/2)) Algorithm
     * Checks if n is divisible by any number from 2 to sqrt(n).
     *
     * @param n An integer to be checked for primality.
     * @return true if n is prime, false if n is not prime.
     */
    public static boolean isPrimeGood(int n) {
        int count = 0;
        // 1 is not prime
        if (n == 1) {
            printStats(++count, n, false);
            return false;
        } else if (n == 2) {
            printStats(++count, n, true);
            return true;
        }

        // return false n is divisible by any i from 2 to n
        int i = 1;
        while (i++ < Math.sqrt(n)) {
            count++;
            if (n % i == 0) {
                printStats(++count, n, false);
                return false;
            }
        }

        // n is prime
        printStats(++count, n, true);
        return true;
    }


    /**
     * Same complexity as the above.
     *
     * @param p
     * @return
     */
    public static boolean isPrimeBetter(long p) {
        if (p > 1) { //0, 1 is not prime
            for (int i = 2; i <= (long) (Math.sqrt(p)); i++) {
                if (p % i == 0) return false;
            }
            return true;
        }
        return false;
    }

    /**
     * Improved O( n^(1/2)) ) Algorithm
     * Checks if n is divisible by 2 or any odd number from 3 to sqrt(n).
     * The only way to improve on this is to check if n is divisible by
     * all KNOWN PRIMES from 2 to sqrt(n).
     *
     * @param n An integer to be checked for primality.
     * @return true if n is prime, false if n is not prime.
     */
    public static boolean isPrimeBest(int n) {
        int count = 0;
        // check lower boundaries on primality
        if (n == 2) {
            printStats(++count, n, true);
            return true;
        } // 1 is not prime, even numbers > 2 are not prime
        else if (n == 1 || (n & 1) == 0) { //偶数不为素数
            printStats(++count, n, false);
            return false;
        }

        // Check for primality using odd numbers from 3 to sqrt(n)
        for (int i = 3; i <= Math.sqrt(n); i += 2) { //n为奇数，一定不会被偶数整除，故可加2
            count++;
            // n is not prime if it is evenly divisible by some 'i' in this range
            if (n % i == 0) {
                printStats(++count, n, false);
                return false;
            }
        }
        // n is prime
        printStats(++count, n, true);
        return true;
    }

    public static void test_is_prime_methods() throws FileNotFoundException {
        //2147483647 the big prime test
        Scanner scan = new Scanner(new File(WebPath.getAbsolutePathWithClass("/input.txt").getPath()));
//        Scanner scan = new Scanner(System.in);
        while (scan.hasNext()) {
            int n = scan.nextInt();
            isPrimeWorst(n);
            isPrimeLessWorst(n);
            isPrimeGood(n);
            isPrimeBest(n);
            System.out.println();
            System.out.println(isPrimeBetter(n));
        }
        scan.close();
    }


    /**
     * 使用筛法计算指定范围内的素数
     *
     * @param start_p include
     * @param end     include
     * @return prime list
     */
    public static ArrayList getPrimeArrayInRange(int start_p, int end) {
        assert (start_p >= 2 && end >= start_p);
        ArrayList<Integer> primes = new ArrayList<Integer>();
        int prime_ar[] = new int[end + 5];//0 - prime, -1- not prime;
        for (int i = 2; i <= (int) (Math.sqrt(end) + 1); i++) {
            if (prime_ar[i] == -1) {
                continue;
            } else if (prime_ar[i] == 0) {
                int start = 2;
                while (start * i <= end) {
                    prime_ar[start * i] = -1;
                    start++;
                }
            }
        }
        for (int i = start_p; i <= end; i++) {
            if (prime_ar[i] == 0) primes.add(i);
        }

        return primes;
    }

    public static void main(String[] argv) throws FileNotFoundException {
        ArrayList primes = getPrimeArrayInRange(2, 2);
        System.out.println(primes.size());
        for (int i = 0; i < primes.size(); i++) {
            System.out.print(primes.get(i) + ", ");
        }
    }

}
