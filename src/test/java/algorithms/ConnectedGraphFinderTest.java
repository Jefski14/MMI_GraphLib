package algorithms;

import entity.Graph;
import org.junit.Test;

import static algorithms.ConnectedGraphFinder.findConnectedGraphs;
import static helper.GraphParser.importGraphFromFile;
import static org.junit.Assert.assertEquals;

public class ConnectedGraphFinderTest {
    @Test
    public void connectedGraphG1Test() {
        final String path = "src/main/resources/p1/Graph1.txt";
        final boolean directed = false;

        final Graph graph = importGraphFromFile(path);
        final int connectedGraphCount = findConnectedGraphs(graph.getVertexList(), directed);

        assertEquals(connectedGraphCount, 2);
    }

    @Test
    public void connectedGraphG2Test() {
        final String path = "src/main/resources/p1/Graph2.txt";
        final boolean directed = false;

        final Graph graph = importGraphFromFile(path);
        final int connectedGraphCount = findConnectedGraphs(graph.getVertexList(), directed);

        assertEquals(connectedGraphCount, 4);
    }

    @Test
    public void connectedGraphG3Test() {
        final String path = "src/main/resources/p1/Graph3.txt";
        final boolean directed = false;

        final Graph graph = importGraphFromFile(path);
        final int connectedGraphCount = findConnectedGraphs(graph.getVertexList(), directed);

        assertEquals(connectedGraphCount, 4);
    }

    @Test
    public void connectedGraphGGrossTest() {
        final String path = "src/main/resources/p1/Graph_gross.txt";
        final boolean directed = false;

        final Graph graph = importGraphFromFile(path);
        final int connectedGraphCount = findConnectedGraphs(graph.getVertexList(), directed);

        assertEquals(connectedGraphCount, 222);
    }
}
