package algorithms;

import entity.Edge;
import entity.Graph;
import entity.Vertex;

import java.math.BigDecimal;
import java.util.ArrayList;

/**
 *
 */
public class NearestNeighbor {

    public static  Graph calculateRoundTrip(Graph graph) {
        return calculateRoundTrip(graph, null);
    }
    /**
     * Calculates Round Trip that schould be realtivly optimal
     * @param graph complete undirected Graph
     * @return graph that contains round trip
     */
    public static Graph calculateRoundTrip(Graph graph, Vertex start) {
        // Start from any node, take the cheapest edge to the next node until every node has been visited and we're back at start
        Vertex currentVertex = start != null ? start : graph.getVertexList().get(0);
        Graph tsp = new Graph();
        ArrayList<Integer> visited = new ArrayList<>();
        graph.getVertexList().forEach(vertex -> {
            tsp.getVertexList().add(new Vertex(vertex.getId()));
        });

        while (tsp.getEdgeList().size() < graph.getVertexList().size()) {
            Edge cheapest = new Edge(new Vertex(-1), new Vertex(-2), BigDecimal.valueOf(1000000000000.0), BigDecimal.valueOf(0));
            for (int i = 0; i < currentVertex.getAttachedEdges().size(); i++) {
                cheapest =
                        ( currentVertex.getAttachedEdges().get(i).getCost().compareTo(cheapest.getCost()) < 0 &&
                                !visited.contains(currentVertex.getAttachedEdges().get(i).getEnd().getId()) )
                                ? currentVertex.getAttachedEdges().get(i) : cheapest;
            }
            // Add vertex and edge to tsp

            if (!graph.isDirected()) {
                int cheapestVertId = cheapest.getEnd().getId();
                Edge tspEdge = new Edge(tsp.getVertexList().get(currentVertex.getId()), tsp.getVertexList().get(cheapestVertId), cheapest.getCost(), cheapest.getCapacity());
                tsp.getVertexList().get(currentVertex.getId())
                        .addEdge(tspEdge);
                tsp.getEdgeList().add(tspEdge);
                System.out.println(tspEdge.toString());
                visited.add(cheapestVertId);
                currentVertex = graph.getVertexList().get(cheapestVertId);
            } else {
                throw new RuntimeException("Graph should be directed!");
            }
        }
        return tsp;
    }

    public static void calculateTour(Graph graph, Vertex start) {
        Vertex currentVertex = start != null ? start : graph.getVertexList().get(0);
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
        System.out.println(tspEdgeList.toString());
    }
}
