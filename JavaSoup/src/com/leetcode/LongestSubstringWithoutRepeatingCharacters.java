package com.leetcode;

import com.lqw.common.WebPath;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class LongestSubstringWithoutRepeatingCharacters {
    public int lengthOfLongestSubstring(String s) {
        int length = Integer.MIN_VALUE, start = -1, end = 0;
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        while (end < s.length()) {
            if (!map.containsKey(s.charAt(end))) {
                map.put(s.charAt(end), end);
                end++;
            } else {
                //get length
                int len = end - 1 - start;
                length = Integer.max(len, length);

                //move start and end
                //remove char in map
                int new_start = map.get(s.charAt(end));
                for (int i = start + 1; i <= new_start; i++) {
                    map.remove(s.charAt(i));
                }
                start = new_start;
            }
        }

        //update the last time
        int len = end - 1 - start;
        length = Integer.max(len, length);

        return length;
    }

    public static void main(String[] argv) throws FileNotFoundException {
        long begin = System.currentTimeMillis();
        Scanner sc = new Scanner(new File(WebPath.getAbsolutePathWithClass("/input.txt").getPath()));
        //        Scanner sc = new Scanner(new BufferedInputStream(System.in));
        //        Scanner sc = new Scanner(System.in);
        //presolve
        //input
        int i = 4;
        while (i-- > 0) {
            String s = sc.nextLine();
            if (s == null || s.equals("")) break;
//            System.out.println(s);
            System.out.println(new LongestSubstringWithoutRepeatingCharacters().lengthOfLongestSubstring(s));
        }


        //resolve

        //output
        sc.close();
        System.err.println("use time: " + (System.currentTimeMillis() - begin) / 1000.0 + " seconds.");
    }
}
