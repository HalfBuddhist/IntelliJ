package com.leetcode;

import com.lqw.common.WebPath;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LetterCombinationsOfAPhoneNumber {

    //iterative brute force, O((3^n)*n)
    //and this complexity is irreducible
    public List<String> letterCombinations(String digits) {
        //boundries resolve
        if (digits == null || digits.equals("") || digits.length() == 0)
            return new ArrayList<String>();

        //presolve
        List<String> ans = new ArrayList<String>();
        String[] mapping = new String[10];
        mapping[0] = " ";
        mapping[1] = " ";
        mapping[2] = "abc";
        mapping[3] = "def";
        mapping[4] = "ghi";
        mapping[5] = "jkl";
        mapping[6] = "mno";
        mapping[7] = "pqrs";
        mapping[8] = "tuv";
        mapping[9] = "wxyz";

        int n = digits.length();
        StringBuilder base = new StringBuilder("");
        for (char c : digits.toCharArray()) {
            base.append("0");
        }

        //accumlate and output
        while (true) {
            //out
            StringBuilder it_str = new StringBuilder("");
            for (int i = 0; i < n; i++) {
                it_str.append(mapping[digits.charAt(i) - '0'].charAt(base.charAt(i) - '0'));
            }

            ans.add(it_str.toString());
            //base +1;
            int jin = 1;
            for (int i = n - 1; i >= 0; i--) {
                if (jin + (base.charAt(i) - '0') >= mapping[digits.charAt(i) - '0'].length()) {
                    base.replace(i, i + 1, "0");
                    jin = 1;
                } else {
                    char c = (char) (jin + base.charAt(i));
                    base.replace(i, i + 1, c + "");
                    jin = 0;
                }
            }
            if (jin == 1) {
                break;
            }
        }
        return ans;
    }

    public static void main(String[] argv) throws FileNotFoundException {
        long begin = System.currentTimeMillis();
        Scanner sc = new Scanner(new File(WebPath.getAbsolutePathWithClass("/input.txt").getPath()));
        //        Scanner sc = new Scanner(new BufferedInputStream(System.in));
        //        Scanner sc = new Scanner(System.in);
        //        System.setOut(new PrintStream(new FileOutputStream(new File(WebPath.getAbsolutePathWithClass().getPath() + "output.txt"))));
        //presolve
        //input
        String i = sc.next();
        List<String> arr = new LetterCombinationsOfAPhoneNumber().letterCombinations(i);
        System.out.println(arr.size());
        for (String str : arr) {
            System.out.println(str);
        }

        //resolve

        //output
        sc.close();
        System.err.println("use time: " + (System.currentTimeMillis() - begin) / 1000.0 + " seconds.");
    }
}
