package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Graph {
    List<Edge> edgeList = new ArrayList<>();
    List<Vertex> vertexList = new ArrayList<>();
    boolean directed;

    public Graph(List<Edge> edgeList, boolean directed, int vertexCount) {
        this.directed = directed;
        // initialize Vertex List
        for (int i = 0; i < vertexCount; i++) {
            this.vertexList.add(new Vertex(i));
        }
        for (Edge e : edgeList) {
            // Add to vertex list
            if (directed) {
                this.vertexList.get(e.getStart().getId()).getAttachedEdges().add(
                        new Edge(this.vertexList.get(e.getStart().getId()), this.vertexList.get(e.getEnd().getId()), e.getCost(), e.getCapacity()));
            } else {
                this.vertexList.get(e.getStart().getId()).getAttachedEdges().add(
                        new Edge(this.vertexList.get(e.getStart().getId()), this.vertexList.get(e.getEnd().getId()), e.getCost(), e.getCapacity()));
                this.vertexList.get(e.getEnd().getId()).getAttachedEdges().add(
                        new Edge(this.vertexList.get(e.getEnd().getId()), this.vertexList.get(e.getStart().getId()), e.getCost(), e.getCapacity()));
            }
            // Copy Edge List
            this.edgeList.add(new Edge(this.vertexList.get(e.getStart().getId()), this.vertexList.get(e.getEnd().getId()), e.getCost(), e.getCapacity()));
        }
    }

    public Double totalEdgeCost() {
        Double result = 0.0;
        for (Edge e : edgeList) {
            result += e.getCost();
        }
        return result;
    }


    /**
     * Searches for Edge with specific End in given edgelist
     *
     * @param attachedEdges
     * @param endpointId
     * @return Copy of Edge
     */
    public static Edge getEdgeWithSpecificEnd(List<Edge> attachedEdges, int endpointId) {
        for (Edge e : attachedEdges) {
            if (e.getEnd().getId() == endpointId) {
                return new Edge(new Vertex(e.getStart().getId()), new Vertex(endpointId), e.getCost(), e.getCapacity());
            }
        }
        throw new RuntimeException("No Edge pointing to vertex with id: " + endpointId + " found");
    }

    /**
     * Searches for Edge with specific Endpoint and returns its cost
     *
     * @param attachedEdges List of edges to search through
     * @param endId         id of end vertex
     * @return cost of edge
     */
    public static Double getCostOfEdge(List<Edge> attachedEdges, int endId) {
        return getEdgeWithSpecificEnd(attachedEdges, endId).getCost();
    }

    /**
     * Searches for edge within start-vertex with destination dest
     *
     * @param start vertex to get attached edges from
     * @param dest  destination of desired edge
     * @return Edge
     * @throws IllegalArgumentException if no edge exists with desired destination
     */
    public Edge getEdge(Vertex start, Vertex dest) {
        for (Edge e : start.getAttachedEdges()) {
            if (e.getEnd().equals(dest.getId())) {
                return e;
            }
        }
        throw new IllegalArgumentException("No Edge on Vertex " + start.getId() + " with ID: " + dest.getId());
    }

    /**
     * Searches for edge from start to dest and returns its cost
     *
     * @param start vertex to get attached edges from
     * @param dest  destination of desired edge
     * @return Cost of edge start to dest
     * @throws IllegalArgumentException if no edge exists with desired destination
     */
    public double getEdgeCost(Vertex start, Vertex dest) {
        return getEdge(start, dest).getCost();
    }

    /**
     * Returns all adjacent vertices to vertex v
     *
     * @param v vertex to get adjacent vertices of
     * @return List of adjacent vertices
     */
    public List<Vertex> getAdjVertices(Vertex v) {
        List<Edge> attachedEdges = v.getAttachedEdges();
        List<Vertex> adjacentVertices = new ArrayList<>();
        for (Edge e : attachedEdges) {
            adjacentVertices.add(e.getEnd());
        }
        return adjacentVertices;
    }

    /**
     * Searches for the edge with given start and endpoint in this graph
     * @param startId id of starting vertex
     * @param endId id of ending vertex
     * @return new Edge with new vertices (!!WARNING the attached edges of the vertices in this edge wont be set!!)
     */
    public Edge getEdgeCopyWithNewVertices(Integer startId, Integer endId) {
        for (Edge e : this.vertexList.get(startId).getAttachedEdges()) {
            if (e.getEnd().getId() == endId) {
                return new Edge(new Vertex(startId), new Vertex(endId), e.getCost(), e.getCapacity());
            }
        }
        throw new IllegalArgumentException("No Edge on Vertex " + startId + " with ID: " + endId);
    }

    /**
     * Builds new Graph (or tree) from pred and dist Map with the cost of the edges of this graph
     * @param predAndDistMap KWB Map for distance and predecessors
     * @return Graph
     */
    public Graph buildTreeFromPredAndDist(Map<Integer, PredAndDist> predAndDistMap) {
        ArrayList<Edge> shortestPathTree = new ArrayList<>();

        for (Map.Entry<Integer, PredAndDist> e : predAndDistMap.entrySet()) {
            if (e.getValue().getPredecessorId() != e.getKey() && e.getValue().getDistance() != Double.POSITIVE_INFINITY) {
                shortestPathTree.add(getEdgeCopyWithNewVertices(e.getValue().getPredecessorId(), e.getKey())); // Add edge from pred to this vertex
            }
        }
        // Build new Graph from EdgeList
        return new Graph(shortestPathTree, this.directed, this.vertexList.size());
    }
}
