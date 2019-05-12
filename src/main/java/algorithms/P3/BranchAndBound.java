package algorithms.P3;

import entity.Graph;
import entity.Tour;

import java.math.BigDecimal;


public class BranchAndBound {

    public void getBestResult(Graph graph, boolean enumerateAll) {

        Graph initialTour = NearestNeighbor.calculateTour(graph);
        Tour t0 = new Tour(initialTour.getEdgeList());
        BigDecimal cheapestTour = t0.getTotalCost();
        System.out.println(cheapestTour);

    }

    public void getPermutations(int vertexCount) {

    }
}
