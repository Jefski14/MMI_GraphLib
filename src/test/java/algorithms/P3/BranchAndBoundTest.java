package algorithms.P3;

import entity.Graph;
import org.junit.Test;

import static helper.GraphParser.importGraphFromFile;

public class BranchAndBoundTest {

    @Test
    public void test2() {

        Graph graph = importGraphFromFile("src/main/resources/p3/vollstaendigerGraph4Knoten.txt", false);
        BranchAndBound.calculateTour(graph);
        System.out.println("Pls work");
    }

    @Test
    public void K_10() {

        Graph graph = importGraphFromFile("src/main/resources/p3/K_10.txt", false);
        BranchAndBound.calculateTour(graph);
        System.out.println("Pls work");
    }


    @Test
    public void K_12() {

        Graph graph = importGraphFromFile("src/main/resources/p3/K_12.txt", false);
        BranchAndBound.calculateTour(graph);
        System.out.println("Pls work");
    }

}