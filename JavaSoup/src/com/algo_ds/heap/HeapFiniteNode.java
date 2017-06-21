package com.algo_ds.heap;


public class HeapFiniteNode<T extends Comparable<T>> {


    // because all the finited elements could be placed into an array, //
    // idx indicate the origine location.
    int idx;
    public T value;

    public HeapFiniteNode(int idx, T value) {
        this.idx = idx;
        this.value = value;
    }

    public int getIdx() {
        return idx;
    }
}
