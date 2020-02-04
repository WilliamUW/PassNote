import java.io.File;
import javax.swing.JFileChooser;

/**
 * Write a description of class Main here.
 *
 * @author William Wang
 * @version 1.0 2019.02.11
 */
public class FileChooser {
    static JFileChooser jfc;
    static String directory = System.getProperty("user.dir");
    static String fileName = "AccountInfo\\accountInfo.txt";
    static String defaultPath = directory + File.separator + "Files";
    
    public FileChooser()
    {
        jfc = new JFileChooser(defaultPath);
    }

    public static String getPath() {
        String path = "";

        int returnValue = jfc.showOpenDialog(null);

        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = jfc.getSelectedFile();
            path = selectedFile.getAbsolutePath();
            // System.out.println(path);
            return path;
        }
        return "";
    }
}