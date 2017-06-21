package com.leetcode;

import com.lqw.common.WebPath;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * TrieTree的节点
 */
class TrieTreeNode {

    private final char nodeChar; //该节点的字符
    private TrieTreeNode[] childNodes = null; //一个TrieTree的节点的子节点
    private int count = 0;// 单词数量，用于判断一个单词是否存在
    private int prefixCount = 0;// 前缀数量，用于查找该前缀出现的次数
    //add by temp
    public int t_count = 0;

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
        this.t_count++;
    }

    public int getCount() {
        return this.count;
    }

    public void addPrefixCount() {
        this.prefixCount++;
    }

//    public boolean is_leaf_node() {
//        return this.childNodes == null;
//    }

    public int getPrefixCount() {
        return this.prefixCount;
    }

    @Override
    public String toString() {
        return "TrieNode [nodeChar=" + nodeChar + "]";
    }

}

/**
 * 一个支持前缀查找以及精确查找的Trie树
 * <p/>
 * 注：还可以添加的操作如下：
 * 1，删除操作，要删除掉count, prefixcnt
 * 2, 查询公共前缀的单词集合，找到公共前缀的结点，向下先根遍历即可
 */
class TrieTree {

    protected TrieTreeNode root = new TrieTreeNode('a');// TrieTree的根节点， a为无意义字符
    //add by lqw
    public int words_cnt = -1;
    public HashSet<TrieTreeNode> words_set = new HashSet<TrieTreeNode>();

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
        index.addCount(); //find one word node.
        words_set.add(index);
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
     * 查询指定次数的word
     *
     * @param word
     * @return
     */
    public boolean queryWordInCNT(String word) {
        TrieTreeNode index = this.root;
        for (char c : word.toLowerCase().toCharArray()) {
            index = index.getChild(c);
            if (null == index) {
                return false;
            }
        }

        if (index.getCount() > 0) { //exist
            //统计count
            int cur_n = index.t_count - 1;
            if (cur_n < 0) return false;
            else {
                if (cur_n == 0) {
                    //has meet, delete from words_cnt;
                    words_cnt -= index.getCount();
                }
                index.t_count--;
                return true;
            }
        } else {
            return false;
        }
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


public class SubstringWithConcatenationOfAllWords {

    /**
     * SPCS - 用哈希表取代trie, 来因为n的大小并不大
     * O(nhl), 因为字符串哈希同样需要O(l)的时间
     *
     * @param S
     * @param L
     * @return
     */
    public List<Integer> findSubstring_2pointers(String S, String[] L) {
        List<Integer> res = new ArrayList<Integer>();
        if (S == null || L == null || L.length == 0) return res;
        int len = L[0].length(); // length of each word

        Map<String, Integer> map = new HashMap<String, Integer>(); // map for L
        for (String w : L) map.put(w, map.containsKey(w) ? map.get(w) + 1 : 1);

        for (int i = 0; i <= S.length() - len * L.length; i++) {
            Map<String, Integer> copy = new HashMap<String, Integer>(map);
            for (int j = 0; j < L.length; j++) { // checkc if match
                String str = S.substring(i + j * len, i + j * len + len); // next word
                if (copy.containsKey(str)) { // is in remaining words
                    int count = copy.get(str);
                    if (count == 1) copy.remove(str);
                    else copy.put(str, count - 1);
                    if (copy.isEmpty()) { // matches
                        res.add(i);
                        break;
                    }
                } else break; // not in L
            }
        }
        return res;
    }

    /**
     * SPCS - Trie tree
     * O(nlh)
     * n - words number; l - word length; h - len of s.
     *
     * @param s
     * @param words
     * @return
     */
    public List<Integer> findSubstring(String s, String[] words) {
        //answer
        ArrayList<Integer> ans = new ArrayList<Integer>();
        //boundary
        if (s == null || s.equals("") || words.length == 0 || words[0].length() == 0)
            return ans;

        //construct trie
        TrieTree trie = new TrieTree();
        trie.words_cnt = words.length;
        for (String word : words) {
            trie.insertWord(word);
        }

        //find in trie.
        int n = words.length;
        int l = n > 0 ? words[0].length() : 0;
        for (int i = 0; i < s.length() - n * l + 1; i++) {
            //search in n*l steps, break case: map < 0, no found
            for (int j = 0; j < n; j++) {
                String word = s.substring(i + j * l, i + j * l + l);
                if (!trie.queryWordInCNT(word)) {
                    break;
                }
            }
            if (trie.words_cnt == 0) { //has found one
                ans.add(i);
            }

            //recovery the map and cnt
            trie.words_cnt = words.length;
            Iterator<TrieTreeNode> iterator = trie.words_set.iterator();
            while (iterator.hasNext()) {
                TrieTreeNode node = iterator.next();
                node.t_count = node.getCount();
            }
        }

        return ans;
    }


    public static void main(String[] argv) throws FileNotFoundException {
        long begin = System.currentTimeMillis();
        Scanner sc = new Scanner(new File(WebPath.getAbsolutePathWithClass("/input.txt").getPath()));
        //        Scanner sc = new Scanner(new BufferedInputStream(System.in));
        //        Scanner sc = new Scanner(System.in);
        //        System.setOut(new PrintStream(new FileOutputStream(new File(WebPath.getAbsolutePathWithClass().getPath() + "output.txt"))));
        //presolve
        //input
        ArrayList<Integer> ans = (ArrayList<Integer>) (new SubstringWithConcatenationOfAllWords().findSubstring("barfoothefoobarman",
                new String[]{"foo", "bar"}));

//        String s = sc.next();
//        int n = sc.nextInt();
//        System.out.println(n + " len ");
//        String[] words = new String[n];
//        int idx = 0;
//        while (n-- > 0) {
//            words[idx++] = sc.next();
//        }
//        ArrayList<Integer> ans = (ArrayList<Integer>) (new SubstringWithConcatenationOfAllWords().findSubstring(s, words));
        //resolve
        System.out.println(ans.size());
        for (Integer i : ans) {
            System.out.print(i + "\t");
        }

        //output
        sc.close();


        System.err.println("use time: " + (System.currentTimeMillis() - begin) / 1000.0 + " seconds.");
    }
}
