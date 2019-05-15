package algorithms.P2;

import algorithms.P2.KruskalMST;
import entity.Graph;
import org.junit.Test;

import static helper.GraphParser.importGraphFromFile;
import static org.junit.Assert.assertEquals;

public class KruskalMSTTest {

    private KruskalMST kruskal = new KruskalMST();

    @Test
    public void KruskalMSTG1_2Test() {
        Graph graph = importGraphFromFile("src/main/resources/p2/G_1_2.txt", false);
        Graph mst = kruskal.getMST(graph);
        assertEquals(286.711, mst.totalEdgeCost(), 0.001);
    }

    @Test
    public void KruskalMSTG1_20Test() {
        Graph graph = importGraphFromFile("src/main/resources/p2/G_1_20.txt", false);
        Graph mst = kruskal.getMST(graph);
        assertEquals(29.5493, mst.totalEdgeCost(), 0.001);
    }

    @Test
    public void KruskalMSTG1_200Test() {
        Graph graph = importGraphFromFile("src/main/resources/p2/G_1_200.txt", false);
        Graph mst = kruskal.getMST(graph);
        assertEquals(3.0228, mst.totalEdgeCost(), 0.001);
    }

    @Test
    public void KruskalMSTG10_20Test() {
        Graph graph = importGraphFromFile("src/main/resources/p2/G_10_20.txt", false);
        Graph mst = kruskal.getMST(graph);
        assertEquals(2775.44, mst.totalEdgeCost(), 0.0013);
    }

    @Test
    public void KruskalMSTG10_200Test() {
        Graph graph = importGraphFromFile("src/main/resources/p2/G_10_200.txt", false);
        Graph mst = kruskal.getMST(graph);
        assertEquals(301.552, mst.totalEdgeCost(), 0.001);
    }

    @Test
    public void KruskalMSTG100_200Test() {
        Graph graph = importGraphFromFile("src/main/resources/p2/G_100_200.txt", false);
        Graph mst = kruskal.getMST(graph);
        assertEquals(27450.6, mst.totalEdgeCost(), 0.018);
    }

}