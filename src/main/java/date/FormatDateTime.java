package date;

import constant.Constant;
import org.junit.Test;
import utils.DateUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class FormatDateTime {

    public static void main(String[] args) {
        SimpleDateFormat myFmt = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
        SimpleDateFormat myFmt1 = new SimpleDateFormat("yy/MM/dd HH:mm");
        //等价于now.toLocaleString()
        SimpleDateFormat myFmt2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat myFmt3 = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒 E ");
        SimpleDateFormat myFmt4 = new SimpleDateFormat(
                "一年中的第 D 天 一年中第w个星期 一月中第W个星期 在一天中k时 z时区");
        Date now = new Date();
        System.out.println(myFmt.format(now));
        System.out.println(myFmt1.format(now));
        System.out.println(myFmt2.format(now));
        System.out.println(myFmt3.format(now));
        System.out.println(myFmt4.format(now));
        System.out.println(now.toGMTString());
        System.out.println(now.toLocaleString());
        System.out.println(now.toString());

        /**
         *
         SimpleDateFormat sdf = new SimpleDateFormat();
         String dateString = sdf.format(Date date);
         Date date = sdf.parse(String sourse);
         * */

    }

    /**
     * 比较日期间隔多少天
     *
     * @throws ParseException
     */
    @Test
    public void test1() throws ParseException {
        Date now = DateUtils.getLongDate19();
        Date getDate = DateUtils.getString2LongDate("2015-04-15 14:57:18");
        int sum = DateUtils.cutTwoDateToDay(getDate, now);
        System.out.println("now:      " + now + "\ngetDate:  " + getDate);
        System.out.println(sum);
        if (sum <= 180) {
            System.out.println("该客户六个月内提交过预审批！");
        } else {
            System.out.println("该客户六个月内未提交过预审批，可以进行预审批业务！");
        }
    }

    /**
     * 比较日期间隔多少天
     * 注意一开始        format.applyPattern("yyyy-MM-dd HH:mm:ss");
     * 但是比较天数的话，如果这样，
     * now:      Tue Aug 27 14:31:23 CST 2019
     * getDate:  Sun Aug 25 14:57:18 CST 2019
     * 那么上面两个时间用下面方法是相差1天的，因为还没过27号14:57:18，所以要是用
     * format.applyPattern("yyyy-MM-dd");
     *
     * @throws ParseException
     */
    @Test
    public void test11() throws ParseException {
        Date now = new Date();
        SimpleDateFormat format = new SimpleDateFormat();
        format.applyPattern("yyyy-MM-dd");
        Calendar calendar1 = Calendar.getInstance();
        Calendar calendar2 = Calendar.getInstance();
        calendar1.setTime(now);
        calendar2.setTime(format.parse("2015-04-15 14:57:18"));
        System.out.println("now:      " + now + "\ngetDate:  " + format.parse("2015-04-15 14:57:18"));
        long sum = (calendar1.getTimeInMillis() - calendar2.getTimeInMillis()) / 86400000L;
        System.out.println(sum);
        if (sum <= 180) {
            System.out.println("该客户六个月内提交过预审批！");
        } else {
            System.out.println("该客户六个月内未提交过预审批，可以进行预审批业务！");
        }
    }



    /**
     * 比较日期间隔多少个月-方法1
     *
     * @throws ParseException
     */
    @Test
    public void test12() throws ParseException {
//        date now = DateUtils.getLongDate19();
        Date now = DateUtils.getString2LongDate("2019-03-24 14:57:18");
        Date getDate = DateUtils.getString2LongDate("2018-06-23 14:57:18");
        int sum = DateUtils.cutTwoDateToMonth(getDate, now);
        System.out.println("now:      " + now + "\ngetDate:  " + getDate);
        System.out.println(sum);
        if (sum < 6) {
            System.out.println("该客户六个月内提交过预审批！");
        } else {
            System.out.println("该客户六个月内未提交过预审批，可以进行预审批业务！");
        }
    }

    /**
     * 比较日期间隔多少个月-方法2
     *
     * @throws ParseException
     */
    @Test
    public void test13() throws ParseException {
        String d1 = "2018-08-12";
        String d2 = "2018-02-11";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.setTime(sdf.parse(d1));
        int year1 = c.get(Calendar.YEAR);
        int month1 = c.get(Calendar.MONTH);

        c.setTime(sdf.parse(d2));
        int year2 = c.get(Calendar.YEAR);
        int month2 = c.get(Calendar.MONTH);

        int result;
        if (year1 == year2) {
            result = month1 - month2;
        } else {
            result = 12 * (year1 - year2) + month1 - month2;
        }
        System.out.println(result);
    }


    /**
     * 时间比较
     * 总结：
     * 如果是以这种形式构建：DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
     * 然后传入这种形式的字符串，那么默认的年月日就是1970-1-1，其实就是要比较时分秒，把年月日先让其相同，
     * 因为要实现的是，在早上08:00之前，晚上19:55分不可以执行操作，那么这两个时间并不能指定具体的年月日，
     * 所以这里采用此种方法
     * 在这种情况下，打印出的getTime，在08:00:00时是0，然后随着时间增加，这个值随之增加，一直增加到23:59:59，
     * 下一秒就跳到了00:00:00，而这个值是一个负的最大值，然后随着时间的增加，这个值随之增加。
     * 其实就是从00:00:00开始，一个负数随着时间增加，不断增加，23:59:59时达到最大值！
     */
    @Test
    public void test2() throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        String nowString = DateUtils.getCurrentFormatDateLong19();
        String[] nowArray = nowString.split(" ");
        String nowTemp = nowArray[1];
        Date now = dateFormat.parse(nowTemp);
        Date date1 = dateFormat.parse("19:55:00");
        Date date2 = dateFormat.parse("08:00:00");
        Date date3 = dateFormat.parse("07:00:00");
        Date date4 = dateFormat.parse("12:00:00");
        Date date5 = dateFormat.parse("25:00:00");
        Date date6 = dateFormat.parse("00:00:00");
        Date date7 = dateFormat.parse("23:59:59");
        Date date8 = dateFormat.parse("23:00:00");
        Date date9 = dateFormat.parse("22:00:00");
        Date date10 = dateFormat.parse("21:00:00");
        Date date11 = dateFormat.parse("20:00:00");
        Date date12 = dateFormat.parse("19:00:00");
        System.out.println(now.getTime());
        System.out.println(date1.getTime());
        System.out.println(date2.getTime());
        System.out.println(date3.getTime());
        System.out.println(date4.getTime());
        System.out.println(date5.getTime());
        System.out.println(date6.getTime());
        System.out.println(date7.getTime());
        System.out.println(date8.getTime());
        System.out.println(date9.getTime());
        System.out.println(date10.getTime());
        System.out.println(date11.getTime());
        System.out.println(date12.getTime());
        if (now.getTime() > date1.getTime() || now.getTime() < date2.getTime()) {
            System.out.println("在时间区间内！");
        } else {
            System.out.println("不在时间区间内！");
        }
    }

    @Test
    public void test3() throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        Date now = dateFormat.parse(dateFormat.format(new Date()));
        Date date1 = dateFormat.parse("19:55:00");
        Date date2 = dateFormat.parse("08:00:00");
        if (now.getTime() > date1.getTime() || now.getTime() < date2.getTime()) {
            System.out.println("在时间区间内！");
        } else {
            System.out.println("不在时间区间内！");
        }
    }

    /**
     * 使用Calendar方法的after或者before方法判比较时间；
     * 这里比较的几点几分，不牵扯到年月日；所以赋予Date的时候，
     * 年月日是默认的1970-01-01
     *
     * @throws ParseException
     */
    @Test
    public void test4() throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        Date now = dateFormat.parse(dateFormat.format(new Date()));
        Date date1 = dateFormat.parse("19:55:00");
        Date date2 = dateFormat.parse("08:00:00");
        boolean flag = belongCalendar(now, date1, date2);
        if (flag) {
            System.out.println("在时间区间内！");
        } else {
            System.out.println("不在时间区间内！");
        }
    }

    /**
     * 校验身份证是否过期，yyyy-MM-dd格式日期就要传入yyyy-MM-dd格式的字符串
     *
     * @throws ParseException
     */
    @Test
    public void test5() throws ParseException {
        String validDate = "2015-12-20";
        if (!"长期".equals(validDate) && DateUtils.getDateByStr(validDate).getTime() < System.currentTimeMillis()) {
            System.out.println("过期了！");
        }
    }

    /**
     * 获取当前日期之前或之后的多少天的日期。
     * 比如现在是2019-08-27 14:20:43
     * 调用方法后2019-05-29 14:20:43
     */
    @Test
    public void test6() {
        String validDaysAgoDate = DateUtils.getNDaysAgoOrAfterFormatDateLong19(90, Constant.DATE_SIGN_AGO);
        System.out.println(validDaysAgoDate);
    }

    public static boolean belongCalendar(Date nowTime, Date beginTime, Date endTime) {
        Calendar date = Calendar.getInstance();
        date.setTime(nowTime);

        Calendar begin = Calendar.getInstance();
        begin.setTime(beginTime);

        Calendar end = Calendar.getInstance();
        end.setTime(endTime);

        if (date.after(begin) || date.before(end)) {
            return true;
        } else {
            return false;
        }
    }
}

