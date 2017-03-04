package com.hackerrank.algo.string;

import com.lqw.common.WebPath;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


/**
 * O(n3*26)
 */
public class SherlockandAnagrams {

    public static String stat_convert(String pstr){
        int[] a = new int[26];
        for (char c : pstr.toCharArray()){
            a[c-'a']++;
        }

        StringBuffer buffer = new StringBuffer("");
        for (int cnt : a){
            buffer.append(new DecimalFormat("000").format(cnt));
        }

        return buffer.toString();
    }

    public static void main(String[] argv) throws FileNotFoundException {
        Scanner sc = new Scanner(new File(WebPath.getAbsolutePathWithClass("/input.txt").getPath()));
        //        Scanner sc = new Scanner(new BufferedInputStream(System.in));
        //        Scanner sc = new Scanner(System.in);
        //input
        int t = sc.nextInt();
        while (t-- > 0) {
            String str = sc.next();

            //store the every substirng-converted number
            HashMap<String, Integer> anagrams_cnt = new HashMap<String, Integer>();
            for (int i = 1; i <= str.length(); i++) {//every length
                for (int j = 0; j + i - 1 < str.length(); j++) {
                    String str_key = stat_convert(str.substring(j, j + i));
                    Integer cnt = anagrams_cnt.get(str_key);
                    anagrams_cnt.put(str_key, cnt == null ? 1 : (cnt.intValue() + 1));
                }
            }

            //accumulate the combinations
            int pair_cnt = 0;
            for (Map.Entry<String, Integer> entry : anagrams_cnt.entrySet()) {
                int n = entry.getValue();
                pair_cnt += n > 1 ? n * (n - 1) / 2 : 0;
            }

            //output
            System.out.println(pair_cnt);
        }
        sc.close();
    }
}
