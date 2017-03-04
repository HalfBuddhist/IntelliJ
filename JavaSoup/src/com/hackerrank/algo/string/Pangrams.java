package com.hackerrank.algo.string;


import com.lqw.common.WebPath;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Pangrams {
    public static void main(String[] argv) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(WebPath.getAbsolutePathWithClass("/input.txt").getPath()));
        //        Scanner scanner = new Scanner(new BufferedInputStream(System.in));
        String p = scanner.nextLine();
        boolean[] flags = new boolean[26];
        int cnt = 0;
        for (char c : p.toLowerCase().toCharArray()) {
            if (c == ' ')
                continue;
            else {
                if (!flags[c - 'a']) {
                    flags[c - 'a'] = true;
                    if (++cnt == 26)
                        break;
                }
            }
        }
        if (cnt == 26) {
            System.out.println("pangram");
        } else
            System.out.println("not pangram");

        scanner.close();
    }
}
