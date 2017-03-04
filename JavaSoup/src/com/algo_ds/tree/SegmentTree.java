package com.algo_ds.tree;

import java.util.Scanner;

public class SegmentTree {
    private static final int MAXN = 10000; //maxlen.
    private static SegmentTreeNode[] tree = new SegmentTreeNode[MAXN]; //存储用数组
    private static int tod = 0; //数组当前可用结点指针
    private static int min, max, number = 0;    //存储插入时的结点信息

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        build(1, 100);
        while (scan.hasNext()) {
// for (int i = 1; i <= 197; i++)
// {
// System.out.println(i + " " + tree[i].min + " " + tree[i].max
// + " " + tree[i].left + " " + tree[i].right);
// }
            number = 0;
            min = scan.nextInt();
            max = scan.nextInt();
            insert(1);
            count(1);
            System.out.println(number);
        }
    }

    /**
     * build the tree
     * <p/>
     * note: 从创建的过程可知，他并不是典型的堆，而是更像是链表，
     * 不过他的存储结点不是随机的在堆中申请，而是从数组中获取当前可用的结点，
     * 所以不可用2n为其左结点， 2n+1为其右结点的方式来进行存取。
     * 从存取上来讲更像是链表。
     *
     * @param min0 - start point.
     * @param max0 - end point.
     */
    public static void build(int min0, int max0) {
        tod++;
        int now = tod;
        tree[now] = new SegmentTreeNode();
        tree[now].min = min0;
        tree[now].max = max0;
        if (max0 - min0 >= 1) {
            int mid = (min0 + max0) >> 1;
            tree[now].left = tod + 1;
            build(min0, mid);
            tree[now].right = tod + 1;
            build(mid + 1, max0);
        }
    }


    /**
     * insert segment
     *
     * @param num insert point
     */
    public static void insert(int num) {
        if (min <= tree[num].min && max >= tree[num].max) {
            tree[num].cover++;
        } else if (max < tree[num].min || min > tree[num].max) {
            return;
        } else {
            int mid = (tree[num].min + tree[num].max) >> 1;
            if (min <= mid) {
                insert(tree[num].left);
            }
            if (max >= mid) {
                insert(tree[num].right);
            }
        }
    }

    public static void del(int num) {
        if (min <= tree[num].min && max >= tree[num].max) {
            tree[num].cover--;
        } else {
            int mid = (tree[num].min + tree[num].max) / 2;
            if (min <= mid) {
                del(tree[num].left);
            }
            if (max >= mid) {
                del(tree[num].right);
            }
        }
    }


    /**
     * count how many seg covered.
     *
     * @param num the count start point
     */
    public static void count(int num) {
        if (tree[num].cover != 0) {
            number += tree[num].max - tree[num].min + 1;
        } else {
            if (tree[num].left != 0) {
                count(tree[num].left);
            }

            if (tree[num].right != 0) {
                count(tree[num].right);
            }
        }
    }
}

class SegmentTreeNode {
    int min, max, left, right, cover;
}