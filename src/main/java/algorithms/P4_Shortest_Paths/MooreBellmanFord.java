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

    public static Graph findKWB(Graph graph, Vertex start) {
        MBFDataDump mbfResult = findKWBMap(graph, start);
        if (mbfResult.negCycle != null) {
            throw new NegativeCyclesException("Negative Cycle found!", mbfResult.negCycle);
        }
        return graph.buildTreeFromPredAndDist(mbfResult.kwbMap);
    }

    public static ArrayList<Edge> getShortestPath(Graph graph, Vertex start, Vertex end) {
        MBFDataDump mbfResult = findKWBMap(graph, start);
        if (mbfResult.negCycle != null) {
            throw new NegativeCyclesException("Negative Cycle found!", mbfResult.negCycle);
        }
        return graph.buildPathFromTo(mbfResult.kwbMap, start.getId(), end.getId());
    }

    public static MBFDataDump findKWBMap(Graph graph, Vertex start) {
        HashMap<Integer, PredAndDist> kwbMap = new HashMap<>();
        for (Vertex v : graph.getVertexList()) {
            kwbMap.put(v.getId(), new PredAndDist(null, Double.POSITIVE_INFINITY));
        }
        // Initialize Dist and Pred for starter vertex
        kwbMap.put(start.getId(), new PredAndDist(start.getId(), 0.0));

        boolean gotBetterInPrevIteration = false;
        for (int iteration = 0; iteration < graph.getVertexList().size(); iteration++) {
            for (Edge e: graph.getEdgeList()) {
                // Edges with 0 capacity shouldn't be respected
                if (e.getCapacity() > 0.0 && (kwbMap.get(e.getStart().getId()).getDistance() + e.getCost() < kwbMap.get(e.getEnd().getId()).getDistance())) {
                    // Check for negative Cycles
                    if (iteration == graph.getVertexList().size()-1) {
                        //Got better in last iteration so we must have negative cycles
                        NegativeCycleWithMinCapacity negCycle = constructNegativeCycle(e.getStart().getId(), graph, kwbMap);
                        return new MBFDataDump(kwbMap, negCycle);
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

        return new MBFDataDump(kwbMap, null);
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
        System.out.println("Cycle with " + cycle.totalCost + " Cost/Capacity Capacity:" + cycle.minCycleCapacity + " Total: " + cycle.totalCost * cycle.minCycleCapacity);
        cycle.edges.forEach(System.out::println);
        System.out.println("===");
        return cycle;
    }
}
