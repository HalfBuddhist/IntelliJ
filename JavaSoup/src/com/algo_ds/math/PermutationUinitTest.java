package com.algo_ds.math;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class PermutationUinitTest {

    /**
     * test the traversal_permutation_recursive mehtod
     */
    @Test
    public void test_traversal_permutation_recursive() {
        int[] a = new int[5];
        Permutation.traversal_permutation_recursive(a, i -> {
            System.out.println(Arrays.toString(i));
        });
    }

    @Test
    public void test_nextKthLexicalPermutation() {
        int[] a = new int[]{1, 2, 3, 4};
        Permutation.nextKthLexicalPermutation(a, 19);
        for (int b : a) {
            System.out.print(b);
        }
    }

    @Test
    public void test_nextKthOrdinalPermutation() {
        int[] a = new int[]{3, 2, 1, 4};
        Permutation.nextKthOrdinalPermutation(a, 13);
        for (int b : a) {
            System.out.print(b);
        }
    }

    /**
     * test to generatet the whole permutations use the ordinal mehthod.
     */
    @Test
    public void test_nextKthOrdinalPermutation2() {
        int[] a = new int[]{1, 2, 3, 4, 5};
        OrdinalIntermediary intermediary;
        int cnt = 0;
        do {
            cnt++;
            for (int b : a) {
                System.out.print(b);
            }
            System.out.println();
            intermediary = new OrdinalIntermediary(a);
            Permutation.nextKthOrdinalPermutation(a, intermediary, 1);
        } while (!intermediary.isZero());
        System.out.println(cnt);


    }


    /**
     * test to generate whole permutations use adjacent exchange method.
     */
    @Test
    public void test_nextPermutation_AdjExchange() {
        int[] a = new int[]{1, 2, 3, 4};
        int cnt = 0;
        Permutation.isFirstCall = true;
        while (Permutation.nextPermutation_AdjExchange(a)) {
            System.out.println(Arrays.toString(a));
            cnt++;
        }
        System.out.println(cnt);
    }

    @Test
    public void test_nextPermutation_Lexical() {
        int[] a = new int[]{1, 2, 3, 4, 5};
        int cnt = 0;
        Permutation.isFirstCall = true;
        while (Permutation.nextPermutation_Lexical(a)) {
            System.out.println(Arrays.toString(a));
            cnt++;
        }
        System.out.println(cnt);
    }
}
