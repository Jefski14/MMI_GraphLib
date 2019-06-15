package algorithms.P5_Max_Flow;

import entity.Edge;
import entity.Graph;

import java.util.ArrayList;

public class GraphWithFlow extends Graph {
    public GraphWithFlow(Graph g) {
        super(g);
        this.max_flow = 0.0;
        this.checkIfResidualAndConstructIfNot();
    }
    public double max_flow;

    /**
     * Checks if the graph has edges in both directions and constructs them if necessary
     */
    private void checkIfResidualAndConstructIfNot() {
        ArrayList<Edge> newEdges = new ArrayList<>();
        for( Edge e : this.getEdgeList()) {
            try {
                this.getEdge(e.getEnd().getId(), e.getStart().getId()); // Check if reverse Edge exists
            } catch (IllegalArgumentException ex) {
                // Build reverse Edge
                Edge reverse = new Edge(this.getVertexList().get(e.getEnd().getId()), this.getVertexList().get(e.getStart().getId()), -e.getCost(), 0.0);
                this.getVertexList().get(e.getEnd().getId()).getAttachedEdges().add(reverse);
                newEdges.add(reverse);
            }
        }
        this.getEdgeList().addAll(newEdges);
    }
}
