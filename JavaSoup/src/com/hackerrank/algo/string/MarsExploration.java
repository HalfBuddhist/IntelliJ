package com.hackerrank.algo.string;

import com.lqw.common.WebPath;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MarsExploration {
    public static void main(String[] argv) throws FileNotFoundException {
        Scanner sc = new Scanner(new File(WebPath.getAbsolutePathWithClass("/input.txt").getPath()));
        //        Scanner sc = new Scanner(new BufferedInputStream(System.in));
        //        Scanner sc = new Scanner(System.in);
        //input
        String s = sc.next();

        //resolve
        int len = s.length() - 1, cnt = 0;
        while (len >= 0){
            if (s.charAt(len--) != 'S') cnt++;
            if (s.charAt(len--) != 'O') cnt++;
            if (s.charAt(len--) != 'S') cnt++;
        }


        //output
        System.out.println(cnt);
        sc.close();
    }
}
