package algorithms;

import entity.Edge;
import entity.Vertex;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Class for creating minimal spanning tree using kruskal
 */
public class KruskalMST {

    /**
     * Method to run the kruskal-mst-algorithm
     *
     * @return List of vertices with minimal set of edges to get MST
     */
    public static List<Vertex> getMST(List<Vertex> vertices) {
        List<Vertex> mst = new ArrayList<>();

        List<Edge> sortedEdgeList = getSortedEdgeListByCost(vertices);

        for (int i = 0; i < sortedEdgeList.size(); i++) {
            Edge edgeWithLeastWeight = sortedEdgeList.remove(0);
            //add to MST if no cycle is created
            mst.add(new Vertex(edgeWithLeastWeight.getStart().getId(), Collections.singletonList(edgeWithLeastWeight)));

            //Abbruchbedinung falls V-1 Edges eingefuegt wurden
            if (mst.size() == vertices.size() - 1) {
                return mst;
            }
        }

        return mst;
    }

    /**
     * Creates an {@link Edge} list from vertices,
     * sorted by cost in ascending order
     *
     * @param vertices list of {@link Vertex}
     * @return sorted {@link Edge}-List
     */
    static List<Edge> getSortedEdgeListByCost(List<Vertex> vertices) {
        List<Edge> edgeList = new ArrayList<>();
        for (Vertex v : vertices) {
            //TODO nebeneffekte durch addAll ? Potentiell zweimal die gleiche Kante ?
            edgeList.addAll(v.getAttachedEdges());
        }

        Collections.sort(edgeList, new Comparator<Edge>() {
            @Override
            public int compare(Edge e1, Edge e2) {
                return (e1.getCost() > e2.getCost() ? 1 : -1);
            }
        });

        return edgeList;
    }
}
