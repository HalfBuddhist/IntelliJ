package com.nowcoder.WAP_FirstRound;

import com.lqw.common.WebPath;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by Qingwei on 2017/5/26.
 */
public class AddAB {
    public static void main(String[] argv) throws FileNotFoundException {
        long begin = System.currentTimeMillis();
        Scanner sc = new Scanner(new File(WebPath.getAbsolutePathWithClass("/input.txt").getPath()));
        //        Scanner sc = new Scanner(new BufferedInputStream(System.in));
        //        Scanner sc = new Scanner(System.in);
        //        System.setOut(new PrintStream(new FileOutputStream(new File(WebPath.getAbsolutePathWithClass().getPath() + "output.txt"))));
        //presolve
        //input
        long a = sc.nextLong();
        long b = sc.nextLong();
        System.out.println(a + b);

        //resolve

        //output

        sc.close();
        System.err.println("use time: " + (System.currentTimeMillis() - begin) / 1000.0 + " seconds.");
    }
}
