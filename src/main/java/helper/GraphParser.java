package helper;

import entity.Edge;
import entity.Graph;
import entity.Vertex;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class GraphParser {

    /**
     * Imports a graph.txt file and converts data
     * into {@link Vertex} and {@link Edge} Objects
     *
     * @param fileName path to file
     * @param directed flag if edges should be imported as directed or undirected
     * @return List of {@link Vertex} Objects
     */
    public static Graph importGraphFromFile(final String fileName, boolean directed) {

//        long startTime = System.currentTimeMillis();
//        System.out.println("Starting import of graph...");
        Graph dracula = new Graph();
        try {
            final FileReader fileReader = new FileReader(fileName);
            final BufferedReader reader = new BufferedReader(fileReader);

            // Read first line
            String firstLine = reader.readLine();
            int nOfVertices = Integer.parseInt(firstLine); // When we represent the graph only by the edge list we lose the standalone vertices
            for (int i = 0; i < nOfVertices; i++) {
                dracula.getVertexList().add(new Vertex(i)); // Initialize list
            }

            final String delimiter = "\t";
            String currentLine;
            while ((currentLine = reader.readLine()) != null) {
                final String[] points = currentLine.split(delimiter);
                final int p1 = Integer.parseInt(points[0]);
                final int p2 = Integer.parseInt(points[1]);
                BigDecimal cost = BigDecimal.valueOf(0.0);
                BigDecimal capacity = BigDecimal.valueOf(0.0);
                if (points.length == 3) {
                    cost = BigDecimal.valueOf(Double.parseDouble(points[2]));
                }
                if (points.length == 4) {
                    capacity = BigDecimal.valueOf(Double.parseDouble(points[3]));
                }

                // Add edge to vertex
                if (directed) {
                    Edge p1p2 = new Edge(dracula.getVertexList().get(p1), dracula.getVertexList().get(p2), cost, capacity);
                    dracula.getVertexList().get(p1).addEdge(p1p2);
                    dracula.getEdgeList().add(p1p2);

                    //Fill edge map
                    if (dracula.getEdgeMap().containsKey(p1)) {
                        List<Edge> edges = dracula.getEdgeMap().get(p1);
                        edges.add(new Edge(new Vertex(p1), new Vertex(p2), cost, capacity));
                        dracula.getEdgeMap().put(p1, edges);
                    } else {
                        ArrayList<Edge> edges = new ArrayList<>();
                        edges.add(new Edge(new Vertex(p1), new Vertex(p2), cost, capacity));
                        dracula.getEdgeMap().put(p1, edges);
                    }
                } else {
                    // Add edge in both directions
                    Edge p1p2 = new Edge(dracula.getVertexList().get(p1), dracula.getVertexList().get(p2), cost, capacity);
                    Edge p2p1 = new Edge(dracula.getVertexList().get(p2), dracula.getVertexList().get(p1), cost, capacity);
                    dracula.getVertexList().get(p1).addEdge(p1p2);
                    dracula.getVertexList().get(p2).addEdge(p2p1);
                    dracula.getEdgeList().add(p1p2);
                    dracula.getEdgeList().add(p2p1);

                    //Fill edge map
                    if (dracula.getEdgeMap().containsKey(p1)) {
                        List<Edge> edges = dracula.getEdgeMap().get(p1);
                        edges.add(new Edge(new Vertex(p1), new Vertex(p2), cost, capacity));
                        dracula.getEdgeMap().put(p1, edges);
                    } else {
                        ArrayList<Edge> edges = new ArrayList<>();
                        edges.add(new Edge(new Vertex(p1), new Vertex(p2), cost, capacity));
                        dracula.getEdgeMap().put(p1, edges);
                    }

                    //Fill edge map
                    if (dracula.getEdgeMap().containsKey(p2)) {
                        List<Edge> edges = dracula.getEdgeMap().get(p2);
                        edges.add(new Edge(new Vertex(p2), new Vertex(p1), cost, capacity));
                        dracula.getEdgeMap().put(p2, edges);
                    } else {
                        ArrayList<Edge> edges = new ArrayList<>();
                        edges.add(new Edge(new Vertex(p2), new Vertex(p1), cost, capacity));
                        dracula.getEdgeMap().put(p2, edges);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
//        long estimatedTime = System.currentTimeMillis() - startTime;
//        System.out.println("Importing graph took: " + estimatedTime + " ms");
        return dracula;
    }
}
