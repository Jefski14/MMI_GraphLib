package entity;

import helper.GraphParser;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class GraphParserTest {
    @Test
    public void parseG1() {
        final String path = "src/main/resources/p1/Graph1.txt";
        final List<Vertex> vertList = new GraphParser().readUndirectedEdgeListFromFile(path);

        assertEquals(vertList.size(), 15);
        assertEquals(vertList.get(0).getAttachedEdges().size(), 3);
        assertEquals(vertList.get(0).getAttachedEdges().get(0).getStart(), vertList.get(0));
        assertEquals(vertList.get(0).getAttachedEdges().get(0).getEnd(), vertList.get(6));
    }

    @Test
    public void parseG2() {
        final String path = "src/main/resources/p1/Graph2.txt";
        final List<Vertex> vertList = new GraphParser().readUndirectedEdgeListFromFile(path);

        assertEquals(vertList.size(), 1000);
        assertEquals(vertList.get(41).getAttachedEdges().size(), 8);
        assertEquals(vertList.get(41).getAttachedEdges().get(0).getStart(), vertList.get(41));
        assertEquals(vertList.get(41).getAttachedEdges().get(0).getEnd(), vertList.get(467));
    }

    @Test
    public void parseGBig() {
        final String path = "src/main/resources/p1/Graph_gross.txt";
        final List<Vertex> vertList = new GraphParser().readUndirectedEdgeListFromFile(path);

        assertEquals(vertList.size(), 100000);
    }
}