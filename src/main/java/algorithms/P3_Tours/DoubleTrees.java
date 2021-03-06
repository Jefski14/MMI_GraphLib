package algorithms.P3_Tours;

import entity.Edge;
import entity.Graph;
import entity.Vertex;

import java.util.*;

import static algorithms.P2_MST.PrimMST.getMST;
import static entity.Graph.getEdgeWithSpecificEnd;

public class DoubleTrees {

    public static Graph calculateTour(Graph graph) {
        return calculateTour(graph, graph.getVertexList().get(0));
    }

    public static Graph calculateTour(Graph graph, Vertex start) {
        // Get Mst (Prim)
        Graph mst = getMST(graph, start);
        // Initialize marked map for dfs
        Map<Integer, Boolean> marked = new HashMap<>();
        for (Vertex v : mst.getVertexList()) {
            marked.put(v.getId(), false);
        }

        ArrayList<Edge> tspEdgeList = new ArrayList<>();
        Stack<Vertex> stack = new Stack<>();

        // Add first vertex to stack
        stack.push(mst.getVertexList().get(start.getId()));
        Vertex lastVisitedVertex = start;

        // iterative depth search
        while (!stack.empty()) {
            Vertex current = stack.pop();
            if (!marked.get(current.getId())) {
                if (current.getId() != start.getId()) { // When we're not in the first iteration add edge from last visited to current
                    tspEdgeList.add(getEdgeWithSpecificEnd(graph.getVertexList().get(lastVisitedVertex.getId()).getAttachedEdges(), current.getId()));
                }
                marked.put(current.getId(), true);
                lastVisitedVertex = current;
                // Add unmarked neighbors to Stack
                for (Edge e : current.getAttachedEdges()) {
                    if (!marked.get(e.getEnd().getId())) {
                        stack.push(e.getEnd());
                    }
                }
            }
        }
        // Add Edge back to start
        tspEdgeList.add(getEdgeWithSpecificEnd(graph.getVertexList().get(lastVisitedVertex.getId()).getAttachedEdges(), start.getId()));

        return new Graph(tspEdgeList, true, mst.getVertexList().size());
    }
}
