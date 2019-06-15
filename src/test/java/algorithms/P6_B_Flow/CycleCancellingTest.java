package algorithms.P6_B_Flow;

import algorithms.P5_Max_Flow.EdmondsKarp;
import entity.Graph;
import org.junit.Test;

import static helper.GraphParser.importGraphFromFile;
import static helper.GraphParser.importGraphWithBalance;
import static org.junit.Assert.assertEquals;

public class CycleCancellingTest {

    @Test
    public void test_EdmondsKarp_Fluss() {
        Graph graph = importGraphWithBalance("src/main/resources/p6/Kostenminimal1.txt");

        long startTime = System.currentTimeMillis();
        System.out.println("Starting CycleCancelling");

        CycleCancelling.getMinimalCostFlow(graph);

        long estimatedTime = System.currentTimeMillis() - startTime;
        System.out.println("Took " + estimatedTime + " ms\nor " + estimatedTime / 1000.0 + " seconds.");

    }
}
