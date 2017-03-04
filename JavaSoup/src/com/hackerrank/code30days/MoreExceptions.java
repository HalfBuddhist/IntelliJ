package com.hackerrank.code30days;


import com.lqw.common.WebPath;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class Calculator{
    public int power(int n, int p) throws IllegalArgumentException{
        if (n< 0 || p < 0)
            throw (new IllegalArgumentException("n and p should be non-negative"));
        else
            return (int)Math.pow(n,p);
    }
}

public class MoreExceptions {
    public static void main(String[] argh) throws FileNotFoundException{
        Scanner in = new Scanner(new File(WebPath.getAbsolutePathWithClass("/com/hackerrank/code30days/input.txt").getPath()));
//        Scanner in = new Scanner(System.in);
        int T = in.nextInt();
        while (T-- > 0) {
            int n = in.nextInt();
            int p = in.nextInt();
            Calculator myCalculator = new Calculator();
            try {
                int ans = myCalculator.power(n, p);
                System.out.println(ans);

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

    }
}
