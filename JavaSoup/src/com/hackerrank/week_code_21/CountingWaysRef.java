package com.hackerrank.week_code_21;

import com.lqw.common.WebPath;

import java.io.*;
import java.math.BigInteger;
import java.math.MathContext;
import java.util.*;

/**
 * reference code
 */

public class CountingWaysRef {
    public static final BigInteger BM = new BigInteger("1000000007");
    public static int MODULO = 1000000007;

    public static void main(String[] args) throws Exception {
        long tm = System.currentTimeMillis();
        InputReader sc = new InputReader(
                new FileInputStream(new File(WebPath.getAbsolutePathWithClass("/com/hackerrank/week_code_21/input.txt").getPath())));
//        InputReader sc = new InputReader(new BufferedInputStream(System.in));

        int n = sc.nextInt();
        int a[] = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }
        long l = sc.nextLong();
        long r = sc.nextLong();
        long cnt;
        cnt = getCnt(n, a, l, r);
        System.out.println(cnt);
        System.err.println((System.currentTimeMillis() - tm) / 1000.0);
    }

    public static long getCnt(int n, int[] a, long l, long r) {
        long cnt = 0;
        int mlt = 1;
        for (int ai = 0; ai < n; ai++) {
            mlt *= a[ai];
        }
        int mxmlt = (n + 3) * mlt;
        long[] prev = new long[mxmlt + 1];
        long[] cur = new long[mxmlt + 1];
        for (int ai = 0; ai < n; ai++) {
            cur[0] = 1;
            for (int i = 1; i <= mxmlt; i++) {
                cur[i] = ((ai > 0 ? prev[i] : 1) + (((i - a[ai] >= 0) ? cur[i - a[ai]] : 0)));
                cur[i] = cur[i] % MODULO;
            }
            //System.out.println(Arrays.toString(cur));
            prev = cur;
        }
        //cnt = (cur[r] - cur[l - 1] + MODULO) % MODULO;
        int idxR = (int) (1 + ((r - 1) % mlt));
        int idxL = (int) (1 + ((l - 2) % mlt));
        long nr = 1 + ((r - 1) / mlt);
        long nl = 1 + ((l - 2) / mlt);
        int[] xin = new int[(n + 3)];
        long[] yin = new long[(n + 3)];
        for (int i = 0; i < (n + 3); i++) {
            xin[i] = i + 1;
            yin[i] = cur[i * mlt + idxL];
        }
        long curl = inter(xin, yin, nl, n + 2);
        for (int i = 0; i < (n + 3); i++) {
            yin[i] = cur[i * mlt + idxR];
        }
        long curr = inter(xin, yin, nr, n + 2);
        if (l <= mxmlt) {
            curl = cur[((int) (l - 1))] % MODULO;
        }
        if (r <= mxmlt) {
            curr = cur[((int) r)] % MODULO;
        }
        cnt = (curr - curl + MODULO) % MODULO;
        return cnt;
    }


    public static long inter(int xin[], long yin[], long x, int n) {
        BigInteger lambda[] = new BigInteger[n + 1];         //array for weights
        long interp = 0;
        int i, j;
        n = n - 1;

        BigInteger cl = new BigInteger("1");
        for (i = 0; i <= n; i++) {
            for (j = 0; j <= n; j++) {
                if (i != j) {
                    cl = cl.multiply(new BigInteger(String.valueOf(xin[i] - xin[j])));
                }
            }
        }

        for (i = 0; i <= n; i++)                     //loop over all x inputs
        {
            lambda[i] = new BigInteger(String.valueOf(yin[i])).multiply(cl);

            for (j = 0; j <= n; j++)              //compute weights
            {
                if (i != j) {
                    lambda[i] = lambda[i].multiply(new BigInteger(String.valueOf(x - xin[j]))).
                            divide(new BigInteger(String.valueOf(xin[i] - xin[j])));
                }

            }
            interp = (interp + lambda[i].mod(BM).longValue()) % MODULO;   //the interpolated function
        }

        return (interp * modinv(cl.mod(BM).longValue(), MODULO)) % MODULO;
    } //end inter method

    static class Point {
        long b;
        long x, y;

        Point(long xx, long yy, long bb) {
            x = xx;
            y = yy;
            b = bb;
        }
    }


    public static Point extendedGCD(long a, long b) {
        if ((a % b) == 0) return new Point(0, 1, a);
        Point p = extendedGCD(b, a % b);
        return new Point(p.y, p.x - (p.y * (a / b)), a);
    }

    public static long modinv(long a, long m) {
        Point p = extendedGCD(a, m);
        return ((p.x + MODULO) % m);
    }


}

class InputReader {

    private InputStream stream;
    private byte[] buf = new byte[1024];
    private int curChar;
    private int numChars;

    public InputReader(InputStream stream) {
        this.stream = stream;
    }

    public int read() {
        if (numChars == -1)
            throw new InputMismatchException();
        if (curChar >= numChars) {
            curChar = 0;
            try {
                numChars = stream.read(buf);
            } catch (IOException e) {
                throw new InputMismatchException();
            }
            if (numChars <= 0)
                return -1;
        }
        return buf[curChar++];
    }

    public int nextInt() {
        int c = read();
        while (isSpaceChar(c))
            c = read();
        int sgn = 1;
        if (c == '-') {
            sgn = -1;
            c = read();
        }
        int res = 0;
        do {
            if (c < '0' || c > '9')
                throw new InputMismatchException();
            res *= 10;
            res += c - '0';
            c = read();
        } while (!isSpaceChar(c));
        return res * sgn;
    }

    public long nextLong() {
        int c = read();
        while (isSpaceChar(c))
            c = read();
        int sgn = 1;
        if (c == '-') {
            sgn = -1;
            c = read();
        }
        long res = 0;
        do {
            if (c < '0' || c > '9')
                throw new InputMismatchException();
            res *= 10;
            res += c - '0';
            c = read();
        } while (!isSpaceChar(c));
        return res * sgn;
    }

    public boolean[] readLine(int m) {
        int c = read();
        while ('\n' == c)
            c = read();
        boolean[] ll = new boolean[m];
        int cn = 0;
        do {
            ll[cn] = c == '1';
            cn++;
            c = read();
        } while ('\n' != c && -1 != c);
        return ll;
    }

    public boolean isSpaceChar(int c) {
        return isWhitespace(c);
    }

    public static boolean isWhitespace(int c) {
        return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
    }

    public interface SpaceCharFilter {
        public boolean isSpaceChar(int ch);
    }
}
