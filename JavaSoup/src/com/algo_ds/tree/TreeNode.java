package com.algo_ds.tree;

public class TreeNode<T extends Comparable<T>> {
    protected T key;
    protected TreeNode<T> parent = null;

    //constructers
    public TreeNode(T key, TreeNode<T> parent) {
        this.key = key;
        this.parent = parent;
    }

    protected void replaceNodeData(TreeNode<T> from) {
        this.key = from.key;
    }

    //getters.
    public T getKey() {
        return key;
    }

    public TreeNode<T> getParent() {
        return parent;
    }
}

