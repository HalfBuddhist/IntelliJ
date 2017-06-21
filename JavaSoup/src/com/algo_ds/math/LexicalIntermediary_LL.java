package com.algo_ds.math;

import java.util.Iterator;
import java.util.LinkedList;


/**
 * the intermediary of the lexical implemented with a linked list
 */
public class LexicalIntermediary_LL extends Intermediary {

    public LexicalIntermediary_LL(int[] nums) {
        intermediary = calcIntermediary(nums);
    }

    /**
     * get the intermediary of lexical method.
     * O(n^2)
     *
     * @param nums
     * @return
     */
    @Override
    public int[] calcIntermediary(int[] nums) {
        int n = nums.length;
        LinkedList<Integer> linkedList = new LinkedList<Integer>();
        for (int i = 0; i < n; i++) {
            linkedList.add(i + 1);
        }
        int[] intermediary = new int[n - 1];
        for (int i = 0; i < n - 1; i++) {
            Iterator<Integer> iter = linkedList.iterator();
            int t = 0;
            while (iter.hasNext()) {
                if (iter.next() == nums[i]) {
                    break;
                }
                t++;
            }
            intermediary[i] = t;
            linkedList.remove(t);
        }
        return intermediary;
    }

    /**
     * restore permutation from the intermediary.
     * O(n^2)
     */
    @Override
    public void restorePermu(int[] nums) {
        int n = intermediary.length + 1;
        LinkedList<Integer> linkedList = new LinkedList<Integer>();
        for (int i = 1; i <= n; i++) {
            linkedList.add(i);
        }

        //resolve
        for (int i = 0; i <= n - 2; i++) {
            //get the cur'th number
            int t = linkedList.remove(intermediary[i]);
            nums[i] = t;
        }
        nums[n - 1] = linkedList.remove(0);
    }
}