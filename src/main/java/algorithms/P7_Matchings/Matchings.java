package algorithms.P7_Matchings;

import algorithms.P5_Max_Flow.EdmondsKarp;
import algorithms.P5_Max_Flow.GraphWithFlow;
import entity.Edge;
import entity.Graph;

import java.util.ArrayList;
import java.util.List;

public class Matchings {

    public static double getMatchings(Graph graph) {
        GraphWithFlow graphWithFlow = EdmondsKarp.runEdmondsKarp(graph, graph.getVertexList().size() - 2, graph.getVertexList().size() - 1);

        return graphWithFlow.max_flow;
    }

}
