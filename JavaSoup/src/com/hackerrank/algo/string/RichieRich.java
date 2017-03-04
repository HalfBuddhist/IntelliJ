package com.hackerrank.algo.string;

import com.lqw.common.WebPath;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


class Digit {
    char leastChangeChar;
    boolean isSingle;
    int leastChangeNum;
    int largestNumChange;

    Digit(char leastChangeChar, boolean isSingle, int leastChangeNum) {
        this.leastChangeChar = leastChangeChar;
        this.isSingle = isSingle;
        this.leastChangeNum = leastChangeNum;
        if (leastChangeChar == '9')
            this.largestNumChange = this.leastChangeNum;
        else
            this.largestNumChange = this.isSingle ? 1 : 2;
    }
}

public class RichieRich {
    public static void main(String[] argv) throws FileNotFoundException {
        Scanner sc = new Scanner(new File(WebPath.getAbsolutePathWithClass("/input.txt").getPath()));
        //        Scanner sc = new Scanner(new BufferedInputStream(System.in));
        //        Scanner sc = new Scanner(System.in);
        //input
        int n = sc.nextInt();
        int k = sc.nextInt();
        String numstr = sc.next();

        //resolve
        int half_len = (int) (Math.ceil(n / 2.0));
        Digit[] digits = new Digit[half_len];
        int least_cnt = 0;
        for (int i = 0; i < half_len; i++) {
            if (i == half_len - 1 && half_len * 2 > n) {
                //is midle
                char first = numstr.charAt(i);
                digits[i] = new Digit(first, true, 0);
            } else {
                char fist = numstr.charAt(i);
                char second = numstr.charAt(n - 1 - i);
                digits[i] = new Digit(fist > second ? fist : second, false, fist == second ? 0 : 1);
                least_cnt += digits[i].leastChangeNum;
            }
        }

        if (least_cnt <= k) {
            //construct the char
            int left = k - least_cnt;
            char[] largestNumCharSeq = new char[n];
            for (int i = 0; i < half_len; i++) {
                if (left < digits[i].largestNumChange - digits[i].leastChangeNum) {
                    largestNumCharSeq[n - 1 - i] = largestNumCharSeq[i] = digits[i].leastChangeChar;
                } else {
                    largestNumCharSeq[i] = largestNumCharSeq[n - 1 - i] = '9';
                    left -= digits[i].largestNumChange - digits[i].leastChangeNum;
                }
            }

            System.out.print(largestNumCharSeq);
        } else {
            System.out.println(-1);
        }

        //output
        sc.close();
    }
}
