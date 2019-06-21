package algorithms.P6_B_Flow;

import algorithms.P4_Shortest_Paths.MBFDataDump;
import algorithms.P4_Shortest_Paths.MooreBellmanFord;
import algorithms.P5_Max_Flow.EdmondsKarp;
import algorithms.P5_Max_Flow.GraphWithFlow;
import entity.Edge;
import entity.Graph;
import entity.PredAndDist;
import java.util.Arrays;
import java.util.Map;

import static helper.GraphParser.importGraphWithBalance;

public class CycleCancelling {

    public static double getMinimalCostFlow(Graph graph) {
        GraphWithFlow graphWithFlow = EdmondsKarp.runEdmondsKarp(graph, graph.getVertexList().get(graph.getVertexList().size() - 2).getId(), graph.getVertexList().get(graph.getVertexList().size() - 1).getId());
        boolean[] visited = new boolean[graph.getVertexList().size()];
        Arrays.fill(visited, false);

        // Check if B Flow is even possible
        if (graphWithFlow.getVertexList().get(graphWithFlow.getVertexList().size() - 2).getBalance() == graphWithFlow.max_flow &&
                graphWithFlow.getVertexList().get(graphWithFlow.getVertexList().size() - 1).getBalance() == -graphWithFlow.max_flow) {

            // Search for negative Cycles until there are no more
            while (true) {
                int nextUnvisitedId = getNextUnvisited(visited);
                if (nextUnvisitedId >= 0) {
                    MBFDataDump mbf = MooreBellmanFord.findKWBMap(graphWithFlow, graphWithFlow.getVertexList().get(nextUnvisitedId));

                    // Mark every vertex visited by MBF as visited (Optimization)
                    for (Map.Entry<Integer, PredAndDist> entry : mbf.kwbMap.entrySet()) {
                        if (entry.getValue().getDistance() < Double.POSITIVE_INFINITY) {
                            visited[entry.getKey()] = true;
                        }
                    }

                    if (mbf.negCycle != null) { // Negative Cycle found
                        // Adjust edges along negative cycle and change cost accordingly (which simultaneously calculates the new residual graph)
                        for (Edge edge : mbf.negCycle.edges) {
                            edge.setCapacity(edge.getCapacity() - mbf.negCycle.minCycleCapacity);
                            Edge reverseEdge = graphWithFlow.getEdge(edge.getEnd().getId(), edge.getStart().getId());
                            reverseEdge.setCapacity(reverseEdge.getCapacity() + mbf.negCycle.minCycleCapacity);
                        }
                        // subtract Cycle cost from total Graph cost
                        graphWithFlow.total_cost += mbf.negCycle.totalCost * mbf.negCycle.minCycleCapacity; // cycle cost is negative

                    // No more cycle and all nodes have been visited
                    } else if (getNextUnvisited(visited) < 0) {
                        break; // We're done!
                    }
                } else {
                    Arrays.fill(visited, false); // All vertices were visited but maybe there are multiple cycles on a path
                }
                // Do it again until there are no more negative cycles
            }
        } else {
            throw new RuntimeException("There is no B-Flow!");
        }
        return graphWithFlow.total_cost;
    }

    /**
     * Returns next index where value in array is false
     * @param visited array of booleans
     * @return index of first element where value is false
     */
    private static int getNextUnvisited(boolean[] visited) {
        for (int i = 0; i < visited.length; i++) {
            if (!visited[i]) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Method to check if given Graph is in bounds of the original Graph capacities (used to debug)
     * @param cc
     * @return
     */
    @Deprecated
    private static boolean checkIfCapacityOfOriginal(GraphWithFlow cc) {
        Graph graph = importGraphWithBalance("src/main/resources/p6/Kostenminimal3.txt", true);

        for (Edge e : graph.getEdgeList()) {
            if (cc.getEdge(e.getEnd().getId(), e.getStart().getId()).getCapacity() > e.getCapacity()) {
                System.out.println("Edge: " + e.toString() + " exeeds capacity");
            }
        }
        return true;
    }

    /**
     * Method to check if given Graph is exactly in bounds of the original Graph capacities (used to debug)
     * @param ec
     * @return
     */
    @Deprecated
    private static boolean checkEdmondsKarpCorrect(GraphWithFlow ec) {
        Graph graph = importGraphWithBalance("src/main/resources/p6/Kostenminimal3.txt", true);

        for (Edge e : graph.getEdgeList()) {
            if (ec.getEdge(e.getEnd().getId(), e.getStart().getId()).getCapacity() + ec.getEdge(e.getStart().getId(), e.getEnd().getId()).getCapacity() != e.getCapacity()) {
                System.out.println("Edge: " + e.toString() + " exeeds capacity");
            }
        }
        return true;
    }
}
