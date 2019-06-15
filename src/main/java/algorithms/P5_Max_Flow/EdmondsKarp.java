package algorithms.P5_Max_Flow;

import entity.Edge;
import entity.Graph;
import entity.Vertex;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class EdmondsKarp {

    /**
     * Does a BFS on given residual graph and
     * returns true if a path exists from source to target
     *
     * @param resiGraph residual graph
     * @param sourceId  vertex to start bfs from
     * @param targetId  vertex to reach if path exists
     * @param parent    array to store path in
     * @return true if path from source to target exists
     */
    private static boolean existsPathFromStoT(Graph resiGraph, int sourceId, int targetId, int[] parent) {

        boolean[] visited = new boolean[resiGraph.getVertexList().size()];
        Arrays.fill(visited, false);

        LinkedList<Integer> queue = new LinkedList<>();
        //Initialize queue by marking start vertex as visited
        queue.add(sourceId);
        visited[sourceId] = true;
        parent[sourceId] = -1;

        while (queue.size() != 0) {
            int u = queue.poll();

            for (int v = 0; v < visited.length; v++) {
                if (!visited[v] && resiGraph.getCapcityForEdge(u, v) > 0.0) {
                    queue.add(v);
                    parent[v] = u;
                    visited[v] = true;
                }
            }
        }

        return (visited[targetId]);
    }

    /**
     * Runs the Edmonds-Karp algorithm to find the maximum flow
     * in given graph with capacities
     *
     * @param graph    input graph with capacities for edges
     * @param sourceId sourceId of {@link entity.Vertex} from {@link Graph} to start from
     * @param targetId targetId of {@link entity.Vertex} from {@link Graph} to get maximum flow to
     * @return
     */
    public static double runEdmondsKarp(Graph graph, int sourceId, int targetId) {

        int u, v;
        //V = Number of vertices in graph
        int V = graph.getVertexList().size();

        //Residual graph is created as matrix,
        //where the value is the capacity of that edge
        //if value is 0.0, no edge exists

        Graph residual = graph;

        for (int i = 0; i < V; i++) {
            Vertex vertex = residual.getVertexList().get(i);
            List<Edge> attachedEdges = vertex.getAttachedEdges();
            for (Edge e : attachedEdges) {
                Vertex reverseEnd = e.getStart();
                Vertex reverseStart = e.getEnd();
                residual.getVertexList().get(reverseStart.getId()).getAttachedEdges().add(new Edge(reverseStart, reverseEnd, 0.0, 0.0));
            }
        }

        //parent array is used to store found paths by BFS
        int[] parent = new int[V];
        //No initial flow, therefore set to 0.0
        double max_flow = 0.0;

        while (existsPathFromStoT(residual, sourceId, targetId, parent)) {

            double path_flow = Double.MAX_VALUE;

            //Find minimum residual capacity of edges
            for (v = targetId; v != sourceId; v = parent[v]) {
                u = parent[v];
                path_flow = Math.min(path_flow, residual.getCapcityForEdge(u, v));
            }

            //Update residual capacities of the edges and reverse edges along path
            for (v = targetId; v != sourceId; v = parent[v]) {
                u = parent[v];

                double currentCapacityUtoV = residual.getEdge(u, v).getCapacity();
                residual.getEdge(u, v).setCapacity(currentCapacityUtoV - path_flow);

                double currenCapacityVtoU = residual.getEdge(u, v).getCapacity();
                residual.getEdge(v, u).setCapacity(currenCapacityVtoU + path_flow);
            }

            max_flow += path_flow;
        }

        //Build up resulting graph
//        Graph g = new Graph();
//        for (int n = 0; n < V; n++) {
//            g.getVertexList().add(new Vertex(n));
//        }
//
//        for (int i = 0; i < V; i++) {
//            for (int j = 0; j < V; j++) {
//                if (residual.getCapcityForEdge(i, j) != 0.0) {
//                    Vertex vi = g.getVertexList().get(i);
//                    Vertex vj = g.getVertexList().get(j);
////                    g.getVertexList().get(i).getAttachedEdges().add(new Edge(vi, vj, cost, capacity));
//                }
//            }
//        }

        return max_flow;
    }
}
