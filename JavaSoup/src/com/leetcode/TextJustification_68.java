package com.leetcode;

import com.lqw.common.WebPath;
import javafx.beans.binding.StringBinding;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class TextJustification_68 {


    /**
     * brute force, defination, simulation.
     * <p>
     * operate as the following cycle.
     * 1, find hte sufficient words to form a line, at least one space between words.
     * 2, calc the extra spaces average and remainder to the first left intervals.
     * 3, form a line use the data from the above two steps.
     * 4, fill to the maxwidth at the end, for the last line situation, and only one word situation.
     * 5, add the new line to the final result.
     * <p>
     * O(n)
     * AC
     *
     * @param words
     * @param maxWidth
     * @return
     */
    public List<String> fullJustify(String[] words, int maxWidth) {
        ArrayList<String> ans = new ArrayList<>();
        int n = words.length;
        int index = 0, start_index; //next to resolve
        int len = 0;
        boolean is_first_word = true;

        while (index < n) {
            //initial before find a line
            is_first_word = true;
            start_index = index;
            len = 0;

            //find the sufficient words.
            while (index < n) {
                int add = is_first_word ? words[index].length() : words[index].length() + 1;
                is_first_word = false;
                if (len + add > maxWidth) {
                    break;
                } else {
                    index++;
                    len += add;
                }
            }

            //calc the spaces.
            int loc = index - start_index - 1;
            int spaces = maxWidth - len;
            int average, lefts;
            if (loc == 0) {
                average = 0;
                lefts = 0;
            } else {
                average = spaces / loc;
                lefts = spaces % loc;
            }

            //form a line
            StringBuilder sb = new StringBuilder("");
            for (int i = start_index; i < index; i++) {
                if (i != start_index) {
                    sb.append(" ");//natural
                    //for extra spaces
                    if (index != n) {//if last line
                        //average
                        for (int j = 0; j < average; j++) {
                            sb.append(" ");
                        }
                        //remainder
                        if (i - start_index <= lefts) {
                            sb.append(" ");
                        }
                    }
                }
                sb.append(words[i]);
            }

            //complement to length maxwidth
            int curlen = maxWidth - sb.length();
            for (int i = 0; i < curlen; i++) {
                sb.append(" ");
            }

            //add to the result.
            ans.add(sb.toString());
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
        int n = sc.nextInt();
        String[] a = new String[n];
        Arrays.setAll(a, i -> {
            return sc.next();
        });
        int l = sc.nextInt();
        List<String> and = new TextJustification_68().fullJustify(a, l);
        and.forEach(e -> {
            System.out.println(e);
        });


        //resolve

        //output

        sc.close();
        System.err.println("use time: " + (System.currentTimeMillis() - begin) / 1000.0 + " seconds.");
    }
}
