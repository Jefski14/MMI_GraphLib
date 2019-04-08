package algorithms;

import entity.Edge;
import entity.Vertex;

import java.util.LinkedList;
import java.util.Map;

public class BFS {
    private LinkedList<Vertex> queue = new LinkedList();

    public void breadthFirstSearch(final Vertex vertex, final Map<Integer, Boolean> marked, final boolean directed) {
        queue.add(vertex);

        Vertex current;
        while ((current = queue.poll()) != null) {
            if (!marked.get(current.getId())) {
                marked.put(current.getId(), true);
                addNeighboursToQueue(current, directed);
            }
        }
    }

    private void addNeighboursToQueue(final Vertex vertex, boolean directed) {
        for (final Edge edge: vertex.getAttachedEdges()) {
            if (directed) {
                queue.add(edge.getEnd());
            } else {
                queue.add(edge.getStart().equals(vertex) ? edge.getEnd() : edge.getStart());
            }
        }
    }
}
