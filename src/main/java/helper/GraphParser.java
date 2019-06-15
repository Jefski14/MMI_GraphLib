package helper;

import entity.Edge;
import entity.Graph;
import entity.Vertex;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class GraphParser {

    /**
     * Imports a graph.txt file and converts data
     * into {@link Vertex} and {@link Edge} Objects
     *
     * @param fileName    path to file
     * @param directed    flag if edges should be imported as directed or undirected
     * @param hasCapacity
     * @return List of {@link Vertex} Objects
     */
    public static Graph importGraphFromFile(final String fileName, boolean directed, boolean hasCapacity) {
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
                Double cost = 0.0;
                Double capacity = 0.0;
                if (points.length == 3) {
                    if (hasCapacity) {
                        capacity = Double.parseDouble(points[2]);
                    } else {
                        cost = Double.parseDouble(points[2]);
                    }
                }

                // Add edge to vertex
                if (directed) {
                    Edge p1p2 = new Edge(dracula.getVertexList().get(p1), dracula.getVertexList().get(p2), cost, capacity);
                    dracula.getVertexList().get(p1).addEdge(p1p2);
                    dracula.getEdgeList().add(p1p2);
                } else {
                    // Add edge in both directions
                    Edge p1p2 = new Edge(dracula.getVertexList().get(p1), dracula.getVertexList().get(p2), cost, capacity);
                    Edge p2p1 = new Edge(dracula.getVertexList().get(p2), dracula.getVertexList().get(p1), cost, capacity);
                    dracula.getVertexList().get(p1).addEdge(p1p2);
                    dracula.getVertexList().get(p2).addEdge(p2p1);
                    dracula.getEdgeList().add(p1p2);
                    dracula.getEdgeList().add(p2p1);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dracula;
    }


    public static Graph importGraphWithBalance(final String fileName) {

        Graph dracula = new Graph();
        try {
            final FileReader fileReader = new FileReader(fileName);
            final BufferedReader reader = new BufferedReader(fileReader);

            // Read first line = number of vertices
            String firstLine = reader.readLine();
            int nOfVertices = Integer.parseInt(firstLine); // When we represent the graph only by the edge list we lose the standalone vertices
            for (int i = 0; i < nOfVertices; i++) {
                dracula.getVertexList().add(new Vertex(i)); // Initialize list
            }

            //Read balances for vertices
            for (int i = 0; i < nOfVertices; i++) {
                String currentBalance = reader.readLine();
                double balance = Double.parseDouble(currentBalance);
                dracula.getVertexList().get(i).setBalance(balance);
            }

            //Read following lines with fromVertex, toVertex, cost and capacity
            final String delimiter = "\t";
            String currentLine;
            while ((currentLine = reader.readLine()) != null) {
                final String[] points = currentLine.split(delimiter);
                final int fromVertex = Integer.parseInt(points[0]);
                final int toVertex = Integer.parseInt(points[1]);
                final double cost = Integer.parseInt(points[2]);
                final double capacity = Integer.parseInt(points[3]);

                // Add edge to vertex
                Edge p1p2 = new Edge(dracula.getVertexList().get(fromVertex), dracula.getVertexList().get(toVertex), cost, capacity);
                dracula.getVertexList().get(fromVertex).addEdge(p1p2);
                dracula.getEdgeList().add(p1p2);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dracula;
    }
}
