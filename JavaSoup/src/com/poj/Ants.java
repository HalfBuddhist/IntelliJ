package com.poj;

import com.lqw.common.WebPath;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Ants {

    public static void main(String[] argv) throws FileNotFoundException {
        long begin = System.currentTimeMillis();
        Scanner sc = new Scanner(new File(WebPath.getAbsolutePathWithClass("/input.txt").getPath()));
        //        Scanner sc = new Scanner(new BufferedInputStream(System.in));
        //        Scanner sc = new Scanner(System.in);
        //        System.setOut(new PrintStream(new FileOutputStream(new File(WebPath.getAbsolutePathWithClass().getPath() + "output.txt"))));
        //presolve
        //input
        int t = sc.nextInt();
        while (t-- > 0) {
            int l = sc.nextInt();
            int n = sc.nextInt();
            int min_time = Integer.MIN_VALUE;
            int max_time = Integer.MIN_VALUE;
            // O(n), greedy algo,
            // by switch the viewpoints by thinking
            // the ants cross each other, other than turn over
            for (int i = 0; i < n; i++) {
                int it_loc = sc.nextInt();
                int imin = Math.min(it_loc, l - it_loc);
                int imax = Math.max(it_loc, l - it_loc);
                min_time = Math.max(min_time, imin);
                max_time = Math.max(max_time, imax);
            }
            //out
            System.out.println(min_time + " " + max_time);

        }

        //resolve

        //output
        sc.close();
        System.err.println("use time: " + (System.currentTimeMillis() - begin) / 1000.0 + " seconds.");
    }
}

//2
//        10 3
//        2 6 7
//        214 7
//        11 12 7 13 176 23 191