import entity.Vertex;
import helper.GraphParser;
import startup.AsciiGenerator;

import java.awt.*;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        //Drawing the ASCII
        AsciiGenerator asciiGenerator = new AsciiGenerator();
        asciiGenerator.drawString("MMI-Praktikum", "*", asciiGenerator.new Settings(Font.getFont("Arial Bold"), 100, 20), true);


        //Importing graph and measuring time
        String path = "src/main/resources/p1/Graph1.txt";
        long startTime = System.currentTimeMillis();
        System.out.println("Starting import of graph...");
        List<Vertex> vertices = GraphParser.readEdgeListFromFile(path, false);
        long estimatedTime = System.currentTimeMillis() - startTime;
        System.out.println("Importing graph took: " + estimatedTime + " ms");

        //Sample Outputs
        System.out.println("Imported vertices: " + vertices.size());
        System.out.println("Vertex 0 has: " + vertices.get(0).getAttachedEdges().size() + " Edges!");
    }
}
