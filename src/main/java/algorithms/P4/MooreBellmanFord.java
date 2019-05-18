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

        boolean gotBetterInLastIteration = false;
        for (int iteration = 0; iteration < graph.getVertexList().size(); iteration++) {
            for (Edge e: graph.getEdgeList()) {
                if (kwbMap.get(e.getStart().getId()).getDistance() + e.getCost() < kwbMap.get(e.getEnd().getId()).getDistance()) {
                    // Update end vertex
                    kwbMap.put(e.getEnd().getId(),
                            new PredAndDist(e.getStart().getId(), kwbMap.get(e.getStart().getId()).getDistance() + e.getCost()));
                    gotBetterInLastIteration = true;

                    // TODO Maybe build in check for negative cycles here later so we can identify were the cycle is
                }
            }

            if(!gotBetterInLastIteration) {
                if (iteration == graph.getVertexList().size()-1) { // TODO doesnt work
                    //Got better in last iteration so we must have negative cycles
                    throw new RuntimeException("Oh no, there are negaitve Cyclists on the road!");
                }
                break; // We're done
            }
        }

        return kwbMap;
    }
}
