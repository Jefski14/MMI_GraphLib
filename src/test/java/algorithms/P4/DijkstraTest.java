package algorithms.P4;

import entity.Edge;
import entity.Graph;
import entity.PredAndDist;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Map;

import static helper.GraphParser.importGraphFromFile;

public class DijkstraTest {

    @Test
    public void W1_KWB_Start_2() {
        Graph graph = importGraphFromFile("src/main/resources/p4/Wege1.txt", true);
        long startTime = System.currentTimeMillis();
        System.out.println("Starting Dijkstra");
        Map<Integer, PredAndDist> predAndDistMap = Dijkstra.calculateShortestPaths(graph, graph.getVertexList().get(2));
        ArrayList<Edge> edges = graph.buildPathFromTo(predAndDistMap, 2, 0);
        long estimatedTime = System.currentTimeMillis() - startTime;
        System.out.println("Took " + estimatedTime + " ms\nor " + estimatedTime / 1000.0 + " seconds.");

        double totalCost = calculateTotalCost(edges);
        Assert.assertEquals(6.0, totalCost, 0.001);
    }

    @Test
    @Ignore
    public void W1_KWB_Start_0() {
        Graph graph = importGraphFromFile("src/main/resources/p4/Wege1.txt", true);
        long startTime = System.currentTimeMillis();
        System.out.println("Starting Dijkstra");
        Map<Integer, PredAndDist> predAndDistMap = Dijkstra.calculateShortestPaths(graph, graph.getVertexList().get(0));
        ArrayList<Edge> edges = graph.buildPathFromTo(predAndDistMap, 0, 2);
        long estimatedTime = System.currentTimeMillis() - startTime;
        System.out.println("Took" + estimatedTime + "ms\n or " + estimatedTime / 1000.0 + "seconds.");

        double totalCost = calculateTotalCost(edges);
        Assert.assertEquals(6.0, totalCost, 0.001);
    }

    private double calculateTotalCost(ArrayList<Edge> edges) {
        double totalCost = 0.0;
        for (Edge e : edges) {
            totalCost += e.getCost();
            System.out.println(String.format("Edge from %s to %s with cost of %f", e.getStart(), e.getEnd(), e.getCost()));
        }
        System.out.println("Total cost of " + totalCost);
        return totalCost;
    }
}