package algorithms;

import entity.Vertex;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static algorithms.Search.breadthFirstSearch;

public class ConnectedGraphFinder {

    public static int findConnectedGraphs(List<Vertex> graph, boolean directed) {
        Map<Integer, Boolean> marked = new HashMap<>();
        for (int i = 0; i < graph.size(); i++) {
            marked.put(i, false);
        }
        int unmarkedVertexId = 0;
        int connectedGraphCount = 0;
        while (unmarkedVertexId >= 0) {
            breadthFirstSearch(graph.get(unmarkedVertexId), marked, directed);
            connectedGraphCount++;
            unmarkedVertexId = findUnmarkedVertexId(marked);
        }
        return connectedGraphCount;
    }

    private static int findUnmarkedVertexId(Map<Integer, Boolean> marked) {
        for (Map.Entry<Integer,Boolean> entry : marked.entrySet()) {
            if(!entry.getValue()) {
                return entry.getKey();
            }
        }
        return -1;
    }
}
