package com.hackerrank.code30days;


import com.lqw.common.WebPath;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MoreLinkedList {

    public static Node insert(Node head, int data) {
        Node p = new Node(data);
        if (head == null)
            head = p;
        else if (head.next == null)
            head.next = p;
        else {
            Node start = head;
            while (start.next != null)
                start = start.next;
            start.next = p;

        }
        return head;
    }

    public static void display(Node head) {
        Node start = head;
        while (start != null) {
            System.out.print(start.data + " ");
            start = start.next;
        }
    }

    public static Node removeDuplicates(Node node){
        if (node == null) return node;
        Node pre = node;
        Node next = pre.next;
        while (next != null)
        {
            if (pre.data == next.data){
                pre.next = next.next;
                next = pre.next;
            }
            else{
                pre = next;
                next = pre.next;
            }
        }
        return node;
    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File(WebPath.getAbsolutePathWithClass("/com/hackerrank/code30days/input.txt").getPath()));
//        Scanner sc = new Scanner(System.in);
        Node head = null;
        int T = sc.nextInt();
        while (T-- > 0) {
            int ele = sc.nextInt();
            head = insert(head, ele);
        }

        head = removeDuplicates(head);
        display(head);
    }
}
