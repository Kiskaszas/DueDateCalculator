package hu.EmarsysTest.calculation;

import hu.EmarsysTest.readFiles.HolidayAndWorkdayFiles;
import hu.EmarsysTest.validation.DateAndTimeValidator;

import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Calculation {

    private final int PLUSDAYS = 2;
    private int countPlusDays = 0;
    private int year, mounth, day, hour, minute;
    private DateAndTimeValidator dateAndTimeValidator;
    private HolidayAndWorkdayFiles holidayAndWorkdayFile;

    public Calculation() {
        this.dateAndTimeValidator = new DateAndTimeValidator();
        this.holidayAndWorkdayFile = new HolidayAndWorkdayFiles();
    }

    public void calculate(LocalDate date, LocalTime now) {
        try {
            countPlusDays = PLUSDAYS;
            LocalDateTime localDateTime = LocalDateTime.of(date, now);
            dateAndTimeValidator.checkCalculationTime(localDateTime);
            calulateFromValidDateTime(localDateTime);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void calulateFromValidDateTime(LocalDateTime localDateTime) throws IOException {
        if (localDateTime.toLocalDate().getDayOfWeek().getValue() < 7) {
            checkNextDay(localDateTime.toLocalDate(), 1);
            checkNextDay(localDateTime.toLocalDate(), 2);
            setDateVariables(localDateTime);
            countPlusDays = 0;
        } else {
            throw new DateTimeException("Bad error notification time.");
        }
    }

    private void checkNextDay(LocalDate date, int dayNum) throws IOException {
        if (countPlusDays != 2) {
            dayNum = countPlusDays;
        }
        while (!checkNextDayIsOk(date, dayNum)) {
            dayNum++;
            countPlusDays++;
        }
    }

    private boolean checkNextDayIsOk(LocalDate date, int nextDays) throws IOException {
        if (!holidayAndWorkdayFile.getHolidays().contains(date.plusDays(nextDays).toString())
                && !holidayAndWorkdayFile.getRequimentWorkDays().contains(date.plusDays(nextDays).toString())
                ) {
            if (!holidayAndWorkdayFile.getRequimentWorkDays().contains(date.plusDays(nextDays).toString())
                    && date.plusDays(nextDays).getDayOfWeek().getValue() > 5) {
                return false;
            }
            return true;
        } else {
            return false;
        }
    }
    protected void setDateVariables(LocalDateTime localDateTime) {
        this.year = localDateTime.plusDays(countPlusDays).getYear();
        this.mounth = localDateTime.plusDays(countPlusDays).getMonth().getValue();
        this.day = localDateTime.plusDays(countPlusDays).getDayOfMonth();
        this.hour = localDateTime.plusDays(countPlusDays).getHour();
        this.minute = localDateTime.plusDays(countPlusDays).getMinute();
    }

    public int getYear() {
        return year;
    }

    public int getMounth() {
        return mounth;
    }

    public int getDay() {
        return day;
    }

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }
}
