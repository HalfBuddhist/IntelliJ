package com.hackerrank.week_code_26;

import com.lqw.common.WebPath;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class BestDivisor {

    public static int getDigitSum(int num){
        int sum = 0;
        do {
            sum += num % 10;
            num /= 10;
        } while (num > 0);

        return sum;
    }

    public static void main(String[] argv) throws FileNotFoundException {
        Scanner sc = new Scanner(new File(WebPath.getAbsolutePathWithClass("/input.txt").getPath()));
        // Scanner sc = new Scanner(new BufferedInputStream(System.in));
        // Scanner sc = new Scanner(System.in);
        //input
        int n = sc.nextInt();
        int max = Integer.MIN_VALUE;
        int better_deivisor = -1;

        //resolve
        for (int i = 1; i <= (int)(1 + Math.sqrt(n)); i++) {
            if (n % i == 0) {
                int temp_sum = getDigitSum(i);
                if (temp_sum > max){
                    max = temp_sum;
                    better_deivisor = i;
                }else if (temp_sum == max){
                    if (i < better_deivisor) better_deivisor = i;
                }

                //resolve the supple
                temp_sum = getDigitSum(n/i);
                if (temp_sum > max){
                    max = temp_sum;
                    better_deivisor = n/i;
                }else if (temp_sum == max){
                    if (n/i < better_deivisor) better_deivisor = n/i;
                }
            }
        }

        //output
        System.out.println(better_deivisor);
        sc.close();
    }
}
