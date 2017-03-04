package com.hackerrank.Ad_infinitum_16;

import com.lqw.common.WebPath;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

/**
 * 采用中位数的概念即可
 */
public class HyperspaceTravel_median {
    public static void main(String[] argv) throws FileNotFoundException {
        Scanner sc = new Scanner(new File(WebPath.getAbsolutePathWithClass("/input2.txt").getPath()));
        //        Scanner sc = new Scanner(new BufferedInputStream(System.in));
        //        Scanner sc = new Scanner(System.in);
        //input
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[][] coordinates = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                coordinates[i][j] = sc.nextInt();
            }
        }

        //resolve
        int[] target_coordinates = new int[m];
        //select the min m's
        for (int i = 0; i < m; i++) {
            final int[] temp = new int[n];
            Integer[] temp_idx = new Integer[n];
            for (int j = 0; j < n; j++) { //get the target array to temp
                temp[j] = coordinates[j][i];
                temp_idx[j] = j;
            }
            //sort the idx
            Arrays.sort(temp_idx, new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    return temp[o1] - temp[o2];
                }
            });

            //get the i'th dimension - median
            target_coordinates[i] = temp[temp_idx[(n - 1) / 2]];
        }

        //output
        for (int i = 0; i < m; i++) {
            System.out.print(target_coordinates[i] + " ");
        }
        System.out.println();
        sc.close();
    }
}
