package com.algo_ds.tree;


public class SizeAVLTreeNode<KeyType extends Comparable<KeyType>> extends AVLTreeNode {
    protected int leftCnt = 0;
    protected int rightCnt = 0;

    public SizeAVLTreeNode(KeyType key, SizeAVLTreeNode leftChild, SizeAVLTreeNode rightChild,
                           SizeAVLTreeNode parent) {
        super(key, leftChild, rightChild, parent);
        this.leftCnt = 0;
        this.rightCnt = 0;
    }


    //getters.
    public int getLeftCnt() {
        return leftCnt;
    }

    public int getRightCnt() {
        return rightCnt;
    }
}