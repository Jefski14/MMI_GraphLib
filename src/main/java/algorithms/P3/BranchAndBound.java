//package algorithms.P3;
//
//import entity.Graph;
//import lombok.Getter;
//
//import java.math.BigDecimal;
//import java.util.Arrays;
//
//@Getter
//public class BranchAndBound {
//
//    //Number of vertices in graph
//    private int numberVertices;
//    //Edge list with n+1 edges
//    private int[] finalPath;
//    //keeps track of already visited vertices
//    private boolean[] visited;
//    //Cost of final shortest tour
//    private BigDecimal finalCost;
//    //Defines whether to stop execution if tour is
//    //more expensive than current best tour
//    private boolean enumerate;
//
//    BranchAndBound(Graph graph, boolean enumerate) {
//        this.numberVertices = graph.getVertexList().size();
//        this.finalPath = new int[numberVertices + 1];
//        this.visited = new boolean[numberVertices];
//        this.finalCost = BigDecimal.valueOf(Double.MAX_VALUE);
//        this.enumerate = enumerate;
//    }
//
//    //Copies temporary solution to final solution
//    //and connects last node with first node
//    private void copyToFinal(int[] curr_path) {
//        if (numberVertices >= 0)
//            System.arraycopy(curr_path, 0, finalPath, 0, numberVertices);
//        finalPath[numberVertices] = curr_path[0];
//    }
//
//    //Function to find the minimum edge cost
//    // having an end at vertex i
//    private BigDecimal findFirstMin(Graph g, int i) {
//        BigDecimal min = BigDecimal.valueOf(Double.MAX_VALUE);
//        for (int k = 0; k < numberVertices; k++) {
//            BigDecimal edgeCost = g.getEdgeMap().get(i).get(k).getCost();
//            //If edgeCost < min
//            if (edgeCost.compareTo(min) < 0 && i != k) {
//                min = edgeCost;
//            }
//        }
//        return min;
//    }
//
//    //Function to find the second minimum edge cost
//    // having an end at the vertex i
//    private BigDecimal findSecondMin(Graph g, int i) {
//        BigDecimal first = BigDecimal.valueOf(Double.MAX_VALUE), second = BigDecimal.valueOf(Double.MAX_VALUE);
//        for (int j = 0; j < numberVertices; j++) {
//            if (i == j) {
//                continue;
//            }
//            BigDecimal edgeCost = g.getEdgeMap().get(i).get(j).getCost();
//            if (edgeCost.compareTo(first) == 0 || edgeCost.compareTo(first) < 0) {
//                second = first;
//                first = edgeCost;
//                //if edgeCost <= second && edgeCost != first
//            } else if ((edgeCost.compareTo(second) == 0 || edgeCost.compareTo(second) < 0) && !edgeCost.equals(first))
//                second = edgeCost;
//        }
//        return second;
//    }
//
//    /**
//     * @param g           Graph object to extract vertices and edges from
//     * @param curr_bound  lower bound
//     * @param curr_weight weight of current path
//     * @param level       current level while moving through search tree
//     * @param curr_path   store of current solution, later being copied to finalPath
//     */
//    private void TSPRec(Graph g, BigDecimal curr_bound, BigDecimal curr_weight,
//                        int level, int[] curr_path) {
//        // base case is when we have reached level N which
//        // means we have covered all the nodes once
//        if (level == numberVertices) {
//            // check if there is an edge from last vertex in
//            // path back to the first vertex
//            BigDecimal edgeCost = g.getEdgeMap().get(curr_path[level - 1]).get(curr_path[0]).getCost();
//            //if curr_res != 0.0
//            if (!edgeCost.equals(BigDecimal.valueOf(0.0))) {
//                // curr_res has the total weight of the
//                // solution we got
//                BigDecimal curr_res = curr_weight.add(edgeCost);
//
//                // Update final result and final path if
//                // current result is better.
//                //if curr_res < finalCost
//                if (curr_res.compareTo(finalCost) < 0) {
//                    copyToFinal(curr_path);
//                    finalCost = curr_res;
//                }
//            }
//            return;
//        }
//
//        // for any other level iterate for all vertices to
//        // build the search space tree recursively
//        for (int i = 0; i < numberVertices; i++) {
//            // Consider next vertex if it is not same (diagonal
//            // entry in adjacency matrix and not visited
//            // already)
//            BigDecimal edgeCost = g.getEdgeMap().get(curr_path[level - 1]).get(i).getCost();
//            if (!edgeCost.equals(BigDecimal.valueOf(0.0)) &&
//                    !visited[i]) {
//                BigDecimal temp = curr_bound;
//                curr_weight = curr_weight.add(edgeCost);
//
//                // different computation of curr_bound for
//                // level 2 from the other levels
//                if (level == 1) {
//                    BigDecimal i1 = findFirstMin(g, curr_path[level - 1]);
//                    BigDecimal i2 = findFirstMin(g, i).divide(BigDecimal.valueOf(2));
//                    BigDecimal add = i1.add(i2);
//                    curr_bound = curr_bound.subtract(add);
//                } else {
//                    BigDecimal i1 = findSecondMin(g, curr_path[level - 1]);
//                    BigDecimal i2 = findFirstMin(g, i).divide(BigDecimal.valueOf(2));
//                    BigDecimal add = i1.add(i2);
//                    curr_bound = curr_bound.subtract(add);
//                }
//
//                // curr_bound + curr_weight is the actual lower bound
//                // for the node that we have arrived on
//                // If current lower bound < final_res, we need to explore
//                // the node further
//                BigDecimal add = curr_bound.add(curr_weight);
//                if (add.compareTo(finalCost) < 0) {
//                    curr_path[level] = i;
//                    visited[i] = true;
//
//                    // call TSPRec for the next level
//                    TSPRec(g, curr_bound, curr_weight, level + 1,
//                            curr_path);
//                }
//
//                // Else we have to prune the node by resetting
//                // all changes to curr_weight and curr_bound
//                BigDecimal edgeCost2 = g.getEdgeMap().get(curr_path[level - 1]).get(i).getCost();
//                curr_weight = curr_weight.subtract(edgeCost2);
//                curr_bound = temp;
//
//                // Also reset the visited array
//                Arrays.fill(visited, false);
//                for (int j = 0; j <= level - 1; j++)
//                    visited[curr_path[j]] = true;
//            }
//        }
//    }
//
//    public void TSP(Graph g) {
//        int[] curr_path = new int[numberVertices + 1];
//
//        // Calculate initial lower bound for the root node
//        // using the formula 1/2 * (sum of first min +
//        // second min) for all edges.
//        // Also initialize the curr_path and visited array
//        BigDecimal curr_bound = BigDecimal.valueOf(0.0);
//        Arrays.fill(curr_path, -1);
//        Arrays.fill(visited, false);
//
//        // Compute initial bound
//        for (int i = 0; i < numberVertices; i++) {
//            BigDecimal firstMin = findFirstMin(g, i);
//            BigDecimal secondMin = findSecondMin(g, i);
//            curr_bound = curr_bound.add(firstMin).add(secondMin);
//        }
//
//        // Rounding off the lower bound to an integer
//        if (curr_bound.equals(BigDecimal.valueOf(1.0))) {
//            curr_bound = curr_bound.divide(BigDecimal.valueOf(2.0)).add(BigDecimal.valueOf(1.0));
//        } else {
//            curr_bound = curr_bound.divide(BigDecimal.valueOf(2.0));
//        }
//
//        // We start at vertex 1 so the first vertex
//        // in curr_path[] is 0
//        visited[0] = true;
//        curr_path[0] = 0;
//
//        // Call to TSPRec for curr_weight equal to
//        // 0 and level 1
//        TSPRec(g, curr_bound, BigDecimal.valueOf(0.0), 1, curr_path);
//    }
//}
