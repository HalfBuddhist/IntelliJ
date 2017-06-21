package com.nowcoder.WAP_FirstRound;

import com.lqw.common.WebPath;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class PrisonBreak {

    /**
     * 求模mod下, a^b
     * log(b)
     */
    static long power_mod(long a, long b, long mod) {
        long ans = 1;
        while (b > 0) {
            if (b % 2 == 1) ans = ans * a % mod;
            a = a * a % mod;
            b >>= 1;
        }
        return ans;
    }

    //get the modular inverse of x ,in m
    static long modular_inverse_iter(long x, long m) {
        return power_mod(x, m - 2, m);
    }


    /**
     * Question:
     * n prisons, and n prisoners from m city randomely.
     * you need to put the prisoners to prison, but if the adjacent prisoners are from the same city,
     * a prison break will happen. Questio is how many situations average under modular 100003,
     * in which the prison will happen.
     * <p>
     * Profession algo, probability of frequency.
     * Divide the prison break situations into n-1 parts:
     * 1, prison break happens between 1-2 prison.
     * 2, prison break happens between 2-3, the 1-2 not happen.
     * 3, prison break happens between 3-4, the 1-2, 2-3, not happen.
     * .....
     * n-1, prison break happens between (n-1)-n, the 1-2, 2-3, 3-4, ....., not happen.
     * and the number of the situatios could be simply calculated as follows.
     * 1, m*1*m*m*.....*m       m^(n-1)*(m-1)^(0)
     * 2, m*m-1*1*m*.....*m     m^(n-2)*(m-1)^(1)
     * 3, m*m-1*m-1*1*m*m*...m  m^(n-3)*(m-1)^(2)
     * .....
     * 4, m*m-1*m-1*m-1*m-1*....*m-1*1  m^(1)*(m-1)^(n-2)
     * Accumate these number is the result.
     * <p>
     * Storage of the n-1 result would result in an memory overflow.
     * so you need calc when the addition happens, (m-1) part would be calculated easily by multi one
     * (m-1), but the (m) would use the divide, because these operation is under the moular,
     * so we could use the multiplication of the modular inverse of m instead of
     * the divide operation.
     * <p>
     * O(n + log(n))
     * TLE
     *
     * @param argv
     * @throws FileNotFoundException
     */
    public static void main(String[] argv) throws FileNotFoundException {
        long begin = System.currentTimeMillis();
        Scanner sc = new Scanner(new File(WebPath.getAbsolutePathWithClass("/input.txt").getPath()));
        //        Scanner sc = new Scanner(new BufferedInputStream(System.in));
        //        Scanner sc = new Scanner(System.in);
        //        System.setOut(new PrintStream(new FileOutputStream(new File(WebPath.getAbsolutePathWithClass().getPath() + "output.txt"))));
        //presolve
        //input
        long m = sc.nextLong();
        int n = sc.nextInt();
        int mod = 100003;

        //calc the jiecheng
        long m_pow = m_pow = power_mod(m, n - 1, mod);
        long inverse = modular_inverse_iter(m, mod);

        //resolve
        long ans = 0;
        long mm_pow = 1;
        for (int i = 1; i < n; i++) {
            if (i != 1) {
                mm_pow = (mm_pow * ((m - 1) % mod)) % mod;
                m_pow = (m_pow * ((inverse) % mod)) % mod;
            }
            ans += (m_pow * mm_pow) % mod;
            ans %= mod;
        }

        //output
        System.out.println(ans % mod);

        sc.close();
        System.err.println("use time: " + (System.currentTimeMillis() - begin) / 1000.0 + " seconds.");
    }
}
