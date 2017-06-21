package com.algo_ds.tree;

/**
 * 可以允许重复，并统计重复的次数
 */
public class RepeatedAVLTreeNode<T extends Comparable<T>> extends AVLTreeNode {
    public int cnt = 0;

    public RepeatedAVLTreeNode(T key, RepeatedAVLTreeNode leftChild, RepeatedAVLTreeNode rightChild, RepeatedAVLTreeNode parent) {
        super(key, leftChild, rightChild, parent);
        this.cnt = 1;
    }

    @Override
    protected void replaceNodeData(TreeNode from) {
        super.replaceNodeData(from);
        this.cnt = ((RepeatedAVLTreeNode) from).cnt;
    }
}
