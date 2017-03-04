package com.hackerrank.Ad_infinitum_16;

import com.lqw.common.WebPath;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class HyperspaceTravel {
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
            long sum = 0;
            for (int j = 0; j < n; j++) { //get the target array to temp
                sum += coordinates[j][i] + (long) Math.pow(10, 9);
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
            sum -= (temp[temp_idx[0]] + (long) Math.pow(10, 9)) * n; //set the first to the first ele
            assert sum >= 0;

            //get the i'th dimension.
            long last_sum = Long.MAX_VALUE, min_sum = Long.MAX_VALUE;
            int min_sum_idx = 0;
            for (int j = 0; j < n; j++) {
                if (j > 0) {
                    int cha = temp[temp_idx[j]] - temp[temp_idx[j - 1]];
                    if (cha == 0) continue;
                    sum += (long)j * cha;
                    sum -= (long)(n - j) * cha;
                }

                if (sum < min_sum) {
                    min_sum = sum;
                    min_sum_idx = j;
                }

                if (sum > last_sum) {
                    break;
                }
                last_sum = sum;
            }
            target_coordinates[i] = temp[temp_idx[min_sum_idx]];
        }

        //output
        for (int i = 0; i < m; i++) {
            System.out.print(target_coordinates[i] + " ");
        }
        System.out.println();
        sc.close();
    }
}
