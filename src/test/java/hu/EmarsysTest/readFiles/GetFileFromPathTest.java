package hu.EmarsysTest.readFiles;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class GetFileFromPathTest {

    private GetFileFromPath getFileFromPath;

    @Before
    public void setUpFiles() {
        getFileFromPath = new GetFileFromPath();
    }

    @Test(expected = NullPointerException.class)
    public void testCalculateDueDateFilePath() throws IOException {
        String fileName = "hello.txt";
        getFileFromPath.getFilePath(fileName);
    }
}
