package com.leetcode;

import com.lqw.common.WebPath;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class String2Integer_atoi {

    public int convert2Int(StringBuilder sb) {
        String min_str = Integer.toString(Integer.MIN_VALUE);
        String max_str = Integer.toString(Integer.MAX_VALUE);

        //output
        char sign_letter = sb.charAt(0);
        StringBuilder sb_n;
        int sign = sb.charAt(0) == '-' ? -1 : 1;//get sign
        if (sign_letter == '-' || sign_letter == '+') {
            sb_n = new StringBuilder(sb.substring(1));
        } else {
            sb_n = new StringBuilder(sb.toString());
        }

        //complement zeros
        int zeros = 10 - sb_n.length();
        if (sb_n.length() > 10) return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
        while (zeros-- > 0) {
            sb_n.insert(0, '0');
        }

        if (sign == -1) {
            sb_n.insert(0, '-');
            int cmp = sb_n.toString().compareTo(min_str);
            if (cmp > 0) return Integer.MIN_VALUE;
            else {
                return Integer.parseInt(sb_n.toString());
            }
        } else {
            int cmp = sb_n.toString().compareTo(max_str);
            if (cmp > 0) return Integer.MAX_VALUE;
            else {
                return Integer.parseInt(sb_n.toString());
            }
        }
    }

    //O(n)
    public int myAtoi(String str) {
        str = str + "dd";
        int state = 1;
        StringBuilder sb = new StringBuilder("");
        for (char c : str.toCharArray()) {
            switch (state) {
                case 1: {
                    if (c == ' ') {
                        continue;
                    } else if (c == '+' || c == '-') {
                        sb.append(c);
                        state = 2;
                    } else if (c >= '0' && c <= '9') {
                        sb.append(c);
                        state = 4;
                    }else{
                        return 0;
                    }
                    break;
                }
                case 2: {
                    if (c >= '0' && c <= '9') {
                        sb.append(c);
                        state = 4;
                    } else {
                        state = 3;
                    }
                    break;
                }
                case 3: {
                    return 0;
                }
                case 4: {
                    if (c >= '0' && c <= '9') {
                        sb.append(c);
                    } else {
                        state = 5;
                    }
                    break;
                }
                case 5: {
                    //output
                    return convert2Int(sb);
                }
                default: {
                    break;
                }
            }
        }
        return 0;
    }

    public int myAtoi2(String str) {
        String min_str = Integer.toString(Integer.MIN_VALUE);
        String max_str = Integer.toString(Integer.MAX_VALUE);

        StringBuilder sb = new StringBuilder("");
        StringBuilder sb_n = new StringBuilder("");
        //filter the whitespace
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c == '+' || c == '-' || (c >= '0' && c <= '9')) {
                sb.append(c);
            }
            if (c >= '0' && c <= '9') {
                sb_n.append(c);
            }
        }

        //null test
        if (sb_n.length() == 0 || (sb.length() >= 2 && !(sb.charAt(1) >= '0' && sb.charAt(1) <= '9')))
            return 0;
//        if(sb.charAt(0) == "+")
        int sign = sb.charAt(0) == '-' ? -1 : 1;//get sign
        //complement zeros
        int zeros = 10 - sb_n.length();
        if (sb_n.length() > 10) return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
        while (zeros-- > 0) {
            sb_n.insert(0, '0');
        }

        if (sign == -1) {
            sb_n.insert(0, '-');
            int cmp = sb_n.toString().compareTo(min_str);
            if (cmp > 0) return Integer.MIN_VALUE;
            else {
                return Integer.parseInt(sb_n.toString());
            }
        } else {
            int cmp = sb_n.toString().compareTo(max_str);
            if (cmp > 0) return Integer.MAX_VALUE;
            else {
                return Integer.parseInt(sb_n.toString());
            }
        }
    }

    public static void main(String[] argv) throws FileNotFoundException {
        long begin = System.currentTimeMillis();
        Scanner sc = new Scanner(new File(WebPath.getAbsolutePathWithClass("/input.txt").getPath()));
        //        Scanner sc = new Scanner(new BufferedInputStream(System.in));
        //        Scanner sc = new Scanner(System.in);
        //presolve
        //input
        System.out.println(new String2Integer_atoi().myAtoi(" b11228552307"));

        //resolve

        //output
        sc.close();
        System.err.println("use time: " + (System.currentTimeMillis() - begin) / 1000.0 + " seconds.");
    }
}
