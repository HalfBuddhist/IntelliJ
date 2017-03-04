package com.hackerrank.algo.string;

import com.lqw.common.WebPath;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class PalindromeIndex {
    public static boolean is_palindrome(String pstr) {
        if (pstr == null || pstr.length() == 1) {
            return true;
        } else {
            int i = 0, j = pstr.length() - 1;
            boolean palindrome = true;
            while (i < j) {
                if (pstr.charAt(i) != pstr.charAt(j)) {
                    palindrome = false;
                    break;
                } else {
                    i++;
                    j--;
                }
            }
            return palindrome;
        }
    }

    public static void main(String[] argv) throws FileNotFoundException {
        Scanner sc = new Scanner(new File(WebPath.getAbsolutePathWithClass("/input.txt").getPath()));
        //        Scanner sc = new Scanner(new BufferedInputStream(System.in));
        //        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while (t-- > 0) {
            String str = sc.next();
            int i = 0;
            int j = str.length() - 1;
            while (i < j) {
                if (str.charAt(i) == str.charAt(j)) {
                    i++;
                    j--;
                } else break;
            }
            if (i >= j) {
                //this is a palindrome.
                System.out.println(-1);
            } else {
                int index;
                //del i
                if (is_palindrome(str.substring(i + 1, j + 1)))
                    index = i;
                else if (is_palindrome(str.substring(i, j))) //del j
                    index = j;
                else index = -1;
                System.out.println(index);
            }
        }
        sc.close();
    }
}
