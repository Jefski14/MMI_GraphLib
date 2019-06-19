package algorithms.P6_B_Flow;

import algorithms.P4_Shortest_Paths.MooreBellmanFord;
import algorithms.P4_Shortest_Paths.NegativeCyclesException;
import algorithms.P5_Max_Flow.EdmondsKarp;
import algorithms.P5_Max_Flow.GraphWithFlow;
import entity.Edge;
import entity.Graph;
import entity.Vertex;

import java.util.Arrays;

import static helper.GraphParser.importGraphWithBalance;

public class CycleCancelling {

    public static double getMinimalCostFlow(Graph graph) {
        GraphWithFlow graphWithFlow = EdmondsKarp.runEdmondsKarp(graph, graph.getVertexList().get(graph.getVertexList().size() - 2).getId(), graph.getVertexList().get(graph.getVertexList().size() - 1).getId());
//        checkEdmondsKarpCorrect(graphWithFlow);
        // Check if B Flow exists
        if (graphWithFlow.getVertexList().get(graphWithFlow.getVertexList().size() - 2).getBalance() == graphWithFlow.max_flow) {
            while (true) {
                try {
                    for (Vertex v : graph.getVertexList()) {
                        // Search for negative Cycle
//                        graphWithFlow.removeEdgesWithZeroCapacity();
                        MooreBellmanFord.findKWBMap(graphWithFlow, v);
                    }
                    break;
                } catch (NegativeCyclesException ex) {
                    NegativeCycleWithMinCapacity negCycle = ex.cycle;
//                    graphWithFlow.checkIfResidualAndConstructIfNot();
                    // Adjust edges along negative cycle and change cost accordingly (which simultaniously calculates the new residual graph)
                    for (Edge edge : ex.cycle.edges) {
                        edge.setCapacity(edge.getCapacity() - negCycle.minCycleCapacity);
                        Edge reverseEdge = graphWithFlow.getEdge(edge.getEnd().getId(), edge.getStart().getId());
                        reverseEdge.setCapacity(reverseEdge.getCapacity() + negCycle.minCycleCapacity);
                    }
                    System.out.println("Cost before using neg cycle: " + graphWithFlow.total_cost);
                    System.out.println("                             " + negCycle.totalCost * negCycle.minCycleCapacity);
                    graphWithFlow.total_cost += negCycle.totalCost * negCycle.minCycleCapacity; // cycle cost is negative
                    System.out.println("Cost after using neg cycle: " +graphWithFlow.total_cost);
                }
                // Do it again until there are no more negative cycles
            }
        } else {
            throw new RuntimeException("There is no B-Flow!");
        }
//        checkIfCapacityOfOriginal(graphWithFlow);

        return graphWithFlow.total_cost;
    }

    private static boolean checkIfCapacityOfOriginal(GraphWithFlow cc) {
        Graph graph = importGraphWithBalance("src/main/resources/p6/Kostenminimal3.txt");

        for (Edge e : graph.getEdgeList()) {
            if ( cc.getEdge(e.getEnd().getId(), e.getStart().getId()).getCapacity() > e.getCapacity()) {
                System.out.println("Edge: " + e.toString() + " exeeds capacity");
            }
        }
        return true;
    }

    private static boolean checkEdmondsKarpCorrect(GraphWithFlow ec) {
        Graph graph = importGraphWithBalance("src/main/resources/p6/Kostenminimal3.txt");

        for (Edge e : graph.getEdgeList()) {
            if ( ec.getEdge(e.getEnd().getId(), e.getStart().getId()).getCapacity() + ec.getEdge(e.getStart().getId(), e.getEnd().getId()).getCapacity() != e.getCapacity()) {
                System.out.println("Edge: " + e.toString() + " exeeds capacity");
            }
        }
        return true;
    }

    private static int checkNotVisited(boolean[] visited) {
        for (int i = 0; i < visited.length; i++) {
            if (!visited[i]) {
                return i;
            }
        }
        return -1;
    }
}
