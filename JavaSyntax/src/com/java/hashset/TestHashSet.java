package com.java.hashset;

import net.mindview.util.Print;

import java.util.HashSet;
import java.util.TreeSet;

/**
 * hashset是按照元素值的hash值来辨别唯一性的， treeset是按照定义的大小比较函数来区别唯一性的。
 * Created by Qingwei on 16/8/25.
 */

class Index2D implements Comparable {
    int i;
    int j;

    Index2D(int i, int j) {
        this.i = i;
        this.j = j;
    }

    @Override
    public boolean equals(Object obj) {
        Index2D p = (Index2D) obj;
        return this.i == p.i && this.j == p.j;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public int compareTo(Object o) {
        Index2D p = (Index2D) o;
        return this.i - p.i != 0 ? (this.i - p.i) : (this.j - p.j);
    }
}

public class TestHashSet {
    public static void main(String[] args) throws Exception {

        Index2D i1 = new Index2D(1, 2);
        Index2D i2 = new Index2D(1, 2);

        HashSet<Index2D> hashSet = new HashSet<Index2D>();
        Print.print(hashSet.size());
        hashSet.add(i1);
        hashSet.add(i2);
        Print.print(hashSet.size());

        TreeSet<Index2D> treeSet = new TreeSet<Index2D>();
        Print.print(treeSet.size());
        treeSet.add(i1);
        treeSet.add(i2);
        Print.print(treeSet.size());
    }
}
