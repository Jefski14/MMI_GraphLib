package algorithm;

import algorithms.BFS;
import entity.Vertex;
import helper.GraphParser;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class BFSTest {
    @Test
    public void bfsUndirectedTest() {
        final Map<Integer, Boolean> markedMapAfterBFSFromV1 = new HashMap<Integer, Boolean>();
        markedMapAfterBFSFromV1.put(0, true);
        markedMapAfterBFSFromV1.put(1, false);
        markedMapAfterBFSFromV1.put(2, false);
        markedMapAfterBFSFromV1.put(3, true);
        markedMapAfterBFSFromV1.put(4, false);
        markedMapAfterBFSFromV1.put(5, true);
        markedMapAfterBFSFromV1.put(6, true);
        markedMapAfterBFSFromV1.put(7, false);
        markedMapAfterBFSFromV1.put(8, false);
        markedMapAfterBFSFromV1.put(9, true);
        markedMapAfterBFSFromV1.put(10, true);
        markedMapAfterBFSFromV1.put(11, false);
        markedMapAfterBFSFromV1.put(12, false);
        markedMapAfterBFSFromV1.put(13, true);
        markedMapAfterBFSFromV1.put(14, false);

        final String path = "src/main/resources/p1/Graph1.txt";
        final boolean directed = false;
        final Map<Integer, Boolean> markedMap = new HashMap<Integer, Boolean>();
        final List<Vertex> vertList = new GraphParser().readEdgeListFromFile(path, directed);
        vertList.forEach(v -> markedMap.put(v.getId(), false));
        final BFS search = new BFS();
        search.breadthFirstSearch(vertList.get(0), markedMap, directed);

        assertEquals(markedMap, markedMapAfterBFSFromV1);
    }
}
