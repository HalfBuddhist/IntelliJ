package com.leetcode;

import com.lqw.common.WebPath;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class RotateList_61 {
    /**
     * brute force, defination
     * find the length n, and get mod of k use n.
     * find the new head first, then connect the tails and form the new tail.
     * <p>
     * O(n)
     * AC
     *
     * @param head
     * @param k
     * @return
     */
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null) return null;
        //calc length
        int n = 0;
        ListNode cur = head;
        ListNode last1 = null;
        while (cur != null) {
            n++;
            last1 = cur;
            cur = cur.next;
        }

        //find the new head
        k = k % n;
        k = n - k; //shift left k
        if (k == n) return head; //no need to rotate.
        int i = 0;
        cur = head;
        ListNode last = last1;
        while (i < k) {
            last = cur;
            cur = cur.next;
            i++;
        }

        ListNode newhead = cur;
        last.next = null;

        //connect
        last1.next = head;

        return newhead;
    }

    public static void main(String[] argv) throws FileNotFoundException {
        long begin = System.currentTimeMillis();
        Scanner sc = new Scanner(new File(WebPath.getAbsolutePathWithClass("/input.txt").getPath()));
        //        Scanner sc = new Scanner(new BufferedInputStream(System.in));
        //        Scanner sc = new Scanner(System.in);
        //        System.setOut(new PrintStream(new FileOutputStream(new File(WebPath.getAbsolutePathWithClass().getPath() + "output.txt"))));
        //presolve
        //input
        ListNode a = new ListNode(1);
        ListNode b = new ListNode(2);
        a.next = b;
        ListNode head = (new RotateList_61().rotateRight(a, 1));


        //resolve

        //output

        sc.close();
        System.err.println("use time: " + (System.currentTimeMillis() - begin) / 1000.0 + " seconds.");
    }
}
