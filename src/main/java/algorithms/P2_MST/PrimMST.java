package algorithms.P2_MST;

import entity.Edge;
import entity.Graph;
import entity.Vertex;

import java.util.PriorityQueue;

/**
 * Class for creating minimal spanning tree using prim
 */
public class PrimMST {

    // TODO fija improve code quality
    /**
     * Method to run the prim-mst-algorithm
     *
     * @return The MST Graph
     */
    public static Graph getMST(Graph graph, Vertex startVertex) {

        Graph mst = new Graph();
        // We could use the mst vertex list for this but we would need to iterate over it each time
        boolean[] inMst = new boolean[graph.getVertexList().size()]; // default initialized with false
        int inMstCount = 1;
        //Initialize mst graph
        graph.getVertexList().forEach(vertex -> mst.getVertexList().add(new Vertex(vertex.getId())));

        // Set starting vertex and initialize queue
        inMst[startVertex.getId()] = true;
        PriorityQueue<Edge> prioQ = new PriorityQueue<>(graph.getVertexList().get(startVertex.getId()).getAttachedEdges());

        while (inMstCount < graph.getVertexList().size()) { // As long as there are unreached nodes
            Edge currentEdge = prioQ.remove();
            int startId = currentEdge.getStart().getId();
            int endId = currentEdge.getEnd().getId();
            int vertexIdToAdd;
            if(!inMst[startId]) { // Start Vertex is not in mst
                vertexIdToAdd = startId;
                // Add new Edge to the Mst Vertex that now connects to the newly created Vertex
                Edge mstEdge = new Edge(mst.getVertexList().get(endId), mst.getVertexList().get(vertexIdToAdd), currentEdge.getCost(), currentEdge.getCapacity());
                mst.getVertexList().get(endId).addEdge(mstEdge);
                mst.getEdgeList().add(mstEdge);
                inMst[vertexIdToAdd] = true;
                inMstCount++;
                prioQ.addAll(currentEdge.getStart().getAttachedEdges());
            } else if (!inMst[endId]) { // End Vertex is not in mst
                vertexIdToAdd = endId;
                // Add new Edge to the Mst Vertex that now connects to the newly created Vertex
                Edge mstEdge = new Edge(mst.getVertexList().get(startId), mst.getVertexList().get(vertexIdToAdd), currentEdge.getCost(), currentEdge.getCapacity());
                mst.getVertexList().get(startId).addEdge(mstEdge);
                mst.getEdgeList().add(mstEdge);
                inMst[vertexIdToAdd] = true;
                inMstCount++;
                prioQ.addAll(currentEdge.getEnd().getAttachedEdges());
            }
            // Else the vertex is already in mst
        }
        return mst;
    }
}
