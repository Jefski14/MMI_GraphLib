package helper;

import entity.Edge;

import java.util.ArrayList;

public class printFunctions {

    public static void printPathInReverseWithCost(ArrayList<Edge> edgeList) {
        Double totalCost = 0.0;
        System.out.println("Shortest Path:");
        System.out.print(edgeList.get(edgeList.size()-1).getStart().getId() + " -> ");
        for (int i = edgeList.size() - 1; i >= 0; i--) {
            System.out.print(edgeList.get(i).getEnd());
            totalCost += edgeList.get(i).getCost();
        }
        System.out.println("\nTotal Cost: " + totalCost);
    }
}
