package algorithms;

import entity.Edge;
import entity.Vertex;

import java.util.LinkedList;
import java.util.Map;
import java.util.Stack;

public class Search {

    public static void breadthFirstSearch(final Vertex vertex, final Map<Integer, Boolean> marked, final boolean directed) {
        LinkedList<Vertex> queue = new LinkedList<>();

        queue.add(vertex);
        Vertex current;
        while ((current = queue.poll()) != null) {
            if (!marked.get(current.getId())) {
                marked.put(current.getId(), true);
                addNeighboursToQueue(current, queue, marked, directed);
            }
        }
    }

    private static void addNeighboursToQueue(final Vertex vertex, final LinkedList<Vertex> queue, final Map<Integer, Boolean> marked, boolean directed) {
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


    public static void iterativeDepthFirstSearch(final Vertex vertex, final Map<Integer, Boolean> marked, boolean directed) {
        Stack<Vertex> stack = new Stack<>();
        stack.push(vertex);
        while (!stack.empty()) {
            Vertex current = stack.pop();
            if (!marked.get(current.getId())) {
                marked.put(current.getId(), true);
                addNeighborsToStack(current, stack, marked, directed);
            }
        }
    }

    private static void addNeighborsToStack(final Vertex vertex, Stack<Vertex> stack, final Map<Integer, Boolean> marked, boolean directed){
        vertex.getAttachedEdges().forEach(edge -> {
            if(directed) {
                if (!marked.get(vertex.getId())) {
                    stack.push(edge.getEnd());
                }
            } else {
                if(edge.getStart().equals(vertex) ? !marked.get(edge.getEnd().getId()) : !marked.get(edge.getStart().getId())) {
                    stack.push(edge.getStart().equals(vertex) ? edge.getEnd() : edge.getStart());
                }
            }
        });
    }
}
