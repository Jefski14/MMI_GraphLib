package algorithms.P5_Max_Flow;

import entity.Graph;
import org.junit.Test;

import static helper.GraphParser.importGraphFromFile;
import static org.junit.Assert.assertEquals;

public class FordFulkersonTest {

    @Test
    public void test_FordFulkerson_Fluss() {
        Graph graph = importGraphFromFile("src/main/resources/p5/Fluss.txt", true, true);

        long startTime = System.currentTimeMillis();
        System.out.println("Starting Ford Fulkerson");

        double max_flow = FordFulkerson.runFordFulkerson(graph, 0, 7);

        long estimatedTime = System.currentTimeMillis() - startTime;
        System.out.println("Took " + estimatedTime + " ms\nor " + estimatedTime / 1000.0 + " seconds.");

        assertEquals(4, max_flow, 0.01);
    }

    @Test
    public void test_FordFulkerson_Fluss2() {
        Graph graph = importGraphFromFile("src/main/resources/p5/Fluss.txt", true, true);

        long startTime = System.currentTimeMillis();
        System.out.println("Starting Ford Fulkerson");

        double max_flow = FordFulkerson.runFordFulkerson(graph, 0, 7);

        long estimatedTime = System.currentTimeMillis() - startTime;
        System.out.println("Took " + estimatedTime + " ms\nor " + estimatedTime / 1000.0 + " seconds.");

        assertEquals(5, max_flow, 0.01);
    }

}