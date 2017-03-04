package com.hackerrank.algo.string;

import com.lqw.common.WebPath;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TwoStrings {
    public static void main(String[] argv) throws FileNotFoundException {
        Scanner sc = new Scanner(new File(WebPath.getAbsolutePathWithClass("/input.txt").getPath()));
        //        Scanner sc = new Scanner(new BufferedInputStream(System.in));
        //        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while (t-- > 0) {
            String str1 = sc.next();
            int[] str1_alpha = new int[26];
            String str2 = sc.next();
            int[] str2_alpha = new int[26];
            for (char c : str1.toCharArray()) {
                str1_alpha[c - 'a']++;
            }
            for (char c : str2.toCharArray()) {
                str2_alpha[c - 'a']++;
            }
            boolean has_common = false;
            for (int i = 0; i < 26; i++) {
                if (str1_alpha[i] != 0 && str2_alpha[i] != 0) {
                    has_common = true;
                    break;
                }
            }

            if (has_common) System.out.println("YES");
            else System.out.println("NO");
        }

        sc.close();
    }
}
