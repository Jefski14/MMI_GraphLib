package algorithms;

import entity.Graph;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static algorithms.P2.PrimMST.getMST;
import static helper.GraphParser.importGraphFromFile;
import static org.junit.Assert.assertEquals;

public class PrimMSTTest {

    @Test
    public void PrimMSTG1_2Test() {
        Graph graph = importGraphFromFile("src/main/resources/p2/G_1_2.txt", false);
        Graph mst = getMST(graph, graph.getVertexList().get(0));
        assertEquals(BigDecimal.valueOf(286.711), mst.totalEdgeCost().setScale(3, RoundingMode.HALF_UP));
    }

    @Test
    public void PrimMSTG1_20Test() {
        Graph graph = importGraphFromFile("src/main/resources/p2/G_1_20.txt", false);
        Graph mst = getMST(graph, graph.getVertexList().get(0));
        assertEquals(BigDecimal.valueOf(29.5493), mst.totalEdgeCost().setScale(4, RoundingMode.HALF_UP));
    }

    @Test
    public void PrimMSTG1_200Test() {
        Graph graph = importGraphFromFile("src/main/resources/p2/G_1_200.txt", false);
        Graph mst = getMST(graph, graph.getVertexList().get(0));
        assertEquals(BigDecimal.valueOf(3.0228), mst.totalEdgeCost().setScale(4, RoundingMode.HALF_UP));
    }

    @Test
    public void PrimMSTG10_20Test() {
        Graph graph = importGraphFromFile("src/main/resources/p2/G_10_20.txt", false);
        Graph mst = getMST(graph, graph.getVertexList().get(0));
        assertEquals(BigDecimal.valueOf(2775.44), mst.totalEdgeCost().setScale(2, RoundingMode.HALF_UP));
    }

    @Test
    public void PrimMSTG10_200Test() {
        Graph graph = importGraphFromFile("src/main/resources/p2/G_10_200.txt", false);
        Graph mst = getMST(graph, graph.getVertexList().get(0));
        assertEquals(BigDecimal.valueOf(301.552), mst.totalEdgeCost().setScale(3, RoundingMode.HALF_UP));
    }

    @Test
    public void PrimMSTG100_200Test() {
        Graph graph = importGraphFromFile("src/main/resources/p2/G_100_200.txt", false);
        Graph mst = getMST(graph, graph.getVertexList().get(0));
        assertEquals(BigDecimal.valueOf(27450.6), mst.totalEdgeCost().setScale(1, RoundingMode.HALF_UP));
    }
}
