package algorithms.P3;

import entity.Edge;
import entity.Graph;
import entity.Vertex;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static entity.Graph.getCostOfEdge;

public class BranchAndBound {

    private static boolean fullEnumeration = false;

    /**
     * Calculates the cheapest TSP Tour
     * Warning this can take very long depending on your graph size
     * @param graph Graph in which to search the tour
     */
    public static Graph calculateTour(Graph graph) {
        ArrayList<Integer> unvisitedVertices = new ArrayList<>();
        for(Vertex v : graph.getVertexList()) { // Clone Vertexlist
            unvisitedVertices.add(v.getId());
        }

        ArrayList<Path> allPaths = new ArrayList<>(); // List to save all Paths
        Graph nnTour = NearestNeighbor.calculateTour(graph);
        Double upper = nnTour.totalEdgeCost(); // Initialize upper bound with nearest neighbor try

        // Start recursive Depth Search Style building of paths
        for (Vertex v : graph.getVertexList()) {
            Path currentPath = new Path();
            // Copy unvisited Vertex List because its passed down by reference (else it would be changed)
            ArrayList<Integer> unvisitedVerticesCopy = (ArrayList<Integer>) unvisitedVertices.clone();
            unvisitedVerticesCopy.remove(v.getId()); // mark current Vertex v as visited
            currentPath.addVertex(v.getId(), 0.0); // Add v to Path (first vertex has no cost)

            // Find all paths recursivly
            upper = buildRecursiveTree(currentPath.clone(), unvisitedVerticesCopy, allPaths, graph.getVertexList(), upper);
        }
        // Pick out the cheapest Path we found
        Collections.sort(allPaths);
        Path cheapest = allPaths.get(0);

        // Build new Graph from found path
        ArrayList<Edge> tspEdgeList = new ArrayList<>();
        for (int i = 1; i < cheapest.vertices.size(); i++) {
            tspEdgeList.add(
                    new Edge(
                            new Vertex(cheapest.vertices.get(i-1)), new Vertex(cheapest.vertices.get(i)),
                                    getCostOfEdge(graph.getVertexList().get(cheapest.vertices.get(i-1)).getAttachedEdges(), cheapest.vertices.get(i)), 0.0));
        }
        return new Graph(tspEdgeList, graph.isDirected(), graph.getVertexList().size());
    }

    /**
     * Recursive depth search function that builds all paths in graph
     * @param currentPath copy of the current path
     * @param unvisitedVertices list of vertices that need to be visited
     * @param allPaths reference of All Path Object in which a newly found path will be written
     * @param graphVertexList reference of the original graph vertex list to get the cost of any edge
     * @param upper current upper bound
     * @return same or improved upper bound
     */
    private static Double buildRecursiveTree(Path currentPath, ArrayList<Integer> unvisitedVertices, List<Path> allPaths, List<Vertex> graphVertexList, Double upper) {
        // Recursion end case
        if (unvisitedVertices.size() == 0) { // All vertices are visited -> complete Hamilton Path
            // Add the edge back to the starting vertex
            currentPath.addVertex(currentPath.vertices.get(0),
                    getCostOfEdge(
                            graphVertexList.get(currentPath.vertices.get(currentPath.vertices.size() - 1)).getAttachedEdges(),
                            currentPath.vertices.get(0)));
            if (currentPath.cost < upper || fullEnumeration) { // If its still cheaper
                upper = currentPath.cost; // Change upper Bound to new upper Bound
                // Add newly found cheapest path
                allPaths.add(currentPath);
            }
            // Propagate the newly found bound back up
            return upper;
        }

        for(Integer i : unvisitedVertices) {
            // Clone current path because java passes down reference
            Path currentPathCopy = currentPath.clone();
            // Add new edge from last edge in path to the new one (i)
            currentPathCopy.addVertex(i, getCostOfEdge(graphVertexList.get(currentPathCopy.vertices.get(currentPathCopy.vertices.size() - 1)).getAttachedEdges(), i));
            // Remove the current Vertex from the unvisited list
            ArrayList<Integer> unvisitedVerticesCopy = (ArrayList<Integer>) unvisitedVertices.clone();
            unvisitedVerticesCopy.remove(i);
            // If its still lower than the bound continue down
            if (currentPath.cost < upper || fullEnumeration)
                upper = buildRecursiveTree(currentPathCopy, unvisitedVerticesCopy, allPaths, graphVertexList, upper);

        }
        return upper;
    }

}
