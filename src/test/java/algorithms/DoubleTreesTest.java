package algorithms;

import entity.Graph;
import org.junit.Test;

import java.math.BigDecimal;

import static algorithms.DoubleTrees.calculateTour;
import static helper.GraphParser.importGraphFromFile;
import static org.junit.Assert.assertEquals;

public class DoubleTreesTest {

    @Test
    public void K_10Test() {
        Graph graph = importGraphFromFile("src/main/resources/p3/K_10.txt", false);
        long startTime = System.currentTimeMillis();
        System.out.println("Starting DoubleTrees");
        Graph tsp = calculateTour(graph); // NN von 9 liefert optimale Tour
        long estimatedTime = System.currentTimeMillis() - startTime;
        System.out.println("NN took: " + estimatedTime + "ms");
        System.out.println(tsp.getEdgeList().toString());
        assertEquals(BigDecimal.valueOf(38.41), tsp.totalEdgeCost());
    }
}
