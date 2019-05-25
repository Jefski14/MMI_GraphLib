package algorithms.P3_Tours;

import entity.Graph;
import org.junit.Test;

import static algorithms.P3_Tours.DoubleTrees.calculateTour;
import static helper.GraphParser.importGraphFromFile;

public class DoubleTreesTest {

    @Test
    public void K_10Test() {
        Graph graph = importGraphFromFile("src/main/resources/p3/K_10.txt", false);
        long startTime = System.currentTimeMillis();
        System.out.println("Starting DoubleTrees");
        Graph tsp = calculateTour(graph); // NN von 9 liefert optimale Tour
        long estimatedTime = System.currentTimeMillis() - startTime;
        System.out.println("DT took: " + estimatedTime + "ms");
        System.out.println(tsp.getEdgeList().toString());
        System.out.println("Total Path Cost: " + tsp.totalEdgeCost());
    }

    @Test
    public void K_10eTest() {
        Graph graph = importGraphFromFile("src/main/resources/p3/K_10e.txt", false);
        long startTime = System.currentTimeMillis();
        System.out.println("Starting DoubleTrees");
        Graph tsp = calculateTour(graph); // NN von 9 liefert optimale Tour
        long estimatedTime = System.currentTimeMillis() - startTime;
        System.out.println("DT took: " + estimatedTime + "ms");
        System.out.println(tsp.getEdgeList().toString());
        System.out.println("Total Path Cost: " + tsp.totalEdgeCost());
    }

    @Test
    public void K_12Test() {
        Graph graph = importGraphFromFile("src/main/resources/p3/K_12.txt", false);
        long startTime = System.currentTimeMillis();
        System.out.println("Starting DoubleTrees");
        Graph tsp = calculateTour(graph); // NN von 9 liefert optimale Tour
        long estimatedTime = System.currentTimeMillis() - startTime;
        System.out.println("DT took: " + estimatedTime + "ms");
        System.out.println(tsp.getEdgeList().toString());
        System.out.println("Total Path Cost: " + tsp.totalEdgeCost());
    }

    @Test
    public void K_12eTest() {
        Graph graph = importGraphFromFile("src/main/resources/p3/K_12e.txt", false);
        long startTime = System.currentTimeMillis();
        System.out.println("Starting DoubleTrees");
        Graph tsp = calculateTour(graph); // NN von 9 liefert optimale Tour
        long estimatedTime = System.currentTimeMillis() - startTime;
        System.out.println("DT took: " + estimatedTime + "ms");
        System.out.println(tsp.getEdgeList().toString());
        System.out.println("Total Path Cost: " + tsp.totalEdgeCost());
    }

    @Test
    public void K_15Test() {
        Graph graph = importGraphFromFile("src/main/resources/p3/K_15.txt", false);
        long startTime = System.currentTimeMillis();
        System.out.println("Starting DoubleTrees");
        Graph tsp = calculateTour(graph); // NN von 9 liefert optimale Tour
        long estimatedTime = System.currentTimeMillis() - startTime;
        System.out.println("DT took: " + estimatedTime + "ms");
        System.out.println(tsp.getEdgeList().toString());
        System.out.println("Total Path Cost: " + tsp.totalEdgeCost());
    }

    @Test
    public void K_15eTest() {
        Graph graph = importGraphFromFile("src/main/resources/p3/K_15e.txt", false);
        long startTime = System.currentTimeMillis();
        System.out.println("Starting DoubleTrees");
        Graph tsp = calculateTour(graph); // NN von 9 liefert optimale Tour
        long estimatedTime = System.currentTimeMillis() - startTime;
        System.out.println("DT took: " + estimatedTime + "ms");
        System.out.println(tsp.getEdgeList().toString());
        System.out.println("Total Path Cost: " + tsp.totalEdgeCost());
    }

    @Test
    public void K_20Test() {
        Graph graph = importGraphFromFile("src/main/resources/p3/K_20.txt", false);
        long startTime = System.currentTimeMillis();
        System.out.println("Starting DoubleTrees");
        Graph tsp = calculateTour(graph); // NN von 9 liefert optimale Tour
        long estimatedTime = System.currentTimeMillis() - startTime;
        System.out.println("DT took: " + estimatedTime + "ms");
        System.out.println(tsp.getEdgeList().toString());
        System.out.println("Total Path Cost: " + tsp.totalEdgeCost());
    }

    @Test
    public void K_30Test() {
        Graph graph = importGraphFromFile("src/main/resources/p3/K_30.txt", false);
        long startTime = System.currentTimeMillis();
        System.out.println("Starting DoubleTrees");
        Graph tsp = calculateTour(graph); // NN von 9 liefert optimale Tour
        long estimatedTime = System.currentTimeMillis() - startTime;
        System.out.println("DT took: " + estimatedTime + "ms");
        System.out.println(tsp.getEdgeList().toString());
        System.out.println("Total Path Cost: " + tsp.totalEdgeCost());
    }

    @Test
    public void K_50Test() {
        Graph graph = importGraphFromFile("src/main/resources/p3/K_50.txt", false);
        long startTime = System.currentTimeMillis();
        System.out.println("Starting DoubleTrees");
        Graph tsp = calculateTour(graph); // NN von 9 liefert optimale Tour
        long estimatedTime = System.currentTimeMillis() - startTime;
        System.out.println("DT took: " + estimatedTime + "ms");
        System.out.println(tsp.getEdgeList().toString());
        System.out.println("Total Path Cost: " + tsp.totalEdgeCost());
    }

    @Test
    public void K_70Test() {
        Graph graph = importGraphFromFile("src/main/resources/p3/K_70.txt", false);
        long startTime = System.currentTimeMillis();
        System.out.println("Starting DoubleTrees");
        Graph tsp = calculateTour(graph); // NN von 9 liefert optimale Tour
        long estimatedTime = System.currentTimeMillis() - startTime;
        System.out.println("DT took: " + estimatedTime + "ms");
        System.out.println(tsp.getEdgeList().toString());
        System.out.println("Total Path Cost: " + tsp.totalEdgeCost());
    }

    @Test
    public void K_100Test() {
        Graph graph = importGraphFromFile("src/main/resources/p3/K_100.txt", false);
        long startTime = System.currentTimeMillis();
        System.out.println("Starting DoubleTrees");
        Graph tsp = calculateTour(graph); // NN von 9 liefert optimale Tour
        long estimatedTime = System.currentTimeMillis() - startTime;
        System.out.println("DT took: " + estimatedTime + "ms");
        System.out.println(tsp.getEdgeList().toString());
        System.out.println("Total Path Cost: " + tsp.totalEdgeCost());
    }
}
