package com.leetcode;

import com.lqw.common.WebPath;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GenerateParentheses {

    public void addRecursive(String cur_str, int left, int right, ArrayList ans) {
        if (left == 0 && right == 0 && !cur_str.equals("")) {
            ans.add(cur_str);
            return;
        }

        if (right > left) {
            if (left > 0) {
                addRecursive(cur_str + "(", left - 1, right, ans);
            }
            addRecursive(cur_str + ")", left, right - 1, ans);
        } else {
            if (left > 0) {
                addRecursive(cur_str + "(", left - 1, right, ans);
            }
        }
    }

    public List<String> generateParenthesis(int n) {
        ArrayList<String> ans = new ArrayList<String>();
        addRecursive("", n, n, ans);
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
        List<String> list = (new GenerateParentheses()).generateParenthesis(0);
        for (String str : list) {
            System.out.println(str);
        }
        //resolve

        //output
        sc.close();
        System.err.println("use time: " + (System.currentTimeMillis() - begin) / 1000.0 + " seconds.");
    }
}
