package utils;



import constant.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtils
{

  public static final String DATEFORMATLONG14 = "yyyyMMddHHmmss";
  public static final String DATEFORMATLONG23 = "yyyy-MM-dd HH:mm:ss.SSS";

  public static final String DATEFORMATLONG17 = "yyyyMMdd HH:mm:ss";
  public static final String DATEFORMATLONG19 = "yyyy-MM-dd HH:mm:ss";
  public static final String DATEFORMATMEDIUM = "yyyy-MM-dd HH:mm";
  public static final String DATE_FORMAT_YMDH = "yyyyMMddHH";
  public static final String DATEFORMATSHORT10 = "yyyy-MM-dd";
  public static final String DATE_SHORT_YEARMONTH = "yyyy-MM";
  public static final String DATENUMBERFORMAT8 = "yyyyMMdd";
  public static final String DATEHOURNUMBERFORMAT = "yyyyMMddHH";
  public static final String DATE_FORMAT_HOUR = "H";
  private static Logger logger = LoggerFactory.getLogger(DateUtils.class);

  public static Date getCurrentDate()
  {
    return new Date();
  }

  public static long getCurrentTimeMillis()
  {
    return System.currentTimeMillis();
  }

  public static String getCurrentYearMonthDate()
  {
    return getCurrentFormatDate("yyyy-MM");
  }

  public static int cutTwoDateToDay(Date a, Date b)
  {
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    Calendar calendar = Calendar.getInstance();
    int theday = 0;
    try {
      Date beginDate = format.parse(format.format(a));
      Date endDate = format.parse(format.format(b));

      calendar.setTime(beginDate);
      long begin = calendar.getTimeInMillis();
      calendar.setTime(endDate);
      long end = calendar.getTimeInMillis();

      theday = (int)((end - begin) / 86400000L);
    } catch (ParseException e) {
      logger.debug("日期转换出错!", e);
    }
    return theday;
  }

  public static String intToTimeString(int time)
  {
    String hour = String.valueOf(time / 60);
    String minute = String.valueOf(time - time / 60 * 60);

    if (hour.length() < 2) {
      hour = "0" + hour;
    }
    if (minute.length() < 2) {
      minute = "0" + minute;
    }
    return hour + ":" + minute;
  }

  public static Date maxDate(Date data1, Date data2)
  {
    if (data1.before(data2)) {
      return data2;
    }
    return data1;
  }

  public static Date minDate(Date a, Date b)
  {
    if (a.before(b)) {
      return a;
    }
    return b;
  }

  public static int getWeekOfDate(Date date)
  {
    Calendar cal = Calendar.getInstance();
    cal.setTime(date);
    int w = cal.get(7) - 1;
    if (w == 0) {
      w = 7;
    }
    return w;
  }

  public static int getDayOfWeek()
  {
    int dayOfWeek = Calendar.getInstance().get(7);

    if (dayOfWeek == 1)
      dayOfWeek = 7;
    else {
      dayOfWeek--;
    }
    return dayOfWeek;
  }

  public static String formatDate(Date date, String pattern)
  {
    SimpleDateFormat format = new SimpleDateFormat(pattern);
    return format.format(date);
  }

  public static Date createDate(String dateString, String pattern)
    throws ParseException
  {
    SimpleDateFormat format = new SimpleDateFormat(pattern);
    return format.parse(dateString);
  }

  public static String getCurrentFormatDate(String formatDate)
  {
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
    Date dateInstance = getCurrentDate();
    simpleDateFormat.applyPattern(formatDate);
    return simpleDateFormat.format(dateInstance);
  }

  public static String getNDaysAgoOrAfterDate(String formatDate,int days,String agoOrAfter){
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
    Date date = getCurrentDate();
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    if(Constant.DATE_SIGN_AGO.equals(agoOrAfter)){
      calendar.add(Calendar.DAY_OF_MONTH, -days);
    }else if(Constant.DATE_SIGN_AFTER.equals(agoOrAfter)){
      calendar.add(Calendar.DAY_OF_MONTH, +days);
    }else{
      calendar.add(Calendar.DAY_OF_MONTH, 0);
    }
    date = calendar.getTime();
    simpleDateFormat.applyPattern(formatDate);
    return simpleDateFormat.format(date);
  }

  public static String getCurrentFormatDateShort10()
  {
    return getCurrentFormatDate("yyyy-MM-dd");
  }


  public static String getCurrentFormatDateLong17()
  {
    return getCurrentFormatDate("yyyyMMdd HH:mm:ss");
  }

  public static String getCurrentFormatDateLong19()
  {
    return getCurrentFormatDate("yyyy-MM-dd HH:mm:ss");
  }

  public static String getNDaysAgoOrAfterFormatDateLong19(int days,String agoOrAfter)
  {
    return getNDaysAgoOrAfterDate("yyyy-MM-dd HH:mm:ss",days,agoOrAfter);
  }

  public static String getCurrentFormatDateLong23()
  {
    return getCurrentFormatDate("yyyy-MM-dd HH:mm:ss.SSS");
  }


  public static String getCurrentFormatDateLong14()
  {
    return getCurrentFormatDate("yyyyMMddHHmmss");
  }



  public static String getCurrentFormatDateMedium()
  {
    return getCurrentFormatDate("yyyy-MM-dd HH:mm");
  }


  private static String getDate2String(String format, Date date)
  {
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
    simpleDateFormat.applyPattern(format);
    return simpleDateFormat.format(date);
  }

  public static String getDate2LongString(Date date)
  {
    return getDate2String("yyyy-MM-dd HH:mm:ss", date);
  }

  public static String getDate2ShortString10(Date date)
  {
    return getDate2String("yyyy-MM-dd", date);
  }

  public static String getDate2LongString17(Date date)
  {
    return getDate2String("yyyyMMdd HH:mm:ss", date);
  }

  public static String getDate2LongString19(Date date)
  {
    return getDate2String("yyyy-MM-dd HH:mm:ss", date);
  }

  public static String getDate2LongString23(Date date)
  {
    return getDate2String("yyyy-MM-dd HH:mm:ss.SSS", date);
  }

  public static String getDate2MediumString(Date date)
  {
    return getDate2String("yyyy-MM-dd HH:mm", date);
  }



  public static String getDate2HourString(Date date)
  {
    return getDate2String("H", date);
  }

  public static String getDate2NumberString8(Date date)
  {
    return getDate2String("yyyyMMdd", date);
  }

  public static String getLong2LongString(long l)
  {
    Date dateInstance = getLong2Date(l);
    return getDate2LongString(dateInstance);
  }

  public static String getLong2MediumString(long l)
  {
    Date dateInstance = getLong2Date(l);
    return getDate2MediumString(dateInstance);
  }

  public static String getLong2ShortString10(long l)
  {
    Date dateInstance = getLong2Date(l);
    return getDate2ShortString10(dateInstance);
  }

  public static Date getLongDate19() throws ParseException {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    return sdf.parse(sdf.format(new Date()));
  }

  private static Date getString2Date(String format, String str)
  {
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
    simpleDateFormat.applyPattern(format);
    ParsePosition parseposition = new ParsePosition(0);
    return simpleDateFormat.parse(str, parseposition);
  }

  public static Date getString2LongDate(String str)
  {
    return getString2Date("yyyy-MM-dd HH:mm:ss", str);
  }


  public static Date getString2ShortDate10(String str)
  {
    return getString2Date("yyyy-MM-dd", str);
  }

  public static Date getString2LongDate17(String str)
  {
    return getString2Date("yyyyMMdd HH:mm:ss", str);
  }

  public static Date getString2LongDate19(String str)
  {
    return getString2Date("yyyy-MM-dd HH:mm:ss", str);
  }

  public static Date getString2LongDate23(String str)
  {
    return getString2Date("yyyy-MM-dd HH:mm:ss.SSS", str);
  }

  public static Date getString2MediumDate(String str)
  {
    return getString2Date("yyyy-MM-dd HH:mm", str);
  }

  public static Date getString2NumberDate8(String str)
  {
    return getString2Date("yyyyMMdd", str);
  }

  public static Date getString2YmdhDate(String str)
  {
    return getString2Date("yyyyMMddHH", str);
  }

  public static Date getEmptyDate()
  {
    return getString2ShortDate10("1971-01-01");
  }

  public static String getEmptyDateString()
  {
    return "1971-01-01";
  }

  public static Date getLong2Date(long l)
  {
    return new Date(l);
  }

  public static int getOffMinutes(long l)
  {
    return getOffMinutes(l, getCurrentTimeMillis());
  }

  public static int getOffMinutes(long from, long to)
  {
    return (int)((to - from) / 60000L);
  }

  public static int getYear()
  {
    return Calendar.getInstance().get(1);
  }

  public static int getMonth()
  {
    return Calendar.getInstance().get(2) + 1;
  }

  public static int getDay() {
    return Calendar.getInstance().get(5);
  }

  public static int getHour() {
    return Calendar.getInstance().get(11);
  }

  public static int getMinute() {
    return Calendar.getInstance().get(12);
  }

  public static int getSecond() {
    return Calendar.getInstance().get(13);
  }

  public static String add(int day)
  {
    GregorianCalendar gregorianCalendar = new GregorianCalendar();
    gregorianCalendar.add(5, day);
    Date dateInstance = gregorianCalendar.getTime();
    return getDate2String("yyyy-MM-dd", dateInstance);
  }

  public static String subtract(int day)
  {
    GregorianCalendar gregorianCalendar = new GregorianCalendar();
    gregorianCalendar.add(5, -day);
    Date dateInstance = gregorianCalendar.getTime();
    return getDate2String("yyyy-MM-dd", dateInstance);
  }

  public static String getlastMonth()
  {
    SimpleDateFormat sdf = new SimpleDateFormat("MM");
    GregorianCalendar gc = new GregorianCalendar();
    gc.roll(2, false);
    return sdf.format(gc.getTime());
  }

  public static String getCurrentLastMonth()
  {
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
    Calendar cal = Calendar.getInstance();
    cal.add(2, -1);
    return format.format(cal.getTime());
  }

  public static String[] getCurrentLastWeek()
  {
    String[] weeks = new String[2];
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    Calendar cal = Calendar.getInstance();
    cal.add(6, -cal.get(7));
    weeks[1] = format.format(cal.getTime());
    cal.add(6, -6);
    weeks[0] = format.format(cal.getTime());
    return weeks;
  }

  public static boolean isStartLessEndTime(String start, String end)
    throws ParseException
  {
    if ((start.equals("")) || (end.equals(""))) {
      return false;
    }
    Date startDate = DateFormat.getDateInstance().parse(start);
    Date endDate = DateFormat.getDateInstance().parse(end);
    return startDate.compareTo(endDate) < 0;
  }

  public static Date getToday()
  {
    Calendar cal = Calendar.getInstance();
    return cal.getTime();
  }

  public static Date getYesterday()
  {
    Calendar cal = Calendar.getInstance();
    cal.add(5, -1);
    return cal.getTime();
  }

  public static Date getFirstDayOfThisWeek()
  {
    Date today = getToday();
    return getFirstDayInWeek(today);
  }

  public static Date getFirstDayInMonth(int year, int month)
  {
    month--;
    Calendar cal = Calendar.getInstance();
    cal.clear();
    cal.set(year, month, 1);
    return cal.getTime();
  }

  public static Date getLastDayInMonth(int year, int month)
  {
    Date firstDay = getFirstDayInMonth(year, month);
    Calendar firstCal = getCalendarByDate(firstDay);
    firstCal.add(2, 1);
    firstCal.add(6, -1);
    return firstCal.getTime();
  }

  public static Date getFirstDayOfThisMonth()
  {
    Calendar cal = Calendar.getInstance();
    int year = cal.get(1);
    int month = cal.get(2);
    month++;
    return getFirstDayInMonth(year, month);
  }

  public static Date getFirstDayOfLastMonth()
  {
    int year = getYearOfLastMonth();
    int month = getMonthOfLastMonth();
    return getFirstDayInMonth(year, month);
  }

  private static int getYearOfLastMonth()
  {
    Calendar cal = Calendar.getInstance();
    cal.add(2, -1);
    return cal.get(1);
  }

  private static int getMonthOfLastMonth()
  {
    Calendar cal = Calendar.getInstance();
    cal.add(2, -1);
    int month = cal.get(2);
    month++;
    return month;
  }

  public static int getThisMonth()
  {
    Calendar cal = Calendar.getInstance();
    int month = cal.get(2);
    month++;
    return month;
  }

  public static Date getFirstDayInYear(int year)
  {
    int month = 0;
    int day = 1;
    Calendar cal = Calendar.getInstance();
    cal.set(year, month, day);
    return cal.getTime();
  }

  public static Date getLastDayInYear(int year)
  {
    int month = 11;
    int day = 31;
    Calendar cal = Calendar.getInstance();
    cal.set(year, month, day);
    return cal.getTime();
  }

  public static int getThisYear()
  {
    Calendar cal = Calendar.getInstance();
    return cal.get(1);
  }

  public static int getLastYear()
  {
    Calendar cal = Calendar.getInstance();
    cal.add(1, -1);
    return cal.get(1);
  }

  public static Date getFirstDayInWeek(Date date)
  {
    Calendar cal = Calendar.getInstance();
    cal.setTime(date);
    int thisweek = cal.get(3);
    int lastweek = thisweek;
    Date thisday = null;
    while (lastweek == thisweek) {
      thisday = cal.getTime();
      cal.add(5, -1);
      lastweek = cal.get(3);
    }
    return thisday;
  }

  public static Date getLastDayInWeek(Date date)
  {
    Date firstdayofweek = getFirstDayInWeek(date);
    Calendar cal = getCalendarByDate(firstdayofweek);
    cal.add(6, 6);
    return cal.getTime();
  }

  private static Calendar getCalendarByDate(Date date)
  {
    Calendar cal = Calendar.getInstance();
    cal.setTime(date);
    return cal;
  }

  public static String getDateStr(Date date)
  {
    String pattern = "yyyy-MM-dd";
    SimpleDateFormat format = new SimpleDateFormat(pattern);
    return format.format(date);
  }

  public static String getMonthStr(Date date)
  {
    String pattern = "yyyy-MM";
    SimpleDateFormat format = new SimpleDateFormat(pattern);
    return format.format(date);
  }

  public static Date getDateByStr(String dateStr)
  {
    String pattern = "yyyy-MM-dd";
    SimpleDateFormat format = new SimpleDateFormat(pattern);
    Date date = null;
    try {
      date = format.parse(dateStr);
    } catch (ParseException e) {
      throw new RuntimeException("wrong date format, should be " + pattern);
    }

    return date;
  }

  public static String addHour(int hour)
  {
    GregorianCalendar gregorianCalendar = new GregorianCalendar();
    gregorianCalendar.add(10, hour);
    Date dateInstance = gregorianCalendar.getTime();
    return getDate2String("yyyyMMddHH", dateInstance);
  }

  public static Date addHour2Date(int hour)
  {
    GregorianCalendar gregorianCalendar = new GregorianCalendar();
    gregorianCalendar.add(10, hour);
    Date dateInstance = gregorianCalendar.getTime();
    return dateInstance;
  }

  public static String addHourReturnShortDate(int hour)
  {
    GregorianCalendar gregorianCalendar = new GregorianCalendar();
    gregorianCalendar.add(10, hour);
    Date dateInstance = gregorianCalendar.getTime();
    return getDate2String("yyyy-MM-dd", dateInstance);
  }

  public static Date getNextHourDate(int hour)
  {
    GregorianCalendar gregorianCalendar = new GregorianCalendar();
    gregorianCalendar.add(10, hour);
    Date dateInstance = gregorianCalendar.getTime();
    String longDateTime = getDate2LongString(dateInstance);
    String NextDateHour = longDateTime.subSequence(0, 14) + "00:00";
    return getString2LongDate(NextDateHour);
  }

  public static Date getNextMinuteDate(int minute)
  {
    GregorianCalendar gregorianCalendar = new GregorianCalendar();
    gregorianCalendar.add(12, minute);
    Date dateInstance = gregorianCalendar.getTime();
    String longDateTime = getDate2LongString(dateInstance);
    String NextDateMinute = longDateTime.subSequence(0, 17) + "00";
    return getString2LongDate(NextDateMinute);
  }

  public static boolean inDateRange(Date startDate, Date endDate, Date date)
  {
    if ((startDate == null) || (endDate == null) || (date == null)) {
      return false;
    }

    return ((startDate.before(date)) || (startDate.equals(date))) && ((endDate.after(date)) || (endDate.equals(date)));
  }
}
