package com.hackerrank.algo.imple;

/**
 * 同一复杂度也有快慢之分，所以局部的优化还是必要的：教训
 * 利用反向思考，将无序归为有序的过程，因为整个过程是完全可逆的。
 * 所以可利用冒泡排序，并记录其中的交换的次数即为bridbe的次数。
 * 利用索引结构记录每个元素的bribe的资料，条件满足立即退出。
 * 一但有序即可退出。
 * 复杂度为O(n^2)
 */
import com.lqw.common.WebPath;

import java.io.File;
import java.util.*;

public class NewYearChaos {

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
            //由于vector为连续的，所以这个索引结构可用数组来代替。
            HashMap<Integer, Integer> bribes = new HashMap<Integer, Integer>();
            for (int i = 0; i < vector.size(); i++) {
                boolean ischanged = false;
                for (int j = 0; j < vector.size() - i - 1; j++) {
                    if (vector.get(j) > vector.get(j + 1)) {
                        //record
                        if (bribes.containsKey(vector.get(j)))
                            bribes.put(vector.get(j), (Integer) (bribes.get(vector.get(j))) + 1);
                        else
                            bribes.put(vector.get(j), 1);
                        //exchage
                        int temp = vector.get(j);
                        vector.set(j, vector.get(j + 1));
                        vector.set(j + 1, temp);
                        sum++;
                        ischanged = true;
                    }
                }
                if (!ischanged) break;
            }
            for (HashMap.Entry entry : bribes.entrySet()) {
                if (2 < (Integer)entry.getValue()) {
                    ischao = true;
                    break;
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
