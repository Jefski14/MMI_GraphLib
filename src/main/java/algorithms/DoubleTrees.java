package algorithms;

import entity.Edge;
import entity.Graph;
import entity.Vertex;

import java.util.*;

import static algorithms.PrimMST.getMST;

public class DoubleTrees {

    public static Graph calculateTour(Graph graph) {
        return calculateTour(graph, graph.getVertexList().get(0));
    }

    public static Graph calculateTour(Graph graph, Vertex start) {
        Graph mst = getMST(graph, start);
        Map<Integer, Boolean> marked = new HashMap<>();
        for(Vertex v : mst.getVertexList()) {
            marked.put(v.getId(), false);
        }
        ArrayList<Edge> tspEdgeList = new ArrayList<>();

        Stack<Vertex> stack = new Stack<>();

        stack.push(mst.getVertexList().get(start.getId()));

        while(!stack.empty()) {
            Vertex current = stack.pop();
            if (!marked.get(current.getId())) {
                // TODO set edge to vertex before
                Vertex lastInEdgeList;
                if (tspEdgeList.isEmpty()) { // Start Vertex
                    lastInEdgeList = mst.getVertexList().get(start.getId());
                } else {
                    lastInEdgeList = tspEdgeList.get(tspEdgeList.size() -1).getEnd();
                }
                if (tspEdgeList.size() == mst.getVertexList().size() -1) {
//                    tspEdgeList.add(getEdgeWithSpecificEnd(graph.getVertexList().get(lastInEdgeList.getId()))); // TODO Fall für letzten Knoten
                } else {
                    tspEdgeList.add(getEdgeWithSpecificEnd(graph.getVertexList().get(lastInEdgeList.getId()).getAttachedEdges(), current.getId()));
                }

                marked.put(current.getId(), true);
                // Add unmarked neighbors to Stack
                for(Edge e: current.getAttachedEdges()) {
                    if (!marked.get(e.getEnd().getId())) {
                        stack.push(e.getEnd());
                    }
                }
            }
        }

        return new Graph(tspEdgeList, true, mst.getVertexList().size());
    }

    private static Edge getEdgeWithSpecificEnd(List<Edge> attachedEdges, int endpointId) {
        for (Edge e: attachedEdges) {
            if (e.getEnd().getId() == endpointId) {
                return new Edge(new Vertex(e.getStart().getId()), new Vertex(endpointId), e.getCost(), e.getCapacity());
            }
        }
        throw new RuntimeException("No Edge pointing to vertex with id: " + endpointId + " found");
    }

    /**
     * Depth first search (iterative)
     *
     * @param vertex   starting vertex
     * @param marked   map of already marked vertices (should be initialized)
     * @param directed boolean flag that indicates if the graph is directed
     */
    public List<Vertex> iterativeDepthFirstSearch(final Vertex vertex, final Map<Integer, Boolean> marked, boolean directed) {
        Stack<Vertex> stack = new Stack<>();
        stack.push(vertex); // Add starting vertex to stack

        //List for output
        List<Vertex> transition = new ArrayList<>();
        //Add new vertex with empty edge-list
        transition.add(new Vertex(vertex.getId()));

        //Index to refer to last added vertex in list
        int i = 0;
        while (!stack.empty()) {
            Vertex current = stack.pop(); // get next vertex (top of stack)
            if (!marked.get(current.getId())) {
                marked.put(current.getId(), true); // mark vertex
                addNeighborsToStack(current, stack, marked, directed);

                transition.add(new Vertex(current.getId()));
                transition.get(i).getAttachedEdges().add(new Edge(transition.get(i), current));
                i++;
            }
        }
        return transition;
    }

    /**
     * Adds neighbours of given vertex to given stack
     *
     * @param vertex   vertex
     * @param stack    stack
     * @param marked   marker map
     * @param directed directed graph flag
     */
    private void addNeighborsToStack(final Vertex vertex, Stack<Vertex> stack, final Map<Integer, Boolean> marked, boolean directed) {
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
