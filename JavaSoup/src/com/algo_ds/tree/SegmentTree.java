package com.algo_ds.tree;


import java.util.Arrays;

/**
 * an implementations of segment tree or point tree.
 * on the leaf node, start = end
 * this tree is build necessarily totally, this tree is static to the range of the points.
 * <p>
 * the lazy spreading operation is need to add to the implementation.
 * 节点懒惰标记下推, see wikipedia
 * <p>
 * 整合如下改进
 * zkw线段树是一种自底向上的线段树，由清华大学的张昆玮提出。
 * 它相对于传统线段树的优势体现在减少了递归操作和增加了位运算等操作以减少常数。
 * see the algo special for the ppt.
 */
public class SegmentTree {

    SegmentTreeNode nodes[];
    int n = 0; // number of the leaves.


    public SegmentTree(int[] leaves) {
        n = leaves.length;
        // 由线段树的性质可知，建树所需要的空间大概是所需处理
        // 最长线段长度的2倍多，所以需要开3倍大小的数组
        nodes = new SegmentTreeNode[n * 3];//this is a nearly full binary tree.
        Arrays.setAll(nodes, SegmentTreeNode::new); //call the SegmentTreeNode(int value), parameter is the index
        this.build(1, n, 1, leaves);
    }

    /**
     * update value for the parent
     */
    private void pushup(int parent) {
        nodes[parent].value = nodes[parent << 1].value + nodes[parent << 1 | 1].value;
    }

    /**
     * build the segment tree.
     * 2*i is the left, 2*i+1 is right child.
     * 1 is the root, and the corresponing segment is [1,n], start from 1 not 0.
     * O(2n)
     *
     * @param l    node start bound, include
     * @param r    node end bound, include
     * @param node node to store the nodes inside the bound.
     */
    protected void build(int l, int r, int node, int[] leaves) {
        if (l == r) {
            nodes[node].value = leaves[l - 1];
            return;
        }
        int mid = (l + r) >> 1;
        build(l, mid, node << 1, leaves);
        build(mid + 1, r, node << 1 | 1, leaves);
        pushup(node);
    }

    /**
     * add value to some location
     * 成段更新, 需要用到lazy标记来提高时间效率, 未实现
     * O(log n)
     *
     * @param pos
     * @param increment positive or negtive
     * @param left      left bound of the segment
     * @param right     right bound of the segment
     * @param node      location to store the nodes of the value.
     */
    protected void modify(int pos, long increment, int left, int right, int node) {
        if (left == right) {
            nodes[node].value += increment;
            return;
        }
        int mid = (left + right) >> 1;
        if (pos <= mid)
            modify(pos, increment, left, mid, node << 1);
        else
            modify(pos, increment, mid + 1, right, node << 1 | 1);
        pushup(node);
    }

    public void modify(int pos, long incre) {
        this.modify(pos, incre, 1, n, 1);
    }

    /**
     * query the L-R interval
     * if has lazy flag, need to lazy update as follows.
     * O(log n)
     *
     * @param query_left
     * @param query_right
     * @param left        left bound of the segment
     * @param right       right bound of the segment
     * @param node        store the nodes value
     * @return
     */
    protected long query(int query_left, int query_right, int left, int right, int node) {
        if (query_left <= left && right <= query_right)
            return nodes[node].value;
//        if (lazy[rt]) push_down(rt, r - l + 1);
        int mid = (left + right) >> 1;
        long ans = 0;
        if (query_left <= mid)
            ans += query(query_left, query_right, left, mid, node << 1);
        if (query_right > mid)
            ans += query(query_left, query_right, mid + 1, right, node << 1 | 1);
        return ans;
    }

    public long query(int query_left, int query_right) {
        return this.query(query_left, query_right, 1, n, 1);
    }
}
