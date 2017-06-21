package com.nowcoder.WAP_live_coding;

import com.lqw.common.WebPath;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ReorderLinkedList {

    private static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }


    /**
     * 1011.Reorder List (in nowcoder)
     * Given a singly linked list L: L0→L1→…→Ln-1→Ln, reorder it to: L0→Ln→L1→Ln-1→L2→Ln-2→…
     * For example, Given {1->2->3->4}, reorder it to {1->4->2->3}.
     * Note:
     * You must do this in-place without altering the nodes’ values.
     * Time Complexity: O(n), Space Complexity: O(1)
     * <p>
     * brute force, defination.
     * <p>
     * divide the linkedlist into 2 parts, reverset the order of the second half.
     * merge the two parts.
     * note the odd and even of the number of the total nodes.
     * <p>
     * O(N) time, O(1) space in total
     * AC
     *
     * @param head
     */
    public void reorderList(ListNode head) {
        // code here
        ListNode first = head;
        ListNode second = null;

        int cnt = 0;//total length
        while (first != null) {
            cnt++;
            first = first.next;
        }

        //split into 2 part.
        int half = cnt / 2;
        first = head;
        while (half-- > 1) {
            first = first.next;
        }
        //get the last one store in head
        second = first.next;
        first.next = null; //get first and second part

        //reverse the second
        ListNode last = null;
        ListNode current = second;
        ListNode next = second == null ? null : second.next;
        while (current != null) {
            //adjjust the pointer
            current.next = last;

            //for next
            last = current;
            current = next;
            next = next == null ? null : next.next;
        }
        second = last; //the second half tail node.


        //merge them
        first = head;
        while (first != null && second != null) {
            //back up the next of new heads.
            ListNode t1 = first.next;
            ListNode t2 = second.next;
            first.next = second;
            second.next = t1 == null ? t2 : t1;

            //for next
            first = t1;
            second = t2;
        }
    }


    /**
     * Input:
     * The first line will be an non-negative integer N,
     * representing the total number of the list;
     * In the second line, then there will be N integer elements,
     * corresponding to value of N nodes respectively.
     * <p>
     * Output:
     * The reordered list.
     * Example:
     * 4
     * 1 2 3 4 -->
     * 1 4 2 3
     *
     * @param argv
     * @throws FileNotFoundException
     */
    public static void main(String[] argv) throws FileNotFoundException {
        long begin = System.currentTimeMillis();
        Scanner jin = new Scanner(new File(WebPath.getAbsolutePathWithClass("/input.txt").getPath()));
        //        Scanner sc = new Scanner(new BufferedInputStream(System.in));
        //        Scanner sc = new Scanner(System.in);
        //        System.setOut(new PrintStream(new FileOutputStream(new File(WebPath.getAbsolutePathWithClass().getPath() + "output.txt"))));
        //presolve
        //input

        int N = jin.nextInt();

        ListNode first = new ListNode(0);
        ListNode current = first;

        while (N-- > 0) {
            current.next = new ListNode(jin.nextInt());
            current = current.next;
        }

        ListNode head = first.next;

        new ReorderLinkedList().reorderList(head);

        while (null != head) {
            System.out.print(head.val);
            head = head.next;
            if (null != head)
                System.out.print(" ");
        }

        System.out.println();

        //resolve

        //output

        jin.close();
        System.err.println("use time: " + (System.currentTimeMillis() - begin) / 1000.0 + " seconds.");
    }

}


