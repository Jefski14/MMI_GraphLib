package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
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

    public BigDecimal totalEdgeCost() {
        BigDecimal result = new BigDecimal(0.0);
        for (Edge e : edgeList) {
            result = result.add(e.getCost());
        }
        return result;
    }
}
