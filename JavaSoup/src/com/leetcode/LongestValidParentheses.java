package com.leetcode;

import com.lqw.common.WebPath;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;

class CharIndex {
    char c;
    int idx;

    CharIndex(char p, int x) {
        c = p;
        idx = x;
    }
}


class LegalParenthese {
    int end_idx;
    int len;

    LegalParenthese(int end_idx, int len) {
        this.end_idx = end_idx;
        this.len = len;
    }

    void setEndLen(int end_idx, int len) {
        this.end_idx = end_idx;
        this.len = len;
    }

    int getStartIDX() {
        return end_idx - len + 1;
    }
}

public class LongestValidParentheses {

    /**
     * Imagination, two traversal
     * <p/>
     * 合法的构成主要有种方式，连接与匹配，用栈的方法可以直接直接解决匹配的问题，而连接不好解决，我是又用了一次栈来缓存已经发现的以供后面连接使用。
     * 标准解决是通过位置相减来做到的。而该方法是通过抓取合法串的特性，即左=右，即中间只能左>=右，这样通过计数就可以判断合法，一下解决了连接与匹配的问题。
     * 缺点是一些合法的中间串由于整个串是倾斜的（如左过多）导致没有发现，因为这时计数可能永远不等于0。
     * 而如果再多加一次从右遍历，同样的过程，对称的操作，就可以发现这些串，从而得到正确的结果。
     * <p/>
     * In this approach, we make use of two counters leftleft and rightright. First, we start traversing the string from
     * the start towards the end and for every  ‘(’ encountered, we increment the leftleft counter and for every ‘)’ encountered,
     * we increment the rightright counter. Whenever leftleft becomes equal to rightright, we calculate the length of the current valid
     * string and keep track of maximum length substring found so far. If end becomes greater than leftleft we reset start and
     * rightright to 0.
     * <p/>
     * Next, we start traversing the string from end to start and similar procedure is applied.
     * O(n); n, length of the string.
     * Accept
     *
     * @param s
     * @return
     */
    public int longestValidParentheses_2traversal(String s) {
        int left = 0, right = 0, maxlength = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                left++;
            } else {
                right++;
            }
            if (left == right) {
                maxlength = Math.max(maxlength, 2 * right);
            } else if (right >= left) {
                left = right = 0;
            }
        }
        left = right = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == '(') {
                left++;
            } else {
                right++;
            }
            if (left == right) {
                maxlength = Math.max(maxlength, 2 * left);
            } else if (left >= right) {
                left = right = 0;
            }
        }
        return maxlength;
    }

    /**
     * SPCS, stacks
     * use ont stack to store the index of the string other than the character, because the only character into the stack is '('
     * so no need to store the char but the index.
     * The length of the legal parenthese is differ, and calculated by substract the index before the start of the legal ones.
     * This enable the freedom to pop any found legal parentheseness withoud worried about the contatenation with afterly  found legals.
     * Because the substractoin works well with the start frontal index such as -1 to get the correct length of the contatenated legal string;
     * <p/>
     * offcial explanning as follows:
     * <p/>
     * Instead of finding every possible string and checking its validity, we can make use of stack while scanning the given string to check
     * if the string scanned so far is valid, and also the length of the longest valid string.
     * In order to do so, we start by pushing -1−1 onto the stack.
     * <p/>
     * For every ‘(’  encountered, we push its index onto the stack.
     * For every ‘)’ encountered, we pop the topmost element and subtract the current element's index from the top element of the stack,
     * which gives the length of the currently encountered valid string of parentheses.
     * If while popping the element, the stack becomes empty, we push the current element's index onto the stack.
     * In this way, we keep on calculating the lengths of the valid substrings, and return the length of the longest valid string at the end.
     * <p/>
     * <p/>
     * O(n); n, length of the string.
     * Accept
     *
     * @param s
     * @return
     */
    public int longestValidParentheses_stack_std(String s) {
        int maxans = 0;
        Stack<Integer> stack = new Stack<Integer>();
        stack.push(-1);
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else {
                stack.pop();
                if (stack.empty()) {
                    stack.push(i);
                } else {
                    maxans = Math.max(maxans, i - stack.peek());
                }
            }
        }
        return maxans;
    }

    /**
     * SPCS, stacks
     * use two stack, one to store the parenthese character, one to store the temporary found legal string.
     * merge the found legal string with the top legal string if they are adjacent.
     * algo ends while reach the end of the string.
     * remember record the max when finding the legal string both check into the second stack and merge with the adjacent string.
     * O(n); n, length of the string.
     * Accept
     *
     * @param s
     * @return
     */
    public int longestValidParentheses_linear(String s) {
        int n = s.length();
        int max_len = 0;
        //transferm
        CharIndex[] charIndexes = new CharIndex[n];
        for (int i = 0; i < n; i++) {
            charIndexes[i] = new CharIndex(s.charAt(i), i);
        }

        //find
        Stack<LegalParenthese> stack_parenthese = new Stack<LegalParenthese>();
        Stack<CharIndex> stack_char = new Stack<CharIndex>();
        int t_len = 0; //len of the last_parenthese
        LegalParenthese last_parenthese = null;
        for (int i = 0; i < n; i++) {
            if (charIndexes[i].c == '(') {
                t_len = 0; //come to find the new parenthese.
                //check if merge the last
                if (last_parenthese != null) {
                    stack_parenthese.push(last_parenthese);
                    last_parenthese = null;
                }
                stack_char.push(charIndexes[i]);
            } else {//')'
                if (stack_char.empty()) {
                    //no probability to connect to others.
                    stack_parenthese.clear();
                    t_len = 0;
                } else {
                    stack_char.pop();
                    t_len += 2;
                    last_parenthese = new LegalParenthese(i, t_len);

                    if (!stack_parenthese.empty()) {
                        LegalParenthese top = stack_parenthese.peek();
                        if (top.end_idx + 1 == last_parenthese.getStartIDX()) {
                            last_parenthese.len += top.len;
                            t_len += top.len;
                            //should concatinate
                            stack_parenthese.pop();
                        }
                    }
                    max_len = Math.max(last_parenthese.len, max_len);
                }
            }
        }

        return max_len;
    }


    /**
     * bruet force
     * iterate every start of the string, and try to find the longest legal patentheses.
     * O(n^2)
     * TLE
     *
     * @param s
     * @return
     */
    public int longestValidParentheses_bf(String s) {
        int n = s.length();
        int max_len = 0;
        Stack<Character> stack = new Stack<Character>();
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == ')')
                continue;
            else {
                stack.clear();
                int t_len = 0;
                for (int j = i; j < n; j++) {
                    if (s.charAt(j) == '(')
                        stack.push(s.charAt(j));
                    else {//')'
                        if (stack.empty()) {
                            break;
                        } else {
                            stack.pop();
                            t_len += 2;
                            if (stack.empty())
                                max_len = Math.max(max_len, t_len);
                        }
                    }
                }

            }
        }
        return max_len;
    }

    /**
     * DP, iterative, not recursive.
     * f(i, j) indicates if a substring from i to j is a legal parentheses
     * f(i-1, j-1)  = true if ......
     * f(i-2, j) = true if ....
     * f(i, j+2) = true if .....
     * <p/>
     * O(n^2), n length of the string.
     * <p/>
     * Wrong Answer
     * proved wrong.
     *
     * @param s
     * @return
     */
    public int longestValidParentheses(String s) {
        int n = s.length();
        int st = -1, e = -1;
        int max_len = 0;
        boolean f[][] = new boolean[n][n]; //both inculded
        for (int l = 2; l <= n; l += 2) {
            for (int i = 0; i <= n - l; i++) {
                int j = i + l - 1;
                //decide f(i, i+l-1) is legal or not
                if (((l == 2 || (i + 1 < j - 1 && f[i + 1][j - 1])) && s.charAt(i) == '(' && s.charAt(j) == ')') ||
                        (i + 2 < j && f[i + 2][j] && s.charAt(i) == '(' && s.charAt(i + 1) == ')') ||
                        (i < j - 2 && f[i][j - 2] && s.charAt(j - 1) == '(' && s.charAt(j) == ')')) {
                    f[i][j] = true;
                    if (max_len < l) {
                        max_len = l;
                        st = i;
                        e = j;
                    }
//                    max_len = Math.max(max_len, l);
                } else
                    f[i][j] = false;
            }
        }
        System.out.println(s + ":\t" + (st + 1) + "\t" + (e + 1));
        return max_len;
    }


    public static void main(String[] argv) throws FileNotFoundException {
        long begin = System.currentTimeMillis();
        Scanner sc = new Scanner(new File(WebPath.getAbsolutePathWithClass("/input.txt").getPath()));
        //        Scanner sc = new Scanner(new BufferedInputStream(System.in));
        //        Scanner sc = new Scanner(System.in);
        //        System.setOut(new PrintStream(new FileOutputStream(new File(WebPath.getAbsolutePathWithClass().getPath() + "output.txt"))));
        //presolve
        //input
        int t = sc.nextInt();
        while (t-- > 0) {
            String str = sc.next();
            System.out.println(new LongestValidParentheses().longestValidParentheses_linear(str));
        }

        //resolve

        //output
        sc.close();
        System.err.println("use time: " + (System.currentTimeMillis() - begin) / 1000.0 + " seconds.");
    }
}
