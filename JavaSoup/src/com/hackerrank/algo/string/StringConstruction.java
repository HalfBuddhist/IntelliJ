package com.hackerrank.algo.string;

import com.lqw.common.WebPath;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class StringConstruction {
    public static void main(String[] argv) throws FileNotFoundException {
        Scanner sc = new Scanner(new File(WebPath.getAbsolutePathWithClass("/input.txt").getPath()));
        //        Scanner sc = new Scanner(new BufferedInputStream(System.in));
        //        Scanner sc = new Scanner(System.in);
        //input
        int n = sc.nextInt();
        while (n-- > 0) {
            String s = sc.next();
            int[] stas = new int[26];
            int cnt = 0;
            for (char c : s.toCharArray()) {
                if (stas[c - 'a'] == 0) cnt++;
                stas[c - 'a']++;
            }
            System.out.println(cnt);
        }

        //resolve

        //output
        sc.close();
    }
}
