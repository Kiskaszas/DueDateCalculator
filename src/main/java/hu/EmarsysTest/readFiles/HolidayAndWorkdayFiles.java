package hu.EmarsysTest.readFiles;

import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.stream.Collectors;

public class HolidayAndWorkdayFiles {

    private GetFileFromPath getFileFromPath;
    private final String HOLIDAY_FILE_PATH = "Holidays.txt";
    private final String REQUIMENT_WORKDAYS_FILE_PATH = "Workdays.txt";

    public HolidayAndWorkdayFiles() {
        this.getFileFromPath = new GetFileFromPath();
    }

    public List<String> getHolidays() throws IOException {
        return getFileLines(HOLIDAY_FILE_PATH);
    }

    public List<String> getRequimentWorkDays() throws IOException {
        return getFileLines(REQUIMENT_WORKDAYS_FILE_PATH);
    }

    private List<String> getFileLines(String wordaysFilePath) throws IOException {
        try {
            return Files.readAllLines(this.getFileFromPath.getFilePath(wordaysFilePath)).stream().collect(Collectors.toList());
        } catch (IOException e) {
            throw e;
        }
    }
}
