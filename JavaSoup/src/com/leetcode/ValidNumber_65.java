package com.leetcode;

import com.lqw.common.WebPath;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ValidNumber_65 {

    protected static int[][] dfa = {
            {0, 0, 0, 0, 0, 0, 0},
            {4, 0, 0, 3, 1, 2, 2},
            {4, 0, 0, 3, 0, 0, 0},
            {5, 6, 0, 3, 9, 0, 0},
            {0, 0, 0, 5, 0, 0, 0},
            {0, 6, 0, 5, 9, 0, 0},
            {0, 0, 0, 8, 0, 7, 7},
            {0, 0, 0, 8, 0, 0, 0},
            {0, 0, 0, 8, 9, 0, 0},
            {0, 0, 0, 0, 9, 0, 0}};

    /**
     * Proffesional algo, DFA
     * The problem is to parse a number, so the DFA method shoud be adopted obviously.
     * As the dfa method a dfa table would be generated as above with some states indicates a legal number.
     * Starting with state 1, and switch between states as the character encountering.
     * Check the legality of the final state would give the answer.
     * <p>
     * O(n*l)
     * l is the length of the alpha bet, n is the length of the string to parse.
     * this is so worese because of the compare the current character to the convert to the index of the
     * dfa table. if the table is a hashmap in which key is the character and value is the next state,
     * the time complexity would be O(n).
     * Howerver because the alphabet is small, so no need to implement this with a hash table.
     * But int case with a large alphabet, a hash table should be used.
     * AC
     *
     * @param s
     * @return
     */
    public boolean isNumber(String s) {
        int state = 1;
        for (char c : s.toCharArray()) {
            if (state == 0) break;
            int col = 0;
            if (c == '.') col = 0;
            else if (c == 'e') col = 1;
            else if (c <= '9' && c >= '0') col = 3;
            else if (c == ' ') col = 4;
            else if (c == '+') col = 5;
            else if (c == '-') col = 6;
            else col = 2;
            state = dfa[state][col];
        }
        if (state == 3 || state == 8 || state == 5 || state == 9) {
            return true;
        } else return false;
    }

    public static void main(String[] argv) throws FileNotFoundException {
        long begin = System.currentTimeMillis();
        Scanner sc = new Scanner(new File(WebPath.getAbsolutePathWithClass("/input.txt").getPath()));
        //        Scanner sc = new Scanner(new BufferedInputStream(System.in));
        //        Scanner sc = new Scanner(System.in);
        //        System.setOut(new PrintStream(new FileOutputStream(new File(WebPath.getAbsolutePathWithClass().getPath() + "output.txt"))));
        //presolve
        //input
        System.out.println(new ValidNumber_65().isNumber("45.e3"));

        //resolve

        //output

        sc.close();
        System.err.println("use time: " + (System.currentTimeMillis() - begin) / 1000.0 + " seconds.");
    }
}
