package algorithms;

import entity.Graph;
import org.junit.Test;

import static algorithms.NearestNeighbor.calculateTour;
import static helper.GraphParser.importGraphFromFile;

public class NearestNeighborTSPTest {

    @Test
    public void K_10Test () {
        Graph graph = importGraphFromFile("src/main/resources/p3/K_10.txt", false);
        calculateTour(graph, graph.getVertexList().get(0));
    }

}
