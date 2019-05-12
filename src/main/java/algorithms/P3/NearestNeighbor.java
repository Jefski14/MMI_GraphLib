package algorithms.P3;

import entity.Edge;
import entity.Graph;
import entity.Vertex;

import java.util.ArrayList;

/**
 *
 */
public class NearestNeighbor {

    public static  Graph calculateTour(Graph graph) {
        return calculateTour(graph, graph.getVertexList().get(0));
    }

    /**
     * Calculates Round Trip that should be relatively optimal
     * @param graph complete undirected Graph
     * @return graph that contains round trip
     */
    public static Graph calculateTour(Graph graph, Vertex start) {
        Vertex currentVertex = start;
        ArrayList<Edge> tspEdgeList = new ArrayList<>();
        ArrayList<Integer> visited = new ArrayList<>();

        while(tspEdgeList.size() < graph.getVertexList().size()) {
            Edge cheapest = null;
            for(Edge e : currentVertex.getAttachedEdges()) {
                if (tspEdgeList.size() == graph.getVertexList().size() -1 && e.getEnd().getId() == visited.get(0)) {
                    // Add edge to start vertex
                    cheapest = e;
                    break;
                } else if (!visited.contains(e.getEnd().getId())) { // If not visited
                    // If Cost is >= 0 and e.cost < cheapest.cost
                    cheapest = cheapest != null && (e.getCost().compareTo(cheapest.getCost()) > 0) ? cheapest : e;
                }
            }
            tspEdgeList.add(new Edge(new Vertex(currentVertex.getId()), new Vertex(cheapest.getEnd().getId()), cheapest.getCost(), cheapest.getCapacity()));
            visited.add(currentVertex.getId());
            currentVertex = graph.getVertexList().get(cheapest.getEnd().getId());
        }
        return new Graph(tspEdgeList, false, graph.getVertexList().size());
    }
}
