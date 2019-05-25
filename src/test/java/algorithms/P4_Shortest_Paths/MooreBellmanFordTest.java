package algorithms.P4_Shortest_Paths;

import entity.Edge;
import entity.Graph;
import org.junit.Test;

import java.util.ArrayList;

import static entity.Graph.totalEdgeCost;
import static helper.GraphParser.importGraphFromFile;
import static helper.printFunctions.printPathInReverseWithCost;
import static junit.framework.TestCase.assertEquals;

public class MooreBellmanFordTest {

    @Test
    public void W1_KWB_2() {
        Graph graph = importGraphFromFile("src/main/resources/p4/Wege1.txt", true, false);
        long startTime = System.currentTimeMillis();
        System.out.println("Starting MBF");
        Graph kwb = MooreBellmanFord.findKWB(graph, graph.getVertexList().get(2));
        long estimatedTime = System.currentTimeMillis() - startTime;
        System.out.println("Took" + estimatedTime + "ms\n or " + estimatedTime/1000.0 + "seconds.");
    }

    @Test
    public void W1_2to0() {
        Graph graph = importGraphFromFile("src/main/resources/p4/Wege1.txt", true, false);
        long startTime = System.currentTimeMillis();
        System.out.println("Starting MBF");
        ArrayList<Edge> kw = MooreBellmanFord.getShortestPath(graph, graph.getVertexList().get(2), graph.getVertexList().get(0));
        printPathInReverseWithCost(kw);
        assertEquals(totalEdgeCost(kw), 6.0);
        long estimatedTime = System.currentTimeMillis() - startTime;
        System.out.println("Took " + estimatedTime + "ms\n or " + estimatedTime/1000.0 + "seconds.");
    }

    @Test
    public void W2_2to0() {
        Graph graph = importGraphFromFile("src/main/resources/p4/Wege2.txt", true, false);
        long startTime = System.currentTimeMillis();
        System.out.println("Starting MBF");
        ArrayList<Edge> kw = MooreBellmanFord.getShortestPath(graph, graph.getVertexList().get(2), graph.getVertexList().get(0));
        printPathInReverseWithCost(kw);
        assertEquals(totalEdgeCost(kw), 2.0);
        long estimatedTime = System.currentTimeMillis() - startTime;
        System.out.println("Took " + estimatedTime + "ms\n or " + estimatedTime/1000.0 + "seconds.");
    }

    @Test(expected = NegativeCyclesException.class)
    public void W3_KWB_0() {
        Graph graph = importGraphFromFile("src/main/resources/p4/Wege3.txt", true, false);
        long startTime = System.currentTimeMillis();
        System.out.println("Starting MBF");
        Graph tsp = MooreBellmanFord.findKWB(graph, graph.getVertexList().get(0));
        // Runs until here
        long estimatedTime = System.currentTimeMillis() - startTime;
        System.out.println("Took " + estimatedTime + "ms\n or " + estimatedTime/1000.0 + "seconds.");
    }

    @Test
    public void G1_2_0to1_directed() {
        Graph graph = importGraphFromFile("src/main/resources/p2/G_1_2.txt", true, false);
        long startTime = System.currentTimeMillis();
        System.out.println("Starting MBF");
        ArrayList<Edge> kw = MooreBellmanFord.getShortestPath(graph, graph.getVertexList().get(0), graph.getVertexList().get(1));
        printPathInReverseWithCost(kw);
        assertEquals(totalEdgeCost(kw), 5.54417, 0.00001);
        long estimatedTime = System.currentTimeMillis() - startTime;
        System.out.println("Took" + estimatedTime + "ms\n or " + estimatedTime/1000.0 + "seconds.");
    }

    @Test
    public void G1_2_0to1_undirected() {
        Graph graph = importGraphFromFile("src/main/resources/p2/G_1_2.txt", false, false);
        long startTime = System.currentTimeMillis();
        System.out.println("Starting MBF");
        ArrayList<Edge> kw = MooreBellmanFord.getShortestPath(graph, graph.getVertexList().get(0), graph.getVertexList().get(1));
        printPathInReverseWithCost(kw);
        assertEquals(totalEdgeCost(kw), 2.36796, 0.00001);
        long estimatedTime = System.currentTimeMillis() - startTime;
        System.out.println("Took" + estimatedTime + "ms\n or " + estimatedTime/1000.0 + "seconds.");
    }


}
