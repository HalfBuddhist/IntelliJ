package com.hackerrank.algo.string;

import com.lqw.common.WebPath;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Anagram {
    public static void main(String[] argv) throws FileNotFoundException {
        Scanner sc = new Scanner(new File(WebPath.getAbsolutePathWithClass("/input.txt").getPath()));
        //        Scanner sc = new Scanner(new BufferedInputStream(System.in));
        //        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while (t-- > 0) {
            String str = sc.next();
            int len = str.length();
            if (len % 2 == 1) System.out.println(-1);
            else {
                int[] first_alpha = new int[26];
                int[] second_alpha = new int[26];
                String first = str.substring(0, len / 2);
                String second = str.substring(len / 2);
                for (int i = 0; i < len / 2; i++) {
                    first_alpha[first.charAt(i) - 'a']++;
                    second_alpha[second.charAt(i) - 'a']++;
                }

                int cnt = 0;
                for (int i = 0; i < 26; i++) {
                    cnt += Math.abs(first_alpha[i] - second_alpha[i]);
                }

                System.out.println(cnt / 2);

            }
        }
        sc.close();
    }
}
