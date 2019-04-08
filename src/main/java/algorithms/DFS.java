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
                addNeighborsToStack(current, marked, directed);
            }
        }
    }

    private void addNeighborsToStack(final Vertex vertex, final Map<Integer, Boolean> marked, boolean directed){
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
