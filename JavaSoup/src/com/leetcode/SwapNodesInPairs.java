package com.leetcode;

import com.lqw.common.WebPath;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SwapNodesInPairs {

    /**
     * brute force
     * O(n)
     *
     * @param head
     * @return
     */
    public ListNode swapPairs(ListNode head) {
        //guard for linkedlist
        ListNode guard = new ListNode(-1);
        guard.next = head;
        ListNode start = head, before = guard;
        while (start != null) {
            ListNode after = start.next;
            if (after == null) break;
            ListNode temp = after.next;
            after.next = start;
            start.next = temp;
            before.next = after;
            //renew start
            before = start;
            start = start.next;
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

        //resolve

        //output
        sc.close();
        System.err.println("use time: " + (System.currentTimeMillis() - begin) / 1000.0 + " seconds.");
    }
}
