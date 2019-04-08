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
        JFrame frame = new JFrame("File chooser");
        JFileChooser fileChooser = new JFileChooser();
        String decodedPath = null;
        try {
            String jarPath = FileChooser.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
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
