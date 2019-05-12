package entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Class to represent a tour of an graph with its cost to traverse it
 * <p>
 * Helpful for Branch and Bound to see, if adding an edge makes the tour more
 * expensive then the current cheapest tour
 */
public class Tour {
    private List<Edge> edgeList;
    private BigDecimal totalCost;

    //New tour with no given edges
    public Tour() {
        edgeList = new ArrayList<>();
        totalCost = new BigDecimal(0.0);
    }

    //New tour with given edge list, calculate total cost
    public Tour(List<Edge> edges) {
        edgeList = new ArrayList<>();
        edgeList.addAll(edges);
        calculateTotalCost();
    }

    //Calculates total cost of given edge list
    private void calculateTotalCost() {
        BigDecimal cost = new BigDecimal(0.0);
        for (Edge e : edgeList) {
            cost = cost.add(e.getCost());
        }
        this.totalCost = cost;
    }

    //Add edge to edge list and add cost of this edge to total cost
    public void addEdge(Edge e) {
        edgeList.add(e);
        totalCost = totalCost.add(e.getCost());
    }

    public BigDecimal getTotalCost() {
        return totalCost;
    }
}
