package com.hackerrank.week_code_27;

import com.lqw.common.WebPath;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SimplifiedChessEngineII {
    public static void main(String[] argv) throws FileNotFoundException {
        long begin = System.currentTimeMillis();
        Scanner in = new Scanner(new File(WebPath.getAbsolutePathWithClass("/input.txt").getPath()));
        //        Scanner sc = new Scanner(new BufferedInputStream(System.in));
        //        Scanner in = new Scanner(System.in);
        int g = in.nextInt();
        for(int a0 = 0; a0 < g; a0++){
            int w = in.nextInt();
            int b = in.nextInt();
            int m = in.nextInt();
            String[][] white = new String[w][3];
            for(int white_i=0; white_i < w; white_i++){
                for(int white_j=0; white_j < 3; white_j++){
                    white[white_i][white_j] = in.next();
                }
            }
            String[][] black = new String[b][3];
            for(int black_i=0; black_i < b; black_i++){
                for(int black_j=0; black_j < 3; black_j++){
                    black[black_i][black_j] = in.next();
                }
            }
            // your code goes here





        }


        //output
        in.close();
        System.err.println("use time: " + (System.currentTimeMillis() - begin) / 1000.0 + " seconds.");
    }
}
