package com.hackerrank.code30days;


import com.lqw.common.WebPath;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

interface AdvancedArithmetic{
    int divisorSum(int n);
}

class Calculator1 implements  AdvancedArithmetic{
    @Override
    public int divisorSum(int n) {
        int sum = 0;
        int i;
        for (i = 1; i <= (int)Math.sqrt(n); i++){
            if (n%i == 0){
                sum += i;
                if (i != n/i)
                    sum += n/i;
            }
        }


        return sum;
    }
}

public class Interfaces {
    public static void main(String[] argh) throws FileNotFoundException {
        Scanner scan = new Scanner(new File(WebPath.getAbsolutePathWithClass("/com/hackerrank/code30days/input.txt").getPath()));
//        Scanner in = new Scanner(System.in);
        int n = scan.nextInt();
        scan.close();

        AdvancedArithmetic myCalculator = new Calculator1();
        int sum = myCalculator.divisorSum(n);
        System.out.println("I implemented: " + myCalculator.getClass().getInterfaces()[0].getName() );
        System.out.println(sum);
    }
}