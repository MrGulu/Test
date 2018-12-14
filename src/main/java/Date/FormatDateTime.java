package Date;

import org.junit.Test;
import utils.DateUtils;

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
}

