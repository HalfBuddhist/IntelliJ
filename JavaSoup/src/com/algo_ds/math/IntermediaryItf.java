package com.algo_ds.math;


public interface IntermediaryItf {
    /**
     * calc the intermediary
     *
     * @param nums
     * @return
     */
    public int[] calcIntermediary(int[] nums);

    /**
     * intermediary add operation
     *
     * @param k
     */
    public void add(int k);


    /**
     * restore permutation from an intermediary.
     *
     * @param nums
     */
    public void restorePermu(int[] nums);

    /**
     * is the intermediary zero, that is the interm of the start of the permutations.
     *
     * @return
     */
    public boolean isZero();
}
