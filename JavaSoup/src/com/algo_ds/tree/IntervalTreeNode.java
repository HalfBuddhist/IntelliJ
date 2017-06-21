package com.algo_ds.tree;


public class IntervalTreeNode extends BinaryTreeNode {
    public Interval interval;
    public int value; //statistic info on the interval of current

    public IntervalTreeNode(Interval interval, IntervalTreeNode leftChild, IntervalTreeNode rightChild,
                            IntervalTreeNode parent, int value) {
        super(interval.mid(), leftChild, rightChild, parent);
        this.value = value;
        this.interval = interval;
    }
}
