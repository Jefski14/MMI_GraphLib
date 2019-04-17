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
    public void PrimMSTG1Test() {
        Graph graph = importGraphFromFileAsEdgeList("src/main/resources/p2/G_1_2.txt", false);
        Graph mst = getMST(graph, graph.getVertexList().get(0));
        assertEquals(mst.totalEdgeCost(), 286.711,0.001);
    }
}
