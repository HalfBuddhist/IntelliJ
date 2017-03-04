package com.hackerrank.code30days;

import com.lqw.common.WebPath;

import java.io.*;
import java.util.*;

class Node {
    int data;
    Node next;

    Node(int d) {
        data = d;
        next = null;
    }
}

class LinkedList {

    public static Node insert(Node head, int data) {
        //Complete this method
        Node newnode = new Node(data);

        if (head == null)
            return newnode;
        else {
            Node tail = head;
            while (tail.next != null) {
                tail = tail.next;
            }
            tail.next = newnode;
            return head;
        }
    }

    public static void display(Node head) {
        Node start = head;
        while (start != null) {
            System.out.print(start.data + " ");
            start = start.next;
        }
    }

    public static void main(String[] args) throws FileNotFoundException{
        Scanner sc = new Scanner(new File(WebPath.getAbsolutePathWithClass("/com/hackerrank/code30days/input.txt").getPath()));
//        Scanner sc = new Scanner(System.in);
        Node head = null;
        int N = sc.nextInt();

        while (N-- > 0) {
            int ele = sc.nextInt();
            head = insert(head, ele);
        }
        display(head);
        sc.close();
    }
}