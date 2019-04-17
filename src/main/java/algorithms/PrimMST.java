package algorithms;

import entity.Edge;
import entity.Graph;
import entity.Vertex;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Class for creating minimal spanning tree using kruskal
 */
public class PrimMST {

    /**
     * Method to run the prim-mst-algorithm
     *
     * @return List of vertices with minimal set of edges to get MST
     */
    public static Graph getMST(Graph graph, Vertex startVertex) {
        Graph mst = new Graph();
        // We could use the mst vertex list for this but we would need to iterate over it each time
        boolean[] inMst = new boolean[graph.getVertexList().size()]; // default initialized with false
        //Initialize mst graph
        graph.getVertexList().forEach(vertex -> { mst.getVertexList().add(new Vertex(vertex.getId())); });
        int inMstCount = 1;
        inMst[startVertex.getId()] = true;
        PriorityQueue<Edge> prioQ = new PriorityQueue<>(graph.getVertexList().get(startVertex.getId()).getAttachedEdges());

        while (inMstCount < graph.getVertexList().size()) { // As long as there are unreached nodes
            Edge currentEdge = prioQ.poll();
            int vertexIdToAdd;
            if(!inMst[currentEdge.getStart().getId()]) { // Start Vertex is not in mst
                vertexIdToAdd = currentEdge.getStart().getId();
                // Add new Edge to the Mst Vertex that now connects to the newly created Vertex
                Edge mstEdge = new Edge(mst.getVertexList().get(currentEdge.getEnd().getId()), mst.getVertexList().get(vertexIdToAdd), currentEdge.getCost(), currentEdge.getCapacity());
                mst.getVertexList().get(currentEdge.getEnd().getId()).addEdge(mstEdge);
                mst.getEdgeList().add(mstEdge);
                inMst[vertexIdToAdd] = true;
                inMstCount++;
                prioQ.addAll(currentEdge.getStart().getAttachedEdges());
            } else if (!inMst[currentEdge.getEnd().getId()]) { // End Vertex is not in mst
                vertexIdToAdd = currentEdge.getEnd().getId();
                // Add new Edge to the Mst Vertex that now connects to the newly created Vertex
                Edge mstEdge = new Edge(mst.getVertexList().get(currentEdge.getStart().getId()), mst.getVertexList().get(vertexIdToAdd), currentEdge.getCost(), currentEdge.getCapacity());
                mst.getVertexList().get(currentEdge.getStart().getId()).addEdge(mstEdge);
                mst.getEdgeList().add(mstEdge);
                inMst[vertexIdToAdd] = true;
                inMstCount++;
                prioQ.addAll(currentEdge.getEnd().getAttachedEdges());
            } else {
                // Vertex already in Mst
                continue;
            }
        }

        return mst;
    }
}
