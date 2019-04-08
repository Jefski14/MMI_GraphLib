package helper;

import entity.Edge;
import entity.Vertex;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
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
    public static List<Vertex> importGraphFromFile(final String fileName, boolean directed) {
        final ArrayList<Vertex> vertices = new ArrayList<>();
        try {
            final FileReader fileReader = new FileReader(fileName);
            final BufferedReader reader = new BufferedReader(fileReader);

            // Read first line
            String firstLine = reader.readLine();
            int nOfVertices = Integer.parseInt(firstLine);
            for (int i = 0; i < nOfVertices; i++) {
                vertices.add(new Vertex(i)); // Initialize list
            }

            final String delimiter = "\t";
            String currentLine;
            while ((currentLine = reader.readLine()) != null) {
                final String[] points = currentLine.split(delimiter);
                final int p1 = Integer.parseInt(points[0]);
                final int p2 = Integer.parseInt(points[1]);

                // Add edge to vertex
                if (directed) {
                    vertices.get(p1).addEdge(new Edge(vertices.get(p1), vertices.get(p2)));
                    vertices.get(p2).addEdge(new Edge(vertices.get(p2), vertices.get(p1)));
                } else {
                    Edge e = new Edge(vertices.get(p1), vertices.get(p2));
                    vertices.get(p1).addEdge(e);
                    vertices.get(p2).addEdge(e);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return vertices;
    }
}
