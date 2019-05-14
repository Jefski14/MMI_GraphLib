package algorithms.P3;

import entity.Graph;
import entity.Vertex;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class BrunchAndLunch {
    private static BigDecimal upperBound;

    public void calculateTour(Graph graph) {
        ArrayList<Integer> unvisitedVertices = new ArrayList<>();
        for(Vertex v : graph.getVertexList()) { // Clone Vertexlist
            unvisitedVertices.add(v.getId());
        }
        double reachedByCost = 0;

        ArrayList<Path> allPaths = new ArrayList<>();
        BigDecimal upper = BigDecimal.valueOf(70);
        for (Vertex v : graph.getVertexList()) {
            ArrayList<Integer> currentPath = new ArrayList<>();
            ArrayList<Integer> unvisitedVerticesCopy = (ArrayList<Integer>) unvisitedVertices.clone();
            unvisitedVerticesCopy.remove(v.getId());
            currentPath.add(v.getId());

            upper = buildRecursiveTree(currentPath, unvisitedVerticesCopy, reachedByCost, allPaths, graph.getVertexList(), upper);
            // connect back to starter vertex
        }
        Collections.sort(allPaths);
        System.out.println("Pls work");
    }

    private BigDecimal buildRecursiveTree(ArrayList<Integer> currentPath, ArrayList<Integer> unvisitedVertices, double currentCost, List<Path> allPaths, List<Vertex> graphVertexList, BigDecimal upper) {
        if (unvisitedVertices.size() == 0) {
            currentCost += DoubleTrees.getEdgeWithSpecificEnd(graphVertexList.get(currentPath.get(currentPath.size() - 1)).getAttachedEdges(), currentPath.get(0)).getCost().doubleValue();
            if (currentCost < upper.doubleValue()) {
                upper = BigDecimal.valueOf(currentCost);
//                upper =  upper.add(BigDecimal.ZERO);
            }
            Path p = new Path(currentPath, BigDecimal.valueOf(currentCost)); // iwie die kosten zurück geben
            allPaths.add(p);
            return upper;
        }
        for(Integer i : unvisitedVertices) {
            ArrayList<Integer> currentPathCopy = (ArrayList<Integer>) currentPath.clone();
            currentPathCopy.add(i);
            ArrayList<Integer> unvisitedVerticesCopy = (ArrayList<Integer>) unvisitedVertices.clone();
            unvisitedVerticesCopy.remove(i);
            currentCost += DoubleTrees.getEdgeWithSpecificEnd(graphVertexList.get(currentPathCopy.get(currentPathCopy.size() - 2)).getAttachedEdges(), i).getCost().doubleValue();
            // add up current cost
            if (currentCost < upper.doubleValue()) {
                upper = buildRecursiveTree(currentPathCopy, unvisitedVerticesCopy, currentCost, allPaths, graphVertexList, upper);
            }
        }
        return upper;
    }

    private class Path implements Comparable<Path> {
        Path(ArrayList<Integer> verts, BigDecimal c) {
            this.vertices = verts;
            this.cost = c;
        }
        ArrayList<Integer> vertices;
        BigDecimal cost;


        @Override
        public int compareTo(Path p1) {
            return this.cost.compareTo(p1.cost);
        }
    }
}
