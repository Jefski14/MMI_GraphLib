package algorithms.P6_B_Flow;

import entity.Edge;

import java.util.ArrayList;

/**
 * Wrapper Class for NegativeCycle
 */
public class NegativeCycleWithMinCapacity {
    public NegativeCycleWithMinCapacity() {
        edges = new ArrayList<>();
        minCycleCapacity = Double.MAX_VALUE;
        totalCost = 0.0;
    }
    public ArrayList<Edge> edges;
    public Double minCycleCapacity;
    public Double totalCost;
}
