package com.leetcode;

import com.lqw.common.WebPath;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class GroupAnagrams {
    /**
     * brute force, defination; Or, SPCS, Hash in string.
     * Sort every string respectively, and store them in an hash table.
     * Then the anagrams will be under the same key of the hash table.
     * A simple coverstion would be the answer.
     * <p/>
     * O(n*l*log(l)), n, len of the array; l, avg len of the strings.
     * AC
     *
     * @param strs
     * @return
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        ArrayList<List<String>> ans = new ArrayList<List<String>>();
        HashMap<String, List<String>> map = new HashMap<String, List<String>>();

        for (String str : strs) {
            char[] t = str.toCharArray();
            Arrays.sort(t);
            String tstr = new String(t);
            if (map.containsKey(tstr)) {
                map.get(tstr).add(str);
            } else {
                ArrayList<String> tlist = new ArrayList<String>();
                tlist.add(str);
                map.put(tstr, tlist);
            }
        }


        for (Map.Entry<String, List<String>> entry : map.entrySet()) {
            ans.add(entry.getValue());
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

        //resolve

        //output
        sc.close();
        System.err.println("use time: " + (System.currentTimeMillis() - begin) / 1000.0 + " seconds.");
    }
}
