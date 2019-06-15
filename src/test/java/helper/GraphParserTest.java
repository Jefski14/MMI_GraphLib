package helper;

import entity.Graph;
import org.junit.Test;

import static helper.GraphParser.importGraphFromFile;
import static helper.GraphParser.importGraphWithBalance;
import static org.junit.Assert.assertEquals;

public class GraphParserTest {
    @Test
    public void parseG1() {
        final String path = "src/main/resources/p1/Graph1.txt";
        final Graph graph = importGraphFromFile(path, false, false);

        assertEquals(graph.getVertexList().size(), 15);
        assertEquals(graph.getVertexList().get(0).getAttachedEdges().size(), 3);
        assertEquals(graph.getVertexList().get(0).getAttachedEdges().get(0).getStart(), graph.getVertexList().get(0));
        assertEquals(graph.getVertexList().get(0).getAttachedEdges().get(0).getEnd(), graph.getVertexList().get(6));
    }

    @Test
    public void parseG2() {
        final String path = "src/main/resources/p1/Graph2.txt";
        final Graph graph = importGraphFromFile(path, false, false);

        assertEquals(graph.getVertexList().size(), 1000);
        assertEquals(graph.getVertexList().get(41).getAttachedEdges().size(), 8);
        assertEquals(graph.getVertexList().get(41).getAttachedEdges().get(0).getStart(), graph.getVertexList().get(41));
        assertEquals(graph.getVertexList().get(41).getAttachedEdges().get(0).getEnd(), graph.getVertexList().get(467));
    }

    @Test
    public void parseGBig() {
        final String path = "src/main/resources/p1/Graph_gross.txt";
        final long startTime = System.currentTimeMillis();
        final Graph graph = importGraphFromFile(path, false, false);
        final long estimatedTime = System.currentTimeMillis() - startTime;


        assertEquals(graph.getVertexList().size(), 100000);
    }

    @Test
    public void parseGBigAsMap() {
        final String path = "src/main/resources/p1/Graph_gross.txt";
        final long startTime = System.currentTimeMillis();
        final Graph graph = importGraphFromFile(path, false, false);
        final long estimatedTime = System.currentTimeMillis() - startTime;

        assertEquals(graph.getVertexList().size(), 100000);
    }

    @Test
    public void parseG_1_2() {
        final String path = "src/main/resources/p2/G_1_2.txt";
        final Graph graph = importGraphFromFile(path, false, false);
        assertEquals(graph.getEdgeList().size(), 4000);
        assertEquals(graph.getVertexList().size(), 1000);
    }

    @Test
    public void parseKostenminimal1() {
        final String path = "src/main/resources/p6/Kostenminimal1.txt";
        final Graph graph = importGraphWithBalance(path);
        assertEquals(graph.getEdgeList().size(), 9);
        assertEquals(graph.getVertexList().size(), 7);
        assertEquals(graph.getVertexList().get(5).getBalance(), 6.0, 0.0); // Pseudo Quelle Balance = 6
        assertEquals(graph.getVertexList().get(6).getBalance(), -6.0, 0.0); // Pseudo Quelle Balance = 6
    }
}
