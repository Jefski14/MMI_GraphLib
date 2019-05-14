package algorithms.P3;

import entity.Graph;
import org.junit.Test;

import static helper.GraphParser.importGraphFromFile;

public class BranchAndBoundTest {

    @Test
    public void test2() {

        Graph graph = importGraphFromFile("src/main/resources/p3/vollstaendigerGraph3Knoten.txt", false);
        BrunchAndLunch bnb = new BrunchAndLunch();
        bnb.calculateTour(graph);
        System.out.println("Pls work");
    }

}