package com.hackerrank.code30days;

import com.algo_ds.math.Primality;
import com.lqw.common.WebPath;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by Qingwei on 16/6/25.
 */
public class RunningTimeandComplexity {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File(WebPath.getAbsolutePathWithClass("/com/hackerrank/code30days/input.txt").getPath()));
//        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        for (int i = 0; i < t; i++) {
            int n = sc.nextInt();
            if (Primality.isPrimeBetter(n))
                System.out.println("Prime");
            else
                System.out.println("Not prime");
        }

    }
}
