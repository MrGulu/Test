package java8.date;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;

@Slf4j
public class LocalDateTest {

    @Test
    public void test1() {
        LocalDate localDate = LocalDate.now();

        //2019-12-23
        System.out.println(localDate);
        //2019
        System.out.println(localDate.getYear());
        //DECEMBER  (getMonth()方法返回的是Month类)
        System.out.println(localDate.getMonth());
        //12 (获取到Month类之后，getValue获取int类型)
        System.out.println(localDate.getMonth().getValue());
        //12 (直接获取月份的int类型)
        System.out.println(localDate.getMonthValue());
        //357
        System.out.println(localDate.getDayOfYear());
        //23
        System.out.println(localDate.getDayOfMonth());
        //MONDAY ((getDayOfWeek()方法返回的是DayOfWeek类))
        System.out.println(localDate.getDayOfWeek());
        //1  (获取一周中的第几天，获取到DayOfWeek类之后，getValue获取int类型)
        System.out.println(localDate.getDayOfWeek().getValue());


        System.out.println("*******************************************");


        //2019 同localDate.getYear() 底层return year
        System.out.println(localDate.get(ChronoField.YEAR));
        //12 同localDate.getMonthValue() 底层return month
        System.out.println(localDate.get(ChronoField.MONTH_OF_YEAR));
        //357 同localDate.getDayOfYear() 底层getDayOfYear()
        System.out.println(localDate.get(ChronoField.DAY_OF_YEAR));
        //23 同localDate.getDayOfMonth() 底层return day
        System.out.println(localDate.get(ChronoField.DAY_OF_MONTH));
        //1 同localDate.getDayOfWeek().getValue() 底层getDayOfWeek().getValue()
        System.out.println(localDate.get(ChronoField.DAY_OF_WEEK));


        System.out.println("*******************************************");


        /**
         * 用于时代相关，例如公元前多少年
         */
        //2019 return (year >= 1 ? year : 1 - year)
        System.out.println(localDate.get(ChronoField.YEAR_OF_ERA));
        //1 return (year >= 1 ? 1 : 0)
        System.out.println(localDate.get(ChronoField.ERA));


        System.out.println("*******************************************");


        /**
         * 关于ALIGEND相关的还需研究
         */
        //??
        //2
        System.out.println(localDate.get(ChronoField.ALIGNED_DAY_OF_WEEK_IN_MONTH));
        //??
        //7
        System.out.println(localDate.get(ChronoField.ALIGNED_DAY_OF_WEEK_IN_YEAR));
        //4
        System.out.println(localDate.get(ChronoField.ALIGNED_WEEK_OF_MONTH));
        //51
        System.out.println(localDate.get(ChronoField.ALIGNED_WEEK_OF_YEAR));
    }

    /**
     * yyyy-MM-dd HH:mm:ss
     *
     * @description 通过LocalDate获取当前时间，并格式化成指定格式字符串
     * LocalDate.toString()方法默认格式 yyyy-MM-dd
     * 若要想将当前时间格式化成此格式的字符串，
     * 则localDate.toString()
     * 或localDate.format(formatter)
     * 两种方法实现效果一样。
     */
    @Test
    public void test2() {
        LocalDate localDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        System.out.println(localDate.toString());
        System.out.println(localDate.format(formatter));


        //使用非默认的yyyy-MM-dd格式
        System.out.println("******************************************************");
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        System.out.println(localDate.format(formatter1));
    }

    /**
     * @description 通过指定格式字符串，格式化成LocalDate。
     * 因为LocalDate默认格式 yyyy-MM-dd，所以如果字符串格式也为此格式，
     * 则LocalDate.parse(CharSequence text)
     * 或LocalDate.parse(CharSequence text, DateTimeFormatter formatter)
     * 两种方法实现效果一样。
     * <p>
     * 注意parse方法是静态方法直接通过类名调用，而format方法是使用的对象调用！
     * 并且使用format后直接就是字符串类型，
     * 而使用parse后，得到的是LocalDate对象，需要调用toString()方法得到字符串
     */
    @Test
    public void test3() {
        String s = "2019-12-23";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate parse = LocalDate.parse(s);
        LocalDate parse1 = LocalDate.parse(s, formatter);
        System.out.println(parse.toString());
        System.out.println(parse1.toString());

        s = "2019/12/23";
        //使用非默认的yyyy-MM-dd格式
        System.out.println("******************************************************");
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        System.out.println(LocalDate.parse(s, formatter1));
    }

    /**
     * @description 通过localDate对象获取年月日，一年中的第几天，一个周的周几
     */
    @Test
    public void test4() {
        LocalDate localDate = LocalDate.now();
        int year = localDate.getYear();
        int month = localDate.getMonthValue();
//        int month1 = localDate.getMonth().getValue();
        int day = localDate.getDayOfMonth();
        int dayOfYear = localDate.getDayOfYear();
        int dayOfWeek = localDate.getDayOfWeek().getValue();
        log.info("{}年{}月{}日,一年中的第{}天，周{}", year, month, day, dayOfYear, dayOfWeek);

        int year2 = localDate.get(ChronoField.YEAR);
        int month2 = localDate.get(ChronoField.MONTH_OF_YEAR);
        int day2 = localDate.get(ChronoField.DAY_OF_MONTH);
        int dayOfYear2 = localDate.get(ChronoField.DAY_OF_YEAR);
        int dayOfWeek2 = localDate.get(ChronoField.DAY_OF_WEEK);
        log.info("{}年{}月{}日,一年中的第{}天，周{}", year2, month2, day2, dayOfYear2, dayOfWeek2);
    }

    /**
     * @description 修改年月日
     */
    @Test
    public void test5() {
        LocalDate localDate = LocalDate.of(2019, 12, 24);

        //修改年份，返回新的LocalDate对象 2019-01-24
        LocalDate year = localDate.withYear(2020);
        System.out.println(year);

        //修改月份，返回新的LocalDate对象 2019-12-11
        LocalDate month = localDate.withMonth(1);
        System.out.println(month);

        //修改月份日，返回新的LocalDate对象 2019-12-31
        LocalDate day = localDate.withDayOfMonth(11);
        System.out.println(day);

        //修改年份日，返回新的LocalDate对象 2019-12-24
        LocalDate withDayOfYear = localDate.withDayOfYear(365);
        System.out.println(withDayOfYear);
    }

    /**
     * @description 其他localDate对象方法测试
     */
    @Test
    public void test6() {
        LocalDate localDate = LocalDate.of(2019, 12, 24);

        //对应LocalDate对象年的第一天
        LocalDate firstDayOfYear = localDate.with(TemporalAdjusters.firstDayOfYear());
        System.out.println(firstDayOfYear);

        //对应LocalDate对象月的第一天
        LocalDate firstDayOfMonth = localDate.with(TemporalAdjusters.firstDayOfMonth());
        System.out.println(firstDayOfMonth);

        //对应LocalDate对象下一年的第一天
        LocalDate firstDayOfNextYear = localDate.with(TemporalAdjusters.firstDayOfNextYear());
        System.out.println(firstDayOfNextYear);

        //对应LocalDate对象下一月的第一天
        LocalDate firstDayOfNextMonth = localDate.with(TemporalAdjusters.firstDayOfNextMonth());
        System.out.println(firstDayOfNextMonth);

        System.out.println("*****************************************************");

        LocalDate localDate1 = LocalDate.of(2019, 12, 31);
        LocalDate localDate2 = LocalDate.of(2019, 12, 1);

        //是否在某日期后面
        System.out.println(localDate.isAfter(localDate1));
        //是否在某日期前面
        System.out.println(localDate.isBefore(localDate1));
        //是否闰年
        System.out.println(localDate.isLeapYear());
        //是否日期相等
        System.out.println(localDate.isEqual(localDate1));

        /*
        与某个时间比较，如果先于某个日期为负数，后于某个日期为正数；
        下例中
        -7表示在指定日期前7天；
        +23表示在指定日期后23天；

        ps:
        可以使用Integer.valueOf()方法将预期相差的天数与localDate.compareTo(localDate1)的返回结果相比较
         */
        System.out.println(Integer.valueOf("-7").equals(localDate.compareTo(localDate1)));
        System.out.println(Integer.valueOf(-7).equals(localDate.compareTo(localDate1)));
        System.out.println(Integer.valueOf("23").equals(localDate.compareTo(localDate2)));
        System.out.println(Integer.valueOf(23).equals(localDate.compareTo(localDate2)));

        System.out.println("*****************************************************");

        /**
         * 1.
         * localDate.util(Temporal endExclusive, TemporalUnit unit);
         * 方法返回与endExclusive日期unit中指定的单位之间的间隔，如下例中相差-7天（也就是在endExclusive日期前7天）
         */
        LocalDate date = LocalDate.parse("2019-12-31");
        LocalDate date1 = LocalDate.parse("2019-12-24");
        System.out.println(date.until(date1, ChronoUnit.DAYS));

        //输出 P-7D （P标识Period，-7D标识-7天？）
        System.out.println(date.until(date1));

        System.out.println("*****************************************************");

        log.info("年有{}天", localDate.lengthOfYear());
        log.info("月有{}天", localDate.lengthOfMonth());

        //加年月日之后的日期 原2019-12-24
        //2029-12-24
        System.out.println(localDate.plusYears(10));
        //2020-01-24
        System.out.println(localDate.plusMonths(1));
        //2019-12-31
        System.out.println(localDate.plusWeeks(1));
        //2020-01-03
        System.out.println(localDate.plusDays(10));


        System.out.println("*****************************************************");


        //减年月日之后的日期 原2019-12-24
        //2009-12-24
        System.out.println(localDate.minusYears(10));
        //2019-11-24
        System.out.println(localDate.minusMonths(1));
        //2019-12-17
        System.out.println(localDate.minusWeeks(1));
        //2019-12-14
        System.out.println(localDate.minusDays(10));

        System.out.println("*****************************************************");
    }
}
