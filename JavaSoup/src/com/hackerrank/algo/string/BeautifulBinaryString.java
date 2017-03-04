package com.hackerrank.algo.string;

import com.lqw.common.WebPath;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * This is a simple greedy algorithm problem.
 * Whenever you encounter a 010 substring,
 * convert it to 011(meaning you must convert the last 0 to a 1).
 */
public class BeautifulBinaryString {

    public static void main(String[] argv) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(WebPath.getAbsolutePathWithClass("/input.txt").getPath()));
        //        Scanner scanner = new Scanner(new BufferedInputStream(System.in));
        int len = scanner.nextInt();
        char[] chars = scanner.next().toCharArray();
        int cnt = 0;
        int idx = 2;
        while (idx < chars.length) {
            if (chars[idx - 2] == '0' && chars[idx - 1] == '1' && chars[idx] == '0') {
                chars[idx] = '1';
                idx += 3;
                cnt++;
            } else
                idx++;
        }
        System.out.println(cnt);

        scanner.close();
    }
}
