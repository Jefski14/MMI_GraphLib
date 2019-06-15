package algorithms.P6_B_Flow;

import algorithms.P5_Max_Flow.EdmondsKarp;
import algorithms.P5_Max_Flow.GraphWithFlow;
import entity.Graph;

public class CycleCancelling {

    public static double getMinimalCostFlow(Graph graph) {
        GraphWithFlow graphWithFlow = EdmondsKarp.runEdmondsKarp(graph, graph.getVertexList().get(graph.getVertexList().size() - 2).getId(), graph.getVertexList().get(graph.getVertexList().size() - 1).getId());

        graphWithFlow.checkIfResidualAndConstructIfNot();

        // Check if B Flow exists

        // Search for negative Cycle

        // Adjust edges along negative cycle and change cost accordingly (which should calculate the new residual graph)

        // Do it again until there are no more negative cycles

        return 0.0;
    }
}
