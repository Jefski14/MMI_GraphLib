package algorithms.P7_Matchings;

import entity.Graph;
import org.junit.Test;

import static helper.GraphParser.importGraphFromFile;

public class MatchingsTest {

    @Test
    public void test_matching1() {
        Graph graph = importGraphFromFile("src/main/resources/p7/Matching_100_100.txt", false, false);

        long startTime = System.currentTimeMillis();
        System.out.println("Starting Matchings");


        long estimatedTime = System.currentTimeMillis() - startTime;
        System.out.println("Took " + estimatedTime + " ms\nor " + estimatedTime / 1000.0 + " seconds.");

//        assertEquals(3.0, cost, 0.0);
    }

    @Test(expected = RuntimeException.class)
    public void test_matching2() {
        Graph graph = importGraphFromFile("src/main/resources/p7/Matching2_100_100.txt", false, false);

        long startTime = System.currentTimeMillis();
        System.out.println("Starting Matchings");


        long estimatedTime = System.currentTimeMillis() - startTime;
        System.out.println("Took " + estimatedTime + " ms\nor " + estimatedTime / 1000.0 + " seconds.");

//        assertEquals(3.0, cost, 0.0);

    }

}