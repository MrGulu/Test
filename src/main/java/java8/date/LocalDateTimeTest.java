package java8.date;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;

@Slf4j
public class LocalDateTimeTest {

    @Test
    public void test1() {
        LocalDateTime localDateTime = LocalDateTime.of(2019, 12, 24, 16, 31, 30);

        //2019-12-24T16:31:30
        System.out.println(localDateTime);
        //2019
        System.out.println(localDateTime.get(ChronoField.YEAR));
        //2
        System.out.println(localDateTime.get(ChronoField.DAY_OF_WEEK));
        //24
        System.out.println(localDateTime.get(ChronoField.DAY_OF_MONTH));
        //358
        System.out.println(localDateTime.get(ChronoField.DAY_OF_YEAR));
        //??  3
        System.out.println(localDateTime.get(ChronoField.ALIGNED_DAY_OF_WEEK_IN_MONTH));
        //??  1
        System.out.println(localDateTime.get(ChronoField.ALIGNED_DAY_OF_WEEK_IN_YEAR));
        //4(一个月中的第四个周)
        System.out.println(localDateTime.get(ChronoField.ALIGNED_WEEK_OF_MONTH));
        //52（一年中的第52个周）
        System.out.println(localDateTime.get(ChronoField.ALIGNED_WEEK_OF_YEAR));
        //2019
        System.out.println(localDateTime.get(ChronoField.YEAR_OF_ERA));
        //1
        System.out.println(localDateTime.get(ChronoField.ERA));

        //1
        System.out.println(localDateTime.get(ChronoField.AMPM_OF_DAY));
        //4
        System.out.println(localDateTime.get(ChronoField.CLOCK_HOUR_OF_AMPM));
        //16
        System.out.println(localDateTime.get(ChronoField.CLOCK_HOUR_OF_DAY));
//        System.out.println(localDateTime.get(ChronoField.EPOCH_DAY));
        //4
        System.out.println(localDateTime.get(ChronoField.HOUR_OF_AMPM));
        //16
        System.out.println(localDateTime.get(ChronoField.HOUR_OF_DAY));
//        System.out.println(localDateTime.get(ChronoField.INSTANT_SECONDS));
//        System.out.println(localDateTime.get(ChronoField.MICRO_OF_DAY));
        //0 （一分钟中的多少微秒）
        System.out.println(localDateTime.get(ChronoField.MICRO_OF_SECOND));
        //59490000 （一天中的多少毫秒）
        System.out.println(localDateTime.get(ChronoField.MILLI_OF_DAY));
        //0 （一分钟中的多少毫秒）
        System.out.println(localDateTime.get(ChronoField.MILLI_OF_SECOND));
        //991 （一天中的多少分钟）
        System.out.println(localDateTime.get(ChronoField.MINUTE_OF_DAY));
        //31 （一小时中的多少分钟）
        System.out.println(localDateTime.get(ChronoField.MINUTE_OF_HOUR));
//        System.out.println(localDateTime.get(ChronoField.NANO_OF_DAY));
        //0 （一分钟中的多少纳秒）
        System.out.println(localDateTime.get(ChronoField.NANO_OF_SECOND));
//        System.out.println(localDateTime.get(ChronoField.OFFSET_SECONDS));
//        System.out.println(localDateTime.get(ChronoField.PROLEPTIC_MONTH));
        //59490 （一天中的多少秒）
        System.out.println(localDateTime.get(ChronoField.SECOND_OF_DAY));
        //30 （一分钟中的多少秒）
        System.out.println(localDateTime.get(ChronoField.SECOND_OF_MINUTE));
    }

    /**
     * yyyy-MM-dd HH:mm:ss
     *
     * @description 通过LocalDateTime获取当前时间，并格式化成指定格式字符串
     * LocalDateTime.toString()方法默认格式 2019-12-24T16:44:20，不要使用，要转化！
     * 若要想将当前时间格式化成此格式的字符串，则使用DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
     * 将其格式化
     */
    @Test
    public void test2() {
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        System.out.println(localDateTime);
        System.out.println(localDateTime.format(formatter));
    }

    /**
     * @description 通过指定格式字符串，格式化成LocalDateTime。
     * 因为LocalDate默认格式 2019-12-24T16:44:20，不易读，而且一般字符串格式yyyy-MM-dd HH:mm:ss，
     * 所以需要使用DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")格式化。
     * <p>
     * <p>
     * 注意parse方法是静态方法直接通过类名调用，而format方法是使用的对象调用！
     * 并且使用format后直接就是字符串类型，
     * 而使用parse后，得到的是LocalDateTime对象，需要调用toString()方法得到字符串
     */
    @Test
    public void test3() {
        String s = "2019-12-24 16:44:20";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        //下面无法转化，如果想转化要是2019-12-24T16:44:20这种形式字符串
//        LocalDateTime parse = LocalDateTime.parse(s);
        LocalDateTime parse1 = LocalDateTime.parse(s, formatter);
        System.out.println(parse1.toString());

        s = "2019/12/23 16:44:20";
        //使用非默认的yyyy-MM-dd格式
        System.out.println("******************************************************");
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        System.out.println(LocalDateTime.parse(s, formatter1));
    }

    /**
     * @description 通过localDate对象获取年月日时分秒，一年中的第几天，一个周的周几
     */
    @Test
    public void test4() {
        LocalDateTime localDate = LocalDateTime.now();
        int year = localDate.getYear();
        int month = localDate.getMonthValue();
//        int month1 = localDate.getMonth().getValue();
        int day = localDate.getDayOfMonth();
        int hour = localDate.getHour();
        int minute = localDate.getMinute();
        int second = localDate.getSecond();
        int dayOfYear = localDate.getDayOfYear();
        int dayOfWeek = localDate.getDayOfWeek().getValue();
        log.info("{}年{}月{}日{}时{}分{}秒,一年中的第{}天，周{}", year, month, day, hour, minute, second, dayOfYear, dayOfWeek);

        int year2 = localDate.get(ChronoField.YEAR);
        int month2 = localDate.get(ChronoField.MONTH_OF_YEAR);
        int day2 = localDate.get(ChronoField.DAY_OF_MONTH);
        int hour2 = localDate.get(ChronoField.HOUR_OF_DAY);
        int minute2 = localDate.get(ChronoField.MINUTE_OF_HOUR);
        int second2 = localDate.get(ChronoField.SECOND_OF_MINUTE);
        int dayOfYear2 = localDate.get(ChronoField.DAY_OF_YEAR);
        int dayOfWeek2 = localDate.get(ChronoField.DAY_OF_WEEK);

        int minuteOfDay = localDate.get(ChronoField.MINUTE_OF_DAY);
        int secondOfDay = localDate.get(ChronoField.SECOND_OF_DAY);
        log.info("{}年{}月{}日{}时{}分{}秒,一年中的第{}天,一天中的第{}分钟,一天中的第{}秒,周{}", year2, month2, day2, hour2, minute2, second2, dayOfYear2, minuteOfDay, secondOfDay, dayOfWeek2);
    }

}
