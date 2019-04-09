package startup;

import javax.swing.*;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.net.URLDecoder;

public class FileChooser {

    /**
     * Opens dialog for file import
     *
     * @return file to extract path from
     */
    public static File chooseFile() {
        JFileChooser chooser = new JFileChooser();

        String decodedPath = null;
        try {
            String jarPath = FileChooser.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
            decodedPath = URLDecoder.decode(jarPath, "UTF-8");
        } catch (URISyntaxException | UnsupportedEncodingException e) {
            System.out.println("Error on fetching current directory !");
            return null;
        }
        chooser.setCurrentDirectory(new File(decodedPath));
        chooser.setDialogTitle("Choose graph.txt");

        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            return chooser.getSelectedFile();
        } else {
            System.out.println("No file selected !");
            return null;
        }
    }
}
