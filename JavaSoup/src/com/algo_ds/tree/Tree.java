package com.algo_ds.tree;


public class Tree<KeyType extends Comparable<KeyType>> {
    protected TreeNode<KeyType> mRoot = null;   // 根结点
    protected int size = 0;

    public Tree() {
        this.mRoot = null;
        this.size = 0;
    }

    //getters.
    public TreeNode<KeyType> getmRoot() {
        return mRoot;
    }

    public int getSize() {
        return size;
    }
}
