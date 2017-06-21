package com.leetcode;

import com.lqw.common.WebPath;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class LengthOfLastWord_58 {
    /**
     * brute force, defination
     * from the end finding the latest space character and record the location
     * the distance will be the answer.
     * remember that the first several space char's at the very end should be neglected.
     * O(n)
     * AC
     *
     * @param s
     * @return
     */
    public int lengthOfLastWord(String s) {
        int n = s.length();
        int j = n - 1, i = n - 1;
        boolean has_last_word = false;
        while (i >= 0) {
            if (s.charAt(i) == ' ') {
                if (has_last_word) {
                    break;
                } else {
                    j--;
                }
            } else {
                has_last_word = true;
            }
            i--;
        }
        return j - i;
    }

    public static void main(String[] argv) throws FileNotFoundException {
        long begin = System.currentTimeMillis();
        Scanner sc = new Scanner(new File(WebPath.getAbsolutePathWithClass("/input.txt").getPath()));
        //        Scanner sc = new Scanner(new BufferedInputStream(System.in));
        //        Scanner sc = new Scanner(System.in);
        //        System.setOut(new PrintStream(new FileOutputStream(new File(WebPath.getAbsolutePathWithClass().getPath() + "output.txt"))));
        //presolve
        //input
        //resolve
        int a = (new LengthOfLastWord_58()).lengthOfLastWord("hello world   ");
        System.out.println(a);

        //output

        sc.close();
        System.err.println("use time: " + (System.currentTimeMillis() - begin) / 1000.0 + " seconds.");
    }
}
