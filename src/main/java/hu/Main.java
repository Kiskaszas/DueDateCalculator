package hu;

import hu.EmarsysTest.calculation.CalculateDueDate;
import hu.EmarsysTest.validation.DateAndTimeValidator;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Main {

    private static CalculateDueDate calculateDueDate;
    private static DateAndTimeValidator dateAndTimeValidator;

    private static final String PATTERN_ERROR = "Wrong report time. Please follow the pattern. Actual year, valid Mounth, Day, hour and minutes";
    private static final String INFORMATION_MESSAGE = "Finding a bug report. The q character is quit.\n" +
            "The pattern is Actual year, valid Mounth, Day, hour and minutes (YYYY-MM-DD HH:mm): ";

    public static void main(String[] args) {
        dateAndTimeValidator = new DateAndTimeValidator();
        boolean stayIn = true;
        LocalDateTime localDateTime = null;
        while (stayIn) {
            try {
                System.out.print(INFORMATION_MESSAGE);
                Scanner scanner = new Scanner(System.in);
                String input = scanner.nextLine();

                if (input.equals("q")){
                    System.exit(-1);
                }

                localDateTime = LocalDateTime.parse(input, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

                stayIn = !dateAndTimeValidator.checkDateAndTime(localDateTime);
            }catch (DateTimeParseException ex){
                stayIn = true;
                System.out.println(PATTERN_ERROR);
            }
        }
        if (localDateTime != null) {
            calculateDueDate = new CalculateDueDate();
            calculateDueDate.calculate(localDateTime.toLocalDate(), localDateTime.toLocalTime());
        }

    }

}