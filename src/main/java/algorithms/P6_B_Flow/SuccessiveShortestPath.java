package algorithms.P6_B_Flow;

import algorithms.P1_Search.BreadthFirstSearch;
import algorithms.P4_Shortest_Paths.MooreBellmanFord;
import entity.Edge;
import entity.Graph;
import entity.Vertex;

import java.util.*;

public class SuccessiveShortestPath {

    private static Map<Integer, Double> relevantBalances;

    public static double getMinimalCostFlow(Graph graph) {

        Graph residualGraph = calculateResidualGraph(graph);
        return calculateMinimalCostFlow(graph, residualGraph);
    }

    private static void updateResidualEdge(Graph graph, Edge edge, double capacity) {
        updateEdge(graph, edge, capacity);
        updateReversiveEdge(graph, edge, capacity);
    }

    private static void updateEdge(Graph graph, Edge edge, double capacity) {
        double newCapacity = edge.getCapacity() - capacity;
        if (newCapacity <= 0) {
            graph.getEdgeList().remove(edge);
        } else {
            edge.setCapacity(newCapacity);
        }
    }

    private static void updateReversiveEdge(Graph graph, Edge edge, double capacity) {
        Edge reversiveEdge = graph.getEdgeAndConstructNewIfNonExistent(edge.getEnd().getId(), edge.getStart().getId());
        if (reversiveEdge != null) {
            double newCapacity = reversiveEdge.getCapacity() + capacity;
            reversiveEdge.setCapacity(newCapacity);
        } else {
            double cost = edge.getCost() != 0.0 ? edge.getCost() * -1 : 0.0;
            reversiveEdge = new Edge(edge.getEnd(), edge.getStart(), capacity, cost);
            graph.getEdgeList().add(reversiveEdge);
        }
    }

    private static boolean checkVerticesBalanced(Collection<Vertex> vertices) {
        return vertices.stream().mapToDouble(Vertex::getBalance).sum() == 0;
    }

    private static double calculateMinimalCostFlow(Graph graph, Graph residualGraph) {
        double cost = 0.0;
        for (Edge residualEdge : residualGraph.getEdgeList()) {
            Vertex source = graph.getVertexList().get(residualEdge.getStart().getId());
            Vertex sink = graph.getVertexList().get(residualEdge.getEnd().getId());

            if (source == null || sink == null) {
                continue;
            }

            Edge originalEdge = graph.getEdge(sink, source);
            if (originalEdge != null) {
                cost += residualEdge.getCapacity() * originalEdge.getCost();
            }
        }

        return cost;
    }

    /**
     * Berechnung den Residualgraphen zu einem gegebenen Graphen
     *
     * @param graph gerichteter Graph
     * @return Residualgraph mit den minimalen Kosten
     * //     * @throws MinimalCostFlowException
     * //     * @throws NegativeCycleException
     */
    private static Graph calculateResidualGraph(Graph graph) /*throws MinimalCostFlowException, NegativeCycleException */ {
        if (!checkVerticesBalanced(graph.getVertexList())) {
            throw new IllegalArgumentException("Balancen sind nicht ausgeglichen!");
//            throw new MinimalCostFlowException("Balancen sind nicht ausgeglichen");
        }

        Graph residualGraph = graph;
        initRelevantBalances(residualGraph);
        updateCapacities(residualGraph);

        while (true) {
//            residualGraph.unvisitAllVertices();

            Vertex source = findSource(residualGraph);
            if (source == null) {
                break;
            }

            Vertex sink = findSink(source, graph);
            if (sink == null) {
                throw new IllegalArgumentException("Sink was null");
//                throw new MinimalCostFlowException("Das Netzwerk ist zu klein (Keine Senke für Quelle [" + source + "] gefunden.");
            }

            ArrayList<Edge> path = MooreBellmanFord.getShortestPath(residualGraph, source, sink);
            double minCapacity = path.stream().mapToDouble(Edge::getCapacity).min().getAsDouble();
            double minSourceBalance = source.getBalance() - relevantBalances.get(source.getId());
            double minSinkBalance = relevantBalances.get(sink.getId()) - sink.getBalance();

            double gamma = calculateGamma(minCapacity, minSourceBalance, minSinkBalance);

            addBalance(source, gamma);
            addBalance(sink, gamma * -1);

            path.forEach(e -> updateResidualEdge(residualGraph, e, gamma));
        }

        return residualGraph;
    }

    private static Vertex findSource(Graph graph) {
        for (Vertex v : graph.getVertexList()) {
            if (v.getBalance() - relevantBalances.get(v.getId()) > 0.0) {
                return v;
            }
        }

        return null;
    }

    private static Vertex findSink(Vertex source, Graph graph) {
        final Map<Integer, Boolean> markedMap = new HashMap<>();
        for (Vertex v : graph.getVertexList()) {
            markedMap.put(v.getId(), false);
        }

        BreadthFirstSearch.breadthFirstSearch(source, markedMap, true);

        for (Integer vId : markedMap.keySet()) {
            //if isMarked == can be reached
            if (markedMap.get(vId)) {
                Vertex v = graph.getVertexList().get(vId);
                if (v == source) {
                    continue;
                }

                if (v.getBalance() - relevantBalances.get(v.getId()) < 0.0) {
                    return v;
                }
            }
        }

        return null;
    }

    private static void updateCapacities(Graph graph) {
        List<Edge> edges = new ArrayList<>(graph.getEdgeList());
        edges.forEach(e ->
        {
            if (e.getCost() < 0.0) {
                updateResidualEdge(graph, e, e.getCapacity());
                updateBalances(e, e.getCapacity());
            }
        });
    }

    private static void updateBalances(Edge edge, double capacity) {
        addBalance(edge.getStart(), capacity);
        addBalance(edge.getEnd(), capacity * -1);
    }

    private static void addBalance(Vertex vertex, double balance) {
        double oldBalance = relevantBalances.get(vertex.getId());
        relevantBalances.put(vertex.getId(), oldBalance + balance);
    }

    private static void initRelevantBalances(Graph graph) {
        relevantBalances = new HashMap<>();
        for (Vertex v : graph.getVertexList()) {
            relevantBalances.put(v.getId(), 0.0);
        }
    }

    private static double calculateGamma(double a, double b, double c) {
        double gamma = a;
        if (gamma > b) {
            gamma = b;
        }
        if (gamma > c) {
            gamma = c;
        }

        return gamma;
    }
}
