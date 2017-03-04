import java.math.BigDecimal;
import java.util.Scanner;

import java.math.BigInteger;

class Factorial {
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
}


public class Solution {

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] r = new int[100];
        int[] a = new int[n];
        boolean isorder = true;
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
            r[i] += 1;
            if (i > 0 && a[i] < a[i - 1]) {
                isorder = false;
            }
        }

//        if (isorder) {
//            System.out.printf("%.6f", 0.0);
//        } else {
            //calcute the p
            BigInteger fenzi = BigInteger.ONE;
            for (int t : r) {
                fenzi = fenzi.multiply(Factorial.iterative_factorial(BigInteger.valueOf(t)));
            }
            BigDecimal prop = new BigDecimal(fenzi).divide(new BigDecimal(Factorial.iterative_factorial(BigInteger.valueOf(n))));
            BigDecimal answer = BigDecimal.ONE.divide(prop);

            //resolve the closed form
            System.out.printf("%.6f", answer.doubleValue());
//        }
    }
}
