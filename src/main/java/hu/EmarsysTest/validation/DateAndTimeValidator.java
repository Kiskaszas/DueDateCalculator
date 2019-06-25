package hu.EmarsysTest.validation;

import hu.EmarsysTest.readFiles.HolidayAndWorkdayFiles;

import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDateTime;

public class DateAndTimeValidator {

    private static final String ERROR_ITS_HOLIDAY = "It's holiday. Bad error notification time.";
    private static final String ERROR_ITS_EARLIER = "Notification earlier than 9 AM. Bad error notification time.";
    private static final String ERROR_ITS_LATER = "Notification later than 17 PM. Bad error notification time.";

    private HolidayAndWorkdayFiles holidayAndWorkdayFile;

    public DateAndTimeValidator() {
        holidayAndWorkdayFile = new HolidayAndWorkdayFiles();
    }

    public void checkCalculationTime(LocalDateTime localDateTime) throws IOException {
        if (holidayAndWorkdayFile.getHolidays().contains(localDateTime.toLocalDate().toString())) {
            throw new DateTimeException(ERROR_ITS_HOLIDAY);
        } else if (localDateTime.getHour() < 9) {
            throw new DateTimeException(ERROR_ITS_EARLIER);
        } else if (localDateTime.getHour() > 17) {
            throw new DateTimeException(ERROR_ITS_LATER);
        } else if (localDateTime.getDayOfWeek().getValue() > 5 && !holidayAndWorkdayFile.getRequimentWorkDays()
                .contains(localDateTime.toString())) {
            throw new DateTimeException(ERROR_ITS_HOLIDAY);
        }
    }

    public boolean checkDateAndTime(LocalDateTime localDateTime){
        return localDateTime.getYear() == LocalDateTime.now().getYear()
                && localDateTime.getMonth().getValue() < 12
                && localDateTime.getDayOfMonth() < 31
                && localDateTime.getHour() < 24
                && localDateTime.getMinute() < 60;
    }
}
