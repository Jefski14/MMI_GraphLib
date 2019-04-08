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
                addNeighboursToQueue(current, marked, directed);
            }
        }
    }

    private void addNeighboursToQueue(final Vertex vertex, final Map<Integer, Boolean> marked, boolean directed) {
        for (final Edge edge: vertex.getAttachedEdges()) {
            if (directed) {
                if (!marked.get(vertex.getId())) {
                    queue.add(edge.getEnd());
                }
            } else {
                if(edge.getStart().equals(vertex) ? !marked.get(edge.getEnd().getId()) : !marked.get(edge.getStart().getId())) {
                    queue.add(edge.getStart().equals(vertex) ? edge.getEnd() : edge.getStart());
                }
            }
        }
    }
}
