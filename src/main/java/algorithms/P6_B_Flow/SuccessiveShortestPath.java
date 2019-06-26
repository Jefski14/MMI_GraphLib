package algorithms.P6_B_Flow;

import algorithms.P1_Search.BreadthFirstSearch;
import algorithms.P4_Shortest_Paths.MooreBellmanFord;
import algorithms.P5_Max_Flow.GraphWithFlow;
import entity.Edge;
import entity.Graph;
import entity.Vertex;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SuccessiveShortestPath {

    /**
     * Calculates the minimal cost flow with SSP
     *
     * @param inputGraph graph that contains edge costs and capacity
     *                   and balances for vertices
     * @return minimal cost flow as double
     */
    public static double getMinimalCostFlow(Graph inputGraph) {
        GraphWithFlow graph = new GraphWithFlow(inputGraph);

        // Use negative edges
        for (int i = 0; i < graph.getEdgeList().size(); i++) {
            Edge e = graph.getEdgeList().get(i);
            if (e.getCost() < 0) { // Use negative Edge
                e.getStart().setBalance(e.getStart().getBalance() - e.getCapacity()); // Adjust Balance of giving vertex
                e.getEnd().setBalance(e.getEnd().getBalance() + e.getCapacity()); // Adjust Balance of receiving vertex
                graph.total_cost += e.getCost() * e.getCapacity();
                // Add residual Edge
                Edge reverse = graph.getEdgeAndConstructNewIfNonExistent(e.getEnd().getId(), e.getStart().getId());
                reverse.setCapacity(e.getCapacity());
                // Use edge completely
                e.setCapacity(0.0);
            }
        }

        graph.checkIfResidualAndConstructIfNot(); // Construct residual Graph

        while (true) {
            Vertex source = findUnusedSource(graph); // in residual graph
            Vertex target = findPossibleTargetForSource(source, graph);
            if (source == null || target == null) {
                // Check if graph is balanced -> success
                if (allVerticesBalanced(graph)) {
                    return graph.total_cost;
                    // if not -> no BFlow
                } else {
                    throw new RuntimeException("There is no B-Flow!");
                }
            } else {
                // Source and target arent null
                ArrayList<Edge> shortestPath = MooreBellmanFord.getShortestPath(graph, source, target);
                double minPathCapacity = shortestPath.stream().mapToDouble(Edge::getCapacity).min().getAsDouble();
                double minSTCap = Math.min(Math.abs(source.getBalance()), Math.abs(target.getBalance()));
                double minCap = Math.min(minSTCap, minPathCapacity);
                for (Edge e : shortestPath) {
                    // reduce edge capacity
                    e.setCapacity(e.getCapacity() - minCap);
                    // Change balances of starting and ending vertex accordingly
                    Vertex start = graph.getVertexList().get(e.getStart().getId());
                    start.setBalance(start.getBalance() - minCap);
                    Vertex end = graph.getVertexList().get(e.getEnd().getId());
                    end.setBalance(end.getBalance() + minCap);
                    // Update residual edge
                    Edge residualEdge = graph.getEdge(end.getId(), start.getId());
                    residualEdge.setCapacity(residualEdge.getCapacity() + minCap);
                    // Add cost*usedcapacity to total graph cost
                    graph.total_cost += e.getCost() * minCap;
                }
            }
        }
    }

    /**
     * Checks if all vertices are balanced
     *
     * @param graph graph
     * @return true if all are balanced,
     * false if just one vertex is unbalanced
     */
    private static boolean allVerticesBalanced(Graph graph) {
        for (Vertex v : graph.getVertexList()) {
            if (v.getBalance() != 0.0) {
                return false;
            }
        }
        return true;
    }

    /**
     * Find first unused source in vertex list
     *
     * @param graph graph
     * @return Vertex with balance > 0.0
     */
    private static Vertex findUnusedSource(Graph graph) {
        for (Vertex v : graph.getVertexList()) {
            if (v.getBalance() > 0.0) {
                return v;
            }
        }
        return null;
    }


    /**
     * Use BFS to find a target with negative balance for given source
     *
     * @param source vertex to start bfs from
     * @param graph  graph
     * @return reachable target with negative balance,
     * returns null if no target is found or source is null
     */
    private static Vertex findPossibleTargetForSource(Vertex source, Graph graph) {
        if (source == null) {
            return null;
        }
        // Fill marked map
        Map<Integer, Boolean> marked = new HashMap<>();
        graph.getVertexList().forEach(v -> marked.put(v.getId(), false));

        BreadthFirstSearch.breadthFirstSearch(source, marked, true);
        for (Map.Entry<Integer, Boolean> e : marked.entrySet()) {
            if (e.getValue() && graph.getVertexList().get(e.getKey()).getBalance() < 0) {
                return graph.getVertexList().get(e.getKey());
            }
        }
        return null;
    }
}
