package com.hackerrank.week_code_21;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

/**
 * reference code
 */

public class CountingWaysRef1 {

    public static void main(String[] args) {
        int MOD = 1000000007;
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt() + 1;
        int[] a = new int[n];
        int prod = 1;
        for (int i = 0; i < n - 1; i++) {
            a[i] = sc.nextInt();
            prod *= a[i];
        }
        a[n - 1] = 1;
        BigInteger l = sc.nextBigInteger();
        BigInteger r = sc.nextBigInteger();

        int[][] dp = new int[n][prod * n];
        for (int i = 0; i < n; i++) {
            dp[i][0] = 1;
        }
        for (int i = 0; i < prod * n; i++) {
            dp[0][i] = 0;
        }
        for (int i = 0; i < prod * n; i += a[0]) {
            dp[0][i] = 1;
        }
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < a[i]; j++) {
                dp[i][j] = dp[i - 1][j];
            }
            for (int j = a[i]; j < prod * n; j++) {
                dp[i][j] = (dp[i - 1][j] + dp[i][j - a[i]]) % MOD;
            }
        }
        int[][] diffs = new int[n][prod * n];
        for (int i = 0; i < prod * n; i++) {
            diffs[0][i] = dp[n - 1][i];
        }
        for (int i = 1; i < n; i++) {
            for (int j = prod * i; j < prod * n; j++) {
                diffs[i][j] = (MOD + diffs[i - 1][j] - diffs[i - 1][j - prod]) % MOD;
            }
        }
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < prod; j++) {
                diffs[n - 1][i * prod + j] = diffs[n - 1][(n - 1) * prod + j];
            }
        }
        for (int i = n - 2; i >= 0; i--) {
            for (int j = (i + 1) * prod - 1; j >= 0; j--) {
                diffs[i][j] = (MOD + diffs[i][j + prod] - diffs[i + 1][j + prod]) % MOD;
            }
        }
        String[][] multnum = {{"0", "1"}, {"0", "1", "1"}, {"0", "1", "1", "1"}, {"0", "0", "1", "1", "1"}, {"0", "-1", "0", "1", "1", "1"}, {"0", "0", "-1", "0", "5", "1", "1"}, {"0", "1", "0", "-1", "0", "1", "1", "1"}, {"0", "0", "1", "0", "-7", "0", "7", "1", "1"}, {"0", "-1", "0", "2", "0", "-7", "0", "2", "1", "1"}, {"0", "0", "-3", "0", "1", "0", "-7", "0", "3", "1", "1"}, {"0", "5", "0", "-1", "0", "1", "0", "-1", "0", "5", "1", "1"}};
        String[][] multden = {{"1", "1"}, {"1", "2", "2"}, {"1", "6", "2", "3"}, {"1", "1", "4", "2", "4"}, {"1", "30", "1", "3", "2", "5"}, {"1", "1", "12", "1", "12", "2", "6"}, {"1", "42", "1", "6", "1", "2", "2", "7"}, {"1", "1", "12", "1", "24", "1", "12", "2", "8"}, {"1", "30", "1", "9", "1", "15", "1", "3", "2", "9"}, {"1", "1", "20", "1", "2", "1", "10", "1", "4", "2", "10"}, {"1", "66", "1", "2", "1", "1", "1", "1", "1", "6", "2", "11"}};
        BigInteger[] lpows = new BigInteger[n + 1];
        BigInteger[] rpows = new BigInteger[n + 1];
        lpows[0] = BigInteger.ONE;
        rpows[0] = BigInteger.ONE;
        for (int i = 1; i <= n; i++) {
            lpows[i] = lpows[i - 1].multiply(l.subtract(BigInteger.ONE).divide(BigInteger.valueOf(prod)));
            rpows[i] = rpows[i - 1].multiply(r.divide(BigInteger.valueOf(prod)));
        }
        int lmod = l.subtract(BigInteger.ONE).mod(BigInteger.valueOf(prod)).intValue();
        int rmod = r.mod(BigInteger.valueOf(prod)).intValue();
        BigInteger[] lpolynums = new BigInteger[n];
        BigInteger[] lpolydens = new BigInteger[n];
        BigInteger[] rpolynums = new BigInteger[n];
        BigInteger[] rpolydens = new BigInteger[n];
        lpolynums[0] = BigInteger.valueOf(diffs[n - 1][lmod]);
        lpolydens[0] = BigInteger.ONE;
        rpolynums[0] = BigInteger.valueOf(diffs[n - 1][rmod]);
        rpolydens[0] = BigInteger.ONE;
        for (int i = 1; i < n; i++) {
            lpolynums[i] = BigInteger.ZERO;
            lpolydens[i] = BigInteger.ONE;
            rpolynums[i] = BigInteger.ZERO;
            rpolydens[i] = BigInteger.ONE;
        }
        for (int i = n - 2; i >= 0; i--) {
            BigInteger[] npnums = new BigInteger[n];
            BigInteger[] npdens = new BigInteger[n];
            BigInteger newnum;
            BigInteger newden;
            npnums[0] = BigInteger.valueOf(diffs[i][lmod]);
            npdens[0] = BigInteger.ONE;
            for (int j = 1; j < n; j++) {
                npnums[j] = BigInteger.ZERO;
                npdens[j] = BigInteger.ONE;
            }
            for (int j = 0; j < n - 1; j++) {
                if (lpolynums[j].compareTo(BigInteger.ZERO) > 0) {
                    for (int k = 1; k <= j + 1; k++) {
                        newden = npdens[k].multiply(lpolydens[j].multiply(new BigInteger(multden[j][k])));
                        newnum = npnums[k].multiply(lpolydens[j].multiply(new BigInteger(multden[j][k]))).add(lpolynums[j].multiply(new BigInteger(multnum[j][k])).multiply(npdens[k]));
                        npnums[k] = newnum;
                        npdens[k] = newden;
                    }
                }
            }
            for (int j = 0; j < n; j++) {
                lpolynums[j] = npnums[j];
                lpolydens[j] = npdens[j];
            }
            npnums[0] = BigInteger.valueOf(diffs[i][rmod]);
            npdens[0] = BigInteger.ONE;
            for (int j = 1; j < n; j++) {
                npnums[j] = BigInteger.ZERO;
                npdens[j] = BigInteger.ONE;
            }
            for (int j = 0; j < n - 1; j++) {
                if (rpolynums[j].compareTo(BigInteger.ZERO) > 0) {
                    for (int k = 1; k <= j + 1; k++) {
                        newden = npdens[k].multiply(rpolydens[j].multiply(new BigInteger(multden[j][k])));
                        newnum = npnums[k].multiply(rpolydens[j].multiply(new BigInteger(multden[j][k]))).add(rpolynums[j].multiply(new BigInteger(multnum[j][k])).multiply(npdens[k]));
                        npnums[k] = newnum;
                        npdens[k] = newden;
                    }
                }
            }
            for (int j = 0; j < n; j++) {
                rpolynums[j] = npnums[j];
                rpolydens[j] = npdens[j];
            }
        }
        for (int i = 0; i < n; i++) {
            BigInteger gcd = lpolynums[i].gcd(lpolydens[i]);
            lpolynums[i] = lpolynums[i].divide(gcd);
            lpolydens[i] = lpolydens[i].divide(gcd);
            gcd = rpolynums[i].gcd(rpolydens[i]);
            rpolynums[i] = rpolynums[i].divide(gcd);
            rpolydens[i] = rpolydens[i].divide(gcd);
        }
        BigInteger answer = BigInteger.ZERO;
        BigInteger tempnum = BigInteger.ZERO;
        BigInteger tempden = BigInteger.ONE;
        for (int i = 0; i < n; i++) {
            BigInteger newden = tempden.multiply(rpolydens[i]);
            BigInteger newnum = tempnum.multiply(rpolydens[i]).add(rpolynums[i].multiply(rpows[i]).multiply(tempden));
            tempden = newden;
            tempnum = newnum;
        }
        answer = answer.add(tempnum.divide(tempden));
        tempnum = BigInteger.ZERO;
        tempden = BigInteger.ONE;
        for (int i = 0; i < n; i++) {
            BigInteger newden = tempden.multiply(lpolydens[i]);
            BigInteger newnum = tempnum.multiply(lpolydens[i]).add(lpolynums[i].multiply(lpows[i]).multiply(tempden));
            tempden = newden;
            tempnum = newnum;
        }
        answer = answer.subtract(tempnum.divide(tempden));
        answer = answer.mod(BigInteger.valueOf(MOD));
        System.out.println(answer);
    }
}
