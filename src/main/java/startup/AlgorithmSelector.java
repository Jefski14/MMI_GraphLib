package startup;

import algorithms.*;
import entity.Graph;
import entity.Vertex;
import helper.GraphParser;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class AlgorithmSelector {


    /**
     * Selection menu for directed or undirecte edges in graph
     *
     * @return true if user selected directed, false if user selected undirected
     */
    private static boolean selectDirected() {
        System.out.println("----------------------------------------------------------------");
        System.out.println("----Edges directed ? [1: true or 0: false]----------------------");
        System.out.println("----------------------------------------------------------------");
        System.out.println("----Enter number: ----------------------------------------------");
        Scanner in = new Scanner(System.in);
        int num = in.nextInt();

        return num == 1;
    }

    /**
     * Shows the menu, where the user can choose the algorithm to execute
     *
     * @return number of chosen algorithm
     */
    private static Integer[] showSelectionMenu(int numberVertices) {
        System.out.println("----------------------------------------------------------------");
        System.out.println("----Choose Algorithm--------------------------------------------");
        System.out.println("----------------------------------------------------------------");
        System.out.println("[1]-Breadth First Search (BFS)----------------------------------");
        System.out.println("[2]-Depth First Search (DFS)------------------------------------");
        System.out.println("[3]-Count connected graphs--------------------------------------");
        System.out.println("[4]-Kruskal MST-------------------------------------------------");
        System.out.println("[4]-Prim MST----------------------------------------------------");
        System.out.println("----------------------------------------------------------------");
        System.out.println("----Enter number: ----------------------------------------------");
        Scanner in = new Scanner(System.in);
        int num = in.nextInt();
        System.out.println("----------------------------------------------------------------");
        System.out.println("----Starting vertex [0-" + (numberVertices - 1) + "]--------------------------------------");
        System.out.println("----------------------------------------------------------------");
        System.out.println("----Enter number: ----------------------------------------------");
        int num2 = in.nextInt();

        if (num2 < 0 || num2 > numberVertices) {
            return null;
        }

        Integer[] selection = new Integer[2];
        selection[0] = num;
        selection[1] = num2;
        return selection;
    }

    /**
     * Depending on the users selection an algorithm is executed.
     * The time for execution is measured and printed to console.
     *
     * @param selection user selection with algorithm to choose
     * @param graph graph entity with vertex and edge list
     * @param directed  if true edges are directed, else edges are undirected
     */
    private static void startAlgorithm(Integer[] selection, Graph graph, boolean directed) {

        int algorithm = selection[0];
        int startVertexId = selection[1];

        //Initialize markedMap
        Map<Integer, Boolean> markedMap = new HashMap<>();
        for (Vertex v : graph.getVertexList()) {
            markedMap.put(v.getId(), false);
        }

        System.out.println("Starting execution of algorithm ...");
        long startTime = System.currentTimeMillis();

        switch (algorithm) {
            case 1:
                BreadthFirstSearch.breadthFirstSearch(graph.getVertexList().get(startVertexId), markedMap, directed);
                break;
            case 2:
                DepthFirstSearch.iterativeDepthFirstSearch(graph.getVertexList().get(startVertexId), markedMap, directed);
                break;
            case 3:
                int connectedGraphs = ConnectedGraphFinder.findConnectedGraphs(graph.getVertexList(), directed);
                System.out.println("Found " + connectedGraphs + " independent graphs");
                break;
            case 4:
                KruskalMST.getMST(graph.getVertexList());
                break;
            case 5:
                PrimMST.getMST(graph, graph.getVertexList().get(startVertexId));
                break;
        }

        long calculatedTime = System.currentTimeMillis() - startTime;
        System.out.println("Execution took around " + calculatedTime + " ms");
    }

    /**
     * Gets the user input to restart the application
     *
     * @return String of user input
     */
    public static String restart() {
        System.out.println("Press [r] restart, [n] new File, {q] quit");
        Scanner input = new Scanner(System.in);  // Create a Scanner object
        return input.nextLine();  // Read user input
    }


    public static void run(File graphFile) {

        boolean directed = AlgorithmSelector.selectDirected();
        //Importing graph and measuring time TODO Change to Graph class or abandon ui completely?
        Graph graph = GraphParser.importGraphFromFileAsEdgeList(graphFile.getAbsolutePath(), directed);

        //Select algorithm and start vertex
        Integer[] selection = AlgorithmSelector.showSelectionMenu(graph.getVertexList().size());
        if (selection != null) {
            AlgorithmSelector.startAlgorithm(selection, graph, directed);
        }
    }
}
