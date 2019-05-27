package algorithms.P1_Search;

import entity.Vertex;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static algorithms.P1_Search.BreadthFirstSearch.breadthFirstSearch;

public class ConnectedGraphFinder {

    /**
     * Counts connected graphs in big graph
     *
     * @param graph    Vertexlist representing the graph
     * @param directed Flag if the graph is directed
     * @return Number of connected graph components
     */
    public static int findConnectedGraphs(List<Vertex> graph, boolean directed) {
        Map<Integer, Boolean> marked = new HashMap<>();
        for (int i = 0; i < graph.size(); i++) {
            marked.put(i, false);
        }
        int unmarkedVertexId = 0;
        int connectedGraphCount = 0;
        while (unmarkedVertexId >= 0) { // as long as there are still unmarked vertices there are still unfound graphs
            breadthFirstSearch(graph.get(unmarkedVertexId), marked, directed);
            connectedGraphCount++;
            unmarkedVertexId = findUnmarkedVertexId(marked); // Get next unmarked vertex
        }
        return connectedGraphCount;
    }

    /**
     * Searches in marked map for first unmarked vertex
     *
     * @param marked Map of marked vertices
     * @return Id of unmarked vertex or -1 if all are marked
     */
    private static int findUnmarkedVertexId(Map<Integer, Boolean> marked) {
        for (Map.Entry<Integer, Boolean> entry : marked.entrySet()) {
            if (!entry.getValue()) {
                return entry.getKey();
            }
        }
        return -1;
    }
}
