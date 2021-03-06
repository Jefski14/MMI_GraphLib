package algorithms.P5_Max_Flow;

import entity.Graph;
import org.junit.Test;

import static helper.GraphParser.importGraphFromFile;
import static org.junit.Assert.assertEquals;

public class EdmondsKarpTest {

    @Test
    public void test_EdmondsKarp_Fluss() {
        Graph graph = importGraphFromFile("src/main/resources/p5/Fluss.txt", true, true);

        long startTime = System.currentTimeMillis();
        System.out.println("Starting Edmonds-Karp");

        double max_flow = EdmondsKarp.runEdmondsKarp(graph, 0, 7).max_flow;

        long estimatedTime = System.currentTimeMillis() - startTime;
        System.out.println("Took " + estimatedTime + " ms\nor " + estimatedTime / 1000.0 + " seconds.");

        assertEquals(4, max_flow, 0.01);
    }

    @Test
    public void test_EdmondsKarp_Fluss2() {
        Graph graph = importGraphFromFile("src/main/resources/p5/Fluss2.txt", true, true);

        long startTime = System.currentTimeMillis();
        System.out.println("Starting Edmonds-Karp");

        double max_flow = EdmondsKarp.runEdmondsKarp(graph, 0, 7).max_flow;

        long estimatedTime = System.currentTimeMillis() - startTime;
        System.out.println("Took " + estimatedTime + " ms\nor " + estimatedTime / 1000.0 + " seconds.");

        assertEquals(5, max_flow, 0.01);
    }

    @Test
    public void test_EdmondsKarp_G_1_2() {
        Graph graph = importGraphFromFile("src/main/resources/p2/G_1_2.txt", true, true);

        long startTime = System.currentTimeMillis();
        System.out.println("Starting Edmonds-Karp");

        double max_flow = EdmondsKarp.runEdmondsKarp(graph, 0, 7).max_flow;

        long estimatedTime = System.currentTimeMillis() - startTime;
        System.out.println("Took " + estimatedTime + " ms\nor " + estimatedTime / 1000.0 + " seconds.");

        assertEquals(0.735802, max_flow, 0.01);
    }

}