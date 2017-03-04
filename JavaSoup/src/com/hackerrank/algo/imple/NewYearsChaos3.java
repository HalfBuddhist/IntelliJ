package com.hackerrank.algo.imple;

import com.algo_ds.tree.SizeAVLTree;
import com.lqw.common.WebPath;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Vector;

/**
 * user the inversion method by AVLTree
 * O(nlogn)
 */
public class NewYearsChaos3 {
    public static void main(String[] argv) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(WebPath.getAbsolutePathWithClass("/com/hackerrank/algo/imple/input.txt").getPath()));
        //        Scanner scanner = new Scanner(new BufferedInputStream(System.in));
        int t = scanner.nextInt();
        while (t-- > 0) {
            int n = scanner.nextInt();
            Vector<Integer> vector = new Vector<Integer>(n);
            for (int i = 1; i <= n; i++) {
                vector.add(scanner.nextInt());
            }

            //resolve
            boolean is_chaos = false;
            SizeAVLTree sizeAVLTree = new SizeAVLTree<Integer>();
            int pre_lesscnt = sizeAVLTree.getLessInverseCnt();
            for (int i = vector.size() - 1; i >= 0; i--) {
                sizeAVLTree.insert(vector.get(i));
                if (sizeAVLTree.getLessInverseCnt() - pre_lesscnt > 2) {
                    is_chaos = true;
                    break;
                }
                pre_lesscnt = sizeAVLTree.getLessInverseCnt();
            }

            if (is_chaos) System.out.println("Too chaotic");
            else System.out.println(sizeAVLTree.getLessInverseCnt());
        }
        scanner.close();
    }
}
