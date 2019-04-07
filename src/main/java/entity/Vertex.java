package entity;

import lombok.*;

import java.util.ArrayList;

@Data
@ToString(exclude="attachedEdges")
@AllArgsConstructor
@RequiredArgsConstructor
public class Vertex {
    @NonNull
    private int id;

    private ArrayList<Edge> attachedEdges = new ArrayList<Edge>();

    public void addEdge(Edge e) {
        if (!(e.getStart().equals(this) || e.getEnd().equals(this))) {
            throw new RuntimeException("Edge doesn't correspond to vertex" + this.id + ": " + e.toString());
        }
        this.attachedEdges.add(e);
    }
}
