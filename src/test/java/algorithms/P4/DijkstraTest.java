package algorithms.P4;

import entity.Edge;
import entity.Graph;
import org.junit.Test;

import java.util.ArrayList;

import static helper.GraphParser.importGraphFromFile;

public class DijkstraTest {

    @Test
    public void W1_KWB_2() {
        Graph graph = importGraphFromFile("src/main/resources/p4/Wege1.txt", true);
        long startTime = System.currentTimeMillis();
        System.out.println("Starting MBF");
        Graph tsp = MooreBellmanFord.findKWB(graph, graph.getVertexList().get(2));
        long estimatedTime = System.currentTimeMillis() - startTime;
        System.out.println("Took" + estimatedTime + "ms\n or " + estimatedTime / 1000.0 + "seconds.");
    }

    @Test
    public void W3_KWB_0() {
        Graph graph = importGraphFromFile("src/main/resources/p4/Wege1.txt", true);
        long startTime = System.currentTimeMillis();
        System.out.println("Starting MBF");
        Graph tsp = MooreBellmanFord.findKWB(graph, graph.getVertexList().get(0));
        long estimatedTime = System.currentTimeMillis() - startTime;
        System.out.println("Took" + estimatedTime + "ms\n or " + estimatedTime / 1000.0 + "seconds.");
    }

    @Test
    public void W1_2to0() {
        Graph graph = importGraphFromFile("src/main/resources/p4/Wege1.txt", true);
        long startTime = System.currentTimeMillis();
        System.out.println("Starting MBF");
        ArrayList<Edge> kw = MooreBellmanFord.getShortestPath(graph, graph.getVertexList().get(2), graph.getVertexList().get(0));
        long estimatedTime = System.currentTimeMillis() - startTime;
        System.out.println("Took" + estimatedTime + "ms\n or " + estimatedTime / 1000.0 + "seconds.");
    }

    @Test
    public void G1_2_0to1_directed() {
        Graph graph = importGraphFromFile("src/main/resources/p2/G_1_2.txt", true);
        long startTime = System.currentTimeMillis();
        System.out.println("Starting MBF");
        ArrayList<Edge> kw = MooreBellmanFord.getShortestPath(graph, graph.getVertexList().get(0), graph.getVertexList().get(1));
        long estimatedTime = System.currentTimeMillis() - startTime;
        System.out.println("Took" + estimatedTime + "ms\n or " + estimatedTime / 1000.0 + "seconds.");
    }

    @Test
    public void G1_2_0to1_undirected() {
        Graph graph = importGraphFromFile("src/main/resources/p2/G_1_2.txt", false);
        long startTime = System.currentTimeMillis();
        System.out.println("Starting MBF");
        ArrayList<Edge> kw = MooreBellmanFord.getShortestPath(graph, graph.getVertexList().get(0), graph.getVertexList().get(1));
        long estimatedTime = System.currentTimeMillis() - startTime;
        System.out.println("Took" + estimatedTime + "ms\n or " + estimatedTime / 1000.0 + "seconds.");
    }
}