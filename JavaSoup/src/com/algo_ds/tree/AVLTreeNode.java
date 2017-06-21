package com.algo_ds.tree;

/**
 * AVL树的节点
 *
 * @param <T>
 */
public class AVLTreeNode<KeyType extends Comparable<KeyType>> extends BinaryTreeNode {
    protected int height = 0;

    public AVLTreeNode(KeyType key, AVLTreeNode<KeyType> leftChild, AVLTreeNode<KeyType> rightChild,
                       AVLTreeNode<KeyType> parent) {
        super(key, leftChild, rightChild, parent);
        this.height = 0;
    }

    protected void replaceNodeData(KeyType key, AVLTreeNode<KeyType> left, AVLTreeNode<KeyType> right,
                                   int height) {
        this.key = key;
        this.left = left;
        this.right = right; // discard the original childrens
        if (hasLeftChild())
            this.left.parent = this;
        if (hasRightChild())
            this.right.parent = this;
        this.height = height;
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
