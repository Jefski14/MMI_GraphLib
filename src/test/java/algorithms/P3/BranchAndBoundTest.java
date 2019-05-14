package algorithms.P3;

import entity.Graph;
import org.junit.Test;

import java.math.BigDecimal;

import static helper.GraphParser.importGraphFromFile;

public class BranchAndBoundTest {

    @Test
    public void test() {

        Graph graph = importGraphFromFile("src/main/resources/p3/K_10.txt", false);

        BranchAndBound branchAndBound = new BranchAndBound(graph, false);
        branchAndBound.TSP(graph);

        BigDecimal finalCost = branchAndBound.getFinalCost();
        int[] finalPath = branchAndBound.getFinalPath();
        System.out.println("Fuck me !");
    }

}