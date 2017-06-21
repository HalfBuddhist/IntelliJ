package com.algo_ds.graph;

import com.algo_ds.heap.HeapFinite;
import com.algo_ds.heap.HeapFiniteNode;


import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;


/**
 * 稀疏有向图，无向图可以用有向图来模拟
 * 用邻接表来表示
 * 稀疏图时，顶点个数相对比边的个数多。此时用邻接表来存储图，不仅节省存储空间，
 * 在dijikstra算法实现上也会更高效 O((E+V)*LogV) ，近似的 O(ELogV)
 * 其中E为边的个数。
 * <p>
 * 图的结点用序号来表示，
 * 至于图结点中的其它内容，可以用其它类的表示，并映射成数组的下标。
 */
public class DirectedGraphSparse {

    public int V;//node cnt
    public LinkedList<AdjListNode>[] adjacentLists;
    public static final int INF = Integer.MAX_VALUE;

    /**
     * constructers
     *
     * @param vertexCNT
     */
    public DirectedGraphSparse(int vertexCNT) {
        this.V = vertexCNT;
        this.adjacentLists = new LinkedList[V];
        for (int i = 0; i < V; i++) {
            adjacentLists[i] = new LinkedList<AdjListNode>();
        }
    }

    public DirectedGraphSparse(LinkedList[] adjacentLists) {
        this.V = adjacentLists.length;
        this.adjacentLists = adjacentLists;
    }

    public DirectedGraphSparse(int vertexCNT, LinkedList adjacentLists[]) {
        this.V = vertexCNT;
        this.adjacentLists = adjacentLists;
    }


    /**
     * Greedy thought inside.
     * <p>
     * Funtion that implements Dijkstra's single source shortest path
     * algorithm with heap of finite size for a graph represented
     * using adjacency list representation
     * 非连通与有target的情况同DirectedGraph
     * <p>
     * 时间复杂度：
     * 虽然有两层循环，但内部代码执行了O(V+E)次(和图的BFS类似).  decreaseKey()的复杂度为 log(V).
     * 因此总的复杂度为：
     * O((E+V)*LogV) = O(ELogV)
     *
     * @param src
     * @param ifRecordPath
     * @param path_in_parent
     * @return int[]: dist from src, Integer.MAX_VALUE represents unreachable.
     */
    public int[] dijkstra(int src, boolean ifRecordPath, int[] path_in_parent, int target) {
        // The output array. dist[i] will hold the shortest distance from src to i
        int dist[] = new int[V];
        HeapFinite<Integer> heap = new HeapFinite<Integer>(V);

        //initial path
        if (ifRecordPath)
            for (int i = 0; i < V; i++) {
                path_in_parent[i] = -1; // now way found
            }

        // Initialize all distances as INFINITE and stpSet[] as false
        for (int i = 0; i < V; i++) {
            dist[i] = Integer.MAX_VALUE; //unreachable
            heap.add(new HeapFiniteNode(i, dist[i]));
        }

        // Distance of source vertex from itself is always 0
        dist[src] = 0;
        heap.modifyNode(src, 0);
        if (ifRecordPath) path_in_parent[src] = src;

        // Find shortest path for all vertices
        // 这个循环中，minHeap包含的是所有未在SPT中的顶点
        while (!heap.empty()) {
            HeapFiniteNode node_near = heap.poll();
            int idx = node_near.getIdx();
            if (node_near.value.compareTo(Integer.MAX_VALUE) == 0) // is not connected
                break;
            if (idx == target) break; // if found the target.

            Iterator<AdjListNode> iter = adjacentLists[idx].iterator();
            while (iter.hasNext()) {
                AdjListNode node = iter.next();
                if (heap.inHeap(node.target) && dist[idx] != Integer.MAX_VALUE &&
                        node.weight + dist[idx] < dist[node.target]) {
                    dist[node.target] = dist[idx] + node.weight;
                    heap.modifyNode(node.target, dist[node.target]);
                    if (ifRecordPath) {
                        path_in_parent[node.target] = idx;
                    }
                }
            }
        }
        return dist;
    }

    public int[] dijkstra(int src) {
        return dijkstra(src, false, null, -1);
    }

    /**
     * Prim MST
     * Function to construct and print MST for a graph represented using adjacency LIST representation with a heap.
     * refer to the DirectedGraph for more detail
     * 复杂度:O(E log V)
     *
     * @param src start point of the MST.
     * @return mst represented in parent array, the extra last ele in the array is the least weight sum.
     */
    public int[] primMST(int src) {
        int sum_weight = 0;
        int parent[] = new int[V + 1];
        int key[] = new int[V];
        HeapFinite<Integer> heap = new HeapFinite<Integer>(V);

        //intitial
        for (int i = 0; i < V; i++) {
            key[i] = Integer.MAX_VALUE;
            parent[i] = -1;
            heap.add(new HeapFiniteNode(i, key[i]));
        }

        key[src] = 0;
        heap.modifyNode(src, 0);
        parent[src] = -1;

        while (!heap.empty()) {
            HeapFiniteNode node = heap.poll();
            int idx = node.getIdx();
            if (node.value.compareTo(Integer.MAX_VALUE) == 0) {
                break;
            }
            sum_weight += (Integer) node.value;

            Iterator<AdjListNode> iter = adjacentLists[idx].iterator();
            while (iter.hasNext()) {
                AdjListNode adjListNode = iter.next();
                if (heap.inHeap(adjListNode.target) && adjListNode.weight < key[adjListNode.target]) {
                    parent[adjListNode.target] = idx;
                    key[adjListNode.target] = adjListNode.weight;
                    heap.modifyNode(adjListNode.target, adjListNode.weight);
                }
            }
        }
        parent[V] = sum_weight;
        return parent;
    }

    public int[] primMST() {
        return primMST(0);
    }

    /**
     * 单源最短路径：给定一个图,和一个源顶点src,找到从src到其它所有所有顶点的最短路径，图中可能含有负权值的边。
     * Dijksra的算法是一个贪婪算法,时间复杂度是O(ELogV)(使用最小堆)。但是迪杰斯特拉算法在有负权值边的图中不适用,
     * Bellman-Ford适合这样的图。在网络路由中，该算法会被用作距离向量路由算法。
     * Bellman-Ford也比迪杰斯特拉算法更简单和同时也适用于分布式系统。但Bellman-Ford的时间复杂度是O(VE),E为边的个数，
     * 这要比迪杰斯特拉算法慢。
     * 算法描述：
     * 输入:图 和 源顶点src
     * 输出:从src到所有顶点的最短距离。如果有负权回路(不是负权值的边),则不计算该最短距离，
     * 没有意义，因为可以穿越负权回路任意次，则最终为负无穷。
     * <p>
     * 算法步骤：
     * <p>
     * 1.初始化：将除源点外的所有顶点的最短距离估计值 d[v] <- INF, d[s] <- 0;
     * 2.迭代求解：反复对边集E中的每条边进行松弛操作，使得顶点集V中的每个顶点v的最短距离估计值逐步逼近其最短距离；（运行|v|-1次）
     * 3.检验负权回路：判断边集E中的每一条边的两个端点是否收敛。
     * 如果存在未收敛的顶点，则算法返回false，表明问题无解；否则算法返回true，并且从源点可达的顶点v的最短距离保存在 d[v]中。
     * 该算法是利用动态规划的思想。该算法以自底向上的方式计算最短路径。(可以认为是基于递推的，也可以是基于状态转移的)
     * 递推关系：d[v] = min(d[u]+adj[u][v], d[v])
     * 它首先计算最多一条边时的最短路径(对于所有顶点)。然后,计算最多两条边时的最短路径。外层循环需要执行|V|-1次。
     * <p>
     * No target, because the target could not help to end the algo in advance.
     *
     * @param src
     * @param ifRecordPath
     * @param path_in_parent
     * @return
     */
    public int[] bellman_ford(int src, boolean ifRecordPath, int[] path_in_parent) throws Exception {
        int dist[] = new int[V];

        // Step 1: Initialize distances from src to all other
        // vertices as INFINITE
        for (int i = 0; i < V; ++i) {
            dist[i] = Integer.MAX_VALUE;
            if (ifRecordPath) path_in_parent[i] = -1; //isolated point.
        }
        dist[src] = 0;
        if (ifRecordPath) path_in_parent[src] = src;

        // Step 2: Relax all edges |V| - 1 times. A simple
        // shortest path from src to any other vertex can
        // have at-most |V| - 1 steps, more is a cycle, should be a non-negtive cyle,
        // so further cycle is useless, and the negtive cyle is meaning less and checked after.
        for (int i = 1; i < V; ++i) {
            for (int u = 0; u < V; ++u) {
                Iterator<AdjListNode> iter = adjacentLists[u].iterator();
                while (iter.hasNext()) {
                    AdjListNode ege = iter.next();
                    int v = ege.target;
                    int weight = ege.weight;
                    if (dist[u] != Integer.MAX_VALUE && dist[u] + weight < dist[v]) {
                        dist[v] = dist[u] + weight;
                        if (ifRecordPath) path_in_parent[v] = u;
                    }
                }
            }
        }

        // Step 3: check for negative-weight cycles.  The above
        // step guarantees shortest distances if graph doesn't
        // contain negative weight cycle. If we get a shorter
        //  path, then there is a cycle.
        for (int u = 0; u < V; ++u) {
            Iterator<AdjListNode> iter = adjacentLists[u].iterator();
            while (iter.hasNext()) {
                AdjListNode ege = iter.next();
                int v = ege.target;
                int weight = ege.weight;
                if (dist[u] != Integer.MAX_VALUE && dist[u] + weight < dist[v]) {
                    throw new Exception("Found negative-weight cycles");
                }
            }
        }

        return dist;
    }

    public int[] bellman_ford(int src) throws Exception {
        return bellman_ford(src, false, null);
    }

    private void topologicalSortUtil(int v, boolean visited[], Stack stack) {
        // Mark the current node as visited.
        visited[v] = true;

        // Recur for all the vertices adjacent to this vertex
        Iterator<AdjListNode> it = adjacentLists[v].iterator();
        while (it.hasNext()) {
            AdjListNode node = it.next();
            if (!visited[node.target])
                topologicalSortUtil(node.target, visited, stack);
        }
        // Push current vertex to stack which stores result
        stack.push(v);
    }

    /**
     * Topological Sort in a DAG
     * A DAG must be guranteed before calling the func.
     * O(V+E)
     *
     * @return order stored in a stack.
     */
    public Stack<Integer> topologicalSort() {
        boolean visited[] = new boolean[V];
        Stack<Integer> stack = new Stack<Integer>();
        // Call the recursive helper function to store Topological
        // Sort starting from all vertices one by one
        for (int i = 0; i < V; i++) {
            if (!visited[i]) topologicalSortUtil(i, visited, stack);
        }

        return stack;
    }

    /**
     * The function to find shortest paths from given vertex.
     * It uses topological sorting of given graph.
     * A DAG must be guranteed.
     * This is a simplified bellman-ford algo under a DAG case,
     * considerating no cyclic, reduce iterations on edges dramatically.
     * <p>
     * In detail, this method does two optimization during the step by step shortest path
     * spreading process.
     * First, when spread only the front nodes, the edges started the later nodes should be avoid
     * attending the calculation because the path length of the source node does not change or doest not
     * chage to the optimal, and the ith iteraton(i is the order in the toplogical order) would be the end
     * time to calculate.
     * Second and last, when spead to the later part, the path length of the front nodes would not
     * change becase the acyclic property, so the edges started from the front nodes should not be calculated.
     * More specifically, when we resovle the ith iteration, edges started from the later that i nodes
     * shoud be avoided, and edges started from nodes before the i should be avoided, that is to say
     * only edges started from the ith node be calculated.
     * So the overall time complexity is O(V+E).
     * <p>
     * 时间复杂度
     * 拓扑排序的时间复杂度是O(V + E)。找到拓扑顺序后，算法依次处理所有顶和其相邻顶点的顶点。总相邻顶点的个数是O(E)。
     * 因此,内循环运行O(V + E)。所以，这个算法的总体时间复杂度是O(V + E)。
     * Fastest if the graph is a DAG.
     *
     * @param src
     * @param ifRecordPath
     * @param path_in_parent
     * @param stack_toplogical the toplogical ordered stack, top of which is the src.
     * @return int[]: dist from src, Integer.MAX_VALUE represents unreachable.
     */
    public int[] shortestPath(int src, boolean ifRecordPath, int[] path_in_parent, Stack<Integer> stack_toplogical) {
        // Initialize distances to all vertices as infinite and distance to source as 0
        int dist[] = new int[V];
        for (int i = 0; i < V; i++) {
            dist[i] = INF;
            if (ifRecordPath) path_in_parent[i] = -1;
        }
        dist[src] = 0;
        if (ifRecordPath)
            path_in_parent[src] = src;

        // Process vertices in topological order
        while (!stack_toplogical.empty()) {
            // Get the next vertex from topological order
            int u = stack_toplogical.pop();

            // Update distances of all adjacent vertices
            Iterator<AdjListNode> it;
            if (dist[u] != INF) {
                it = adjacentLists[u].iterator();
                while (it.hasNext()) {
                    AdjListNode i = it.next();
                    if (dist[i.target] > dist[u] + i.weight) {
                        dist[i.target] = dist[u] + i.weight;
                        if (ifRecordPath) path_in_parent[i.target] = u;
                    }
                }
            }
        }
        return dist;
    }

    public int[] shortestPath(int src, boolean ifRecordPath, int[] path_in_parent) {
        //get the toplogical order.
        Stack<Integer> stack = topologicalSort();
        return shortestPath(src, ifRecordPath, path_in_parent, stack);
    }

    public int[] shortestPath(int src) {
        return shortestPath(src, false, null);
    }

}