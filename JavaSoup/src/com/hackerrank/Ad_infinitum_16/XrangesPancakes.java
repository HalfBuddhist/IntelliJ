package com.hackerrank.Ad_infinitum_16;

import com.lqw.common.WebPath;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


class RegularNGonState {
    double jiaodu = 0;
    boolean isFlipped = false;

    RegularNGonState() {
        this.jiaodu = 0;
        this.isFlipped = false;
    }
}

public class XrangesPancakes {
    public static void regulation_jiaodu(RegularNGonState regularNGonState) {
        while (regularNGonState.jiaodu >= 360) {
            regularNGonState.jiaodu -= 360;
        }
        while (regularNGonState.jiaodu < 0) {
            regularNGonState.jiaodu += 360;
        }
    }

    public static void main(String[] argv) throws FileNotFoundException {
        Scanner sc = new Scanner(new File(WebPath.getAbsolutePathWithClass("/input2.txt").getPath()));
        //        Scanner sc = new Scanner(new BufferedInputStream(System.in));
        //        Scanner sc = new Scanner(System.in);
        //input
        int n = sc.nextInt();
        int m = sc.nextInt();
        RegularNGonState regularNGonState = new RegularNGonState();
        while (m-- > 0) {
            int t = sc.nextInt();
            int k = sc.nextInt();
            if (t == 1) {
                regularNGonState.jiaodu += 360.0 * k / n;
            } else if (t == 2) {
                regulation_jiaodu(regularNGonState);
                if (regularNGonState.jiaodu >= 180.0 * k / n) {
                    regularNGonState.jiaodu -= (regularNGonState.jiaodu - 180.0 * k / n) * 2;
                } else {
                    regularNGonState.jiaodu += (-regularNGonState.jiaodu + 180.0 * k / n) * 2;
                }
                regularNGonState.isFlipped = !regularNGonState.isFlipped;
            }
            regulation_jiaodu(regularNGonState);
        }

        int o_k = 0;
        if (regularNGonState.isFlipped) {
            o_k = (int) ((regularNGonState.jiaodu * n) / (360.0) + 0.5);
            assert (o_k >= 0 && o_k < n);
            System.out.println("2 " + o_k);
        } else {
            o_k = (int) ((regularNGonState.jiaodu) * n / 360.0 + 0.5);
            assert (o_k >= 0 && o_k < n);
            System.out.println("1 " + (o_k > 0 ? (n - o_k) : o_k));
        }


        //output
        sc.close();
    }
}
