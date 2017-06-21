package com.poj;

import com.lqw.common.WebPath;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class BestCowLine_3617 {
    /**
     * Greedy, base algo
     * Select the smaller character between the head and the tail of the string.
     * if the two characters is the same, then choose the one whose adjacent character is smaller than
     * the other, that is compare the source string to the reverse copy, and select the smaller one.
     * proof: Recursive
     * the proof of the greedy algo is recursive and shown as follows.
     * the first character chosen by this algo is the leading char of the target minimum string because
     * the any operations violating this algo may generate a larget string.
     * The remaing operation form a same problem that select characters from the tail and the head to
     * generate a minimum string in lexigraphically, we then could recursive prove that
     * the next operations of this algo is correct.
     * <p/>
     * O(n^2)
     * AC
     *
     * @param argv
     * @throws FileNotFoundException
     */
    public static void main(String[] argv) throws FileNotFoundException {
        long begin = System.currentTimeMillis();
        Scanner sc = new Scanner(new File(WebPath.getAbsolutePathWithClass("/input.txt").getPath()));
        //        Scanner sc = new Scanner(new BufferedInputStream(System.in));
        //        Scanner sc = new Scanner(System.in);
        //        System.setOut(new PrintStream(new FileOutputStream(new File(WebPath.getAbsolutePathWithClass().getPath() + "output.txt"))));
        //presolve
        //input
        int n = sc.nextInt();
        int t = n;
        StringBuilder sb = new StringBuilder("");
        while (t-- > 0) {
            sb.append(sc.next());
        }
        String str = sb.toString();
        String rev = sb.reverse().toString();

        //resolve
        StringBuilder ans = new StringBuilder("");
        int idx = 0, idx2 = 0;
        while (idx + idx2 < n) {
            int cmp = str.substring(idx).compareTo(rev.substring(idx2));
            if (cmp < 0) {
                ans.append(str.charAt(idx++));
            } else {
                ans.append(rev.charAt(idx2++));
            }
        }

        //output
        int idx3 = 0;
        while (idx3 < n) {
            if (idx3 + 80 <= n)
                System.out.println(ans.substring(idx3, idx3 + 80));
            else
                System.out.println(ans.substring(idx3));
            idx3 += 80;
        }

        sc.close();
        System.err.println("use time: " + (System.currentTimeMillis() - begin) / 1000.0 + " seconds.");
    }
}
