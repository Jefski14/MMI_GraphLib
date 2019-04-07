package helper;

import entity.Edge;
import entity.Vertex;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GraphParser {
    public List<Vertex> readUndirectedEdgeListFromFile(final String fileName) {
        final ArrayList<Vertex> vertices = new ArrayList<Vertex>();
        try {
            final FileReader fileReader = new FileReader(fileName);
            final BufferedReader reader = new BufferedReader(fileReader);

            String firstLine = reader.readLine();
            int nOfVertices = Integer.parseInt(firstLine);
            for (int i = 0; i < nOfVertices; i++) {
                vertices.add(new Vertex(i));
            }

            final String delimiter = "\t";
            String currentLine;
            while ((currentLine = reader.readLine()) != null) {
                final String[] points = currentLine.split(delimiter);
                final int p1 = Integer.parseInt(points[0]);
                final int p2 = Integer.parseInt(points[1]);

                Edge e = new Edge(vertices.get(p1), vertices.get(p2));
                vertices.get(p1).addEdge(e);
                vertices.get(p2).addEdge(e);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return vertices;
    }

    public List<Vertex> readDirectedEdgeListFromFile(final String fileName) {
        final ArrayList<Vertex> vertices = new ArrayList<Vertex>();
        try {
            final FileReader fileReader = new FileReader(fileName);
            final BufferedReader reader = new BufferedReader(fileReader);

            String firstLine = reader.readLine();
            int nOfVertices = Integer.parseInt(firstLine);
            for (int i = 0; i < nOfVertices; i++) {
                vertices.add(new Vertex(i));
            }

            final String delimiter = "\t";
            String currentLine;
            while ((currentLine = reader.readLine()) != null) {
                final String[] points = currentLine.split(delimiter);
                final int p1 = Integer.parseInt(points[0]);
                final int p2 = Integer.parseInt(points[1]);

                vertices.get(p1).addEdge(new Edge(vertices.get(p1), vertices.get(p2)));
                vertices.get(p2).addEdge(new Edge(vertices.get(p2), vertices.get(p1)));
            }
            reader.close();
            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return vertices;
    }

    public Map<Integer, Vertex> readUndirectedEdgeListFromFileAsMap(final String fileName) {
        final Map<Integer, Vertex> vertices = new HashMap<Integer, Vertex>();
        try {
            final FileReader fileReader = new FileReader(fileName);
            final BufferedReader reader = new BufferedReader(fileReader);

            String firstLine = reader.readLine();
            int nOfVertices = Integer.parseInt(firstLine);
            for (int i = 0; i < nOfVertices; i++) {
                vertices.put(i, new Vertex(i));
            }

            final String delimiter = "\t";
            String currentLine;
            while ((currentLine = reader.readLine()) != null) {
                final String[] points = currentLine.split(delimiter);
                final int p1 = Integer.parseInt(points[0]);
                final int p2 = Integer.parseInt(points[1]);

                vertices.get(p1).addEdge(new Edge(vertices.get(p1), vertices.get(p2)));
                vertices.get(p2).addEdge(new Edge(vertices.get(p2), vertices.get(p1)));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return vertices;
    }
}
