package com.algo_ds.tree;

import junit.framework.Assert;
import org.junit.Test;

/**
 * Created by Qingwei on 2017/5/7.
 */
public class IntervalTreeUT {

    /**
     * test add, remove, and length calc function.
     */
    @Test
    public void test_cnt_length() {
        //coordinates.
        int x[] = {1, 2, 5};
        int y[] = {3, 4, 6};

        IntervalTree tree = new IntervalTree(new Interval(1, 6));

        // 插入
        for (int i = 0; i < 3; ++i) {
            tree.add(new Interval(x[i], y[i]));
        }
        int len = tree.calcLength();
        System.out.println(len);
        Assert.assertEquals("total length:", 4, len);


        // 删除
        tree.remove(new Interval(2, 3));

        // 覆盖长度
        len = tree.calcLength();
        System.out.println(len);
        Assert.assertEquals("total length:", 3, len);

    }
}
