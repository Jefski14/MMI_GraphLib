package algorithms.P4;

import entity.Graph;
import entity.PredAndDist;
import entity.Vertex;
import lombok.Getter;

import java.util.*;

@Getter
public class Dijkstra {

    private static Map<Integer, PredAndDist> predAndDist = new HashMap<>();

    /**
     * Calculates the shortest paths in graph with dijsktra
     *
     * @param graph Input graph
     * @param start start vertex to start paths from
     * @return Map of preds and distances for each vertex
     */
    public static Map<Integer, PredAndDist> calculateShortestPaths(Graph graph, Vertex start) {
        Set<Integer> unvisited = new HashSet<>();

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
            unvisited.add(v.getId());
        }

        //while there are still unvisited vertices
        while (unvisited.size() > 0) {//&& getCheapestEdge(currentVertex).getCost() != Double.POSITIVE_INFINITY) {

            //get cheapest unvisted node from PredAndDist
            try {
                Integer currentVertexId = getCheapestNode(unvisited, predAndDist);
                Vertex currentVertex = graph.getVertexList().get(currentVertexId);

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
                unvisited.remove(currentVertexId);
            } catch (NoFurtherVertexException e) {
                return predAndDist;
            }
        }
        return predAndDist;
    }

    private static Integer getCheapestNode(Set<Integer> unvisted, Map<Integer, PredAndDist> predAndDist) throws NoFurtherVertexException {
        double cheapest = Double.POSITIVE_INFINITY;
        Integer cheapestVertex = null;
        for (Integer v : unvisted) {
            if (predAndDist.get(v).getDistance() < cheapest) {
                cheapest = predAndDist.get(v).getDistance();
                cheapestVertex = v;
            }
        }
        //If no further vertex with distance < infinity exists stop algorithm
        if (cheapest == Double.POSITIVE_INFINITY) {
            throw new NoFurtherVertexException("No further unvisited vertices with cost other than infinity!");
        }
        return cheapestVertex;
    }

    /**
     * Calculates the shortest paths for given graph and
     * transforms it into shortest path tree
     *
     * @param g     input graph to calculate shortest paths from
     * @param start vertex to start paths from
     * @return Graph which contains the shortest path tree
     */
    public Graph getShortestPathTree(Graph g, Vertex start) {
        Map<Integer, PredAndDist> predAndDistMap = calculateShortestPaths(g, start);
        return g.buildTreeFromPredAndDist(predAndDistMap);
    }
}
