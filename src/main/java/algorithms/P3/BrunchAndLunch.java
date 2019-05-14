package algorithms.P3;

import entity.Edge;
import entity.Graph;
import entity.Vertex;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class BrunchAndLunch {

    public void calculateTour(Graph graph) {
        ArrayList<Integer> unvisitedVertices = new ArrayList<>();
        for(Vertex v : graph.getVertexList()) { // Clone Vertexlist
            unvisitedVertices.add(v.getId());
        }

        ArrayList<Path> allPaths = new ArrayList<>();
        Double upper = 70.0;
        for (Vertex v : graph.getVertexList()) {
            Path currentPath = new Path();
            ArrayList<Integer> unvisitedVerticesCopy = (ArrayList<Integer>) unvisitedVertices.clone();
            unvisitedVerticesCopy.remove(v.getId());
            currentPath.addVertex(v.getId(), 0.0);

            upper = buildRecursiveTree(currentPath.clone(), unvisitedVerticesCopy, allPaths, graph.getVertexList(), upper);
            // connect back to starter vertex
        }
        Collections.sort(allPaths);
        List<Path> collect = allPaths.stream().filter(p -> p.vertices.get(0) == 9 && p.vertices.get(1) == 1 && p.vertices.get(2) == 0 &&
                p.vertices.get(3) == 6 && p.vertices.get(4) == 2 && p.vertices.get(5) == 7 &&
                p.vertices.get(6) == 4 && p.vertices.get(7) == 8 && p.vertices.get(8) == 5).collect(Collectors.toList());
        System.out.println("Pls work");
    }

    private Double buildRecursiveTree(Path currentPath, ArrayList<Integer> unvisitedVertices, List<Path> allPaths, List<Vertex> graphVertexList, Double upper) {
        if (unvisitedVertices.size() == 0) {
            currentPath.addVertex(currentPath.vertices.get(0), getCostOfEdge(graphVertexList.get(currentPath.vertices.get(currentPath.vertices.size() - 1)).getAttachedEdges(), currentPath.vertices.get(0)));
            if (currentPath.cost < upper) {
                upper = currentPath.cost; // Clone
                // Evtl alle anderen in allPaths löschen da jetzt veraltet
            }
            allPaths.add(currentPath);
            return upper;
        }
        for(Integer i : unvisitedVertices) {
            Path currentPathCopy = currentPath.clone();
            currentPathCopy.addVertex(i, getCostOfEdge(graphVertexList.get(currentPathCopy.vertices.get(currentPathCopy.vertices.size() - 1)).getAttachedEdges(), i));
            ArrayList<Integer> unvisitedVerticesCopy = (ArrayList<Integer>) unvisitedVertices.clone();
            unvisitedVerticesCopy.remove(i);
            // add up current cost
            if (currentPath.cost < upper)
                upper = buildRecursiveTree(currentPathCopy, unvisitedVerticesCopy, allPaths, graphVertexList, upper);

        }
        return upper;
    }

    private static Double getCostOfEdge(List<Edge> attachedEdges, int endId) {
        for (Edge e: attachedEdges) {
            if (e.getEnd().getId() == endId) {
                return e.getCost();
            }
        }
        throw new RuntimeException("No Edge with endpoint found");
    }

    private class Path implements Comparable<Path> {
        Path() {
            this.vertices = new ArrayList<>();
            this.vertcost = new ArrayList<>();
            this.cost = 0.0;
        }
        Path(ArrayList<Integer> verts, double c) {
            this.vertices = verts;
            this.cost = c;
        }

        public void addVertex(int id, Double cost) {
            this.vertices.add(id);
            this.vertcost.add(cost);
            this.cost += cost;
        }

        public Path clone() {
            Path p = new Path();
            p.vertices = (ArrayList<Integer>) this.vertices.clone();
            p.vertcost = (ArrayList<Double>) this.vertcost.clone();
            p.cost = this.cost;
            return p;
        }

        ArrayList<Integer> vertices;
        ArrayList<Double> vertcost;
        Double cost;

        @Override
        public int compareTo(Path p1) {
            return this.cost.compareTo(p1.cost);
        }
    }
}
