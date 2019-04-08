package algorithm;

import algorithms.BFS;
import entity.Vertex;
import helper.GraphParser;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BFSTest {
    @Test
    public void bfsDirectedTest() {
        final String path = "src/main/resources/p1/Graph1.txt";
        final boolean directed = false;
        final Map<Integer, Boolean> markedMap = new HashMap<Integer, Boolean>();
        final List<Vertex> vertList = new GraphParser().readEdgeListFromFile(path, directed);
        vertList.stream().forEach(v -> markedMap.put(v.getId(), false));
        final BFS search = new BFS();
        search.breadthFirstSearch(vertList.get(0), markedMap, directed);
        
    }
}
