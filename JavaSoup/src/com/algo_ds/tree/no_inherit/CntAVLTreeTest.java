package com.algo_ds.tree.no_inherit;

import org.junit.Test;

public class CntAVLTreeTest {
    @Test
    public void test_cnt_avl_tree() {
//        int arr[] = {3, 2, 1, 4, 5, 6, 7, 16, 15, 14, 13, 12, 11, 10, 8, 9}; //very unbalanced tree.
//        int arr[] = {3, 4, 6, 2, 5, 1}; //simple example.
//        int arr[] = {7, 4, 8, 3, 5, 9, 6}; //test for delete 9, one rotation.
        int arr[] = {10, 6, 13, 4, 8, 12, 14, 3, 5, 7, 9, 11, 2}; // delete 14,会有两次旋转

        CntAVLTree<Integer> tree = new CntAVLTree<Integer>();

        System.out.printf("== 依次添加: ");
        for (int i = 0; i < arr.length; i++) {
            System.out.printf("%d ", arr[i]);
            tree.insert(arr[i]);
        }

        System.out.printf("\n== 前序遍历: ");
        tree.preOrder();

        System.out.printf("\n== 中序遍历: ");
        tree.inOrder();

        System.out.printf("\n== 后序遍历: ");
        tree.postOrder();

        System.out.printf("\n== 层次遍历: ");
        tree.levelorder();
        System.out.printf("\n");

        System.out.printf("== 高度: %d\n", tree.height());
        System.out.printf("== 最小值: %d\n", tree.minimum());
        System.out.printf("== 最大值: %d\n", tree.maximum());
        System.out.printf("== 大小： %d\n", tree.getSize());
        System.out.printf("== 树的详细信息: \n");
        tree.print();

        int del = 14;
        System.out.printf("\n== 删除根节点: %d", del);
//        tree.remove(del);
        tree.k_remove(5);

        System.out.printf("\n== 高度: %d", tree.height());
        System.out.printf("\n== 中序遍历: ");
        tree.inOrder();
        System.out.printf("\n== 树的详细信息: \n");
        tree.print();
    }

}
