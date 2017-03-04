package com.hackerrank.algo.string;


import com.lqw.common.WebPath;

import java.io.File;
import java.util.*;

public class SuperReducedStringRef {

    public static void main(String[] args) throws Exception{
        Scanner scan = new Scanner(new File(WebPath.getAbsolutePathWithClass("/input.txt").getPath()));
//        Scanner scan = new Scanner(System.in);
        String s = scan.next();
        scan.close();

        while (true) {
            int len = s.length();
            s = s.replaceAll("(.)\\1", "");
            if (s.length() == len) {
                break;
            }
        }

        System.out.println((s.isEmpty()) ? "Empty String" : s);
    }
}