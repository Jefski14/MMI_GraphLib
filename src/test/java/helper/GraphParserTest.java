package helper;

import entity.Edge;
import entity.Graph;
import entity.Vertex;
import org.junit.Test;

import java.util.List;

import static helper.GraphParser.importGraphFromFile;
import static helper.GraphParser.importGraphFromFileAsEdgeList;
import static org.junit.Assert.assertEquals;

public class GraphParserTest {
    @Test
    public void parseG1() {
        final String path = "src/main/resources/p1/Graph1.txt";
        final List<Vertex> vertList = importGraphFromFile(path, false);

        assertEquals(vertList.size(), 15);
        assertEquals(vertList.get(0).getAttachedEdges().size(), 3);
        assertEquals(vertList.get(0).getAttachedEdges().get(0).getStart(), vertList.get(0));
        assertEquals(vertList.get(0).getAttachedEdges().get(0).getEnd(), vertList.get(6));
    }

    @Test
    public void parseG2() {
        final String path = "src/main/resources/p1/Graph2.txt";
        final List<Vertex> vertList = importGraphFromFile(path, false);

        assertEquals(vertList.size(), 1000);
        assertEquals(vertList.get(41).getAttachedEdges().size(), 8);
        assertEquals(vertList.get(41).getAttachedEdges().get(0).getStart(), vertList.get(41));
        assertEquals(vertList.get(41).getAttachedEdges().get(0).getEnd(), vertList.get(467));
    }

    @Test
    public void parseGBig() {
        final String path = "src/main/resources/p1/Graph_gross.txt";
        final long startTime = System.currentTimeMillis();
        final List<Vertex> vertList = importGraphFromFile(path, false);
        final long estimatedTime = System.currentTimeMillis() - startTime;


        assertEquals(vertList.size(), 100000);
    }

    @Test
    public void parseGBigAsMap() {
        final String path = "src/main/resources/p1/Graph_gross.txt";
        final long startTime = System.currentTimeMillis();
        List<Vertex> vertices = importGraphFromFile(path, false);
        final long estimatedTime = System.currentTimeMillis() - startTime;

        assertEquals(vertices.size(), 100000);
    }

    @Test
    public void parseG_1_2() {
        final String path = "src/main/resources/p2/G_1_2.txt";
        final Graph graph = importGraphFromFileAsEdgeList(path, false);
        assertEquals(graph.getEdgeList().size(), 2000);
        assertEquals(graph.getVertexList().size(), 1000);
    }
}
