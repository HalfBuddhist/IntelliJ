package com.leetcode;

import com.lqw.common.WebPath;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class MergeKSortedLists {


    /**
     * use PriorityQueue,
     * nlogn + nl*logn, a little higher than divide & couquer, for l is in  order of magnitude, sqrt(n), n^x...
     * when l is in constant level same with DC,
     * when l is logn, unknown, guess still DC better as their complexity is Monotonically increasing with
     * the complexity of l in n's order of magnitude regarding the two observation above.
     *
     * @param lists
     * @return
     */
    public ListNode mergeKLists4(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;

        PriorityQueue<ListNode> queue = new PriorityQueue<ListNode>(lists.length, new Comparator<ListNode>() {
            @Override
            public int compare(ListNode o1, ListNode o2) {
                if (o1.val < o2.val)
                    return -1;
                else if (o1.val == o2.val)
                    return 0;
                else
                    return 1;
            }
        });

        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;

        for (int i = 0; i < lists.length; i++) {
            ListNode node = lists[i];
            if (node != null)
                queue.add(node);
        }

        while (!queue.isEmpty()) {
            tail.next = queue.poll();
            tail = tail.next;

            if (tail.next != null)
                queue.add(tail.next);
        }
        return dummy.next;
    }

    /**
     * Divide & conquer
     * n^2, if l is in the same order of magnitude with n, still better than PQ as above.
     * cause the merge need nl operations at most, then as the main theorem, should be n^2.
     * nlogn, if l is a constant level.
     * fastest method for now.
     *
     * @param lists
     * @return
     */
    public ListNode mergeKLists3(ListNode[] lists) {
        return mergeKListsWithIndex(lists, 0, lists.length);
    }


    /**
     * end exclusive, start inclusive
     *
     * @param lists
     * @param start
     * @param end
     * @return
     */
    public ListNode mergeKListsWithIndex(ListNode[] lists, int start, int end) {
        int len = end - start;
        if (len == 1) return lists[start];
        else if (len == 0) return null;
        else {
            ListNode l1 = mergeKListsWithIndex(lists, start, start + len / 2);
            ListNode l2 = mergeKListsWithIndex(lists, start + len / 2, end);
            return mergeTwoLists(l1, l2);
        }
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {

        ListNode ans = new ListNode(-1);
        ListNode cur = ans;
        ListNode start = l1;
        ListNode end = l2;
        while (start != null || end != null) {
            if (end == null ||
                    (start != null && end != null && start.val < end.val)) {
                cur.next = start;
                start = start.next;
            } else {
                cur.next = end;
                end = end.next;
            }
            cur = cur.next;
        }
        return ans.next;

    }

    /**
     * Qsort
     * nl*log(nl)+nl+nl
     *
     * @param lists
     * @return
     */
    public ListNode mergeKLists2(ListNode[] lists) {
        ArrayList<Integer> arr = new ArrayList<Integer>();
        for (int i = 0; i < lists.length; i++) {
            ListNode c = lists[i];
            while (c != null) {
                arr.add(c.val);
                c = c.next;
            }
        }

        Collections.sort(arr);

        //cover to list linked
        ListNode head = new ListNode(-1);
        ListNode cur = head;
        for (int i = 0; i < arr.size(); i++) {
            cur.next = new ListNode(arr.get(i));
            cur = cur.next;
        }
        return head.next;
    }

    /**
     * merge
     * n^2*l
     * l - the average length of the lists
     */
    public ListNode mergeKLists(ListNode[] lists) {
        int len = lists.length;
        ListNode ans = new ListNode(-1);
        ListNode cur = ans;
        boolean end = false;
        for (int i = 0; i < len; i++) {
            end = end || lists[i] != null;
        }
        while (end) {
            int minv = Integer.MAX_VALUE;
            int min_idx = 0;
            for (int i = 0; i < len; i++) {
                if (lists[i] != null && lists[i].val < minv) {
                    minv = lists[i].val;
                    min_idx = i;
                }
            }
            cur.next = lists[min_idx];
            lists[min_idx] = lists[min_idx].next;
            cur = cur.next;

            //get next end
            end = false;
            for (int i = 0; i < len; i++) {
                end = end || lists[i] != null;
            }
        }
        return ans.next;
    }

    public static void main(String[] argv) throws FileNotFoundException {
        long begin = System.currentTimeMillis();
        Scanner sc = new Scanner(new File(WebPath.getAbsolutePathWithClass("/input.txt").getPath()));
        //        Scanner sc = new Scanner(new BufferedInputStream(System.in));
        //        Scanner sc = new Scanner(System.in);
        //        System.setOut(new PrintStream(new FileOutputStream(new File(WebPath.getAbsolutePathWithClass().getPath() + "output.txt"))));
        //presolve
        //input
//        (new MergeKSortedLists()).mergeKLists3(new ListNode[]{null, null});
        int cnt = sc.nextInt();
        sc.nextLine();
        ListNode[] lists = new ListNode[cnt];
        for (int i = 0; i < cnt; i++) {
            ListNode head = new ListNode(-1);
            ListNode cur = head;
            String[] num_str = sc.nextLine().trim().split(" ");
            for (String n_str : num_str) {
                cur.next = new ListNode(Integer.parseInt(n_str));
                cur = cur.next;
            }
            lists[i] = head.next;
        }


        //resolve
        (new MergeKSortedLists()).mergeKLists4(lists);

        //output
        sc.close();
        System.err.println("use time: " + (System.currentTimeMillis() - begin) / 1000.0 + " seconds.");
    }
}
