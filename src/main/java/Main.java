import entity.Vertex;
import helper.GraphParser;
import startup.AlgorithmSelector;
import startup.AsciiGenerator;
import startup.FileChooser;

import java.awt.*;
import java.io.File;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        //Drawing the ASCII
        AsciiGenerator asciiGenerator = new AsciiGenerator();
        asciiGenerator.drawString("MMI-Praktikum", "*", asciiGenerator.new Settings(Font.getFont("Arial Bold"), 100, 20), true);

        //select file for import
        File selectedFile = FileChooser.chooseFile();
        if (selectedFile == null) {
            return;
        }

        boolean directed = AlgorithmSelector.selectDirected();
        //Importing graph and measuring time
        List<Vertex> vertices = GraphParser.importGraphFromFile(selectedFile.getAbsolutePath(), directed);

        //Select algorithm and start vertex
        Integer[] selection = AlgorithmSelector.showSelectionMenu(vertices);
        if (selection != null) {
            AlgorithmSelector.startAlgorithm(selection, vertices, directed);
        }
    }
}
