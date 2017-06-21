package com.poj;

import com.lqw.common.WebPath;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * base algo, greedy, proved by recursive technique.
 * From the start run to the R distancee to put a stone or light, another R to get the start position
 * of the next light laying out. repeate this process untill the end of the array.
 * This greedy algo could be proved by recursive technique, that is, prove the first layouted light is
 * a part of the optimal laying out strategy, then select the others, which then became a similar problem
 * to the current, so the optimality of the operations in the greedy algo could be proved in the recursive
 * way.
 * O(nlogn)
 * AC
 */
public class SarumansArmy {
    public static void main(String[] argv) throws FileNotFoundException {
        long begin = System.currentTimeMillis();
        Scanner sc = new Scanner(new File(WebPath.getAbsolutePathWithClass("/input.txt").getPath()));
        //        Scanner sc = new Scanner(new BufferedInputStream(System.in));
        //        Scanner sc = new Scanner(System.in);
        //        System.setOut(new PrintStream(new FileOutputStream(new File(WebPath.getAbsolutePathWithClass().getPath() + "output.txt"))));
        //presolve
        //input
        int r = sc.nextInt();
        int n = sc.nextInt();
        while (r != -1 && n != -1) {
            int[] locations = new int[n];
            for (int i = 0; i < n; i++) {
                locations[i] = sc.nextInt();
            }
            Arrays.sort(locations);

            //resovle
            int cnt = 0;
            int first = 0;
            int cur = first;//next to check
            while (cur < n) {
                //find the R to put
                while (cur < n && locations[cur] - locations[first] <= r) {
                    cur++;
                }
                cnt++;
                if (cur >= n) {
                    break;
                }
                //find the R to start
                first = cur - 1;//first is the local to put
                cur = first;
                while (cur < n && locations[cur] - locations[first] <= r) {
                    cur++;
                }
                if (cur >= n) break;
                first = cur;
            }

            System.out.println(cnt);
            //next
            r = sc.nextInt();
            n = sc.nextInt();
        }

        //resolve

        //output
        sc.close();
        System.err.println("use time: " + (System.currentTimeMillis() - begin) / 1000.0 + " seconds.");
    }
}
