package hu.EmarsysTest.calculation;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;

public class CalculateDueDateTest {

    private CalculateDueDate calculateDueDate;
//    private Calculation calculation;

    @Before
    public void setVariable() {
        calculateDueDate = new CalculateDueDate();
    }

    @Test
    public void testCalculateDueDateForOctober() {
        calculateDueDate.calculate(LocalDate.of(2019, 10, 22), LocalTime.of(12, 25, 12));
        Assert.assertEquals(2019, calculateDueDate.getCalculation().getYear());
        Assert.assertEquals(10, calculateDueDate.getCalculation().getMounth());
        Assert.assertEquals(25, calculateDueDate.getCalculation().getDay());
        Assert.assertEquals(12, calculateDueDate.getCalculation().getHour());
        Assert.assertEquals(25, calculateDueDate.getCalculation().getMinute());
    }

    @Test
    public void testCalculateDueDateForChrismash() {
        calculateDueDate.calculate(LocalDate.of(2019, 12, 20), LocalTime.of(12, 25, 12));
        Assert.assertEquals(2019, calculateDueDate.getCalculation().getYear());
        Assert.assertEquals(12, calculateDueDate.getCalculation().getMounth());
        Assert.assertEquals(30, calculateDueDate.getCalculation().getDay());
        Assert.assertEquals(12, calculateDueDate.getCalculation().getHour());
        Assert.assertEquals(25, calculateDueDate.getCalculation().getMinute());
    }

    @Test(expected = DateTimeException.class)
    public void testCalculateDueDateForHolidaySaturday() {
        calculateDueDate.calculate(LocalDate.of(2019, 06, 29), LocalTime.of(12, 25, 12));
    }

    @Test(expected = DateTimeException.class)
    public void testCalculateDueDateForHolidaySunday() {
        calculateDueDate.calculate(LocalDate.of(2019, 06, 30), LocalTime.of(12, 25, 12));
    }

    @Test(expected = DateTimeException.class)
    public void testCalculateDueDateForBeforeEightOClock() {
        calculateDueDate.calculate(LocalDate.of(2019, 06, 30), LocalTime.of(8, 22, 12));
    }

    @Test(expected = DateTimeException.class)
    public void testCalculateDueDateForAfterSeventeenOClock() {
        calculateDueDate.calculate(LocalDate.of(2019, 06, 30), LocalTime.of(18, 26, 12));
    }
}
