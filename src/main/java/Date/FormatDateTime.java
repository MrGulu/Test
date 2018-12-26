package Date;

import com.sun.org.apache.xml.internal.dtm.ref.sax2dtm.SAX2DTM2;
import org.junit.Test;
import utils.DateUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FormatDateTime {

    public static void main(String[] args) {
        SimpleDateFormat myFmt=new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
        SimpleDateFormat myFmt1=new SimpleDateFormat("yy/MM/dd HH:mm");
        //等价于now.toLocaleString()
        SimpleDateFormat myFmt2=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat myFmt3=new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒 E ");
        SimpleDateFormat myFmt4=new SimpleDateFormat(
                "一年中的第 D 天 一年中第w个星期 一月中第W个星期 在一天中k时 z时区");
        Date now=new Date();
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
         *  SimpleDateFormat sdf = new SimpleDateFormat();
            String d = sdf.format(Date date);
            Date d = sdf.parse(String sourse);
         * */

    }

    /**
     * 比较日期间隔多少天
     * @throws ParseException
     */
    @Test
    public void test1() throws ParseException {
        Date now = DateUtils.getLongDate19();
        Date getDate = DateUtils.getString2LongDate("2015-04-15 14:57:18");
        int sum = DateUtils.cutTwoDateToDay(getDate, now);
        System.out.println("now:      "+now+"\ngetDate:  "+getDate);
        System.out.println(sum);
        if (sum <= 180) {
            System.out.println("该客户六个月内提交过预审批！");
        } else {
            System.out.println("该客户六个月内未提交过预审批，可以进行预审批业务！");
        }
    }

    /**
     * 时间比较
     * 总结：
     *      如果是以这种形式构建：DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
     *  然后传入这种形式的字符串，那么默认的年月日就是1970-1-1，其实就是要比较时分秒，把年月日先让其相同，
     *  因为要实现的是，在早上08:00之前，晚上19:55分不可以执行操作，那么这两个时间并不能指定具体的年月日，
     *  所以这里采用此种方法
     *      在这种情况下，打印出的getTime，在08:00:00时是0，然后随着时间增加，这个值随之增加，一直增加到23:59:59，
     *  下一秒就跳到了00:00:00，而这个值是一个负的最大值，然后随着时间的增加，这个值随之增加。
     *  其实就是从00:00:00开始，一个负数随着时间增加，不断增加，23:59:59时达到最大值！
     *
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
        }else {
            System.out.println("不在时间区间内！");
        }
    }
}

