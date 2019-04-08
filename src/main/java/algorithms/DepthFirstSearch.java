package algorithms;

import entity.Vertex;

import java.util.Map;
import java.util.Stack;

public class DepthFirstSearch {

    /**
     * Depth first search (iterative)
     *
     * @param vertex   starting vertex
     * @param marked   map of already marked vertices (should be initialized)
     * @param directed boolean flag that indicates if the graph is directed
     */
    public static void iterativeDepthFirstSearch(final Vertex vertex, final Map<Integer, Boolean> marked, boolean directed) {
        Stack<Vertex> stack = new Stack<>();
        stack.push(vertex); // Add starting vertex to stack
        while (!stack.empty()) {
            Vertex current = stack.pop(); // get next vertex (top of stack)
            if (!marked.get(current.getId())) {
                marked.put(current.getId(), true); // mark vertex
                addNeighborsToStack(current, stack, marked, directed);
            }
        }
    }

    /**
     * Adds neighbours of given vertex to given stack
     *
     * @param vertex   vertex
     * @param stack    stack
     * @param marked   marker map
     * @param directed directed graph flag
     */
    private static void addNeighborsToStack(final Vertex vertex, Stack<Vertex> stack, final Map<Integer, Boolean> marked, boolean directed) {
        vertex.getAttachedEdges().forEach(edge -> {
            if (directed) {
                if (!marked.get(vertex.getId())) {
                    stack.push(edge.getEnd()); // add unmarked vertex to stack
                }
            } else {
                if (edge.getStart().equals(vertex) ? !marked.get(edge.getEnd().getId()) : !marked.get(edge.getStart().getId())) {
                    stack.push(edge.getStart().equals(vertex) ? edge.getEnd() : edge.getStart()); // add unmarked vertex to stack
                }
            }
        });
    }
}
