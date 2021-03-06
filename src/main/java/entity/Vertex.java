package entity;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@ToString(exclude = "attachedEdges")
@AllArgsConstructor
@RequiredArgsConstructor
public class Vertex implements Cloneable {

    @NonNull
    private int id;

    private double balance;

    private List<Edge> attachedEdges = new ArrayList<>();

    public void addEdge(Edge e) {
        if (!(e.getStart().equals(this) || e.getEnd().equals(this))) {
            throw new RuntimeException("Edge doesn't correspond to vertex" + this.id + ": " + e.toString());
        }
        this.attachedEdges.add(e);
    }

    @Override
    public Vertex clone() throws CloneNotSupportedException {
        return (Vertex) super.clone();
    }
}
