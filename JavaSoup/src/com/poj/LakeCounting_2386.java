package com.poj;

import com.lqw.common.WebPath;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class LakeCounting_2386 {
    static boolean[][] squares = new boolean[105][105];
    static int cnt = 0;
    static int m;
    static int n;

    static void lake_count(int row, int column) {
        if (row < 0 || row >= n || column < 0 || column >= m) {
            return;
        }

        if (squares[row][column]) {
            // enumerate the 3*3 states.
            squares[row][column] = false;
            for (int i = row - 1; i <= row + 1; i++) {
                for (int j = column - 1; j <= column + 1; j++) {
                    lake_count(i, j);
                }
            }
        }
    }


    public static void main(String[] argv) throws FileNotFoundException {
        long begin = System.currentTimeMillis();
        Scanner sc = new Scanner(new File(WebPath.getAbsolutePathWithClass("/input.txt").getPath()));
        //        Scanner sc = new Scanner(new BufferedInputStream(System.in));
        //        Scanner sc = new Scanner(System.in);
        //        System.setOut(new PrintStream(new FileOutputStream(new File(WebPath.getAbsolutePathWithClass().getPath() + "output.txt"))));
        //presolve
        //input

        n = sc.nextInt();
        m = sc.nextInt();
        cnt = 0;
        for (int i = 0; i < n; i++) {
            String row = sc.next();
            int idx = 0;
            for (char c : row.toCharArray()) {
                squares[i][idx++] = c == 'W';
            }
        }


        //resolve
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (squares[i][j]) {
                    cnt++;
                    lake_count(i, j);
                }
            }
        }


        //output
        System.out.println(cnt);
        sc.close();
        System.err.println("use time: " + (System.currentTimeMillis() - begin) / 1000.0 + " seconds.");
    }
}
