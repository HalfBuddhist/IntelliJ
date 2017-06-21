package com.algo_ds.tree;

import jdk.nashorn.internal.ir.BinaryNode;
import org.apache.commons.lang3.ArrayUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class BinaryTreeUT {
    @Test
    public void test_bst_general() {
        int arr[] = {3, 2, 1, 4, 5, 6, 7, 16, 15, 14, 13, 12, 11, 10, 8, 9};
//        int arr[] = {3, 4, 6, 2, 5, 1};

        BinaryTree<Integer> tree = new BinaryTree<Integer>();
        System.out.printf("== 依次添加: ");
        for (int i : arr) {
            System.out.printf("insert %d ", i);
            tree.insert(i);
        }

        System.out.printf("\n== 前序遍历: ");
        tree.preOrder(node -> {
            System.out.print(((BinaryTreeNode) node).key + " ");
        });

        System.out.printf("\n== 中序遍历: ");
        tree.inOrder(node -> {
            System.out.print(((BinaryTreeNode) node).key + " ");
        });

        System.out.printf("\n== 后序遍历: ");
        tree.postOrder(node -> System.out.print(((BinaryTreeNode) node).key + " "));
        System.out.printf("\n");

        System.out.printf("\n== 层次遍历: ");
        tree.levelorder(node -> System.out.print(((BinaryTreeNode) node).key + " "));
        System.out.printf("\n");

        System.out.printf("== 最小值: %d\n", tree.minimum());
        System.out.printf("== 最大值: %d\n", tree.maximum());
        System.out.printf("== 数量值: %d\n", tree.getSize());
        System.out.printf("== 树的详细信息: \n");
        tree.print();

        //test the find func
        System.out.println(tree.search(5));
        System.out.println(tree.iterativeSearch(5));

        int i = 7;
        System.out.printf("\n== 删除节点: %d", i);
        tree.delete(i);

        System.out.printf("\n== 中序遍历: ");
        tree.inOrder(node -> {
            System.out.print(((BinaryTreeNode) node).key + " ");
        });
        System.out.printf("\n== 树的详细信息: \n");
        tree.print();
        System.out.printf("== 数量值: %d\n", tree.getSize());
    }

    /**
     * test convert the bst to a doulbe cycled linked list
     */
    @Test
    public void test_conver2_cycled_doulbe_ll() {
        List<Integer> a = Arrays.asList(ArrayUtils.toObject(new int[]{4, 2, 1, 3, 5}));
        BinaryTree<Integer> bst = new BinaryTree<>();
        a.forEach(i -> {
            bst.insert(i);
        });

        //print tree
        System.out.println("tree:");
        bst.inOrder(node -> {
            System.out.print(node.key + " ");
        });
        System.out.println();

        //print linked list
        System.out.println("list:");
        BinaryTreeNode head = bst.convert2CycledDoubleLL();
        printList(head);   // 1 2 3 4 5   yay!
    }

    // 打印双链表
    private static void printList(BinaryTreeNode head) {
        BinaryTreeNode current = head;

        while (current != null) {
            System.out.print(current.key + " ");
            current = current.right;
            if (current == head) //this is cycled double linked list.
                break;
        }
        System.out.println();
    }
}
