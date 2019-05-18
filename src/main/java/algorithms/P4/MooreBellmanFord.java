package algorithms.P4;

import entity.Edge;
import entity.Graph;
import entity.PredAndDist;
import entity.Vertex;

import java.util.ArrayList;
import java.util.HashMap;

public class MooreBellmanFord {

    public static Graph findKWB(Graph graph) {
        return findKWB(graph, graph.getVertexList().get(0));
    }

    public static Graph findKWB(Graph graph, Vertex start) {
        return graph.buildTreeFromPredAndDist(findKWBMap(graph, start));
    }

    public static ArrayList<Edge> getShortestPath(Graph graph, Vertex start, Vertex end) {
        HashMap<Integer, PredAndDist> kwbMap = findKWBMap(graph, start);

        return graph.buildPathFromTo(kwbMap, start.getId(), end.getId());
    }

    public static HashMap<Integer, PredAndDist> findKWBMap(Graph graph, Vertex start) {
        HashMap<Integer, PredAndDist> kwbMap = new HashMap();
        for (Vertex v : graph.getVertexList()) {
            kwbMap.put(v.getId(), new PredAndDist(0, Double.POSITIVE_INFINITY));
        }
        // Initialize Dist and Pred for starter vertex
        kwbMap.put(start.getId(), new PredAndDist(start.getId(), 0.0));

        boolean gotBetterInPrevIteration = false;
        for (int iteration = 0; iteration < graph.getVertexList().size(); iteration++) {
            for (Edge e: graph.getEdgeList()) {
                if (kwbMap.get(e.getStart().getId()).getDistance() + e.getCost() < kwbMap.get(e.getEnd().getId()).getDistance()) {
                    // Check for negative Cycles
                    if (iteration == graph.getVertexList().size()-1) {
                        //Got better in last iteration so we must have negative cycles
                        throw new NegativeCyclesException("Oh no, there are negative Cyclists on the road! (Got better while updating with Edge: " + e.toString() + ")");
                    }
                    // Update end vertex
                    kwbMap.put(e.getEnd().getId(),
                            new PredAndDist(e.getStart().getId(), kwbMap.get(e.getStart().getId()).getDistance() + e.getCost()));
                    gotBetterInPrevIteration = true;
                }
            }

            if(!gotBetterInPrevIteration) {
                break;
            }
            gotBetterInPrevIteration = false;
        }

        return kwbMap;
    }
}
