package com.algo_ds.tree.reference;


/**
 * This Java program is to Implement Segment tree.
 * a segment tree is a tree data structure for storing intervals, or segments.
 * It allows querying which of the stored segments contain a given point.
 * It is, in principle, a static structure;
 * that is, its content cannot be modified once the structure is built.
 * A similar data structure is the interval tree.
 * A segment tree for a set I of n intervals uses O(n log n) storage and can be built in O(n log n) time.
 * Segment trees support searching for all the intervals that contain a query point in O(log n + k),
 * k being the number of retrieved intervals or segments.
 * <p>
 * 是闭区间，并非左闭右开区间
 */
public class SegmentTree2 {
    private int[] tree;
    private int maxsize;
    private int height;

    private final int STARTINDEX = 0;
    private final int ENDINDEX;
    private final int ROOT = 0;

    public SegmentTree2(int size) {
        height = (int) (Math.ceil(Math.log(size) / Math.log(2)));
        maxsize = 2 * (int) Math.pow(2, height) - 1;
        tree = new int[maxsize];
        ENDINDEX = size - 1;
    }

    private int leftchild(int pos) {
        return 2 * pos + 1;
    }

    private int rightchild(int pos) {
        return 2 * pos + 2;
    }

    private int mid(int start, int end) {
        return (start + (end - start) / 2);
    }

    private int getSumUtil(int startIndex, int endIndex, int queryStart, int queryEnd, int current) {
        if (queryStart <= startIndex && queryEnd >= endIndex) {
            return tree[current];
        }
        if (endIndex < queryStart || startIndex > queryEnd) {
            return 0;
        }
        int mid = mid(startIndex, endIndex);
        return getSumUtil(startIndex, mid, queryStart, queryEnd, leftchild(current))
                + getSumUtil(mid + 1, endIndex, queryStart, queryEnd, rightchild(current));
    }

    /**
     * get sum of the interval
     *
     * @param queryStart
     * @param queryEnd
     * @return
     */
    public int getSum(int queryStart, int queryEnd) {
        if (queryStart < 0 || queryEnd > tree.length) {
            return -1;
        }
        return getSumUtil(STARTINDEX, ENDINDEX, queryStart, queryEnd, ROOT);
    }

    /**
     * @param elements
     * @param startIndex
     * @param endIndex
     * @param parent     location of sum
     * @return sum from the startIndex to endIndex
     */
    private int constructSegmentTreeUtil(int[] elements, int startIndex, int endIndex, int parent) {
        if (startIndex == endIndex) {
            tree[parent] = elements[startIndex];
            return tree[parent];
        }
        int mid = mid(startIndex, endIndex);
        tree[parent] = constructSegmentTreeUtil(elements, startIndex, mid, leftchild(parent))
                + constructSegmentTreeUtil(elements, mid + 1, endIndex, rightchild(parent));
        return tree[parent];
    }

    /**
     * @param elements
     */
    public void constructSegmentTree(int[] elements) {
        constructSegmentTreeUtil(elements, STARTINDEX, ENDINDEX, ROOT);
    }

    private void updateTreeUtil(int startIndex, int endIndex, int updatePos, int update, int current) {
        if (updatePos < startIndex || updatePos > endIndex) {
            return;
        }
        tree[current] = tree[current] + update;
        if (startIndex != endIndex) {
            int mid = mid(startIndex, endIndex);
            updateTreeUtil(startIndex, mid, updatePos, update, leftchild(current));
            updateTreeUtil(mid + 1, endIndex, updatePos, update, rightchild(current));
        }
    }

    public void update(int update, int updatePos, int[] elements) {
        int updatediff = update - elements[updatePos];
        elements[updatePos] = update;
        updateTreeUtil(STARTINDEX, ENDINDEX, updatePos, updatediff, ROOT);
    }

    public static void main(String... arg) {
        int[] elements = {1, 3, 5, 7, 9, 11};
        SegmentTree2 segmentTree = new SegmentTree2(6);
        segmentTree.constructSegmentTree(elements);
        int num = segmentTree.getSum(1, 5);

        System.out.println("the num is " + num);
        segmentTree.update(10, 5, elements);
        num = segmentTree.getSum(1, 5);
        System.out.println("the num is " + num);
    }
}

