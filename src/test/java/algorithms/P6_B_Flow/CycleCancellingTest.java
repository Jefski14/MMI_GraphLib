package algorithms.P6_B_Flow;

import entity.Graph;
import org.junit.Test;

import static helper.GraphParser.importGraphWithBalance;
import static org.junit.Assert.assertEquals;

public class CycleCancellingTest {

    @Test
    public void test_cc_Kostenminimal1() {
        Graph graph = importGraphWithBalance("src/main/resources/p6/Kostenminimal1.txt");

        long startTime = System.currentTimeMillis();
        System.out.println("Starting CycleCancelling");

        double cost = CycleCancelling.getMinimalCostFlow(graph);

        long estimatedTime = System.currentTimeMillis() - startTime;
        System.out.println("Took " + estimatedTime + " ms\nor " + estimatedTime / 1000.0 + " seconds.");

        assertEquals(3.0, cost, 0.0);
    }

    @Test(expected = RuntimeException.class)
    public void test_cc_Kostenminimal2() {
        Graph graph = importGraphWithBalance("src/main/resources/p6/Kostenminimal2.txt");

        long startTime = System.currentTimeMillis();
        System.out.println("Starting CycleCancelling");

        double cost = CycleCancelling.getMinimalCostFlow(graph);

        long estimatedTime = System.currentTimeMillis() - startTime;
        System.out.println("Took " + estimatedTime + " ms\nor " + estimatedTime / 1000.0 + " seconds.");
    }

    @Test
    public void test_cc_Kostenminimal3() {
        Graph graph = importGraphWithBalance("src/main/resources/p6/Kostenminimal3.txt");

        long startTime = System.currentTimeMillis();
        System.out.println("Starting CycleCancelling");

        double cost = CycleCancelling.getMinimalCostFlow(graph);

        long estimatedTime = System.currentTimeMillis() - startTime;
        System.out.println("Took " + estimatedTime + " ms\nor " + estimatedTime / 1000.0 + " seconds.");

        assertEquals(1537.0, cost, 0.0);
    }

    @Test
    public void test_cc_Kostenminimal4() {
        Graph graph = importGraphWithBalance("src/main/resources/p6/Kostenminimal4.txt");

        long startTime = System.currentTimeMillis();
        System.out.println("Starting CycleCancelling");

        double cost = CycleCancelling.getMinimalCostFlow(graph);

        long estimatedTime = System.currentTimeMillis() - startTime;
        System.out.println("Took " + estimatedTime + " ms\nor " + estimatedTime / 1000.0 + " seconds.");

        assertEquals(0.0, cost, 0.0);
    }

    @Test
    public void test_cc_Kostenminimal5() {
        Graph graph = importGraphWithBalance("src/main/resources/p6/Kostenminimal5.txt");

        long startTime = System.currentTimeMillis();
        System.out.println("Starting CycleCancelling");

        double cost = CycleCancelling.getMinimalCostFlow(graph);

        long estimatedTime = System.currentTimeMillis() - startTime;
        System.out.println("Took " + estimatedTime + " ms\nor " + estimatedTime / 1000.0 + " seconds.");

        assertEquals(0.0, cost, 0.0);
    }
}
