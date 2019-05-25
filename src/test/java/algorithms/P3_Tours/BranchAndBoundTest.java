package algorithms.P3_Tours;

import entity.Graph;
import org.junit.Test;

import static helper.GraphParser.importGraphFromFile;
import static org.junit.Assert.assertEquals;

public class BranchAndBoundTest {

    @Test
    public void K10() {
        Graph graph = importGraphFromFile("src/main/resources/p3/K_10.txt", false, false);
        long startTime = System.currentTimeMillis();
        System.out.println("Starting AirBnB");
        Graph tsp = BranchAndBound.calculateTour(graph);
        long estimatedTime = System.currentTimeMillis() - startTime;
        System.out.println("BnB took: " + estimatedTime + "ms\n or " + estimatedTime/1000.0 + "seconds. Cheapest Path has cost: " + tsp.totalEdgeCost() + "\nWith Path:\n" + tsp.getEdgeList().toString());
        assertEquals(38.41, tsp.totalEdgeCost(), 0.0001);
    }

    @Test
    public void K10e() {
        Graph graph = importGraphFromFile("src/main/resources/p3/K_10e.txt", false, false);
        long startTime = System.currentTimeMillis();
        System.out.println("Starting BrunchAndBreakfast");
        Graph tsp = BranchAndBound.calculateTour(graph);
        long estimatedTime = System.currentTimeMillis() - startTime;
        System.out.println("BnB took: " + estimatedTime + "ms\n or " + estimatedTime/1000.0 + "seconds. Cheapest Path has cost: " + tsp.totalEdgeCost() + "\nWith Path:\n" + tsp.getEdgeList().toString());
        assertEquals(27.26, tsp.totalEdgeCost(), 0.0001);
    }

    @Test
    public void K12() {
        Graph graph = importGraphFromFile("src/main/resources/p3/K_12.txt", false, false);
        long startTime = System.currentTimeMillis();
        System.out.println("Starting BreakpointAtBulletpoint");
        Graph tsp = BranchAndBound.calculateTour(graph);
        long estimatedTime = System.currentTimeMillis() - startTime;
        System.out.println("BnB took: " + estimatedTime + "ms\n or " + estimatedTime/1000.0 + "seconds. Cheapest Path has cost: " + tsp.totalEdgeCost() + "\nWith Path:\n" + tsp.getEdgeList().toString());
        assertEquals(45.19, tsp.totalEdgeCost(), 0.0001);
    }

    @Test
    public void K12e() {
        Graph graph = importGraphFromFile("src/main/resources/p3/K_12e.txt", false, false);
        long startTime = System.currentTimeMillis();
        System.out.println("Starting BranchesAndLeaves (Sry no more creativity here...)");
        Graph tsp = BranchAndBound.calculateTour(graph);
        long estimatedTime = System.currentTimeMillis() - startTime;
        System.out.println("BnB took: " + estimatedTime + "ms\n or " + estimatedTime/1000.0 + " seconds. Cheapest Path has cost: " + tsp.totalEdgeCost() + "\nWith Path:\n" + tsp.getEdgeList().toString());
        assertEquals(36.13, tsp.totalEdgeCost(), 0.0001);
    }

}