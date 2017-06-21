package com.algo_ds.graph;

import com.lqw.common.WebPath;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class DirectedGraphUnitTest {

    /**
     * test 1 hamiltonian cycle
     */
    @Test
    public void hamiltonian_cycle_test1() {
        System.out.println("****** hamiltonian_cycle_test1 ****** ");
        /* Let us create the following graph
           (0)--(1)--(2)
            |   / \   |
            |  /   \  |
            | /     \ |
           (3)-------(4)    */
        int graph1[][] = {{0, 1, 0, 1, 0},
                {1, 0, 1, 1, 1},
                {0, 1, 0, 0, 1},
                {1, 1, 0, 0, 1},
                {0, 1, 1, 1, 0},
        };
        DirectedGraph graph = new DirectedGraph(graph1);
        int path[] = new int[graph1.length];
        boolean res = graph.hamiltonianCycle(path);
        if (res) {
            // Print the solution
            System.out.println("Solution Exists: Following is one Hamiltonian Cycle");
            for (int i = 0; i < graph.V; i++)
                System.out.print(" " + path[i] + " ");

            // Let us print the first vertex again to show the
            // complete cycle
            System.out.println(" " + path[0] + " ");
        } else {
            System.out.println("\nSolution does not exist");
        }
    }

    /**
     * test 2 hamiltonian cycle
     */
    @Test
    public void hamiltonian_cycle_test2() {
        System.out.println("****** hamiltonian_cycle_test2 ****** ");
        /* Let us create the following graph
           (0)--(1)--(2)
            |   / \   |
            |  /   \  |
            | /     \ |
           (3)       (4)    */
        int graph1[][] = {{0, 1, 0, 1, 0},
                {1, 0, 1, 1, 1},
                {0, 1, 0, 0, 1},
                {1, 1, 0, 0, 0},
                {0, 1, 1, 0, 0},
        };
        DirectedGraph graph = new DirectedGraph(graph1);
        int path[] = new int[graph1.length];
        boolean res = graph.hamiltonianCycle(path);
        if (res) {
            // Print the solution
            System.out.println("Solution Exists: Following is one Hamiltonian Cycle");
            for (int i = 0; i < graph.V; i++)
                System.out.print(" " + path[i] + " ");

            // Let us print the first vertex again to show the
            // complete cycle
            System.out.println(" " + path[0] + " ");
        } else {
            System.out.println("\nSolution does not exist");
        }
    }

    /**
     * Test prim MST algo on exmaple 1
     */
    @Test
    public void prim_test1() {
        int graph[][] = new int[][]{
                {0, 4, 0, 0, 0, 0, 0, 8, 0},
                {4, 0, 8, 0, 0, 0, 0, 11, 0},
                {0, 8, 0, 7, 0, 4, 0, 0, 2},
                {0, 0, 7, 0, 9, 14, 0, 0, 0},
                {0, 0, 0, 9, 0, 10, 0, 0, 0},
                {0, 0, 4, 14, 10, 0, 2, 0, 0},
                {0, 0, 0, 0, 0, 2, 0, 1, 6},
                {8, 11, 0, 0, 0, 0, 1, 0, 7},
                {0, 0, 2, 0, 0, 0, 6, 7, 0}
        };
        DirectedGraph directedGraph = new DirectedGraph(graph);
        int parent[] = directedGraph.primMST();

        // Print the solution
        System.out.println("Edge   Weight, prim_test1");
        for (int i = 0; i < graph.length; i++)
            if (parent[i] != -1)
                System.out.println(parent[i] + " - " + i + "    " + graph[i][parent[i]]);
        System.out.println("Total weight: " + parent[graph.length]);

    }

    /**
     * Test prim mst algo on example 2
     */
    @Test
    public void prim_test2() {
        /* Let us create the following graph
           2    3
        (0)--(1)--(2)
        |    / \   |
        6| 8/   \5 |7
        | /      \ |
        (3)-------(4)
             9
        */
        int graph[][] = new int[][]{{0, 2, 0, 6, 0},
                {2, 0, 3, 8, 5},
                {0, 3, 0, 0, 7},
                {6, 8, 0, 0, 9},
                {0, 5, 7, 9, 0},
        };
        DirectedGraph directedGraph = new DirectedGraph(graph);
        int parent[] = directedGraph.primMST(4);

        // Print the solution
        System.out.println("Edge   Weight, prim_test2");
        for (int i = 0; i < graph.length; i++)
            if (parent[i] != -1)
                System.out.println(parent[i] + " - " + i + "    " + (parent[i] >= 0 ? graph[i][parent[i]] : ""));
        System.out.println("Total weight: " + parent[graph.length]);
        Assert.assertTrue(parent[graph.length] == 16);
    }


    /**
     * Test on dijikstra algo on example data 1
     *
     * @throws java.io.FileNotFoundException
     */
    @Test
    public void dijikstra_test1() throws FileNotFoundException {
        Scanner sc = new Scanner(new File(WebPath.getAbsolutePathWithClass("/com/algo_ds/graph/DirectedGraph_graph_test_1.txt").getPath()));
        // input
        int v = sc.nextInt();
        int e = sc.nextInt();
        int graph[][] = new int[v][v];
        while (e-- > 0) {
            int start = sc.nextInt();
            int end = sc.nextInt();
            int weight = sc.nextInt();
            graph[start][end] = weight;
        }
        int src = sc.nextInt();

        DirectedGraph graph1 = new DirectedGraph(v, graph);
        int[] path = new int[v];
//        int[] dist = graph1.dijkstra(src, true, path);
        int[] dist = graph1.dijkstra(src);
        System.out.println("Vertex   Distance from Source, dijikstra_test1");
        for (int i = 0; i < 9; i++) {
            System.out.println(i + " \t\t " + dist[i]);
            if (false) {
                int cur = i;
                while (cur != 0) {
                    System.out.print(cur + "<-");
                    cur = path[cur];
                }
                System.out.println(cur);
            }
        }

    }


    /**
     * Test dijikstra algo on example 2
     *
     * @throws java.io.FileNotFoundException
     */
    @Test
    public void dijikstra_test2() throws FileNotFoundException {
        int graph[][] = new int[][]{
                {0, 4, 0, 0, 0, 0, 0, 8, 0},
                {4, 0, 8, 0, 0, 0, 0, 11, 0},
                {0, 8, 0, 7, 0, 4, 0, 0, 2},
                {0, 0, 7, 0, 9, 14, 0, 0, 0},
                {0, 0, 0, 9, 0, 10, 0, 0, 0},
                {0, 0, 4, 14, 10, 0, 2, 0, 0},
                {0, 0, 0, 0, 0, 2, 0, 1, 6},
                {8, 11, 0, 0, 0, 0, 1, 0, 7},
                {0, 0, 2, 0, 0, 0, 6, 7, 0}
        };

        //output, to convert an output
//        int n = graph.length;
//        int cnt = 0;
//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < n; j++) {
//                if (graph[i][j] != 0) {
//                    System.out.println(i + " " + j + " " + graph[i][j]);
//                    cnt++;
//                }
//            }
//        }
//        System.out.println(cnt);

        DirectedGraph graph1 = new DirectedGraph(9, graph);
        int[] path = new int[9];
        int[] dist = graph1.dijkstra(0, true, path, -1);
//        int[] dist = graph1.dijkstra(0);
        System.out.println("Vertex   Distance from Source, dijikstra_test2");
        for (int i = 0; i < 9; i++) {
            System.out.println(i + " \t\t " + dist[i]);
            if (true) {
                int cur = i;
                while (cur != 0) {
                    System.out.print(cur + "<-");
                    cur = path[cur];
                }
                System.out.println(cur);
            }
        }
    }
}
