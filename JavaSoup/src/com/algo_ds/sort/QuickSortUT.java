package com.algo_ds.sort;

import org.junit.Test;

import java.util.Arrays;

/**
 * Created by Qingwei on 2017/6/8.
 */
public class QuickSortUT {

    @Test
    public void test_sort() {
        int[] a = {0, 2, 5, 6, 3, 1, -2, -3, 4, -1};
        QuickSort.sort(a);
        Arrays.stream(a).forEach(e -> {
            System.out.println(e);
        });
    }
}
