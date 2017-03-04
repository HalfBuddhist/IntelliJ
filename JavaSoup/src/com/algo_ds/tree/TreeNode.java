package com.algo_ds.tree;

public class TreeNode<KeyType extends Comparable<KeyType>> {
    protected KeyType key;
    protected TreeNode<KeyType> parent = null;

    public TreeNode(KeyType key, TreeNode<KeyType> parent) {
        this.key = key;
        this.parent = parent;
    }

    //getters.
    public KeyType getKey() {
        return key;
    }

    public TreeNode<KeyType> getParent() {
        return parent;
    }
}

