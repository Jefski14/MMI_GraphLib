package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Edge implements Comparable<Edge>, Cloneable {
    /**
     * If used as an undirected Edge it doesnt matter which vertex is the start vertex
     */
    @NonNull
    private Vertex start;
    @NonNull
    private Vertex end;
    private Double cost;
    private Double capacity;

    @Override
    public int compareTo(Edge e1) {
        return this.cost.compareTo(e1.cost);
    }

    @Override
    public String toString() {
        return String.format("Start-Vertex: %d \t End-Vertex: %d \t Cost: %f  Capacity: %f\n", this.start.getId(), this.end.getId(), this.cost, this.capacity);
    }

    @Override
    public Edge clone() throws CloneNotSupportedException {
        return (Edge) super.clone();
    }
}
