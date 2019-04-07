package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Edge {
    /**
     * If used as an undirected Edge it doesnt matter which vertex is the start vertex
     */
    @NonNull
    private Vertex start;
    @NonNull
    private Vertex end;
    private int cost;
    private int capacity;
}
