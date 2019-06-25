package hu.EmarsysTest.readFiles;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class GetFileFromPath {

    private static final String ERROR_INPUT_FILE_PATH_TEXT = "Input file is bad.";

    public Path getFilePath(String fileName) {
        try {
            return Paths.get(getClass().getClassLoader().getResource(fileName).toURI());
        } catch (URISyntaxException e) {
            System.err.print(ERROR_INPUT_FILE_PATH_TEXT);
            e.printStackTrace();
            return null;
        }

    }
}
