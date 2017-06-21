package com.leetcode;

import com.lqw.common.WebPath;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Sqrt_69 {

    /**
     * Proffesional algo, square root.
     * use the iteration method of the average inequality.
     * O(logn)
     * AC
     *
     * @param x
     * @return
     */
    public int mySqrt(int x) {
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
    public int mySqrt2(int x) {
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

    private int isSqrt(int aa, int x) {
        long a = (long) aa;
        if (a * a <= x && (a + 1) * (a + 1) > x) {
            return 0;
        } else if (a * a > x) {
            return 1;
        } else return -1;
    }

    public static void main(String[] argv) throws FileNotFoundException {
        long begin = System.currentTimeMillis();
        Scanner sc = new Scanner(new File(WebPath.getAbsolutePathWithClass("/input.txt").getPath()));
        //        Scanner sc = new Scanner(new BufferedInputStream(System.in));
        //        Scanner sc = new Scanner(System.in);
        //        System.setOut(new PrintStream(new FileOutputStream(new File(WebPath.getAbsolutePathWithClass().getPath() + "output.txt"))));
        //presolve
        //input
//        for (int i = 0; i <= 100; i++) {
//            System.out.println(new Sqrt_69().mySqrt2(i));
//        }

//        System.out.println(new Sqrt_69().mySqrt2(4));
        System.out.println(new Sqrt_69().mySqrt2(2147395599));


        //resolve

        //output

        sc.close();
        System.err.println("use time: " + (System.currentTimeMillis() - begin) / 1000.0 + " seconds.");
    }
}
