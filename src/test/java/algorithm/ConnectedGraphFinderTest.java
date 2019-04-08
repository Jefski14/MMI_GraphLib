package algorithm;

import entity.Vertex;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static algorithms.ConnectedGraphFinder.findConnectedGraphs;
import static algorithms.Search.breadthFirstSearch;
import static helper.GraphParser.readEdgeListFromFile;
import static org.junit.Assert.assertEquals;

public class ConnectedGraphFinderTest {
    @Test
    public void connectedGraphG1Test() {
        final String path = "src/main/resources/p1/Graph1.txt";
        final boolean directed = false;

        final List<Vertex> vertList = readEdgeListFromFile(path, directed);
        final int connectedGraphCount = findConnectedGraphs(vertList, directed);

        assertEquals(connectedGraphCount, 2);
    }

    @Test
    public void connectedGraphG2Test() {
        final String path = "src/main/resources/p1/Graph2.txt";
        final boolean directed = false;

        final List<Vertex> vertList = readEdgeListFromFile(path, directed);
        final int connectedGraphCount = findConnectedGraphs(vertList, directed);

        assertEquals(connectedGraphCount, 4);
    }

    @Test
    public void connectedGraphG3Test() {
        final String path = "src/main/resources/p1/Graph3.txt";
        final boolean directed = false;

        final List<Vertex> vertList = readEdgeListFromFile(path, directed);
        final int connectedGraphCount = findConnectedGraphs(vertList, directed);

        assertEquals(connectedGraphCount, 4);
    }

    @Test
    public void connectedGraphGGrossTest() {
        final String path = "src/main/resources/p1/Graph_gross.txt";
        final boolean directed = false;

        final List<Vertex> vertList = readEdgeListFromFile(path, directed);
        final int connectedGraphCount = findConnectedGraphs(vertList, directed);

        assertEquals(connectedGraphCount, 222);
    }
}
