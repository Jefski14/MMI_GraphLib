package algorithms.P3_Tours;

import entity.Edge;
import entity.Graph;
import entity.Vertex;

import java.util.ArrayList;

/**
 *
 */
public class NearestNeighbor {

    public static Graph calculateTour(Graph graph) {
        return calculateTour(graph, graph.getVertexList().get(0));
    }

    /**
     * Calculates Round Trip that should be relatively optimal
     *
     * @param graph complete undirected Graph
     * @return graph that contains round trip
     */
    public static Graph calculateTour(Graph graph, Vertex start) {
        Vertex currentVertex = start;
        ArrayList<Edge> tspEdgeList = new ArrayList<>();
        ArrayList<Integer> visited = new ArrayList<>();

        while (tspEdgeList.size() < graph.getVertexList().size()) { // n edges in graph with n nodes is one circle
            Edge cheapest = null;
            for (Edge e : currentVertex.getAttachedEdges()) {
                // If this is the last vertex to finish the circle
                if (tspEdgeList.size() == graph.getVertexList().size() - 1 && e.getEnd().getId() == visited.get(0)) {
                    // Add edge to start vertex
                    cheapest = e;
                    break;
                } else if (!visited.contains(e.getEnd().getId())) { // If not visited
                    // If Cost is >= 0 and e.cost < cheapest.cost || Update cheapest edge
                    cheapest = cheapest != null && (e.getCost().compareTo(cheapest.getCost()) > 0) ? cheapest : e;
                }
            }
            // Add found cheapest edge to tsp
            tspEdgeList.add(new Edge(new Vertex(currentVertex.getId()), new Vertex(cheapest.getEnd().getId()), cheapest.getCost(), cheapest.getCapacity()));
            // mark current vertex as visited
            visited.add(currentVertex.getId());
            // continue with next vertex
            currentVertex = graph.getVertexList().get(cheapest.getEnd().getId());
        }
        return new Graph(tspEdgeList, false, graph.getVertexList().size());
    }
}
