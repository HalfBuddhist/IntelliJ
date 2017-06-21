package com.acmerblog;

import com.lqw.common.WebPath;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * 排序数组转化为平衡二分查找树
 */
public class SortedArrayToBalancedBST {

    /**
     * Base algo, recursive & backtracking.
     * 给一个已排序的数组，将其转化为一颗平衡二叉树。
     * <p/>
     * 平衡二叉树要求左右子树的高度差不超过1。我们把排序数组的中间节点作为根，
     * 可保证左右子树的元素个数差不超过1，则肯定是平衡二叉树。这个很好理解，不多解释了。利用递归可以很容易的解决。
     * 使用递归的一个要点是，一定要相信我们的递归函数会返回正确的结果，只要处理好返回条件，代码就很简单了。
     * <p/>
     * O(n): length of arr
     * <p/>
     * Examples:
     * Input:  Array {1, 2, 3}
     * Output: A Balanced BST
     * 2
     * /  \
     * 1    3
     * <p/>
     * Input: Array {1, 2, 3, 4}
     * Output: A Balanced BST
     * 3
     * /  \
     * 2    4
     * /
     * 1
     *
     * @param arr
     * @param s
     * @param e
     * @return
     */
    public static Tree sortedArray2BalancedBST(int arr[], int s, int e) {
        //返回条件
        if (s > e)
            return null;
        int mid = (s + e) / 2;
        //把中间节点作为根
        Tree root = new Tree(arr[mid]);
        //分别递归左右子树
        root.left = sortedArray2BalancedBST(arr, s, mid - 1);
        root.right = sortedArray2BalancedBST(arr, mid + 1, e);
        return root;
    }

    //二叉树类
    static class Tree {
        public Tree left, right;
        public int data;

        public Tree(int d) {
            data = d;
        }

        //中序遍历
        public void inOrder() {
            if (this.left != null)
                this.left.inOrder();
            System.out.print(data + " ");
            if (this.right != null)
                this.right.inOrder();
        }

        //先序遍历
        public void preOrder() {
            System.out.print(data + " ");
            if (this.left != null)
                this.left.preOrder();
            if (this.right != null)
                this.right.preOrder();
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
        int arr[] = {1, 2, 3, 4, 5, 6, 7};
        Tree tree = sortedArray2BalancedBST(arr, 0, arr.length - 1);
        tree.inOrder();
        System.out.println();
        tree.preOrder();

        //resolve

        //output
        sc.close();
        System.err.println("use time: " + (System.currentTimeMillis() - begin) / 1000.0 + " seconds.");
    }
}
