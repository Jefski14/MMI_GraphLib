package algorithms.P4;

import entity.Graph;
import entity.PredAndDist;
import entity.Vertex;

import java.util.HashMap;

public class MooreBellmanFord {

    public static Graph findKWB(Graph graph, Vertex start) {
        HashMap currentCostList = new HashMap();
        for (Vertex v : graph.getVertexList()) {
            currentCostList.put(v.getId(), new PredAndDist(0, Double.POSITIVE_INFINITY));
        }

        for (int iteration = 0; iteration < graph.getVertexList().size(); iteration++) {

        }

        return null;
    }
}
