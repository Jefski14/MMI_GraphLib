package algorithms.P5_Max_Flow;

import entity.Graph;

import java.util.Arrays;
import java.util.LinkedList;

public class FordFulkerson {

    /**
     * Returns true if a path exists from source to target in residual graph
     *
     * @param resiGraph residual graph
     * @param sourceId  vertex to start bfs from
     * @param targetId  vertex to reach if path exists
     * @param parent    array to store path in
     * @return true if path from source to target exists
     */
    private static boolean existsPathFromStoT(int[][] resiGraph, int sourceId, int targetId, int[] parent) {

        boolean[] visited = new boolean[resiGraph.length];
        Arrays.fill(visited, false);

        LinkedList<Integer> queue = new LinkedList<>();
        //Initialize queue by marking start vertex as visited
        queue.add(sourceId);
        visited[sourceId] = true;
        parent[sourceId] = -1;

        while (queue.size() != 0) {
            int u = queue.poll();

            for (int v = 0; v < visited.length; v++) {
                if (!visited[v] && resiGraph[u][v] > 0) {
                    queue.add(v);
                    parent[v] = u;
                    visited[v] = true;
                }
            }
        }

        return (visited[targetId]);
    }

    public static double runFordFulkerson(Graph graph, int sourceId, int targetId) {

        int u, v;
        int V = graph.getVertexList().size();
        int[][] resiGraph = new int[V][V];
        for (u = 0; u < V; u++) {
            for (v = 0; v < V; v++) {
                resiGraph[u][v] = graph.existsEdge(u, v) ? 1 : 0;
            }
        }

        int[] parent = new int[V];
        double max_flow = 0.0;

        while (existsPathFromStoT(resiGraph, sourceId, targetId, parent)) {
            double path_flow = Double.MAX_VALUE;
            for (v = targetId; v != sourceId; v = parent[v]) {
                u = parent[v];
                resiGraph[u][v] -= path_flow;
                resiGraph[v][u] += path_flow;
            }

            max_flow += path_flow;
        }

        return max_flow;
    }
}
