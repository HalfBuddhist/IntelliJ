package com.algo_ds.math;

/**
 * 序数中介数
 * 首位为n右边比它小的数，其次为n-1右边比它的小的数
 */
public class OrdinalIntermediary extends Intermediary {

    public OrdinalIntermediary(int[] nums) {
        intermediary = calcIntermediary(nums);
    }

    /**
     * O(n^2)
     *
     * @param nums
     * @return
     */
    @Override
    public int[] calcIntermediary(int[] nums) {
        int n = nums.length;
        int[] intermediary = new int[n - 1];
        for (int i = 0; i < n - 1; i++) {
            int cnt = 0;
            for (int j = n - 1; j >= 0; j--) {
                if (nums[j] == (n - i)) {
                    break;
                } else if (nums[j] < (n - i)) {
                    cnt++;
                }
            }
            intermediary[i] = cnt;
        }

        return intermediary;
    }

    /**
     * O(n^2)
     * reference the combinatorics
     *
     * @param nums
     */
    @Override
    public void restorePermu(int[] nums) {
        int n = intermediary.length + 1;
        for (int i = 0; i < n; i++) {
            nums[i] = -1;
        }
        for (int i = 0; i < n - 1; i++) {
            int cnt = 0;
            for (int j = n - 1; j >= 0; j--) {
                if (nums[j] != -1) {
                    continue;
                } else if (cnt == intermediary[i]) {
                    nums[j] = n - i;
                    break;
                } else if (nums[j] == -1) {
                    cnt++;
                }
            }
        }

        //set the last ele, 1
        for (int i = 0; i < n; i++) {
            if (nums[i] == -1)
                nums[i] = 1;
        }
    }
}
