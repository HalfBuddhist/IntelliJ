package com.hackerrank.algo.imple;

/**
 * 同一复杂度也有快慢之分，所以局部的优化还是必要的：教训
 * O（n）,以只翻转两进行一趟排序，如最大的元素肯定在倒数第1，2，3中去找,
 * 而如果排序不成功，则一定会有走过2次的翻转。
 */

import com.lqw.common.WebPath;

import java.io.File;
import java.util.*;

public class NewYearChaos2 {

    public static void main(String[] argv) throws Exception {
        Scanner scanner = new Scanner(new File(WebPath.getAbsolutePathWithClass("/com/hackerrank/algo/imple/input.txt").getPath()));
//                Scanner scanner = new Scanner(new BufferedInputStream(System.in));
        int t = scanner.nextInt();
        while (t-- > 0) {
            int n = scanner.nextInt();
            Vector<Integer> vector = new Vector<Integer>(n);
            for (int i = 1; i <= n; i++) {
                vector.add(scanner.nextInt());
            }

            //calc
            int sum = 0;
            boolean ischao = false;
            if (vector.size() > 1) {
                for (int i = vector.size() - 1; i > 1; i--) {
                    //一定要从最大的找起，因为最小的元素可能因为被贿赂次数太多而到最后，将依次找次大的，次次大的。
                    int first = vector.get(i - 2);
                    int second = vector.get(i - 1);
                    int third = vector.get(i);
                    int max = Collections.max(vector.subList(i - 2, i + 1));
                    vector.set(i, max);
                    if (max == first) {
                        sum += 2;
                        vector.set(i - 2, second);
                        vector.set(i - 1, third);
                    } else if (max == second) {
                        sum += 1;
                        vector.set(i - 1, third);
                    }
                }

                int first = vector.get(0);
                int second = vector.get(1);
                if (vector.get(1) < vector.get(0)) {
                    sum++;
                    vector.set(0, second);
                    vector.set(1, first);
                }

                //if in order
                for (int i = 0; i < vector.size()-1; i++) {
                    if (vector.get(i) > vector.get(i+1)){
                        ischao = true;
                        break;
                    }
                }
            }

            if (!ischao)
                System.out.println(sum);
            else
                System.out.println("Too chaotic");

        }

        scanner.close();
    }
}
