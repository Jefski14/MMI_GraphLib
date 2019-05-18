package algorithms.P4;

import entity.Edge;
import entity.Graph;
import entity.PredAndDist;
import entity.Vertex;

import java.util.HashMap;

public class MooreBellmanFord {

    public static Graph findKWB(Graph graph) {
        return findKWB(graph, graph.getVertexList().get(0));
    }

    public static Graph findKWB(Graph graph, Vertex start) {
        HashMap<Integer, PredAndDist> currentCostList = new HashMap();
        for (Vertex v : graph.getVertexList()) {
            currentCostList.put(v.getId(), new PredAndDist(0, Double.POSITIVE_INFINITY));
        }
        // Initialize Dist and Pred for starter vertex
        currentCostList.put(start.getId(), new PredAndDist(start.getId(), 0.0));

        boolean gotBetterInLastIteration = false;
        for (int iteration = 0; iteration < graph.getVertexList().size(); iteration++) {
            for (Edge e: graph.getEdgeList()) {
                if (currentCostList.get(e.getStart().getId()).getDistance() + e.getCost() < currentCostList.get(e.getEnd().getId()).getDistance()) {
                    // Update end vertex
                    currentCostList.put(e.getEnd().getId(),
                            new PredAndDist(e.getStart().getId(), currentCostList.get(e.getStart().getId()).getDistance() + e.getCost()));
                    gotBetterInLastIteration = true;

                    // TODO Maybe build in check for negative cycles here later so we can identify were the cycle is
                }
            }

            if(!gotBetterInLastIteration) {
                if (iteration == graph.getVertexList().size()) {
                    //Got better in last iteration so we must have negative cycles
                    throw new RuntimeException("Oh no, there are negaitve Cyclists on the road!");
                }
                break; // We're done
            }
        }


        return null;
    }
}
