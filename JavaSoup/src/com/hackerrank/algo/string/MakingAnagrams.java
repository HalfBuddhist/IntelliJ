package com.hackerrank.algo.string;

import com.lqw.common.WebPath;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MakingAnagrams {
    public static void main(String[] argv) throws FileNotFoundException {
        Scanner sc = new Scanner(new File(WebPath.getAbsolutePathWithClass("/input.txt").getPath()));
        //        Scanner sc = new Scanner(new BufferedInputStream(System.in));
        //        Scanner sc = new Scanner(System.in);
        String a = sc.next();
        int[] aphabet_a = new int[26];
        String b = sc.next();
        int[] aphabet_b = new int[26];
        for (char c : a.toCharArray()) {
            aphabet_a[c - 'a']++;
        }
        for (char c : b.toCharArray()) {
            aphabet_b[c - 'a']++;
        }

        int sum = 0;
        for (int i = 0; i < 26; i++) {
            sum += Math.abs(aphabet_a[i] - aphabet_b[i]);
        }

        System.out.println(sum);

        sc.close();
    }
}
