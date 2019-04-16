package algorithms;

import entity.Edge;
import entity.Vertex;
import helper.GraphParser;
import org.junit.Test;

import java.io.File;
import java.net.URL;
import java.util.List;

import static org.junit.Assert.assertFalse;

public class KruskalMSTTest {

    @Test
    public void testSortingEdgeList() {

        URL url = Thread.currentThread().getContextClassLoader().getResource("p2/G_1_2.txt");
        File file = new File(url.getPath());
        List<Vertex> vertices = GraphParser.importGraphFromFile(file.getAbsolutePath(), false);


        List<Edge> sortedEdgeList = KruskalMST.getSortedEdgeListByCost(vertices);

        Edge lastEdge = sortedEdgeList.get(0);
        boolean first = true;
        boolean unsorted = false;
        for (Edge e : sortedEdgeList) {
            if (first) {
                first = false;
            } else {
                if (e.getCost() >= lastEdge.getCost()) {
                    lastEdge = e;
                } else {
                    unsorted = true;
                    break;
                }
            }
        }

        assertFalse(unsorted);
    }

}