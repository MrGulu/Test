package java8.date;

import org.junit.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;

public class TimeUtilsTest {

    /**
     * java.time.Period
     * 主要是 Period 类方法 getYears()，getMonths() 和 getDays() 来计算。只能精确到年月日。
     *
     * 用于 LocalDate 之间的比较。
     */
    @Test
    public void periodTest() {
        LocalDate today = LocalDate.now();
        System.out.println(today);
        LocalDate birthDate = LocalDate.of(1995, 1, 11);
        System.out.println(birthDate);
        Period period = Period.between(birthDate, today);
        System.out.printf("年龄 : %d 年 %d 月 %d 日", period.getYears(), period.getMonths(), period.getDays());
    }

    /**
     * java.time.Duration
     * 提供了使用基于时间的值测量时间量的方法。
     *
     * 用于 LocalDateTime 之间的比较。也可用于 Instant 之间的比较。
     */
    @Test
    public void durationTest() {
        LocalDateTime today = LocalDateTime.now();
        System.out.println(today);
        LocalDateTime birthDate = LocalDateTime.of(1995,1,11,7,10,30);
        System.out.println(birthDate);

        Duration duration = Duration.between(birthDate, today);//第二个参数减第一个参数
        System.out.println(duration.toDays());//两个时间差的天数
        System.out.println(duration.toHours());//两个时间差的小时数
        System.out.println(duration.toMinutes());//两个时间差的分钟数
        System.out.println(duration.toMillis());//两个时间差的毫秒数
        System.out.println(duration.toNanos());//两个时间差的纳秒数
    }

    /**
     * java.time.temporal.ChronoUnit
     * ChronoUnit类可用于在单个时间单位内测量一段时间，这个工具类是最全的了，可以用于比较所有的时间单位。
     */
    @Test
    public void chronoUnitTest() {
        LocalDateTime today = LocalDateTime.now();
        System.out.println(today);
        LocalDateTime birthDate = LocalDateTime.of(1995,1,11,7,10,30);
        System.out.println(birthDate);

        System.out.println("相差的年数：" + ChronoUnit.YEARS.between(birthDate, today));
        System.out.println("相差的月数：" + ChronoUnit.MONTHS.between(birthDate, today));
        System.out.println("相差的周数：" + ChronoUnit.WEEKS.between(birthDate, today));
        System.out.println("相差的天数：" + ChronoUnit.DAYS.between(birthDate, today));
        System.out.println("相差的时数：" + ChronoUnit.HOURS.between(birthDate, today));
        System.out.println("相差的分数：" + ChronoUnit.MINUTES.between(birthDate, today));
        System.out.println("相差的秒数：" + ChronoUnit.SECONDS.between(birthDate, today));
        System.out.println("相差的毫秒数：" + ChronoUnit.MILLIS.between(birthDate, today));
        System.out.println("相差的微秒数：" + ChronoUnit.MICROS.between(birthDate, today));
        System.out.println("相差的纳秒数：" + ChronoUnit.NANOS.between(birthDate, today));

        System.out.println("相差的半天数：" + ChronoUnit.HALF_DAYS.between(birthDate, today));
        System.out.println("相差的十年数：" + ChronoUnit.DECADES.between(birthDate, today));
        System.out.println("相差的世纪（百年）数：" + ChronoUnit.CENTURIES.between(birthDate, today));
        System.out.println("相差的千年数：" + ChronoUnit.MILLENNIA.between(birthDate, today));
        System.out.println("相差的纪元数：" + ChronoUnit.ERAS.between(birthDate, today));
    }

    @Test
    public void chronoUnitTest2() {
        LocalDate begin = LocalDate.of(2021, 2, 28);
        LocalDate end = LocalDate.of(2021, 5, 23);
        long between = ChronoUnit.DAYS.between(begin, end);
        System.out.println(between);
    }
}
