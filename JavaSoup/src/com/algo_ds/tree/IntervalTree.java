package com.algo_ds.tree;

import java.util.TreeSet;
import java.util.function.Consumer;

/**
 * an implementations of interval tree.
 * on the leaf node, end - start = 1
 * for the interval including only one element, should be treated seperately,
 * not resolve them on the interval tree.
 * <p>
 * this tree is built lazily and dynamically to reduce the programme complexity as much as possible.
 * the split point is always the middle to gurrantee the balance over the total range.
 * <p>
 * Created by Qingwei on 2017/5/5.
 */
public class IntervalTree extends BinaryTree {

    public IntervalTree(Interval interval) {
        this.mRoot = new IntervalTreeNode(interval, null, null, null, 0);
        size++;
    }

    private void addChildren(BinaryTreeNode node1, int default_value) {
        IntervalTreeNode node = (IntervalTreeNode) node1;
        if (!node.hasRightChild()) {
            node.right = new IntervalTreeNode(new Interval((Integer) node.key, node.interval.end),
                    null, null, node, default_value);
            size++;
        }
        if (!node.hasLeftChild()) {
            node.left = new IntervalTreeNode(new Interval(node.interval.start, (Integer) node.key),
                    null, null, node, default_value);
            size++;
        }
    }

    /**
     * add interval
     * O(log m)
     * m is the length of the maximum interval possible
     *
     * @param node1
     * @param interval should not be single elements.
     * @return
     */
    protected IntervalTreeNode add(BinaryTreeNode node1, Interval interval) {
        IntervalTreeNode node = (IntervalTreeNode) node1;
        if (node.value > 0) {//has covered
            node.value++;
            return node;
        }
        // no cover
        if (node.interval.equals(interval)) {
            node.value++;
        } else {
            //check children
            this.addChildren(node, 0);

            //insert
            if (interval.start >= (Integer) node.key) { // insert end
                add(node.right, interval);

            } else if (interval.end <= (Integer) node.key) { // insert start
                add(node.left, interval);

            } else { // split and insert
                add(node.right, new Interval((Integer) node.key, interval.end));
                add(node.left, new Interval(interval.start, (Integer) node.key));
            }
            //check merge, for future traversal
            if (((IntervalTreeNode) node.left).value > 0 && ((IntervalTreeNode) node.right).value > 0) {
                node.value = ((IntervalTreeNode) node.right).value + ((IntervalTreeNode) node.left).value;
            }
        }
        return node;
    }

    public void add(Interval interval) {
        mRoot = add((IntervalTreeNode) mRoot, interval);
    }

    protected void remove(BinaryTreeNode node1, Interval interval) {
        IntervalTreeNode node = (IntervalTreeNode) node1;
        if (node == null)//the interval to delete has no match interval node.
            return;

        if (node.value > 0) {
            if (!node.interval.equals(interval)) {
                this.addChildren(node, node.value);
            }
            //no need to update the value info, becaseu the value will always be 0.
            node.value = 0;//because no single element.
        }

        //delete recursively.
        if (interval.start >= (Integer) node.key) { // insert end
            remove(node.right, interval);
        } else if (interval.end <= (Integer) node.key) { // insert start
            remove(node.left, interval);
        } else { // split and insert
            remove(node.right, new Interval((Integer) node.key, interval.end));
            remove(node.left, new Interval(interval.start, (Integer) node.key));
        }
    }

    /**
     * delete interval
     * if the interval is not covered, do nothing.
     *
     * @param interval should not be single elements.
     */
    public void remove(Interval interval) {
        this.remove((IntervalTreeNode) mRoot, interval);
    }

    /**
     * get the total length of the current interval.
     * this could be also complished by O(n) linear scan algo.
     * O(n)
     *
     * @return
     */
    public int calcLength() {
        int[] total_length = new int[1];
        this.preOrder(node -> {
            IntervalTreeNode n = (IntervalTreeNode) node;
            total_length[0] += n.interval.length();
        });
        return total_length[0];
    }

    /**
     * 前序遍历， 先根遍历
     * override to return the meaning full nodes only
     * and omit the nodes whose parent has covered.
     * O(n)
     */
    protected void preOrder(BinaryTreeNode node1, Consumer c) {
        IntervalTreeNode node = (IntervalTreeNode) node1;
        if (node != null) {
            if (node.value > 0) {
                c.accept(node);
            } else {
                preOrder(node.left, c);
                preOrder(node.right, c);
            }
        }
    }
}
