package com.algo_ds.math;


import org.junit.Test;

public class IntermediaryUT {

    @Test
    public void test_ordinal_intermediary() {
        int[] test = new int[]{4, 3, 1, 2};
        OrdinalIntermediary inter = new OrdinalIntermediary(test);
        for (int i = 0; i <= test.length - 2; i++) {
            System.out.print(inter.intermediary[i]);
        }
        System.out.println();
        inter.restorePermu(test);
        for (int i = 0; i < test.length; i++) {
            System.out.print(test[i]);
        }
        System.out.println();
    }

    @Test
    public void test_lexical_intermediary() {
        int[] test = new int[]{4, 2, 3, 1};
        LexicalIntermediary inter = new LexicalIntermediary(test);
        for (int i = 0; i <= test.length - 2; i++) {
            System.out.print(inter.intermediary[i]);
        }
        System.out.println();
        inter.restorePermu(test);
        for (int i = 0; i < test.length; i++) {
            System.out.print(test[i]);
        }
        System.out.println();
    }
}
