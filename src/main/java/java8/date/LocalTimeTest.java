package java8.date;

import org.junit.Test;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;

public class LocalTimeTest {

    @Test
    public void test1() {
        LocalTime localTime = LocalTime.now();
        System.out.println(localTime);

        System.out.println(localTime.get(ChronoField.AMPM_OF_DAY));
        System.out.println(localTime.get(ChronoField.CLOCK_HOUR_OF_AMPM));
        System.out.println(localTime.get(ChronoField.CLOCK_HOUR_OF_DAY));
        System.out.println(localTime.get(ChronoField.HOUR_OF_AMPM));
        System.out.println(localTime.get(ChronoField.HOUR_OF_DAY));
        System.out.println(localTime.get(ChronoField.MICRO_OF_SECOND));
        System.out.println(localTime.get(ChronoField.MILLI_OF_DAY));
        System.out.println(localTime.get(ChronoField.MILLI_OF_SECOND));
        System.out.println(localTime.get(ChronoField.MINUTE_OF_DAY));
        System.out.println(localTime.get(ChronoField.MINUTE_OF_HOUR));
        System.out.println(localTime.get(ChronoField.NANO_OF_SECOND));
        System.out.println(localTime.get(ChronoField.SECOND_OF_DAY));
        System.out.println(localTime.get(ChronoField.SECOND_OF_MINUTE));
    }

    @Test
    public void test2() {
        LocalTime localTime = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        System.out.println(localTime.format(formatter));

        System.out.println(localTime);
        System.out.println(localTime.toString().equals(localTime.format(formatter)));


    }
}
