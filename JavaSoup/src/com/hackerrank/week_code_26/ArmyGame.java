package com.hackerrank.week_code_26;

import com.lqw.common.WebPath;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ArmyGame {
    public static void main(String[] argv) throws FileNotFoundException {
        Scanner sc = new Scanner(new File(WebPath.getAbsolutePathWithClass("/input.txt").getPath()));
        //        Scanner sc = new Scanner(new BufferedInputStream(System.in));
        //        Scanner sc = new Scanner(System.in);
        //presolve
        //input
        int n = sc.nextInt();
        int m = sc.nextInt();
        int cnt = 0;

        //resolve
        cnt = (n / 2) * (m / 2);
        if (n % 2 != 0) {
            cnt += (m+1) / 2;
        }
        if (m % 2 != 0) {
            cnt += (n+1) / 2;
        }
        if (m % 2 != 0 && n % 2 != 0) {
            cnt -= 1;
        }

        //output
        System.out.println(cnt);
        sc.close();
    }
}
