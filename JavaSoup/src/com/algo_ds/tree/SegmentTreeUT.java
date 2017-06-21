package com.algo_ds.tree;


import com.lqw.common.WebPath;
import org.junit.Test;

import java.io.*;
import java.util.Scanner;

public class SegmentTreeUT {

    /**
     * reference the EnemyEmbattle in hdu_oj for the example
     * the statistic info on the node is the sum on the segment.
     *
     * @throws IOException
     */
    @Test
    public void test_segment_tree() throws IOException {
        Scanner sc = new Scanner(new File(WebPath.getAbsolutePathWithClass("/input.txt").getPath()));
        int n, cas = 1;
        int a[];
        n = sc.nextInt(); //camp number
        a = new int[n];
        for (int i = 1; i <= n; i++) {
            a[i - 1] = sc.nextInt();
        }

        //resolve
        SegmentTree tree = new SegmentTree(a);
        while (true) {
            String order = sc.next();
            if (order.equals("End")) break;
            else if (order.equals("Query")) {
                int L = sc.nextInt();
                int R = sc.nextInt();
                long ans = tree.query(L, R);
                System.out.println(ans);
            } else if (order.equals("Add")) {
                int pos = sc.nextInt();
                long val = sc.nextLong();
                tree.modify(pos, val);
            } else if (order.equals("Sub")) {
                int pos = sc.nextInt();
                long val = sc.nextLong() * (-1);
                tree.modify(pos, val);
            }
        }
    }
}

//10
//        1 2 3 4 5 6 7 8 9 10
//        Query 1 3
//        Add 3 6
//        Query 2 7
//        Sub 10 2
//        Add 6 3
//        Query 3 10
//        End
