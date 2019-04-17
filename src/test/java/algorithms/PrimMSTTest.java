package algorithms;

import entity.Graph;
import entity.Vertex;
import org.junit.Test;

import java.util.List;

import static algorithms.PrimMST.getMST;
import static helper.GraphParser.importGraphFromFileAsEdgeList;
import static org.junit.Assert.assertEquals;

public class PrimMSTTest {

    @Test
    public void PrimMSTG1_2Test() {
        Graph graph = importGraphFromFileAsEdgeList("src/main/resources/p2/G_1_2.txt", false);
        Graph mst = getMST(graph, graph.getVertexList().get(0));
        assertEquals(286.711, mst.totalEdgeCost(), 0.001);
    }

    @Test
    public void PrimMSTG1_20Test() {
        Graph graph = importGraphFromFileAsEdgeList("src/main/resources/p2/G_1_20.txt", false);
        Graph mst = getMST(graph, graph.getVertexList().get(0));
        assertEquals(29.5493, mst.totalEdgeCost(), 0.001);
    }

    @Test
    public void PrimMSTG1_200Test() {
        Graph graph = importGraphFromFileAsEdgeList("src/main/resources/p2/G_1_200.txt", false);
        Graph mst = getMST(graph, graph.getVertexList().get(0));
        assertEquals(3.0228, mst.totalEdgeCost(),0.001);
    }

    @Test
    public void PrimMSTG10_20Test() {
        Graph graph = importGraphFromFileAsEdgeList("src/main/resources/p2/G_10_20.txt", false);
        Graph mst = getMST(graph, graph.getVertexList().get(0));
        assertEquals(2775.44, mst.totalEdgeCost(),0.0013);
    }

    @Test
    public void PrimMSTG10_200Test() {
        Graph graph = importGraphFromFileAsEdgeList("src/main/resources/p2/G_10_200.txt", false);
        Graph mst = getMST(graph, graph.getVertexList().get(0));
        assertEquals(301.552, mst.totalEdgeCost(), 0.001);
    }

    @Test
    public void PrimMSTG100_200Test() {
        Graph graph = importGraphFromFileAsEdgeList("src/main/resources/p2/G_100_200.txt", false);
        Graph mst = getMST(graph, graph.getVertexList().get(0));
        assertEquals(27450.6, mst.totalEdgeCost(), 0.018);
    }
}
