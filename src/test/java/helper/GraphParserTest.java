package helper;

import entity.Graph;
import org.junit.Test;

import static helper.GraphParser.importGraphFromFile;
import static org.junit.Assert.assertEquals;

public class GraphParserTest {
    @Test
    public void parseG1() {
        final String path = "src/main/resources/p1/Graph1.txt";
        final Graph graph = importGraphFromFile(path);

        assertEquals(graph.getVertexList().size(), 15);
        assertEquals(graph.getVertexList().get(0).getAttachedEdges().size(), 3);
        assertEquals(graph.getVertexList().get(0).getAttachedEdges().get(0).getStart(), graph.getVertexList().get(0));
        assertEquals(graph.getVertexList().get(0).getAttachedEdges().get(0).getEnd(), graph.getVertexList().get(6));
    }

    @Test
    public void parseG2() {
        final String path = "src/main/resources/p1/Graph2.txt";
        final Graph graph = importGraphFromFile(path);

        assertEquals(graph.getVertexList().size(), 1000);
        assertEquals(graph.getVertexList().get(41).getAttachedEdges().size(), 8);
        assertEquals(graph.getVertexList().get(41).getAttachedEdges().get(0).getStart(), graph.getVertexList().get(41));
        assertEquals(graph.getVertexList().get(41).getAttachedEdges().get(0).getEnd(), graph.getVertexList().get(467));
    }

    @Test
    public void parseGBig() {
        final String path = "src/main/resources/p1/Graph_gross.txt";
        final long startTime = System.currentTimeMillis();
        final Graph graph = importGraphFromFile(path);
        final long estimatedTime = System.currentTimeMillis() - startTime;


        assertEquals(graph.getVertexList().size(), 100000);
    }

    @Test
    public void parseGBigAsMap() {
        final String path = "src/main/resources/p1/Graph_gross.txt";
        final long startTime = System.currentTimeMillis();
        final Graph graph = importGraphFromFile(path);
        final long estimatedTime = System.currentTimeMillis() - startTime;

        assertEquals(graph.getVertexList().size(), 100000);
    }

    @Test
    public void parseG_1_2() {
        final String path = "src/main/resources/p2/G_1_2.txt";
        final Graph graph = importGraphFromFile(path);
        assertEquals(graph.getEdgeList().size(), 4000);
        assertEquals(graph.getVertexList().size(), 1000);
    }
}
