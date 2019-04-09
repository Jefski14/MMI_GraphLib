import startup.AlgorithmSelector;
import startup.AsciiGenerator;

import java.awt.*;

public class Main {

    public static void main(String[] args) {

        //Drawing the ASCII
        AsciiGenerator asciiGenerator = new AsciiGenerator();
        asciiGenerator.drawString("MMI-Praktikum", "*", asciiGenerator.new Settings(Font.getFont("Arial Bold"), 100, 20), true);

        String restart = null;
        do {
            AlgorithmSelector.run();
            restart = AlgorithmSelector.restart();

        } while (restart.equals("r"));
    }
}
