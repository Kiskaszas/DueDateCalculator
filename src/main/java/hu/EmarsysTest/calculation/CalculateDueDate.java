package hu.EmarsysTest.calculation;

import hu.EmarsysTest.readFiles.HolidayAndWorkdayFiles;
import hu.EmarsysTest.validation.DateAndTimeValidator;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.TextStyle;
import java.util.*;

public class CalculateDueDate {

    private HolidayAndWorkdayFiles holidayAndWorkdayFile;
    private DateAndTimeValidator dateAndTimeValidator;

    private List<String> holidays;
    private List<String> workDdays;

    private Calculation calculation;

    public CalculateDueDate() {
        this.holidayAndWorkdayFile = new HolidayAndWorkdayFiles();
        this.dateAndTimeValidator = new DateAndTimeValidator();
        this.calculation = new Calculation();
        setHolidayAndWorkDays();
    }

    private void setHolidayAndWorkDays() {
        try {
            holidays = holidayAndWorkdayFile.getHolidays();
            workDdays = holidayAndWorkdayFile.getRequimentWorkDays();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void calculate(LocalDate date, LocalTime now) {
        calculation.calculate(date, now);
        writeOut();
    }

    private void writeOut() {
        LocalDateTime localDateTime = createLocatDateTime();

        StringBuilder sb = new StringBuilder();
        sb.append(localDateTime.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.getDefault()))
                .append(" ")
                .append(localDateTime.getMonth().getValue())
                .append(".")
                .append(localDateTime.getDayOfMonth())
                .append(".")
                .append(" ").append(localDateTime.getHour()).append(":")
                .append(localDateTime.getMinute())
                .append("\n");
        System.out.println(sb.toString());
    }

    private LocalDateTime createLocatDateTime(){
        return LocalDateTime.of(
                calculation.getYear(),
                calculation.getMounth(),
                calculation.getDay(),
                calculation.getHour(),
                calculation.getMinute()
        );
    }

    public Calculation getCalculation() {
        return calculation;
    }
}