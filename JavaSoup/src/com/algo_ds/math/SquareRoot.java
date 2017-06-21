package com.algo_ds.math;

/**
 * Created by Qingwei on 2017/5/25.
 */
public class SquareRoot {

    /**
     * Proffesional algo, square root.
     * use the iteration method of the average inequality.
     * faster than O(logn)
     * better than the mysqrt2, because reduce to a smaller interval than half everytime.
     * AC
     *
     * @param x
     * @return
     */
    public static int sqrt(int x) {
        float a = 1;
        float b = x;
        while (a <= b) {
            float tiao = 2 * a * b / (a + b);
            float suan = (a + b) / 2;
            a = tiao;
            b = suan;
            if (Math.round(a) + 1 == Math.round(b) || Math.round(a) == Math.round(b)) {
                int t = (int) Math.round(b);
                if ((long) t * t <= x) return t;
                else return t - 1;
            }
        }
        return x;
    }


    /**
     * SPCS, binary search
     * test the middle is the square root or not. Then modify the search range.
     * Iteratively search and would always find a answer.
     * O(logn)
     * AC
     *
     * @param x
     * @return
     */
    public static int sqrt_binary_search(int x) {
        int a = 0;
        int b = x;
        while (a <= b) {
            int mid = (a + b) / 2;
            int res = isSqrt(mid, x);
            if (res == 0)
                return mid;
            else if (res == -1) {
                a = mid + 1;
            } else {
                b = mid - 1;
            }
        }
        return -1;
    }

    /**
     * is the aa the integer square root of the eleemnt x;
     *
     * @param aa
     * @param x
     * @return
     */
    protected static int isSqrt(int aa, int x) {
        long a = (long) aa;
        if (a * a <= x && (a + 1) * (a + 1) > x) {
            return 0;
        } else if (a * a > x) {
            return 1;
        } else return -1;
    }
}
