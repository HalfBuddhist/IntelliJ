package com.hackerrank.algo.string;

import com.lqw.common.WebPath;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class GameThronesI {
    public static void main(String[] argv) throws FileNotFoundException {
        Scanner sc = new Scanner(new File(WebPath.getAbsolutePathWithClass("/input.txt").getPath()));
        //        Scanner sc = new Scanner(new BufferedInputStream(System.in));
        //        Scanner sc = new Scanner(System.in);
        String str = sc.next();
        int[] alphabet = new int[26];
        for (char c : str.toCharArray()) {
            alphabet[c - 'a']++;
        }
        int odds = 0, evens = 0, len = str.length();
        for (int i : alphabet) {
            if (i % 2 == 0) evens++;
            else odds++;
        }

        //output
        boolean if_palindrome;
        if (odds >= 2) if_palindrome = false;
        else if_palindrome = true;

        if (if_palindrome) System.out.println("YES");
        else System.out.println("NO");

        sc.close();
    }
}
