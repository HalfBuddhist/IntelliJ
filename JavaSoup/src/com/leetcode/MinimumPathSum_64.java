package com.leetcode;

import com.algo_ds.graph.AdjListNode;
import com.algo_ds.graph.DirectedGraphSparse;
import com.lqw.common.WebPath;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;

public class MinimumPathSum_64 {

    /**
     * SPCS, graph
     * this is a sparse directed graph prolbem to fin the shortest path of the single source.
     * use dijikstra algo
     * m*n*log(m*n)
     * AC
     *
     * @param grid
     * @return
     */
    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = m > 0 ? grid[0].length : 0;
        LinkedList[] adj = new LinkedList[m * n];//overflow??
        Arrays.setAll(adj, i -> {
            return new LinkedList<AdjListNode>();
        });

        //form the graph
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
            }
        }

        //dijikstra
        DirectedGraphSparse graphSparse = new DirectedGraphSparse(adj);
        int dist[] = graphSparse.dijkstra(0);
        return (dist[m * n - 1] + grid[0][0] + grid[m - 1][n - 1]) / 2;
    }

    /**
     * SPCS, graph
     * this is a sparse directed graph prolbem to fin the shortest path of the single source.
     * as the problems states, the array is already a toplogical sorted array, so the simplified version
     * of bellman-ford that is the shortest_path method in DirectedGraphSparse.
     * O(m*n)
     * AC
     *
     * @param grid
     * @return
     */
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
        DirectedGraphSparse graph = new DirectedGraphSparse(adj);
        int dist[] = graph.shortestPath(0, false, null, order);
        return (dist[m * n - 1] + grid[0][0] + grid[m - 1][n - 1]) / 2;
    }

    public static void main(String[] argv) throws FileNotFoundException {
        long begin = System.currentTimeMillis();
        Scanner sc = new Scanner(new File(WebPath.getAbsolutePathWithClass("/input.txt").getPath()));
        //        Scanner sc = new Scanner(new BufferedInputStream(System.in));
        //        Scanner sc = new Scanner(System.in);
        //        System.setOut(new PrintStream(new FileOutputStream(new File(WebPath.getAbsolutePathWithClass().getPath() + "output.txt"))));
        //presolve
        //input
        int[][] grid = {{0}};
        int a = (new MinimumPathSum_64().minPathSum2(grid));
        System.out.println(a);

        //resolve

        //output

        sc.close();
        System.err.println("use time: " + (System.currentTimeMillis() - begin) / 1000.0 + " seconds.");
    }
}
