package algorithms.P7_Matchings;

import entity.Graph;
import org.junit.Test;

import static helper.GraphParser.importBipartiteGraph;
import static helper.GraphParser.importGraphFromFile;
import static org.junit.Assert.assertEquals;

public class MatchingsTest {

    @Test
    public void test_matching1() {
        Graph graph = importBipartiteGraph("src/main/resources/p7/Matching_100_100.txt");

        long startTime = System.currentTimeMillis();
        System.out.println("Starting Matchings");
        double matchings = Matchings.getMatchings(graph);


        long estimatedTime = System.currentTimeMillis() - startTime;
        System.out.println("Took " + estimatedTime + " ms\nor " + estimatedTime / 1000.0 + " seconds.");

        assertEquals(100.0, matchings, 0.0);
    }

    @Test
    public void test_matching2() {
        Graph graph = importBipartiteGraph("src/main/resources/p7/Matching2_100_100.txt");

        long startTime = System.currentTimeMillis();
        System.out.println("Starting Matchings");

        double matchings = Matchings.getMatchings(graph);


        long estimatedTime = System.currentTimeMillis() - startTime;
        System.out.println("Took " + estimatedTime + " ms\nor " + estimatedTime / 1000.0 + " seconds.");

        assertEquals(99.0, matchings, 0.0);

    }

}