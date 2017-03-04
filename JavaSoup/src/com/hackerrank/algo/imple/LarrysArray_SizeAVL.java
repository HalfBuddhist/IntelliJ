package com.hackerrank.algo.imple;


import com.algo_ds.tree.SizeAVLTree;
import com.lqw.common.WebPath;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class LarrysArray_SizeAVL {


    public static void main(String[] argv) throws FileNotFoundException{
       Scanner scanner = new Scanner(new File(WebPath.getAbsolutePathWithClass("/com/hackerrank/algo/imple/input.txt").getPath()));
        //        Scanner scanner = new Scanner(new BufferedInputStream(System.in));
        int times = scanner.nextInt();
        while(times-- > 0){
            int n = scanner.nextInt();
            int[] a = new int[n];
            for (int i = 0; i < n; i++){
                a[i] = scanner.nextInt();
            }

            //resolve
            SizeAVLTree sizeAVLTree = new SizeAVLTree<Integer>();
            for (int ele : a){
                sizeAVLTree.insert(ele);
            }
//            System.out.println(sizeAVLTree.getGreatInverseCnt());

            if (sizeAVLTree.getGreatInverseCnt() %2 == 0)
                System.out.println("YES");
            else
                System.out.println("NO");
        }
        scanner.close();
    }
}
