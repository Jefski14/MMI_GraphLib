package algorithm;

import entity.Vertex;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static algorithms.Search.breadthFirstSearch;
import static algorithms.Search.iterativeDepthFirstSearch;
import static helper.GraphParser.importGraphFromFile;
import static org.junit.Assert.assertEquals;

public class SearchTest {
    @Test
    public void bfsUndirectedTest() {
        final Map<Integer, Boolean> markedMapAfterBFSFromV1 = new HashMap<>();
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
        final Map<Integer, Boolean> markedMap = new HashMap<>();
        final List<Vertex> vertList = importGraphFromFile(path, directed);
        vertList.forEach(v -> markedMap.put(v.getId(), false));
        breadthFirstSearch(vertList.get(0), markedMap, directed);

        assertEquals(markedMap, markedMapAfterBFSFromV1);
    }

    @Test
    public void dfsUndirectedTest() {
        final Map<Integer, Boolean> markedMapAfterBFSFromV1 = new HashMap<>();
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
        final List<Vertex> vertList = importGraphFromFile(path, directed);
        vertList.forEach(v -> markedMap.put(v.getId(), false));
        iterativeDepthFirstSearch(vertList.get(0), markedMap, directed);

        assertEquals(markedMap, markedMapAfterBFSFromV1);
    }
}
