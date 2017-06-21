package com.algo_ds.tree;


public class SegmentTreeNode {
    public int value; // statistic info on the current segment, e.g. sum.
    public int lazy; // if need to lazy update the downwards nodes.

    public SegmentTreeNode(int value) {
        this.value = value;
    }
}
