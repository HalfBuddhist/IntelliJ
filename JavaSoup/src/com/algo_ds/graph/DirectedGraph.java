package com.algo_ds.graph;

/**
 * 有向图，无向图可以用有向图来模拟
 * 用连接矩阵表示
 * 图的结点用序号来表示，
 * 至于图结点中的其它内容，可以用其它类的表示，并映射成数组的下标。
 */
public class DirectedGraph {

    public int V;//node cnt
    public int matrix[][];

    /**
     * Constructers
     *
     * @param vertexCNT
     */
    public DirectedGraph(int vertexCNT) {
        this.V = vertexCNT;
        this.matrix = new int[V][V];
    }

    public DirectedGraph(int matrix[][]) {
        this.V = matrix.length;
        this.matrix = matrix;
    }

    public DirectedGraph(int vertexCNT, int matrix[][]) {
        this.V = vertexCNT;
        this.matrix = matrix;
    }

    /**
     * pick the nearest node to the sptee
     * A utility function to find the vertex with minimum distance value,
     * from the set of vertices not yet included in shortest path tree
     * O(N)
     *
     * @param dist
     * @param sptSet
     * @return
     */
    private int minDistance(int dist[], boolean sptSet[]) {
        int min = Integer.MAX_VALUE, min_index = -1;

        for (int v = 0; v < V; v++)
            if (!sptSet[v] && dist[v] < min) {
                min = dist[v];
                min_index = v;
            }

        return min_index;
    }


    /**
     * Greedy thought inside.
     * Funtion that implements Dijkstra's single source shortest path
     * algorithm for a graph represented
     * using adjacency matrix representation
     * 不适用于有负权值边的图
     * <p/>
     * 这个算法是找到所有顶点的最短路径，如果我们只需要到其中一个顶点target的最短路径，
     * 可以在循环中加一个break，当u==target即可退出。
     * <p/>
     * If the graph is not connected, the return distance for these unconnected node from src
     * is Integer.MAX_VALUE, path start -1 meaning unknown.
     * <p/>
     * O(n^2); n - vertex number.
     *
     * @param src            start point of the Dijikstra
     * @param ifRecordPath   if record the path
     * @param path_in_parent path info represented in parent info.
     * @param target         the target node to find the shortest path, -1 meaning no target.
     *                       method would return if the shortest path to the target if find,
     *                       and the return value and path info is only valid for target,
     *                       meaningless but intermeidate state for others.
     * @return int[]: dist from src, Integer.MAX_VALUE represents unreachable.
     */
    public int[] dijkstra(int src, boolean ifRecordPath, int[] path_in_parent, int target) {
        // The output array. dist[i] will hold the shortest distance from src to i
        int dist[] = new int[V];

        // sptSet[i] will true if vertex i is included in shortest path tree(SPT)
        // or shortest distance from src to i is finalized,
        boolean sptSet[] = new boolean[V];

        //initial path
        if (ifRecordPath)
            for (int i = 0; i < V; i++) {
                path_in_parent[i] = -1;
            }

        // Initialize all distances as INFINITE and stpSet[] as false
        for (int i = 0; i < V; i++) {
            dist[i] = Integer.MAX_VALUE;
            sptSet[i] = false;
        }

        // Distance of source vertex from itself is always 0
        dist[src] = 0;
        if (ifRecordPath) path_in_parent[src] = src;

        // Find shortest path for all vertices
        for (int count = 0; count < V - 1; count++) {
            // Pick the minimum distance vertex from the set of vertices
            // not yet processed. u is always equal to src in first
            // iteration.
            int u = minDistance(dist, sptSet);
            if (u == -1) {// not connected any more.
                break;
            }
            if (u == target) {//have found the target.
                break;
            }

            // Mark the picked vertex as processed
            sptSet[u] = true;

            // Update dist value of the adjacent vertices of the
            // picked vertex.
            for (int v = 0; v < V; v++) {
                // Update dist[v] only if is not in sptSet, there is an
                // edge from u to v, and total weight of path from src to
                // v through u is smaller than current value of dist[v]
                if (!sptSet[v] && matrix[u][v] != 0 && dist[u] != Integer.MAX_VALUE &&
                        dist[u] + matrix[u][v] < dist[v]) {
                    dist[v] = dist[u] + matrix[u][v];
                    if (ifRecordPath) {
                        path_in_parent[v] = u;
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
     * Prim minimum spanning tree
     * Function to construct and print MST for a graph represented using adjacency matrix representation
     * 描述如下：
     * 1） 创建一个集合mstSet记录已经包含在MST中的顶点
     * 2） 对图中的所有顶点设置一个key值，代表代价，并初始化无穷大。第一个点设置为0，以便总是能第一个取到第一个点
     * 3） While( mstSet没有包含所有的顶点 )
     * a)   从mstSet集合中剩下的顶点中，选取一个最小key的顶点u
     * b)   把u加入到mstSet
     * c)   更新所有的和u相连的那些顶点的key值。
     * <p/>
     * the graph must better  be guranteed to be a connected graph.
     * if not, the MST of cluster of graph including src node would be returned, with the path of
     * the other nodes start -1, meaning unknown.
     * <p/>
     * 时间复杂度：O(V^2)
     *
     * @param src start point of the MST.
     * @return mst represented in parent array, the extra last ele in the array is the least weight sum.
     */
    public int[] primMST(int src) {
        // Array to store constructed MST
        int parent[] = new int[V + 1];
        int sum_weight = 0;

        // Key values used to pick minimum weight edge in cut
        int key[] = new int[V];

        // To represent set of vertices not yet included in MST
        boolean mstSet[] = new boolean[V];

        // Initialize all keys as INFINITE
        for (int i = 0; i < V; i++) {
            key[i] = Integer.MAX_VALUE;
            mstSet[i] = false;
            parent[i] = -1;
        }

        // Always include first 1st vertex in MST.
        key[src] = 0;     // Make key 0 so that this vertex is picked as first vertex
        parent[src] = -1; // First node is always root of MST

        // The MST will have V vertices if connected.
        for (int count = 0; count < V; count++) {
            // Pick thd minimum key vertex from the set of vertices
            // not yet included in MST
            int u = minDistance(key, mstSet);
            if (u == -1) {// no more conntecd
                break;
            }


            // Add the picked vertex to the MST Set
            mstSet[u] = true;
            sum_weight += key[u];

            // Update key value and parent index of the adjacent
            // vertices of the picked vertex. Consider only those
            // vertices which are not yet included in MST
            for (int v = 0; v < V; v++)
                // graph[u][v] is non zero only for adjacent vertices of m
                // mstSet[v] is false for vertices not yet included in MST
                // Update the key only if graph[u][v] is smaller than key[v]
                if (matrix[u][v] != 0 && !mstSet[v] && matrix[u][v] < key[v]) {
                    parent[v] = u;
                    key[v] = matrix[u][v];
                }
        }
        parent[V] = sum_weight;
        return parent;
    }

    //default start to zero
    public int[] primMST() {
        return primMST(0);
    }


    /**
     * A utility function to check if the vertex v can be added at index 'pos'in the Hamiltonian Cycle
     * constructed so far (stored in 'path[]')
     * O(n)
     *
     * @param v
     * @param path
     * @param pos
     * @return
     */
    private boolean isFeasible(int v, int path[], int pos) {
        /* Check if this vertex is an adjacent vertex of the previously added vertex. */
        if (matrix[path[pos - 1]][v] == 0)
            return false;

        /* Check if the vertex has already been included.
           This step can be optimized by creating an array of size V */
        for (int i = 0; i < pos; i++)
            if (path[i] == v)
                return false;

        return true;
    }

    /**
     * base algo, backtracking
     * <p/>
     * A recursive utility function to solve hamiltonian cycle problem
     * <p/>
     * O(n^(n+1))
     *
     * @param path path info the hamilton cycle
     * @param pos  the pos'th node in the cycle to be found.
     * @return
     */
    private boolean hamiltonianCycleUtil(int path[], int pos) {
        /* exit condition: If all vertices are included in Hamiltonian Cycle */
        if (pos == V) {
            // And if there is an edge from the last included vertex to the first vertex
            return matrix[path[pos - 1]][path[0]] != 0;
        }

        // Try different vertices as a next candidate in Hamiltonian Cycle.
        // We don't try for 0 as we included 0 as starting point in in hamiltonianCycle()
        for (int v = 1; v < V; v++) {
            /* Check if this vertex can be added to Hamiltonian Cycle */
            if (isFeasible(v, path, pos)) {
                path[pos] = v;

                /* recur to construct rest of the path */
                if (hamiltonianCycleUtil(path, pos + 1))
                    return true;

                /* If adding vertex v doesn't lead to a solution,
                   then remove it, backtracking */
                path[pos] = -1;
            }
        }

        /* If no vertex can be added to Hamiltonian Cycle constructed so far, then return false */
        return false;
    }

    /**
     * 哈密顿图（英语：Hamiltonian path，或Traceable path）是一个无向图，由天文学家哈密顿提出，
     * 由指定的起点前往指定的终点，途中经过所有其他节点且只经过一次。
     * 在图论中是指含有哈密顿回路的图， 闭合的哈密顿路径称作哈密顿回路（Hamiltonian cycle），
     * 含有图中所有顶的路径称作哈密顿路径。
     * 哈密顿回路和N皇后等问题类似，属于NP难题。一般应用回溯法准确求解。
     * <p/>
     * 对一个给定的无向图确定其是否包含哈密顿回路
     * This function solves the Hamiltonian Cycle problem using
     * Backtracking. It mainly uses hamiltonianCycleUtil() to solve the
     * problem. It returns false if there is no Hamiltonian Cycle
     * possible, otherwise return true and prints the path.
     * Please note that there may be more than one solutions,
     * this function prints one of the feasible solutions.
     * <p/>
     * O(n^(n+1))
     *
     * @param path 哈密顿回路
     * @return if has a hamilton cycle
     */
    public boolean hamiltonianCycle(int[] path) {
        //initialize path infomation.
        for (int i = 0; i < V; i++)
            path[i] = -1;

        /* Let us put vertex 0 as the first vertex in the path, because the selection of the
        start point doest not affect the existance of teh hamiltonian cycle.
        If there is a Hamiltonian Cycle, then the path can be
        started from any point of the cycle as the graph is undirected */
        path[0] = 0;
        return hamiltonianCycleUtil(path, 1);
    }
}
