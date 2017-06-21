package com.leetcode;

import com.lqw.common.WebPath;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }


    ListNode(int x, ListNode next) {
        val = x;
        this.next = next;
    }
}

public class AddTwoNumbers {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int jin = 0;
        ListNode start = new ListNode(-1);
        ListNode cur = start;
        while (l1 != null && l2 != null) {
            int sum = l1.val + l2.val + jin;
            cur.next = new ListNode(sum % 10);
            jin = sum / 10;
            cur = cur.next;
            l1 = l1.next;
            l2 = l2.next;
        }

        ListNode nnull = l1 == null ? l2 : l1;
        while (nnull != null) {
            int sum = nnull.val + jin;
            cur.next = new ListNode(sum % 10);
            jin = sum / 10;
            cur = cur.next;
            nnull = nnull.next;
        }
        if (jin != 0) {
            cur.next = new ListNode(1);
        }

        return start.next;
    }


    public static void main(String[] argv) throws FileNotFoundException {
        long begin = System.currentTimeMillis();
        Scanner sc = new Scanner(new File(WebPath.getAbsolutePathWithClass("/input.txt").getPath()));
        //        Scanner sc = new Scanner(new BufferedInputStream(System.in));
        //        Scanner sc = new Scanner(System.in);
        //presolve
        //input
        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(8);
//        l1.next.next = new ListNode(3);

        ListNode l2 = new ListNode(0);
//        l2.next = new ListNode(6);
//        l2.next.next = new ListNode(4);

        ListNode ans = (new AddTwoNumbers()).addTwoNumbers(l1, l2);

        while (ans != null) {
            System.out.print(ans.val);
            ans = ans.next;
        }

        //resolve

        //output
        sc.close();
        System.err.println("use time: " + (System.currentTimeMillis() - begin) / 1000.0 + " seconds.");
    }
}
