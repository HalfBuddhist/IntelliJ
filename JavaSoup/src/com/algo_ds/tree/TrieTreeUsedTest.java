package com.algo_ds.tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TrieTreeUsedTest {
    private TrieTree trie;

    @Before
    public void before() throws IOException {
        Pattern pattern = Pattern.compile("[a-zA-Z]+");

        // 从文件中读取单词，构建TriedTree
        InputStreamReader read = new InputStreamReader(this.getClass().getResourceAsStream("words.txt"));
        BufferedReader reader = new BufferedReader(read);
        trie = new TrieTree();
        String line = null;
        while (null != (line = reader.readLine())) {
            line = line.trim();
            if (!pattern.matcher(line).matches()) {// 去除非法单词，如包含“-”
                continue;
            }
            trie.insertWord(line);
        }
    }

    /**
     * 测试使用TriedTree搜索前缀出现的次数
     */
    @Test
    public void searchExactWords() {
        String prefix = "vi";
        Assert.assertTrue(trie.doesWordExist("Vicky"));
    }

    /**
     * 测试使用TriedTree搜索前缀出现的次数
     */
    @Test
    public void searchPrefixWords() {
        String prefix = "vi";
        int cnt = trie.selectPrefixCount(prefix);
        System.out.println(cnt);
        Assert.assertEquals("vi prefix cnt:", 1600 ,cnt);
    }
}
