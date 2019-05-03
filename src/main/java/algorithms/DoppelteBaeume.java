package algorithms;

import entity.Edge;
import entity.Graph;
import entity.Vertex;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class for DoppelteBaueme-Algorithm, which creates a tour
 * with a maximum of 2 times the optimum solution
 */
public class DoppelteBaeume {

    /**
     * Convert graph into mst to further create a tour with depth-search
     *
     * @param graph Graph which is then converted into a MST with kruskal or prim
     */
    public static List<Vertex> runDoppelteBaume(Graph graph, int startVertexId, boolean useKruskal) {

        //create MST with Kruskal or Prim
        Graph mst = null;
        if (useKruskal) {
            mst = new KruskalMST().getMST(graph);
        } else {
            mst = PrimMST.getMST(graph, graph.getVertexList().get(0));
        }

        //Initialize marked-map for DFS
        List<Vertex> vertices = mst.getVertexList();
        Map<Integer, Boolean> marked = new HashMap<>();
        for (Vertex v : vertices) {
            marked.put(v.getId(), false);
        }

        //Run DFS
        List<Vertex> dfsVertices = DepthFirstSearch.iterativeDepthFirstSearch(vertices.get(startVertexId), marked, false);

        //Connect last vertex with first one
        int lastIndex = dfsVertices.size() - 1;
        dfsVertices.get(lastIndex).getAttachedEdges().add(new Edge(dfsVertices.get(lastIndex), dfsVertices.get(0)));

        return dfsVertices;

    }

    public static void drawTour(List<Vertex> vertices) {
        for (Vertex v : vertices) {
            Edge edge = v.getAttachedEdges().get(0);
            System.out.println(String.format("Vertex-Id: %s, toVertex: %s, Edge-Cost: %s", edge.getStart().getId(), edge.getEnd().getId(), edge.getCost().toString()));
        }
    }
}
