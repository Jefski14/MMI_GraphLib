package algorithms.P6_B_Flow;

import algorithms.P4_Shortest_Paths.MooreBellmanFord;
import algorithms.P4_Shortest_Paths.NegativeCyclesException;
import algorithms.P5_Max_Flow.EdmondsKarp;
import algorithms.P5_Max_Flow.GraphWithFlow;
import entity.Edge;
import entity.Graph;

import java.util.Arrays;

public class CycleCancelling {

    public static double getMinimalCostFlow(Graph graph) {
        GraphWithFlow graphWithFlow = EdmondsKarp.runEdmondsKarp(graph, graph.getVertexList().get(graph.getVertexList().size() - 2).getId(), graph.getVertexList().get(graph.getVertexList().size() - 1).getId());

        // Check if B Flow exists
        if (graphWithFlow.getVertexList().get(graphWithFlow.getVertexList().size()-2).getBalance() == graphWithFlow.max_flow) {
            boolean[] visited = new boolean[graphWithFlow.getVertexList().size()];
            Arrays.fill(visited, false);
            int currentStart = checkNotVisited(visited);
//            visited[currentStart] = true;
            while(currentStart >= 0) {
                try {
                    // Search for negative Cycle
                    MooreBellmanFord.getShortestPath(graphWithFlow, graphWithFlow.getVertexList().get(currentStart), graphWithFlow.getVertexList().get(graphWithFlow.getVertexList().size()-1));
                } catch (NegativeCyclesException ex) {
                    NegativeCycleWithMinCapacity negCycle = ex.cycle;
                    // Adjust edges along negative cycle and change cost accordingly (which should calculate the new residual graph)
                    for (Edge edge: ex.cycle.edges) {
                        edge.setCapacity(edge.getCapacity() - negCycle.minCycleCapacity);
                        Edge reverseEdge = graphWithFlow.getEdge(edge.getEnd().getId(), edge.getStart().getId());
                        reverseEdge.setCapacity(reverseEdge.getCapacity() + negCycle.minCycleCapacity);
                    }
                    graphWithFlow.total_cost += negCycle.totalCost * negCycle.minCycleCapacity; // cycle cost is negative
                }
                visited[currentStart] = true;
                currentStart = checkNotVisited(visited);
                // Do it again until there are no more negative cycles
            }
        } else {
            throw new RuntimeException("There is no B-Flow!");
        }

        return graphWithFlow.total_cost;
    }

    private static int checkNotVisited(boolean[] visited) {
        for (int i = 0; i < visited.length; i++) {
            if(!visited[i]) {
                return i;
            }
        }
        return -1;
    }
}
