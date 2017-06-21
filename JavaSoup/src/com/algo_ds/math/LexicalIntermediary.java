package com.algo_ds.math;

import com.algo_ds.tree.no_inherit.CntAVLTree;

/**
 * 字典序中介数
 * 首位为所有剩余未使用元素中他的序号（从0开始），依次类推
 */
public class LexicalIntermediary extends Intermediary {

    public LexicalIntermediary(int[] nums) {
        intermediary = calcIntermediary(nums);
    }

    /**
     * get the intermediary of lexical method.
     * when calc the intermediary, you need to dynamically count the order of the spec number,
     * and the numbers set changes.
     * here we solve this by a modified avl tree, called cntavltree
     * a segment tree also could be adopted instead of the avl tree variant, but the time complexity
     * remains the same. In the segment tree, the number treated as the [number, maxlen] segment,
     * the order is the number of the segs above the current number.
     * <p>
     * O(nlogn)
     *
     * @param nums
     * @return
     */
    @Override
    public int[] calcIntermediary(int[] nums) {
        int n = nums.length;
        CntAVLTree<Integer> tree = new CntAVLTree<Integer>();
        for (int i = 0; i < n; i++) {
            tree.insert(i + 1);
        }
        int[] intermediary = new int[n - 1];
        for (int i = 0; i < n - 1; i++) {
            tree.lessInverseCnt = 0;
            tree.remove(nums[i]);
            intermediary[i] = tree.lessInverseCnt;
        }
        return intermediary;
    }


    /**
     * restore permutation from the intermediary.
     * O(nlogn)
     */
    @Override
    public void restorePermu(int[] nums) {
        int n = intermediary.length + 1;
        CntAVLTree<Integer> tree = new CntAVLTree<Integer>();
        for (int i = 1; i <= n; i++) {
            tree.insert(i);
        }

        //resolve
        for (int i = 0; i <= n - 2; i++) {
            //get the cur'th number
            int t = tree.k_remove(intermediary[i] + 1);
            nums[i] = t;
        }
        nums[n - 1] = tree.k_remove(1);
    }
}