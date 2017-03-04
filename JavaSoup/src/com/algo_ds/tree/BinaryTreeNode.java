package com.algo_ds.tree;

public class BinaryTreeNode<KeyType extends Comparable<KeyType>> extends TreeNode {
    protected BinaryTreeNode<KeyType> leftChild = null;
    protected BinaryTreeNode<KeyType> rightChild = null;

    public BinaryTreeNode(KeyType key, BinaryTreeNode<KeyType> leftChild,
                          BinaryTreeNode<KeyType> rightChild,
                          BinaryTreeNode<KeyType> parent) {
        super(key, parent);
        this.leftChild = leftChild;
        this.rightChild = rightChild;
    }

    public boolean hasLeftChild() {
        return !(this.leftChild == null);
    }

    public boolean hasRightChild() {
        return !(this.rightChild == null);
    }

    public boolean isLeftChild() {
        return this.parent != null && (((BinaryTreeNode) this.parent).leftChild == this);
    }

    public boolean isRightChild() {
        return this.parent != null && (((BinaryTreeNode) this.parent).rightChild == this);
    }

    public boolean isRoot() {
        return (this.parent == null);
    }

    public boolean isLeaf() {
        return (this.leftChild == null) && (this.rightChild == null);
    }

    public boolean hasAnyChildren() {
        return (this.leftChild != null) || (this.rightChild != null);
    }

    public boolean hasBothChildren() {
        return (this.leftChild != null) && (this.rightChild != null);
    }

    protected void replaceNodeData(KeyType key, BinaryTreeNode<KeyType> left, BinaryTreeNode<KeyType> right) {
        this.key = key;
        this.leftChild = left;
        this.rightChild = right; // discard the original childrens
        if (hasLeftChild())
            leftChild.parent = this;
        if (hasRightChild())
            rightChild.parent = this;
    }

    public String toString() {
        if (parent != null)
            return key + " " + parent.key;
        else
            return key.toString();
    }

    //setters and getters.
    public BinaryTreeNode<KeyType> getLeftChild() {
        return leftChild;
    }


    public BinaryTreeNode<KeyType> getRightChild() {
        return rightChild;
    }

}


