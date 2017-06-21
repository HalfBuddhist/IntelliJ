package com.algo_ds.heap;

/**
 * 有限堆
 * 应用于：有固定有限元素的堆中进行频繁修改的场景
 * <p/>
 * 相比较于传统的优先队列，如PriorityQueue in Java, 对比如下：
 * add      delete      modify              search          datasize
 * <p/>
 * infite   yes         not-efficient       not-efficient   infite      PriorityQueue
 * <p/>
 * finite   move2end    O(1)                O(1)            finite      HeapFinite
 * <p/>
 * minimum heap default.
 */
public class HeapFinite<T extends Comparable<T>> {

    int size; //current size
    int capacity; //total size, so size <= capacity;
    int[] index; // original elements' location
    HeapFiniteNode<T>[] nodes; //heap body

    public HeapFinite(int capacity) {
        this.capacity = capacity;
        index = new int[capacity];
        size = 0;
        nodes = new HeapFiniteNode[capacity];
    }

    public boolean empty() {
        return this.size == 0;
    }

    /**
     * adjust the heap in location specific
     * from idx downwards
     * O(nlogn)
     *
     * @param idx
     */
    private void adjust_downwards(int idx) {
        int smallest, left, right;
        smallest = idx;
        left = 2 * idx + 1;
        right = 2 * idx + 2;

        if (left < size && (nodes[left].value.compareTo(nodes[smallest].value)) < 0)
            smallest = left;

        if (right < size && (nodes[right].value.compareTo(nodes[smallest].value)) < 0)
            smallest = right;

        if (smallest != idx) {
            //交换下标
            index[nodes[smallest].idx] = idx;
            index[nodes[idx].idx] = smallest;

            //交换节点
            HeapFiniteNode t = nodes[smallest];
            nodes[smallest] = nodes[idx];
            nodes[idx] = t;

            adjust_downwards(smallest);
        }
    }

    /**
     * adjust the heeap upwards from the specific location.
     * O(logn)
     *
     * @param idx
     */
    private void adjust_upwards(int idx) {

        while (idx > 0 && nodes[idx].value.compareTo(nodes[(idx - 1) / 2].value) < 0) {
            //swap index
            index[nodes[idx].idx] = (idx - 1) / 2;
            index[nodes[(idx - 1) / 2].idx] = idx;
            //swap
            HeapFiniteNode t = nodes[idx];
            nodes[idx] = nodes[(idx - 1) / 2];
            nodes[(idx - 1) / 2] = t;
            //next
            idx = (idx - 1) / 2;
        }

    }


    /**
     * delete and return the top element in the heap
     * In practice, the node is not delete from the heap, but store in the tail beyoud the size,
     * still could access through the index of the heap.
     * <p/>
     * O(logn), still need to adjust the rest heap.
     *
     * @return
     */
    public HeapFiniteNode poll() {
        if (empty()) return null;

        HeapFiniteNode first = nodes[0];
        nodes[0] = nodes[size - 1];
        nodes[size - 1] = first;

        // 更新下标
        index[nodes[0].idx] = 0;
        index[first.idx] = size - 1;

        //减少堆的大小
        size--;
        adjust_downwards(0);

        return first;
    }

    /**
     * 当节点v的距离更新后(变小了)调整堆
     * O(logn)
     *
     * @param idx
     * @param new_value
     */
    public void modifyNode(int idx, T new_value) {
        int location = index[idx];
        T old_value = nodes[location].value;
        int cmp = new_value.compareTo(old_value);
        if (cmp == 0) {
            return;
        } else if (cmp < 0) {
            //adjust upwards
            nodes[location].value = new_value;
            adjust_upwards(location);
        } else {
            //adjust downwards
            nodes[location].value = new_value;
            adjust_downwards(location);
        }

    }

    /**
     * if the node of the idx in the heap end now
     *
     * @param idx
     * @return
     */
    public boolean inHeap(int idx) {
        return index[idx] < size;
    }

    /**
     * add new node to the heap.
     * generally, you should add the elements to the array sequentially, at least no more repeated addition.
     * O(logn)
     *
     * @param node
     * @return false if the heap is overflow
     */
    public boolean add(HeapFiniteNode node) {
        if (size + 1 > capacity) return false;
        nodes[size++] = node;
        index[node.idx] = size - 1;
        //adjust upwards
        adjust_upwards(size - 1);
        return true;
    }
}
