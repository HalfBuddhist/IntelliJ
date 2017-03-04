package com.hackerrank.code30days;

import com.lqw.common.WebPath;

import java.util.*;
import java.io.*;
import java.util.LinkedList;

class Node3 {
    Node3 left, right;
    int data;

    Node3(int data) {
        this.data = data;
        left = right = null;
    }

}

public class BSTLevelOrderTravelsal {

    public static void levelOrder(Node3 node1){
        if (node1 == null) return;
        Queue queue = new LinkedList<Node3>();
        queue.add(node1);
        while (!queue.isEmpty()) {
            Node3 node3 = (Node3)queue.remove();
            System.out.print(node3.data + " ");
            if (node3.left != null)
                queue.add(node3.left);
            if (node3.right != null)
                queue.add(node3.right);
        }
    }

    public static Node3 insert(Node3 root, int data) {
        if (root == null) {
            return new Node3(data);
        } else {
            Node3 cur;
            if (data <= root.data) {
                cur = insert(root.left, data);
                root.left = cur;
            } else {
                cur = insert(root.right, data);
                root.right = cur;
            }
            return root;
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File(WebPath.getAbsolutePathWithClass("/com/hackerrank/code30days/input.txt").getPath()));
//        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        Node3 root = null;
        while (T-- > 0) {
            int data = sc.nextInt();
            root = insert(root, data);
        }
        levelOrder(root);
    }

}
