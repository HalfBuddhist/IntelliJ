package com.hackerrank.code30days;

import com.lqw.common.WebPath;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class String2Interger {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File(WebPath.getAbsolutePathWithClass("/com/hackerrank/code30days/input.txt").getPath()));
//        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String digitstr = sc.next();
            try {
                int abc = Integer.parseInt(digitstr);
                System.out.println(abc);
            } catch (NumberFormatException e) {
                System.out.println("Bad String");
            }
        }
    }
}