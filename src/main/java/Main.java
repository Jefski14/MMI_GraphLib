import entity.Vertex;
import helper.GraphParser;
import startup.AsciiGenerator;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.net.URLDecoder;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        //Drawing the ASCII
        AsciiGenerator asciiGenerator = new AsciiGenerator();
        asciiGenerator.drawString("MMI-Praktikum", "*", asciiGenerator.new Settings(Font.getFont("Arial Bold"), 100, 20), true);

        //select file for import
        File selectedFile = chooseFile();
        if(selectedFile == null){
            return;
        }

        //Importing graph and measuring time
        long startTime = System.currentTimeMillis();
        System.out.println("Starting import of graph...");
        List<Vertex> vertices = GraphParser.readEdgeListFromFile(selectedFile.getAbsolutePath(), false);
        long estimatedTime = System.currentTimeMillis() - startTime;
        System.out.println("Importing graph took: " + estimatedTime + " ms");

        //Sample Outputs
        System.out.println("Imported vertices: " + vertices.size());
        System.out.println("Vertex 0 has: " + vertices.get(0).getAttachedEdges().size() + " Edges!");
    }

    private static File chooseFile(){
        JFrame frame = new JFrame("File chooser");
        JFileChooser fileChooser = new JFileChooser();
        String decodedPath = null;
        try {
            String jarPath = Main.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
            decodedPath = URLDecoder.decode(jarPath, "UTF-8");
        } catch (URISyntaxException | UnsupportedEncodingException e) {
            System.out.println("Error on fetching current directory !");
            return null;
        }
        fileChooser.setCurrentDirectory(new File(decodedPath));

        int result = fileChooser.showOpenDialog(frame);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            System.out.println("Selected file: " + selectedFile.getAbsolutePath());
            return selectedFile;
        }
        return null;
    }
}
