package com.algo_ds.math;


public abstract class Intermediary implements IntermediaryItf {
    protected int[] intermediary;

    /**
     * intermediary add operation
     * O(n)
     *
     * @param k
     */
    @Override
    public void add(int k) {
        int n = intermediary.length + 1;
        int jin = 0, index = 1;
        while ((n - 1 - index >= 0) && (jin > 0 || k > 0)) {
            int cur = k % (index + 1);
            int he = cur + jin + intermediary[n - 1 - index];
            intermediary[n - 1 - index] = he % (index + 1);
            jin = he / (1 + index);
            k /= (index + 1);
            index++;
        }
    }

    @Override
    public boolean isZero() {
        int n = intermediary.length + 1;
        for (int i = 0; i < n - 1; i++) {
            if (intermediary[i] != 0) {
                return false;
            }
        }
        return true;
    }
}
