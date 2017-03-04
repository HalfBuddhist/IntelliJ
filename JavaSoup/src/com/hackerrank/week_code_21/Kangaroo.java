package com.hackerrank.week_code_21;


import com.lqw.common.WebPath;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Scanner;

public class Kangaroo {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File(WebPath.getAbsolutePathWithClass("/com/hackerrank/week_code_21/input.txt").getPath()));
//        Scanner sc = new Scanner(System.in);
        BigDecimal x1 = new BigDecimal(sc.next());
        BigDecimal v1 = new BigDecimal(sc.next());
        BigDecimal x2 = new BigDecimal(sc.next());
        BigDecimal v2 = new BigDecimal(sc.next());

        int cmp = v1.compareTo(v2);
        if (cmp == 0){
            int cmp2 = x1.compareTo(x2);
            if (cmp2 == 0) System.out.println("YES");
            else System.out.println("NO");
        }else{
            try {
                BigDecimal time = x1.subtract(x2).divide(v2.subtract(v1));
                BigInteger time_int = time.toBigIntegerExact();
                int cmp3 = time_int.compareTo(BigInteger.ZERO);
                if (cmp3 >=0 )
                    System.out.println("YES");
                else
                    System.out.println("NO");
            }catch (ArithmeticException e){
                System.out.println("NO");
            }
        }
    }
}
