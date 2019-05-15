package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

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
            if(directed) {
                this.vertexList.get(e.getStart().getId()).getAttachedEdges().add(
                        new Edge(this.vertexList.get(e.getStart().getId()), this.vertexList.get(e.getEnd().getId()), e.getCost(), e.getCapacity()));
            } else {
                this.vertexList.get(e.getStart().getId()).getAttachedEdges().add(
                        new Edge(this.vertexList.get(e.getStart().getId()), this.vertexList.get(e.getEnd().getId()), e.getCost(), e.getCapacity()));
                this.vertexList.get(e.getEnd().getId()).getAttachedEdges().add(
                        new Edge(this.vertexList.get(e.getEnd().getId()), this.vertexList.get(e.getStart().getId()), e.getCost(), e.getCapacity()));
            }
            // Copy Edge List
            this.edgeList.add(new Edge(this.vertexList.get(e.getStart().getId()),this.vertexList.get(e.getEnd().getId()),e.getCost(), e.getCapacity()));
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
     * @param attachedEdges List of edges to search through
     * @param endId id of end vertex
     * @return cost of edge
     */
    public static Double getCostOfEdge(List<Edge> attachedEdges, int endId) {
        return getEdgeWithSpecificEnd(attachedEdges, endId).getCost();
    }
}
