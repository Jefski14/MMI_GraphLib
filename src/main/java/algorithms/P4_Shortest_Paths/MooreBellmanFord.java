package algorithms.P4_Shortest_Paths;

import algorithms.P6_B_Flow.NegativeCycleWithMinCapacity;
import entity.Edge;
import entity.Graph;
import entity.PredAndDist;
import entity.Vertex;

import java.util.ArrayList;
import java.util.Collections;
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
            kwbMap.put(v.getId(), new PredAndDist(null, Double.POSITIVE_INFINITY));
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
                        NegativeCycleWithMinCapacity negCycle = constructNegativeCycle(e.getStart().getId(), graph, kwbMap);
                        throw new NegativeCyclesException("Found negative Cycle at edge:" + e.toString() + ")", negCycle);
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

    private static NegativeCycleWithMinCapacity constructNegativeCycle(int vertexId, Graph graph, HashMap<Integer, PredAndDist> kwbMap) {
        NegativeCycleWithMinCapacity cycle = new NegativeCycleWithMinCapacity();
        // Gehe n mal zurück vor dem constructCycle
        for (int i = 0; i < graph.getVertexList().size(); i++) {
            vertexId = kwbMap.get(vertexId).getPredecessorId();
        }
        int currentId = vertexId;
        while (kwbMap.get(currentId).getPredecessorId() != vertexId) {
            Edge e = graph.getEdge(kwbMap.get(currentId).getPredecessorId(), currentId);
            cycle.edges.add(e);
            cycle.totalCost += e.getCost();
            cycle.minCycleCapacity = Math.min(cycle.minCycleCapacity, e.getCapacity());
            currentId = kwbMap.get(currentId).getPredecessorId(); // get Parent
        }
        // Add edge from "startVertex" to last in cycle
        Edge e = graph.getEdge(kwbMap.get(currentId).getPredecessorId(), currentId);
        cycle.edges.add(e);
        cycle.totalCost += e.getCost();
        cycle.minCycleCapacity = Math.min(cycle.minCycleCapacity, e.getCapacity());

        // For better readablity in debug
        Collections.reverse(cycle.edges);
        return cycle;
    }
}
