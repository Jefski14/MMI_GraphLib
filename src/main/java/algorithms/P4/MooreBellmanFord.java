package algorithms.P4;

import entity.Edge;
import entity.Graph;
import entity.PredAndDist;
import entity.Vertex;

import java.util.HashMap;

public class MooreBellmanFord {

    public static Graph findKWB(Graph graph, Vertex start) {
        HashMap currentCostList = new HashMap();
        for (Vertex v : graph.getVertexList()) {
            currentCostList.put(v.getId(), new PredAndDist(0, Double.POSITIVE_INFINITY));
        }
        // Initialize Dist and Pred for starter vertex
        currentCostList.put(start.getId(), new PredAndDist(start.getId(), 0.0));

        boolean gotBetterInLastIteration = false; // TODO use for optimization
        for (int iteration = 0; iteration < graph.getVertexList().size(); iteration++) {
            for (Edge e: graph.getEdgeList()) {
                if (currentCostList.get(e.getStart().getId()))
            }
        }

        return null;
    }
}
