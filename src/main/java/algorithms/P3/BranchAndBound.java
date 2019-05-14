package algorithms.P3;

import entity.Graph;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.Arrays;

@Getter
public class BranchAndBound {

    //Number of vertices in graph
    private int numberVertices;
    //Edge list with n+1 edges
    private int[] finalPath;
    //keeps track of already visited vertices
    private boolean[] visited;
    //Cost of final shortest tour
    private double finalCost;
    //Defines whether to stop execution if tour is
    //more expensive than current best tour
    private boolean enumerate;

    BranchAndBound(Graph graph, boolean enumerate) {
        this.numberVertices = graph.getVertexList().size();
        this.finalPath = new int[numberVertices + 1];
        this.visited = new boolean[numberVertices];
        this.finalCost = Double.MAX_VALUE;
        this.enumerate = enumerate;
    }

    //Copies temporary solution to final solution
    //and connects last node with first node
    private void copyToFinal(int[] curr_path) {
        if (numberVertices >= 0)
            System.arraycopy(curr_path, 0, finalPath, 0, numberVertices);
        finalPath[numberVertices] = curr_path[0];
    }

    //Function to find the minimum edge cost
    // having an end at vertex i
    private Double findFirstMin(Graph g, int i) {
        Double min = Double.MAX_VALUE;
        for (int k = 0; k < numberVertices; k++) {
            Double edgeCost = g.getVertexList().get(i).getAttachedEdges().get(k).getCost();
            //If edgeCost < min
            if (edgeCost < min && i != k) {
                min = edgeCost;
            }
        }
        return min;
    }

    //Function to find the second minimum edge cost
    // having an end at the vertex i
    private Double findSecondMin(Graph g, int i) {
        Double first = Double.MAX_VALUE, second = Double.MAX_VALUE;
        for (int j = 0; j < numberVertices; j++) {
            if (i == j) {
                continue;
            }
            Double edgeCost = g.getVertexList().get(i).getAttachedEdges().get(j).getCost();
            if (edgeCost <= first) {
                second = first;
                first = edgeCost;
                //if edgeCost <= second && edgeCost != first
            } else if (edgeCost <= second && !edgeCost.equals(first))
                second = edgeCost;
        }
        return second;
    }

    /**
     * @param g           Graph object to extract vertices and edges from
     * @param curr_bound  lower bound
     * @param curr_weight weight of current path
     * @param level       current level while moving through search tree
     * @param curr_path   store of current solution, later being copied to finalPath
     */
    private void TSPRec(Graph g, Double curr_bound, Double curr_weight,
                        int level, int[] curr_path) {
        // base case is when we have reached level N which
        // means we have covered all the nodes once
        if (level == numberVertices) {
            // check if there is an edge from last vertex in
            // path back to the first vertex
            Double edgeCost = g.getVertexList().get(curr_path[level - 1]).getAttachedEdges().get(curr_path[0]).getCost();
            //if curr_res != 0.0
            if (edgeCost != 0.0) {
                // curr_res has the total weight of the
                // solution we got
                Double curr_res = curr_weight + edgeCost;

                // Update final result and final path if
                // current result is better.
                //if curr_res < finalCost
                if (curr_res.compareTo(finalCost) < 0) {
                    copyToFinal(curr_path);
                    finalCost = curr_res;
                }
            }
            return;
        }

        // for any other level iterate for all vertices to
        // build the search space tree recursively
        for (int i = 0; i < numberVertices; i++) {
            // Consider next vertex if it is not same (diagonal
            // entry in adjacency matrix and not visited
            // already)
            Double edgeCost = g.getVertexList().get(curr_path[level - 1]).getAttachedEdges().get(i).getCost();
            if (edgeCost != 0.0 && !visited[i]) {
                Double temp = curr_bound;
                curr_weight = curr_weight + edgeCost;

                // different computation of curr_bound for
                // level 2 from the other levels
                if (level == 1) {
                    Double i1 = findFirstMin(g, curr_path[level - 1]);
                    Double i2 = (findFirstMin(g, i) / 2);
                    Double add = findFirstMin(g, curr_path[level - 1]) + (findFirstMin(g, i) / 2);
                    curr_bound = curr_bound - add;
                } else {
                    Double i1 = findSecondMin(g, curr_path[level - 1]);
                    Double i2 = findFirstMin(g, i) / 2;
                    Double add = i1 + i2;
                    curr_bound = curr_bound - add;
                }

                // curr_bound + curr_weight is the actual lower bound
                // for the node that we have arrived on
                // If current lower bound < final_res, we need to explore
                // the node further
                Double add = curr_bound + curr_weight;
                if (add.compareTo(finalCost) < 0) {
                    curr_path[level] = i;
                    visited[i] = true;

                    // call TSPRec for the next level
                    TSPRec(g, curr_bound, curr_weight, level + 1,
                            curr_path);
                }

                // Else we have to prune the node by resetting
                // all changes to curr_weight and curr_bound
                Double edgeCost2 = g.getVertexList().get(curr_path[level - 1]).getAttachedEdges().get(i).getCost();
                curr_weight = curr_weight - edgeCost2;
                curr_bound = temp;

                // Also reset the visited array
                Arrays.fill(visited, false);
                for (int j = 0; j <= level - 1; j++)
                    visited[curr_path[j]] = true;
            }
        }
    }

    public void TSP(Graph g) {
        int[] curr_path = new int[numberVertices + 1];

        // Calculate initial lower bound for the root node
        // using the formula 1/2 * (sum of first min +
        // second min) for all edges.
        // Also initialize the curr_path and visited array
        Double curr_bound = 0.0;
        Arrays.fill(curr_path, -1);
        Arrays.fill(visited, false);

        // Compute initial bound
        for (int i = 0; i < numberVertices; i++) {
            Double firstMin = findFirstMin(g, i);
            Double secondMin = findSecondMin(g, i);
            curr_bound = curr_bound + firstMin + secondMin;
        }

        // Rounding off the lower bound to an integer
        if (curr_bound.equals(BigDecimal.valueOf(1.0))) {
            curr_bound = (curr_bound / 2) + 1;
        } else {
            curr_bound = curr_bound / 2;
        }

        // We start at vertex 1 so the first vertex
        // in curr_path[] is 0
        visited[0] = true;
        curr_path[0] = 0;

        // Call to TSPRec for curr_weight equal to
        // 0 and level 1
        TSPRec(g, curr_bound, 0.0, 1, curr_path);
    }
}
