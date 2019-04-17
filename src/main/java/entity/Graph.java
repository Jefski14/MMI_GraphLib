package entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Graph {
    List<Edge> edgeList = new ArrayList<>();
    List<Vertex> vertexList = new ArrayList<>();
    boolean directed;

    public double totalEdgeCost() {
        return edgeList.size() > 0 ? edgeList.stream().mapToDouble(Edge::getCost).sum() : -1.0;
    }
}
