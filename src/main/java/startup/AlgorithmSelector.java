package startup;

import algorithms.BreadthFirstSearch;
import algorithms.ConnectedGraphFinder;
import algorithms.DepthFirstSearch;
import entity.Vertex;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class AlgorithmSelector {


    public static boolean selectDirected() {
        System.out.println("----------------------------------------------------------------");
        System.out.println("----Edges directed ? [1: true or 0: false]----------------------");
        System.out.println("----------------------------------------------------------------");
        System.out.println("----Enter number: ----------------------------------------------");
        Scanner in = new Scanner(System.in);
        int num = in.nextInt();

        return num == 1;
    }

    /**
     * Show the menu, where the user can choose the algorithm to execute
     *
     * @return number of chosen algorithm
     */
    public static Integer[] showSelectionMenu(List<Vertex> vertices) {
        System.out.println("----------------------------------------------------------------");
        System.out.println("----Choose Algorithm--------------------------------------------");
        System.out.println("----------------------------------------------------------------");
        System.out.println("[1]-Breadth First Search (BFS)----------------------------------");
        System.out.println("[2]-Depth First Search (DFS)------------------------------------");
        System.out.println("[3]-Count connected graphs--------------------------------------");
        System.out.println("----------------------------------------------------------------");
        System.out.println("----Enter number: ----------------------------------------------");
        Scanner in = new Scanner(System.in);
        int num = in.nextInt();
        System.out.println("----------------------------------------------------------------");
        System.out.println("----Starting vertex [0-" + vertices.size() + "]--------------------------------------");
        System.out.println("----------------------------------------------------------------");
        System.out.println("----Enter number: ----------------------------------------------");
        int num2 = in.nextInt();

        if (num2 < 0 || num2 > vertices.size()) {
            in.close();
            return null;
        }

        Integer[] selection = new Integer[2];
        selection[0] = num;
        selection[1] = num2;
        in.close();
        return selection;
    }

    public static void startAlgorithm(Integer[] selection, List<Vertex> vertices, boolean directed) {

        int algorithm = selection[0];
        int startVertexId = selection[1];

        //Initialize markedMap
        Map<Integer, Boolean> markedMap = new HashMap<>();
        for (Vertex v : vertices) {
            markedMap.put(v.getId(), false);
        }

        System.out.println("Starting execution of algorithm ...");
        long startTime = System.currentTimeMillis();

        switch (algorithm) {
            case 1:
                BreadthFirstSearch.breadthFirstSearch(vertices.get(startVertexId), markedMap, directed);
                break;
            case 2:
                DepthFirstSearch.iterativeDepthFirstSearch(vertices.get(startVertexId), markedMap, directed);
                break;
            case 3:
                int connectedGraphs = ConnectedGraphFinder.findConnectedGraphs(vertices, directed);
                System.out.println("Found " + connectedGraphs + " independent graphs");
                break;
        }

        long calculatedTime = System.currentTimeMillis() - startTime;
        System.out.println("Execution took around " + calculatedTime + " ms");
    }
}
