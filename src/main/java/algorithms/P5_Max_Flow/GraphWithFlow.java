package algorithms.P5_Max_Flow;

import entity.Graph;

public class GraphWithFlow extends Graph {
    public GraphWithFlow(Graph g) {
        super(g);
        this.max_flow = 0.0;
    }
    public double max_flow;
}
