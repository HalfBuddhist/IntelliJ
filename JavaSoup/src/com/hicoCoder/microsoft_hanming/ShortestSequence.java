package com.hicoCoder.microsoft_hanming;

import com.lqw.common.WebPath;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ShortestSequence {
    public static void main(String[] argv) throws FileNotFoundException {
        Scanner sc = new Scanner(new File(WebPath.getAbsolutePathWithClass("/input.txt").getPath()));
        //        Scanner sc = new Scanner(new BufferedInputStream(System.in));
        //        Scanner sc = new Scanner(System.in);
        //presolve
        //input
        int n = sc.nextInt();
        int[] num = new int[n];
        for (int i = 0; i < n; i++) {
            num[i] = sc.nextInt();
        }

        //resolve
        boolean cur_odd = true;
        int len = 0;
        for (int i = 0; i < n; i++) {
            if (len == 0) {
                cur_odd = (num[i] % 2 != 0);
                len++;
            } else {
                if ((num[i] % 2 != 0) == cur_odd) {
                    len++;
                } else {
                    len--;
                }
            }
        }


        //output
        System.out.println(len);
        sc.close();
    }
}
