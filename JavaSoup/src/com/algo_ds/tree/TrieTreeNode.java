package com.algo_ds.tree;

/**
 * TrieTree的节点
 */
public class TrieTreeNode {
    /**
     * 该节点的字符
     */
    private final char nodeChar;//
    /**
     * 一个TrieTree的节点的子节点
     */
    private TrieTreeNode[] childNodes = null;
    private int count = 0;// 单词数量，用于判断一个单词是否存在
    private int prefixCount = 0;// 前缀数量，用于查找该前缀出现的次数

    public TrieTreeNode(char nodeChar) {
        this.nodeChar = nodeChar;
    }

    public TrieTreeNode addChild(char ch) {
        int index = ch - 'a';
        if (null == childNodes) {
            this.childNodes = new TrieTreeNode[26];
        }
        if (null == childNodes[index]) {
            childNodes[index] = new TrieTreeNode(ch);
        }
        return childNodes[index];
    }

    public TrieTreeNode getChild(char ch) {
        int index = ch - 'a';
        if (null == childNodes || null == childNodes[index]) {
            return null;
        }
        return childNodes[index];
    }

    public void addCount() {
        this.count++;
    }

    public int getCount() {
        return this.count;
    }

    public void addPrefixCount() {
        this.prefixCount++;
    }

    public int getPrefixCount() {
        return this.prefixCount;
    }

    @Override
    public String toString() {
        return "TrieNode [nodeChar=" + nodeChar + "]";
    }

}
