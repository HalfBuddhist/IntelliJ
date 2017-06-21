package com.lqw.common;

import org.apache.commons.lang3.ArrayUtils;

/**
 * an supplement to appache common lang3
 * Created by Qingwei on 2017/5/16.
 */
public class ArraysUtils<T> {

    public static <T> void shuffle(T[] array) {
        int n = array.length;
        for (int i = 0; i < n; i++) {
            int sel = (int) (Math.random() * (n - i));
            ArrayUtils.swap(array, sel, n - i - 1);
        }
    }
}
