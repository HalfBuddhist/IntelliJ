package com.leetcode;
import com.lqw.common.WebPath;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
class AdjListNode {
    int weight;
    int target;
    public AdjListNode(int weight, int target) {
        this.weight = weight;
        this.target = target;
    }
}
class DirectedGraphSparse {
    public int V;//node cnt
    public LinkedList<AdjListNode>[] adjacentLists;
    public static final int INF = Integer.MAX_VALUE;
    public DirectedGraphSparse(LinkedList[] adjacentLists) {
        this.V = adjacentLists.length;
        this.adjacentLists = adjacentLists;
    }
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
}
public class Solution {
    public int minPathSum2(int[][] grid) {
        int m = grid.length;
        int n = m > 0 ? grid[0].length : 0;
        LinkedList[] adj = new LinkedList[m * n];//overflow??
        Arrays.setAll(adj, i -> {
            return new LinkedList<AdjListNode>();
        });

        //form the graph and toplogical
        Stack<Integer> order = new Stack<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (j < n - 1) {
                    adj[j + i * n].add(new AdjListNode(grid[i][j] + grid[i][j + 1], j + i * n + 1));
//                    adj[j + i * n + 1].add(new AdjListNode(grid[i][j] + grid[i][j + 1], j + i * n));
                }
                if (i < m - 1) {
                    adj[j + i * n].add(new AdjListNode(grid[i][j] + grid[i + 1][j], j + (i + 1) * n));
//                    adj[j + (i + 1) * n].add(new AdjListNode(grid[i][j] + grid[i + 1][j], j + i * n));
                }

                order.push(m * n - 1 - (j + i * n));
            }
        }

        //bellman-ford
        DirectedGraphSparse graphSparse = new DirectedGraphSparse(adj);
        int dist[] = graphSparse.shortestPath(0, false, null, order);
        return (dist[m * n - 1] + grid[0][0] + grid[m - 1][n - 1]) / 2;
    }
}
