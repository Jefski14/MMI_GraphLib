package algorithms.P2_MST;

import entity.Edge;
import entity.Graph;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Class for creating minimal spanning tree using kruskal
 */
public class KruskalMST {

    /**
     * Method to run the kruskal-mst-algorithm
     *
     * @return List of vertices with minimal set of edges to get MST
     */
    public Graph getMST(Graph dracula) {
        List<Edge> edgeList = new ArrayList<>();
        //priority-queue ordered by cost ascending
        PriorityQueue<Edge> pq = new PriorityQueue<>(dracula.getEdgeList());

        int numberVertices = dracula.getVertexList().size();

        //initialize Subsets
        Subset[] subsets = new Subset[numberVertices];
        for (int i = 0; i < numberVertices; i++) {
            subsets[i] = new Subset();
            subsets[i].parent = i;
            subsets[i].rank = 0;
        }

        int index = 0;
        while (index < numberVertices - 1) {
            //pick edge with least cost
            Edge currentEdge = pq.remove();

            int x = find(subsets, currentEdge.getStart().getId());
            int y = find(subsets, currentEdge.getEnd().getId());

            // if including this edge doesn't cause cycle, add to mst
            if (x != y) {
                edgeList.add(currentEdge);
                union(subsets, x, y);
                index++;
            }
            // else discard edge and move on
        }

        //Create new graph-object and replace minimal spanning edge list
        Graph result = new Graph();
        result.setVertexList(dracula.getVertexList());
        result.setDirected(dracula.isDirected());
        result.setEdgeList(edgeList);

        return result;
    }

    private int find(Subset[] subsets, int i) {
        // find root and make root as parent of i (path compression)
        if (subsets[i].parent != i)
            subsets[i].parent = find(subsets, subsets[i].parent);

        return subsets[i].parent;
    }

    private void union(Subset[] subsets, int x, int y) {
        int xroot = find(subsets, x);
        int yroot = find(subsets, y);

        // Attach smaller rank tree under root of high rank tree
        // (Union by Rank)
        if (subsets[xroot].rank < subsets[yroot].rank)
            subsets[xroot].parent = yroot;
        else if (subsets[xroot].rank > subsets[yroot].rank)
            subsets[yroot].parent = xroot;

            // If ranks are same, then make one as root and increment
            // its rank by one
        else {
            subsets[yroot].parent = xroot;
            subsets[xroot].rank++;
        }
    }

    class Subset {
        int parent, rank;
    }
}
