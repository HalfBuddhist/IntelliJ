package com.hackerrank.algo.string;


import com.lqw.common.WebPath;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class AlternatingCharacters {

    static class Result {
        String restr;
        int del_cnt;

        Result(String restr, int del_cnt) {
            this.restr = restr;
            this.del_cnt = del_cnt;
        }
    }

    public static Result count_del(String pstr) {
        int len = pstr.length();
        if (len == 1) return new Result(pstr, 0);
        Result res1 = count_del(pstr.substring(0, len / 2));
        Result res2 = count_del(pstr.substring(len / 2));

        if (res1.restr.charAt(res1.restr.length() - 1) == res2.restr.charAt(0)) {
            return new Result(res1.restr + res2.restr.substring(1), res1.del_cnt + res2.del_cnt + 1);
        } else {
            return new Result(res1.restr + res2.restr, res1.del_cnt + res2.del_cnt);
        }

    }

    public static void main(String[] argv) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(WebPath.getAbsolutePathWithClass("/input.txt").getPath()));
        //        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        while (t-- > 0) {
            String str = scanner.next();
            System.out.println(count_del(str).del_cnt);
        }
        scanner.close();
    }
}
