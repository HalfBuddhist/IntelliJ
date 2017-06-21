package com.algo_ds.tree;

public class BinaryTreeNode<T extends Comparable<T>> extends TreeNode {
    //children
    protected BinaryTreeNode<T> left = null;
    protected BinaryTreeNode<T> right = null;

    public BinaryTreeNode(T key, BinaryTreeNode<T> left, BinaryTreeNode<T> right, BinaryTreeNode<T> parent) {
        super(key, parent);
        this.left = left;
        this.right = right;
    }

    public boolean hasLeftChild() {
        return !(this.left == null);
    }

    public boolean hasRightChild() {
        return !(this.right == null);
    }

    public boolean getLeft() {
        return this.parent != null && (((BinaryTreeNode) this.parent).left == this);
    }

    public boolean getRight() {
        return this.parent != null && (((BinaryTreeNode) this.parent).right == this);
    }

    public boolean isRoot() {
        return (this.parent == null);
    }

    public boolean isLeaf() {
        return (this.left == null) && (this.right == null);
    }

    public boolean hasAnyChildren() {
        return (this.left != null) || (this.right != null);
    }

    public boolean hasBothChildren() {
        return (this.left != null) && (this.right != null);
    }

    protected void replaceNodeData(T key, BinaryTreeNode<T> left, BinaryTreeNode<T> right) {
        this.key = key;
        this.left = left;
        this.right = right; // discard the original childrens
        if (hasLeftChild())
            this.left.parent = this;
        if (hasRightChild())
            this.right.parent = this;
    }


    public String toString() {
        if (parent != null)
            return key + " " + parent.key;
        else
            return key.toString();
    }

    //setters and getters.
    public BinaryTreeNode<T> getLeftChild() {
        return left;
    }


    public BinaryTreeNode<T> getRightChild() {
        return right;
    }

}


