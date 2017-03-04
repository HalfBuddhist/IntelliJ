package com.hackerrank.algo.string;

import com.lqw.common.WebPath;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


/**
 * O(n^2*26)
 * <p/>
 * 因为我们要统计对于每一个子串的频率表a, 而我们统计的子串是相邻或者说相似的，这样不用对于每一个子串都要重新计算其频率，
 * 而是可以重用上一个子串的频率，只需做一些小的更改就可以了。这样我们少了一层n的复杂度。
 * <p/>
 * 教训：在重复计算一个特征时，如果被计算的对象具有相邻或者相似的特性时，特征有时是可以避免重复计算而重用
 * 上一次得到的特征，这样的技巧是可以减少一次n因子的复杂度的，这个技巧应该熟记。
 */
public class SherlockandAnagrams2 {

    public static String stat_convert(int[] a) {
        StringBuffer buffer = new StringBuffer("");
        for (int cnt : a) {
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
            for (int i = 0; i < str.length(); i++) {//start index
                int[] a = new int[26];
                for (int j = i; j < str.length(); j++) {
                    a[str.charAt(j)-'a']++;
                    String str_key = stat_convert(a);
                    Integer cnt = anagrams_cnt.get(str_key);
                    anagrams_cnt.put(str_key, cnt == null ? 1 : (cnt.intValue() + 1));
                }
            }

            //accumulate the combinations, O(n^2)
            int pair_cnt = 0;
            for (Map.Entry<String, Integer> entry : anagrams_cnt.entrySet()) {
                int n = entry.getValue();
                pair_cnt += n * (n - 1) / 2;
            }

            //output
            System.out.println(pair_cnt);
        }
        sc.close();
    }
}
