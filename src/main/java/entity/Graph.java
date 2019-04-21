package entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
public class Graph {
    List<Edge> edgeList = new ArrayList<>();
    List<Vertex> vertexList = new ArrayList<>();
    boolean directed;

    public BigDecimal totalEdgeCost() {
        BigDecimal result = new BigDecimal(0.0);
        for (Edge e : edgeList) {
            result = result.add(e.getCost());
        }
        return result;
    }
}
