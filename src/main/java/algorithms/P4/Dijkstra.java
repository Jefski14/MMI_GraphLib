package algorithms.P4;

import entity.Edge;
import entity.Graph;
import entity.PredAndDist;
import entity.Vertex;
import lombok.Getter;

import java.util.*;

@Getter
public class Dijkstra {

    private Map<Integer, PredAndDist> predAndDist = new HashMap<>();

    /**
     * Calculates the shortest paths in graph with dijsktra
     *
     * @param graph Input graph
     * @param start start vertex to start paths from
     * @return Graph which contains all shortest paths
     */
    public Graph calculateShortestPaths(Graph graph, Vertex start) {
        Set<Vertex> unvisited = new HashSet<>();
        Map<Vertex, Boolean> visited = new HashMap<>();

        //Initialize all vertices with pred=null and dist=infinity
        //except for start vertex
        //add all vertices to unvisited map
        for (Vertex v : graph.getVertexList()) {
            if (v.getId() == start.getId()) {
                predAndDist.put(v.getId(),
                        new PredAndDist(v.getId(), 0.0));
            } else {
                predAndDist.put(v.getId(),
                        new PredAndDist(null, Double.POSITIVE_INFINITY));
            }
            unvisited.add(v);
            visited.put(v, false);
        }

        //while there are still unvisited vertices
        while (unvisited.size() > 0) {//&& getCheapestEdge(currentVertex).getCost() != Double.POSITIVE_INFINITY) {

            Vertex currentVertex = graph.getVertexList().get(start.getId());
            Edge cheapestEdge = getCheapestEdge(currentVertex);

            //get all adjacent vertices of current vertex
            List<Vertex> adjVertices = graph.getAdjVertices(currentVertex);
            for (Vertex w : adjVertices) {

                //Check if Dreiecksungleichung can be applied
                double pathCost = predAndDist.get(currentVertex.getId()).getDistance() + graph.getEdgeCost(currentVertex, w);
                if (pathCost < predAndDist.get(w.getId()).getDistance()) {
                    predAndDist.put(w.getId(), new PredAndDist(currentVertex.getId(), pathCost));
                }
            }

            //Remove current vertex from unvisited list and add it as visited
            unvisited.remove(currentVertex);
            visited.put(currentVertex, true);
        }

        return graph.buildTreeFromPredAndDist(predAndDist);
    }

    private Edge getCheapestEdge(Vertex vertex) {

        double cheapest = Double.POSITIVE_INFINITY;
        Edge cheapestEdge = null;
        for (Edge e : vertex.getAttachedEdges()) {
            if (e.getCost() < cheapest) {
                cheapest = e.getCost();
                cheapestEdge = e;
            }
        }
        return cheapestEdge;
    }
}
