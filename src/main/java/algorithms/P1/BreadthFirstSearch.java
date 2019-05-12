package algorithms.P1;

import entity.Vertex;

import java.util.LinkedList;
import java.util.Map;

public class BreadthFirstSearch {

    /**
     * Breadth first search
     *
     * @param vertex   starting vertex
     * @param marked   map of already marked vertices (should be initialized)
     * @param directed boolean flag that indicates if the graph is directed
     */
    public static void breadthFirstSearch(final Vertex vertex, final Map<Integer, Boolean> marked, final boolean directed) {
        LinkedList<Vertex> queue = new LinkedList<>();

        queue.add(vertex); // Add starting vertex to queue
        Vertex current;
        while ((current = queue.poll()) != null) { // get next vertex
            if (!marked.get(current.getId())) { // check if already marked
                marked.put(current.getId(), true); // mark vertex
                addNeighboursToQueue(current, queue, marked, directed);
            }
        }
    }

    /**
     * Adds neighbours of vertex to given queue if not marked
     *
     * @param vertex   vertex
     * @param queue    stack
     * @param marked   Marker map
     * @param directed Directed graph flag
     */
    private static void addNeighboursToQueue(final Vertex vertex, final LinkedList<Vertex> queue, final Map<Integer, Boolean> marked, boolean directed) {
        vertex.getAttachedEdges().forEach(edge -> {
            if (directed) {
                if (!marked.get(vertex.getId())) {
                    queue.add(edge.getEnd()); // Add unmarked neighbour to queue
                }
            } else {
                if (edge.getStart().equals(vertex) ? !marked.get(edge.getEnd().getId()) : !marked.get(edge.getStart().getId())) {
                    queue.add(edge.getStart().equals(vertex) ? edge.getEnd() : edge.getStart()); // Add unmarked neighbour to queue
                }
            }
        });
    }
}
