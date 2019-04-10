import startup.AlgorithmSelector;
import startup.AsciiGenerator;
import startup.FileChooser;

import java.awt.*;
import java.io.File;

public class Main {

    public static void main(String[] args) {

        //Drawing the ASCII
        AsciiGenerator asciiGenerator = new AsciiGenerator();
        asciiGenerator.drawString("MMI-Praktikum", "*", asciiGenerator.new Settings(Font.getFont("Arial Bold"), 100, 20), true);

        String restart = "n";
        File selectedFile = null;
        do {
            if (restart.equals("n")) {
                //select file for import
                selectedFile = FileChooser.chooseFile();
                if (selectedFile == null) {
                    return;
                }
            }

            AlgorithmSelector.run(selectedFile);
            restart = AlgorithmSelector.restart();

        } while (restart.equals("r") || restart.equals("n"));
    }
}
