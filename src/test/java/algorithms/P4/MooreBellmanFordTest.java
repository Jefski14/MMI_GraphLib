package algorithms.P4;

import entity.Graph;
import org.junit.Test;

import static helper.GraphParser.importGraphFromFile;

public class MooreBellmanFordTest {

    @Test
    public void K10() {
        Graph graph = importGraphFromFile("src/main/resources/p4/Wege1.txt", true);
        long startTime = System.currentTimeMillis();
        System.out.println("Starting MBF");
        Graph tsp = MooreBellmanFord.findKWB(graph, graph.getVertexList().get(2));
        long estimatedTime = System.currentTimeMillis() - startTime;
        System.out.println("Took" + estimatedTime + "ms\n or " + estimatedTime/1000.0 + "seconds.");
    }
}
