package com.hdu_oj;

import com.lqw.common.WebPath;

import java.io.*;
import java.util.Scanner;


/**
 * 一个线段树的实例，
 * <p>
 * 处理的题目是hdu上的敌兵布阵 hdu-1166
 * 今天我们涉及的是线段树的单点更新以及区间查询功能。
 * 我们以HDU上面的敌兵布阵为例。
 * 题目描述：
 * C国的死对头A国这段时间正在进行军事演习，所以C国间谍头子Derek和他手下Tidy又开始忙乎了。
 * A国在海岸线沿直线布置了N个工兵营地,Derek和Tidy的任务就是要监视这些工兵营地的活动情况。由于采取了某种先进的监测手段，
 * 所以每个工兵营地的人数C国都掌握的一清二楚,每个工兵营地的人数都有可能发生变动，可能增加或减少若干人手,
 * 但这些都逃不过C国的监视。
 * 中央情报局要研究敌人究竟演习什么战术,所以Tidy要随时向Derek汇报某一段连续的工兵营地一共有多少人,
 * 例如Derek问:“Tidy,马上汇报第3个营地到第10个营地共有多少人!”Tidy就要马上开始计算这一段的总人数并汇报。
 * 但敌兵营地的人数经常变动，而Derek每次询问的段都不一样，所以Tidy不得不每次都一个一个营地的去数，很快就精疲力尽了，
 * Derek对Tidy的计算速度越来越不满:"你个死肥仔，算得这么慢，我炒你鱿鱼!”Tidy想：
 * “你自己来算算看，这可真是一项累人的工作!我恨不得你炒我鱿鱼呢!”无奈之下，Tidy只好打电话向计算机专家Windbreaker求救,
 * Windbreaker说：“死肥仔，叫你平时做多点acm题和看多点算法书，现在尝到苦果了吧!”Tidy说："我知错了。。。"
 * 但Windbreaker已经挂掉电话了。Tidy很苦恼，这么算他真的会崩溃的，聪明的读者，
 * 你能写个程序帮他完成这项工作吗？不过如果你的程序效率不够高的话，Tidy还是会受到Derek的责骂的.
 */
public class EnemyEmbattle {
    private static final int maxn = 50050;
    private static long[] sum = new long[maxn << 2]; //middle node, parent node
    private static long[] a = new long[maxn]; // leaf node.

    //update value for the parent
    protected static void pushup(int parent) {
        sum[parent] = sum[parent << 1] + sum[parent << 1 | 1];
    }

    /**
     * build the segment tree.
     * O(n)
     *
     * @param l    start bound
     * @param r    end bound
     * @param node location to store the sum of the value.
     */
    private static void build(int l, int r, int node) {
        if (l == r) {
            sum[node] = a[l];
            return;
        }
        int mid = (l + r) >> 1;
        build(l, mid, node << 1);
        build(mid + 1, r, node << 1 | 1);
        pushup(node);
    }

    /**
     * add value to some location
     * O(log n)
     *
     * @param pos
     * @param increment positive or negtive
     * @param left      left bound of the segment
     * @param right     right bound of the segment
     * @param node      location to store the sum of the value.
     */
    protected static void add(int pos, long increment, int left, int right, int node) {
        if (left == right) {
            sum[node] += increment;
            return;
        }
        int mid = (left + right) >> 1;
        if (pos <= mid)
            add(pos, increment, left, mid, node << 1);
        else
            add(pos, increment, mid + 1, right, node << 1 | 1);
        pushup(node);
    }

    /**
     * query the L-R interval
     * O(log n)
     *
     * @param query_left
     * @param query_right
     * @param left        left bound of the segment
     * @param right       right bound of the segment
     * @param node        store the sum value
     * @return
     */
    private static long query(int query_left, int query_right, int left, int right, int node) {
        if (query_left <= left && right <= query_right) return sum[node];
        int mid = (left + right) >> 1;
        long ans = 0;
        if (query_left <= mid) ans += query(query_left, query_right, left, mid, node << 1);
        if (query_right > mid) ans += query(query_left, query_right, mid + 1, right, node << 1 | 1);
        return ans;
    }

    public static void main(String[] args) throws IOException {
        int T, n, cas = 1;
        StreamTokenizer in = new StreamTokenizer(new FileInputStream(new File(WebPath.getAbsolutePathWithClass("/input.txt").getPath())));
//        StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        in.nextToken();
        T = (int) in.nval;
        while (T > 0) {
            T--;
            out.println("Case " + cas + ":");
            cas++;
            in.nextToken();
            n = (int) in.nval; //capm number
            for (int i = 1; i <= n; i++) {
                in.nextToken();
                a[i] = (long) in.nval;
            }
            build(1, n, 1);
            while (true) {
                in.nextToken();
                String order = (String) in.sval;
                if (order.equals("End")) break;
                else if (order.equals("Query")) {
                    in.nextToken();
                    int L = (int) in.nval;
                    in.nextToken();
                    int R = (int) in.nval;
                    long ans = query(L, R, 1, n, 1);
                    out.println(ans);
                } else if (order.equals("Add")) {
                    in.nextToken();
                    int pos = (int) in.nval;
                    in.nextToken();
                    long val = (long) in.nval;
                    add(pos, val, 1, n, 1);
                } else if (order.equals("Sub")) {
                    in.nextToken();
                    int pos = (int) in.nval;
                    in.nextToken();
                    long val = -(long) in.nval;
                    add(pos, val, 1, n, 1);
                }
            }
        }
        out.flush();
    }
}

//test data
//1
//        10
//        1 2 3 4 5 6 7 8 9 10
//        Query 1 3
//        Add 3 6
//        Query 2 7
//        Sub 10 2
//        Add 6 3
//        Query 3 10
//        End

