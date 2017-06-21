package com.algo_ds.tree.no_inherit;


/**
 * AVL树的节点
 */
public class CntAVLTreeNode<T extends Comparable<T>> {
    protected T key;
    protected CntAVLTreeNode<T> parent = null;
    protected CntAVLTreeNode<T> leftChild = null;
    protected CntAVLTreeNode<T> rightChild = null;
    protected int height = 0;
    public int cnt = 0; // number of the nodes under the node, include the current node.

    public CntAVLTreeNode(T key, CntAVLTreeNode<T> leftChild, CntAVLTreeNode<T> rightChild, CntAVLTreeNode<T> parent) {
        this.key = key;
        this.parent = parent;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
        this.height = 0;
        this.cnt = 1;
    }

    protected void replaceNodeData(CntAVLTreeNode<T> from) {
        this.key = from.key;
    }

    protected void replaceNodeData(T key, CntAVLTreeNode<T> left, CntAVLTreeNode<T> right,
                                   int height, int cnt) {
        this.key = key;
        this.leftChild = left;
        this.rightChild = right; // discard the original childrens
        if (hasLeftChild())
            leftChild.parent = this;
        if (hasRightChild())
            rightChild.parent = this;
        this.height = height;
        this.cnt = cnt;
    }

    public boolean hasLeftChild() {
        return !(this.leftChild == null);
    }

    public boolean hasRightChild() {
        return !(this.rightChild == null);
    }

    public boolean isLeftChild() {
        return this.parent != null && (((CntAVLTreeNode) this.parent).leftChild == this);
    }

    public boolean isRightChild() {
        return this.parent != null && (((CntAVLTreeNode) this.parent).rightChild == this);
    }

    public String toString() {
        if (parent != null)
            return key + " " + height + " " + parent.key;
        else
            return key.toString() + " " + height;
    }

    //getters.
    public int getHeight() {
        return height;
    }
}
