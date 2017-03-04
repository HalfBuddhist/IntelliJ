package com.hackerrank.code30days;

import com.lqw.common.WebPath;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by Qingwei on 16/6/24.
 */
public class QueuesAndStacks {

    private Stack stack;
    private Queue queue;

    public QueuesAndStacks() {
        this.stack = new Stack<Character>();
        this.queue = new LinkedList<Character>();
    }

    public void pushCharacter(Character c){
        this.stack.push(c);
    }

    public void enqueueCharacter(Character c){
        this.queue.add(c);
    }

    public char popCharacter(){
        return ((Character)this.stack.pop()).charValue();
    }

    public char dequeueCharacter(){
        return ((Character)queue.remove()).charValue();
    }

    public static void main(String[] argh) throws FileNotFoundException {
        Scanner scan = new Scanner(new File(WebPath.getAbsolutePathWithClass("/com/hackerrank/code30days/input.txt").getPath()));
//        Scanner in = new Scanner(System.in);
        String input = scan.nextLine();
        scan.close();

        // Convert input String to an array of characters:
        char[] s = input.toCharArray();

        // Create a Solution object:
        QueuesAndStacks p = new QueuesAndStacks();

        // Enqueue/Push all chars to their respective data structures:
        for (char c : s) {
            p.pushCharacter(c);
            p.enqueueCharacter(c);
        }

        // Pop/Dequeue the chars at the head of both data structures and compare them:
        boolean isPalindrome = true;
        for (int i = 0; i < s.length / 2; i++) {
            if (p.popCharacter() != p.dequeueCharacter()) {
                isPalindrome = false;
                break;
            }
        }

        //Finally, print whether string s is palindrome or not.
        System.out.println("The word, " + input + ", is "
                + ((!isPalindrome) ? "not a palindrome." : "a palindrome."));
    }
}
