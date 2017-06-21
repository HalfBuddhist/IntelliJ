package com.leetcode;

import com.lqw.common.WebPath;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ReverseNodesInKGroup {

    /**
     * LinkedList, brute force, 2 pointers
     * O(n)
     *
     * @param head
     * @param k
     * @return
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        if (k==1) return head;
        ListNode start, end, guard = new ListNode(-1);//start not included, end included.
        guard.next = head;
        start = end = guard;
        while (true) {
            //find k
            int cnt = 0;
            while (end.next != null && cnt < k) {
                end = end.next;
                cnt++;
            }
            if (cnt != k)
                break;

            //back up
            ListNode next = end.next;

            //have get the stat and end, reverse the currentK
            ListNode c = start.next.next;
            ListNode last = start.next;
            while (c != end) {
                ListNode temp = c.next;
                c.next = last;
                //renew last and c
                last = c;
                c = temp;
            }
            end.next = last;
            start.next.next = next;
            ListNode newStart = start.next;
            start.next = end;

            //next five
            start = end = newStart;
        }

        return guard.next;
    }

    public static void main(String[] argv) throws FileNotFoundException {
        long begin = System.currentTimeMillis();
        Scanner sc = new Scanner(new File(WebPath.getAbsolutePathWithClass("/input.txt").getPath()));
        //        Scanner sc = new Scanner(new BufferedInputStream(System.in));
        //        Scanner sc = new Scanner(System.in);
        //        System.setOut(new PrintStream(new FileOutputStream(new File(WebPath.getAbsolutePathWithClass().getPath() + "output.txt"))));
        //presolve
        //input
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2, node1);
        ListNode node3 = new ListNode(3, node2);
        ListNode node4 = new ListNode(4, node3);
        ListNode node5 = new ListNode(5, node4);

        ListNode node = (new ReverseNodesInKGroup()).reverseKGroup(node5, 1);

        while (node != null) {
            System.out.print(node.val + "->");
            node = node.next;
        }
        System.out.println();
        //resolve

        //output
        sc.close();
        System.err.println("use time: " + (System.currentTimeMillis() - begin) / 1000.0 + " seconds.");
    }
}
