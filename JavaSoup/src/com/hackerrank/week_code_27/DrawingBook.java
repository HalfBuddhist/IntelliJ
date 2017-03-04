package com.hackerrank.week_code_27;

import com.lqw.common.WebPath;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class DrawingBook {
    public static void main(String[] argv) throws FileNotFoundException {
        long begin = System.currentTimeMillis();
        Scanner sc = new Scanner(new File(WebPath.getAbsolutePathWithClass("/input.txt").getPath()));
        //        Scanner sc = new Scanner(new BufferedInputStream(System.in));
        //        Scanner sc = new Scanner(System.in);
        //presolve
        //input
        int n = sc.nextInt();
        int p = sc.nextInt();

        //resolve
        int cn = n % 2 == 0 ? n / 2 : (n - 1) / 2;
        int cp = p % 2 == 0 ? p / 2 : (p - 1) / 2;
        System.out.println((cn - cp) > cp ? cp : cn - cp);

        //output
        sc.close();
        System.err.println("use time: " + (System.currentTimeMillis() - begin) / 1000.0 + " seconds.");
    }
}
