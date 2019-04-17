package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Edge implements Comparable {
    /**
     * If used as an undirected Edge it doesnt matter which vertex is the start vertex
     */
    @NonNull
    private Vertex start;
    @NonNull
    private Vertex end;
    private double cost;
    private double capacity;

    @Override
    public int compareTo(Object o) {
        if (o instanceof Edge) {
            // Lower is better (Maybe a comparator function for the specific prioQ is better)
            //
            return - Double.compare(((Edge) o).getCost(), this.getCost());
        }
        return 0;
    }
}
