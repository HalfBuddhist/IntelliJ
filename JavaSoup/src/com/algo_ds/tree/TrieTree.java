package com.algo_ds.tree;

/**
 * 一个支持前缀查找以及精确查找的Trie树
 *
 * 注：还可以添加的操作如下：
 * 1，删除操作，要删除掉count, prefixcnt
 * 2, 查询公共前缀的单词集合，找到公共前缀的结点，向下先根遍历即可
 */
public class TrieTree {

    protected TrieTreeNode root = new TrieTreeNode('a');// TrieTree的根节点， a为无意义字符

    /**
     * 插入
     *
     * @param word
     */
    public void insertWord(String word) {
        TrieTreeNode index = this.root;
        for (char c : word.toLowerCase().toCharArray()) {
            index = index.addChild(c);
            index.addPrefixCount();
        }
        index.addCount();
        return;
    }

    /**
     * 查找字符串是否存在
     *
     * @param word
     * @return
     */
    public boolean doesWordExist(String word) {
        TrieTreeNode index = this.root;
        for (char c : word.toLowerCase().toCharArray()) {
            index = index.getChild(c);
            if (null == index) {
                return false;
            }
        }
        return index.getCount() > 0;
    }

    /**
     * 查找前缀出现的次数
     *
     * @param prefix
     * @return
     */
    public int selectPrefixCount(String prefix) {
        TrieTreeNode index = this.root;
        for (char c : prefix.toLowerCase().toCharArray()) {
            index = index.getChild(c);
            if (null == index) {
                return 0;
            }
        }
        return index.getPrefixCount();
    }

    public static void main(String[] args) {
        TrieTree trie = new TrieTree();
        trie.insertWord("Vicky");
    }
}