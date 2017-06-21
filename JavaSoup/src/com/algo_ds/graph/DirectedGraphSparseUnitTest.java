package com.algo_ds.graph;

import com.lqw.common.WebPath;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;

public class DirectedGraphSparseUnitTest {

    @Test
    public void shortestPath_test() throws FileNotFoundException {
        System.out.println("shortestPath_test");
        File file = new File(WebPath.getAbsolutePathWithClass("/com/algo_ds/graph/DirectedGraph_graph_test_DAG.txt").getPath());
        DirectedGraphSparse graph = createGraphFromFile(file);

        int[] path = new int[graph.V];
        int src = 1;
//        int[] dist = graph.shortestPath(src);
        int[] dist = graph.shortestPath(src, true, path);

        // Print the calculated shortest distances
        System.out.println("Following are shortest distances " + "from source " + 1);
        for (int i = 0; i < graph.V; i++) {
            System.out.print(i + ":\t");
            if (dist[i] == graph.INF)
                System.out.println("INF\t");
            else {
                System.out.print(dist[i] + "\t");
                if (true) {
                    int cur = i;
                    while (cur != src) {
                        System.out.print(cur + "<-");
                        cur = path[cur];
                    }
                    System.out.println(cur);
                }
            }
        }
    }

    @Test
    public void toplogicalSort_test() throws FileNotFoundException {
        System.out.println("toplogicalSort_test");
        File file = new File(WebPath.getAbsolutePathWithClass("/com/algo_ds/graph/DirectedGraph_graph_test_DAG.txt").getPath());
        DirectedGraphSparse graph = createGraphFromFile(file);
        Stack<Integer> stack = graph.topologicalSort();
        while (!stack.empty()) {
            int a = stack.pop();
            System.out.print(a + " ");
        }
    }

    @Test
    public void bellman_ford_test() throws FileNotFoundException {
        System.out.println("bellman_ford_test");
//        File file = new File(WebPath.getAbsolutePathWithClass("/com/algo_ds/graph/DirectedGraph_graph_test_2.txt").getPath());
        File file = new File(WebPath.getAbsolutePathWithClass("/com/algo_ds/graph/DirectedGraph_graph_test_3.txt").getPath());
        DirectedGraphSparse graph = createGraphFromFile(file);
        try {
            int[] path = new int[graph.V];
            int[] dist = graph.bellman_ford(0, true, path);
//            int[] dist = graph.bellman_ford(0);
            System.out.println("Vertex   Distance from Source");
            for (int i = 0; i < graph.V; i++) {
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
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }

    /**
     * Test prim MST algo on exmaple 1
     */
    @Test
    public void prim_test1() throws FileNotFoundException {
        System.out.println("prim_test1");
//        File file = new File(WebPath.getAbsolutePathWithClass("/com/algo_ds/graph/DirectedGraph_graph_test_1.txt").getPath());
        File file = new File(WebPath.getAbsolutePathWithClass("/com/algo_ds/graph/DirectedGraph_graph_test_2.txt").getPath());
        DirectedGraphSparse graph = createGraphFromFile(file);
        int parent[] = graph.primMST();

        // Print the solution
        System.out.println("Edge   Weight, prim_test1");
        for (int i = 0; i < graph.V; i++)
            if (parent[i] != -1)
                System.out.println(parent[i] + " - " + i);
        System.out.println("Total weight: " + parent[graph.V]);
        Assert.assertTrue(parent[graph.V] == 37);
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
        LinkedList<AdjListNode>[] adj = new LinkedList[graph.length];
        for (int i = 0; i < graph.length; i++) {
            adj[i] = new LinkedList<AdjListNode>();
        }

        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph.length; j++) {
                if (graph[i][j] != 0)
                    adj[i].add(new AdjListNode(graph[i][j], j));
            }
        }

        DirectedGraphSparse graph1 = new DirectedGraphSparse(adj);
        int[] parent = graph1.primMST();

        // Print the solution
        System.out.println("Edge   Weight, prim_test2");
        for (int i = 0; i < graph1.V; i++)
            if (parent[i] != -1)
                System.out.println(parent[i] + " - " + i + "    " + graph[i][parent[i]]);
        System.out.println("Total weight: " + parent[graph.length]);
        Assert.assertTrue(parent[graph.length] == 16);
    }

    /**
     * create graph form file in specific format.
     *
     * @param file
     * @return
     * @throws FileNotFoundException
     */
    private DirectedGraphSparse createGraphFromFile(File file) throws FileNotFoundException {
        Scanner sc = new Scanner(file);
        // input
        int n = sc.nextInt();
        int e = sc.nextInt();
        LinkedList<AdjListNode>[] adj = new LinkedList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new LinkedList<AdjListNode>();
        }
        while (e-- > 0) {
            int start = sc.nextInt();
            int end = sc.nextInt();
            int weight = sc.nextInt();
            //add to adj
            adj[start].add(new AdjListNode(weight, end));
//            if (adj[end] == null)
//                adj[end] = new LinkedList<AdjListNode>();
//            adj[end].add(new AdjListNode(weight, start));
        }

//        int src = sc.nextInt();
        sc.close();
        return new DirectedGraphSparse(n, adj);
    }


    /**
     * Test on dijikstra algo on example data 1
     *
     * @throws java.io.FileNotFoundException
     */
    @Test
    public void dijikstra_test1() throws FileNotFoundException {
        System.out.println("dijikstra_test1");
        File file = new File(WebPath.getAbsolutePathWithClass("/com/algo_ds/graph/DirectedGraph_graph_test_1.txt").getPath());
        DirectedGraphSparse graph1 = createGraphFromFile(file);

        int[] path = new int[graph1.V];
//        int[] dist = graph1.dijkstra(src, true, path, -1);
        int[] dist = graph1.dijkstra(0);
        System.out.println("Vertex   Distance from Source");
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
        System.out.println("dijikstra_test2");
        File file = new File(WebPath.getAbsolutePathWithClass("/com/algo_ds/graph/DirectedGraph_graph_test_2.txt").getPath());
        DirectedGraphSparse graph1 = createGraphFromFile(file);

        int[] path = new int[graph1.V];
        int[] dist = graph1.dijkstra(0, true, path, -1);
//        int[] dist = graph1.dijkstra(0);
        System.out.println("Vertex   Distance from Source");
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
