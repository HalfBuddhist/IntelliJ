package com.algo_ds.tree;

/**
 * interval class
 * Created by Qingwei on 2017/5/5.
 */
public class Interval {
    public int start;
    public int end;

    //constructers.
    public Interval() {
        start = 0;
        end = 0;
    }

    public Interval(int left, int right) {
        this.start = left;
        this.end = right;
    }

    /**
     * get the length of the interval.
     *
     * @return
     */
    public int length() {
        return end - start;
    }

    /**
     * middle point of the interval
     */
    public int mid() {
        return (start + end) / 2;
    }

    /**
     * Does this interval has only a single point?
     *
     * @return
     */
    public boolean isSinglePoint() {
        return start == end;
    }

    /**
     * judge the relation between the interval and the nubmer i.
     *
     * @param i
     * @return
     */
    public boolean include(int i) {
        return i >= start && i <= end;
    }

    /**
     * where is the number locate
     *
     * @return -1 left; 0 middle, 1 rihgt;
     */
    public int where(int i) {
        if (include(i)) return 0;
        else return i < start ? -1 : 1;
    }

    @Override
    public boolean equals(Object obj) {
        Interval t = (Interval) obj;
        return start == t.start && end == t.end;
    }
}
