package algorithms;

import entity.Vertex;

import java.util.Map;
import java.util.Stack;

public class DFS {

    private Stack<Vertex> stack = new Stack<>();

    public void iterativeDepthFirstSearch(final Vertex vertex, final Map<Integer, Boolean> marked, boolean directed) {
        stack.push(vertex);
        while (!stack.empty()) {
            Vertex current = stack.pop();
            if (!marked.get(current.getId())) {
                marked.put(current.getId(), true);
                addNeighborsToStack(current, directed);
            }
        }
    }

    private void addNeighborsToStack(final Vertex vertex, boolean directed){
        vertex.getAttachedEdges().forEach(edge -> {
            if(directed) {
                stack.push(edge.getEnd());
            } else {
                stack.push(edge.getStart().equals(vertex) ? edge.getEnd() : edge.getEnd());
            }
        });
    }
}
