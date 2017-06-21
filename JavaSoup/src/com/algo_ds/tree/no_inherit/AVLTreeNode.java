package com.algo_ds.tree.no_inherit;


/**
 * AVL树的节点
 */
public class AVLTreeNode<T extends Comparable<T>> {
    protected T key;
    protected AVLTreeNode<T> parent = null;
    protected AVLTreeNode<T> leftChild = null;
    protected AVLTreeNode<T> rightChild = null;
    protected int height = 0;

    public AVLTreeNode(T key, AVLTreeNode<T> leftChild, AVLTreeNode<T> rightChild, AVLTreeNode<T> parent) {
        this.key = key;
        this.parent = parent;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
        this.height = 0;
    }

    protected void replaceNodeData(AVLTreeNode<T> from) {
        this.key = from.key;
    }

    protected void replaceNodeData(T key, AVLTreeNode<T> left, AVLTreeNode<T> right,
                                   int height) {
        this.key = key;
        this.leftChild = left;
        this.rightChild = right; // discard the original childrens
        if (hasLeftChild())
            leftChild.parent = this;
        if (hasRightChild())
            rightChild.parent = this;
        this.height = height;
    }

    public boolean hasLeftChild() {
        return !(this.leftChild == null);
    }

    public boolean hasRightChild() {
        return !(this.rightChild == null);
    }

    public boolean isLeftChild() {
        return this.parent != null && (((AVLTreeNode) this.parent).leftChild == this);
    }

    public boolean isRightChild() {
        return this.parent != null && (((AVLTreeNode) this.parent).rightChild == this);
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
