package com.algo_ds.graph;


import java.lang.*;

/**
 * A class to represent a connected, directed and weighted graph
 * 只使用边与结点数的形式
 */
class DirectedGraphGeneral {
    // A class to represent a weighted edge in graph
    class Edge {
        int src, dest, weight;

        Edge() {
            src = dest = weight = -1;
        }
    }

    int V, E;
    Edge edge[];

    // Creates a graph with V vertices and E edges
    public DirectedGraphGeneral(int v, int e) {
        V = v;
        E = e;
        edge = new Edge[e];
        for (int i = 0; i < e; ++i)
            edge[i] = new Edge();
    }

    // The main function that finds shortest distances from src
    // to all other vertices using Bellman-Ford algorithm.
    // The function also detects negative weight cycle
    public int[] BellmanFord(int src) {
        int dist[] = new int[V];

        // Step 1: Initialize distances from src to all other
        // vertices as INFINITE
        for (int i = 0; i < V; ++i)
            dist[i] = Integer.MAX_VALUE;
        dist[src] = 0;

        // Step 2: Relax all edges |V| - 1 times. A simple
        // shortest path from src to any other vertex can
        // have at-most |V| - 1 edges
        for (int i = 1; i < V; ++i) {
            for (int j = 0; j < E; ++j) {
                int u = edge[j].src;
                int v = edge[j].dest;
                int weight = edge[j].weight;
                if (dist[u] != Integer.MAX_VALUE &&
                        dist[u] + weight < dist[v])
                    dist[v] = dist[u] + weight;
            }
        }

        // Step 3: check for negative-weight cycles.  The above
        // step guarantees shortest distances if graph doesn't
        // contain negative weight cycle. If we get a shorter
        //  path, then there is a cycle.
        for (int j = 0; j < E; ++j) {
            int u = edge[j].src;
            int v = edge[j].dest;
            int weight = edge[j].weight;
            if (dist[u] != Integer.MAX_VALUE &&
                    dist[u] + weight < dist[v])
                System.out.println("Graph contains negative weight cycle");
        }

        return dist;
    }
}