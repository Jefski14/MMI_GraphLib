package algorithms.P3;

import entity.Edge;
import entity.Graph;
import entity.Vertex;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BrunchAndLunch {
    private static BigDecimal upperBound;

    public void calculateTour(Graph graph) {
        ArrayList<Integer> unvisitedVertices = new ArrayList<>();
        for(Vertex v : graph.getVertexList()) { // Clone Vertexlist
            unvisitedVertices.add(v.getId());
        }

        ArrayList<Path> allPaths = new ArrayList<>();
        BigDecimal upper = BigDecimal.valueOf(70);
        for (Vertex v : graph.getVertexList()) {
            ArrayList<Integer> currentPath = new ArrayList<>();
            ArrayList<Integer> unvisitedVerticesCopy = (ArrayList<Integer>) unvisitedVertices.clone();
            unvisitedVerticesCopy.remove(v.getId());
            currentPath.add(v.getId());

            upper = buildRecursiveTree(currentPath, unvisitedVerticesCopy, BigDecimal.ZERO, allPaths, graph.getVertexList(), upper);
            // connect back to starter vertex
        }
        Collections.sort(allPaths);
        System.out.println("Pls work");
    }

    private BigDecimal buildRecursiveTree(ArrayList<Integer> currentPath, ArrayList<Integer> unvisitedVertices, BigDecimal currentCost, List<Path> allPaths, List<Vertex> graphVertexList, BigDecimal upper) {
        if (unvisitedVertices.size() == 0) {
            currentCost = currentCost.add(getCostOfEdge(graphVertexList.get(currentPath.get(currentPath.size() - 1)).getAttachedEdges(), currentPath.get(0)));
            if (currentCost.compareTo(upper) < 0) {
                upper = currentCost.add(BigDecimal.ZERO); // Clone
                // Evtl alle anderen in allPaths löschen da jetzt veraltet
            }
            Path p = new Path(currentPath, currentCost); // iwie die kosten zurück geben
            allPaths.add(p);
            return upper;
        }
        for(Integer i : unvisitedVertices) {
            ArrayList<Integer> currentPathCopy = (ArrayList<Integer>) currentPath.clone();
            currentPathCopy.add(i);
            ArrayList<Integer> unvisitedVerticesCopy = (ArrayList<Integer>) unvisitedVertices.clone();
            unvisitedVerticesCopy.remove(i);
            currentCost = currentCost.add(getCostOfEdge(graphVertexList.get(currentPathCopy.get(currentPathCopy.size() - 2)).getAttachedEdges(), i));
            // add up current cost
            if (currentCost.compareTo(upper) < 0) {
                upper = buildRecursiveTree(currentPathCopy, unvisitedVerticesCopy, currentCost, allPaths, graphVertexList, upper);
            }
        }
        return upper;
    }

    private static BigDecimal getCostOfEdge(List<Edge> attachedEdges, int endId) {
        for (Edge e: attachedEdges) {
            if (e.getEnd().getId() == endId) {
                return e.getCost().add(BigDecimal.ZERO);
            }
        }
        throw new RuntimeException("No Edge with endpoint found");
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
