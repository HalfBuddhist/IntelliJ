package com.acmerblog;

//============================================================================
// Name        : TreeNodesDistanceAtK.java
// Author      : GaoTong
// Date        : 2014/7/26
// Copyright   : www.acmerblog.com
//============================================================================

public class TreeNodesDistanceAtK {
    static class Node {
        Node left, right;
        int data;

        public Node(int i) {
            this.data = i;
        }
    }

    /**
     * 找到root子树下面的所有距离为k的节点
     */
    public static void kDistanceNodeDown(Node root, int k) {
        if (root == null || k < 0) return;

        if (k == 0) {
            System.out.println(root.data);
            return;
        }
        kDistanceNodeDown(root.left, k - 1);
        kDistanceNodeDown(root.right, k - 1);
    }

    /**
     * 打印以root为根的二叉树中，到target距离为k的节点
     * 时间复杂度为 O(N)，因为每个节点都最多会被遍历2次
     * (一次是在查找target时，另一次是在另一半子树查找减少距离的节点时))。
     *
     * @param root   树的根节点
     * @param target 目标节点
     * @param k      距离k
     * @return root到target的距离,-1表示target不在root下面
     */
    public static int kDistanceNode(Node root, Node target, int k) {
        if (root == null) return -1;

        if (root == target) {
            kDistanceNodeDown(root, k);
            return 0;
        }

        int dis_left = kDistanceNode(root.left, target, k);
        if (dis_left != -1) {
            if (dis_left + 1 == k)
                System.out.println(root.data);
            else
                kDistanceNodeDown(root.right, k - dis_left - 2); //左孩子和右孩子之间相差2个距离
            return 1 + dis_left;
        }

        //和上面的代码是对称的
        int dis_right = kDistanceNode(root.right, target, k);
        if (dis_right != -1) {
            if (dis_right + 1 == k)
                System.out.println(root.data);
            else
                kDistanceNodeDown(root.left, k - dis_right - 2);
            return dis_right + 1;
        }
        return -1;
    }

    public static void main(String args[]) {
        Node root = new Node(20);
        root.left = new Node(8);
        root.right = new Node(22);
        root.left.left = new Node(4);
        root.left.right = new Node(12);
        root.left.right.left = new Node(10);
        root.left.right.right = new Node(14);
        Node target = root.left;

        kDistanceNode(root, target, 2);
    }
}
