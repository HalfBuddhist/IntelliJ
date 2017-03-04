package com.hackerrank.algo.string;

import com.lqw.common.WebPath;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;

public class Gemstones {
    public static void main(String[] argv) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(WebPath.getAbsolutePathWithClass("/input.txt").getPath()));
        //        Scanner scanner = new Scanner(new BufferedInputStream(System.in));
        //        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        String[] stones = new String[n];
        for (int i = 0; i < n; i++) {
            stones[i] = scanner.next();
        }

        //form sets
        HashSet<Character>[] set_char_ele = new HashSet[n];
        for (int i = 0; i < n; i++) {
            HashSet<Character> t = new HashSet<Character>();
            for (char c : stones[i].toCharArray()){
                t.add(c);
            }
            set_char_ele[i] = t;
        }

        //form interjectins
        HashSet<Character> interj = set_char_ele[0];
        for (int i = 1; i < n; i++) {
            interj.retainAll(set_char_ele[i]);
        }

        //output
        System.out.println(interj.size());
        scanner.close();
    }
}
