
package club.javafamily.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class DateUtils {

   public static final String DAY_FORMAT = "yyyyMMdd";
   public static final String DAY_FORMAT1 = "yyyy-MM-dd";
   public static final String HOUR_FORMAT = "yyyyMMddHH";
   public static final String HOUR_FORMAT2 = "yyyy-MM-dd HH";
   public static final String MINUTE_FORMAT = "yyyyMMddHHmm";
   public static final String MINUTE_FORMAT1 = "yyyy-MM-dd HH:mm";
   public static final String SECOND_FORMAT = "yyyyMMddHHmmss";
   public static final String SECOND_FORMAT1 = "yyyy-MM-dd HH:mm:ss";
   public static final String HOUR_FORMAT1 = "MM月dd日 HH时";

   public static final int DAY_IN_MILLSECOND = 1000 * 3600 * 24;
   public static final int HOUR_IN_MILLSECOND = 1000 * 3600;
   public static final int MINUTE_IN_MILLSECOND = 1000 * 60;
   public static final int MINUTE_IN_SECOND = 60;
   public static final int DAY_IN_SECOND = 3600 * 24;
   public static final int HOUR_IN_SECOND = 3600;

   public static String buildTodayStr() {
      SimpleDateFormat sdf = new SimpleDateFormat(DAY_FORMAT);
      Date date = new Date();
      try {
         String time = sdf.format(date);
         return time;
      } catch (Exception ex) {
      }
      return null;
   }

   public static String buildNowStr(String format) {
      SimpleDateFormat sdf = new SimpleDateFormat(format);
      Date date = new Date();
      try {
         String time = sdf.format(date);
         return time;
      } catch (Exception ex) {
      }
      return null;
   }

   /**
    * @param start
    * @param day   (负号表示过去几天)
    * @return
    * @Title: futureDay
    * @Description: 未来的第几天的string
    */
   public static String futureDay(String start, String format, int day) {
      SimpleDateFormat sdf = new SimpleDateFormat(format);

      long startTime;

      try {
         startTime = sdf.parse(start).getTime();
         long endTime = startTime + ((long) DAY_IN_MILLSECOND * day);

         return sdf.format(new Date(endTime));
      } catch (ParseException e) {
         e.printStackTrace();
      }
      return null;
   }

   /**
    * @param start
    * @param year
    * @return
    * @Title: futureYear
    * @Description: 未来的第几天的string
    */
   public static String futureYear(String start, String formmat, int year) {
      Calendar cal = Calendar.getInstance();
      cal.setTime(formatDate(start, formmat));//设置起时间
      cal.add(Calendar.YEAR, year);//增加一年
      //cd.add(Calendar.DATE, n);//增加一天
      //cd.add(Calendar.DATE, -10);//减10天
      //cd.add(Calendar.MONTH, n);//增加一个月
      return formatDataFromString(cal.getTime(), formmat);
   }

   public static List<String> buildHourTimes(String startTime, String endTime, String format) {
      SimpleDateFormat sdf = new SimpleDateFormat(format);
      try {
         long startTimeMillus = sdf.parse(startTime).getTime();
         long endTimeMillus = sdf.parse(endTime).getTime();
         if (endTimeMillus < startTimeMillus) {
            return null;
         }

         List<String> hours = new ArrayList<String>();

         while (endTimeMillus >= startTimeMillus) {
            hours.add(sdf.format(new Date(startTimeMillus)));
            startTimeMillus += HOUR_IN_MILLSECOND;
         }
         return hours;
      } catch (Exception ex) {
      }
      return null;

   }

   public static List<String> buildTimes(String startTime, String endTime, String format, long timeGap) {
      SimpleDateFormat sdf = new SimpleDateFormat(format);
      try {
         long startTimeMillus = sdf.parse(startTime).getTime();
         long endTimeMillus = sdf.parse(endTime).getTime();
         if (endTimeMillus < startTimeMillus) {
            return null;
         }

         List<String> hours = new ArrayList<String>();

         while (endTimeMillus >= startTimeMillus) {
            hours.add(sdf.format(new Date(startTimeMillus)));
            startTimeMillus += timeGap;
         }
         return hours;
      } catch (Exception ex) {
      }
      return null;

   }

   public static List<String> buildDayTimes(String startTime, String endTime, String format) {
      SimpleDateFormat sdf = new SimpleDateFormat(format);
      try {
         long startTimeMillus = sdf.parse(startTime).getTime();
         long endTimeMillus = sdf.parse(endTime).getTime();
         if (endTimeMillus < startTimeMillus) {
            return null;
         }

         List<String> hours = new ArrayList<String>();

         while (endTimeMillus >= startTimeMillus) {
            hours.add(sdf.format(new Date(startTimeMillus)));
            startTimeMillus += DAY_IN_MILLSECOND;
         }
         return hours;
      } catch (Exception ex) {
      }
      return null;

   }

   /**
    * @param start (负号表示过去几小时)
    * @return
    * @Title: futureHour
    * @Description: 未来的第几小时的string
    */
   public static String futureHour(String start, String format, int hour) {
      SimpleDateFormat sdf = new SimpleDateFormat(format);
      long startTime;
      try {
         startTime = sdf.parse(start).getTime();
         long endTime = startTime + ((long) HOUR_IN_MILLSECOND * hour);
         return sdf.format(new Date(endTime));
      } catch (ParseException e) {
         e.printStackTrace();
      }
      return null;
   }

   /**
    * @param start
    * @param format
    * @param minute
    * @return
    * @Title: futureHour
    * @Description: 未来的第几分钟的string
    */
   public static String futureMinute(String start, String format, int minute) {
      SimpleDateFormat sdf = new SimpleDateFormat(format);
      long startTime;
      try {
         startTime = sdf.parse(start).getTime();
         long endTime = startTime + ((long) MINUTE_IN_MILLSECOND * minute);
         return sdf.format(new Date(endTime));
      } catch (ParseException e) {
         e.printStackTrace();
      }
      return null;
   }

   public static int dayGap(String start, String end, String startFormat, String endFormat) {
      SimpleDateFormat startSdf = new SimpleDateFormat(startFormat);
      SimpleDateFormat endSdf = new SimpleDateFormat(endFormat);
      long startTime;
      try {
         startTime = startSdf.parse(start).getTime();
         long endTime = endSdf.parse(end).getTime();
         return (int) ((endTime - startTime) / DAY_IN_MILLSECOND);
      } catch (ParseException e) {
         e.printStackTrace();
      }
      return -1;
   }

   public static int hourGap(String start, String end, String startFormat, String endFormat) {
      SimpleDateFormat startSdf = new SimpleDateFormat(startFormat);
      SimpleDateFormat endSdf = new SimpleDateFormat(endFormat);
      long startTime;
      try {
         startTime = startSdf.parse(start).getTime();
         long endTime = endSdf.parse(end).getTime();
         return (int) ((endTime - startTime) / HOUR_IN_MILLSECOND);
      } catch (ParseException e) {
         e.printStackTrace();
      }
      return -1;
   }

   public static int minuteGap(String start, String end, String startFormat, String endFormat) {
      SimpleDateFormat startSdf = new SimpleDateFormat(startFormat);
      SimpleDateFormat endSdf = new SimpleDateFormat(endFormat);
      long startTime;
      try {
         startTime = startSdf.parse(start).getTime();
         long endTime = endSdf.parse(end).getTime();
         return (int) ((endTime - startTime) / MINUTE_IN_MILLSECOND);
      } catch (ParseException e) {
         e.printStackTrace();
      }
      return -1;
   }

   public static long getTime(String time, String format) {
      SimpleDateFormat sdf = new SimpleDateFormat(format);
      try {
         return sdf.parse(time).getTime();
      } catch (ParseException e) {
         e.printStackTrace();
         return 0;
      }
   }

   public static String getTime(long time, String format) {
      SimpleDateFormat sdf = new SimpleDateFormat(format);
      try {
         return sdf.format(new Date(time));
      } catch (Exception e) {
         e.printStackTrace();
         return null;
      }
   }

   public static String formatDataFromString(String time, String format) {
      SimpleDateFormat sdf = new SimpleDateFormat(format);
      try {
         return sdf.format(sdf.parse(time));
      } catch (Exception e) {
         e.printStackTrace();
         return null;
      }
   }

   public static String formatDataFromString(Date time, String format) {
      SimpleDateFormat sdf = new SimpleDateFormat(format);
      try {
         return sdf.format(time);
      } catch (Exception e) {
         e.printStackTrace();
         return null;
      }
   }

   /**
    * 将字符串转换成指定的时间格式的字符串
    *
    * @param time   yyyyMMddHHmmss
    * @param format
    * @return String
    * @Title: getTimeString
    * @Description: TODO
    */
   public static String getTimeString(String time, String format) {
      SimpleDateFormat sdf = new SimpleDateFormat(format);
      SimpleDateFormat sdfTemp = new SimpleDateFormat(SECOND_FORMAT);
      try {
         return sdf.format(sdfTemp.parse(time));
      } catch (Exception e) {
//			LogUtils.error("convert date to String error:" + e);
      }
      return null;
   }

   /**
    * 将字符串转换成指定的时间格式的字符串
    *
    * @param time yyyyMMddHHmmss
    * @param
    * @return String
    * @Title: getTimeString
    * @Description: TODO
    */
   public static String getTimeString(String time, String inFormat, String outFormat) {
      SimpleDateFormat inSdf = new SimpleDateFormat(inFormat);
      SimpleDateFormat outSdf = new SimpleDateFormat(outFormat);
      try {
         return outSdf.format(inSdf.parse(time));
      } catch (Exception e) {
//			LogUtils.error("convert date to String error:" + e);
      }
      return null;
   }

   public static Date formatDate(String dateStr, String format) {
      Date date = null;

      try {
         SimpleDateFormat e = new SimpleDateFormat(format);
         date = e.parse(dateStr);
      } catch (Exception e) {
//			LogUtils.error("format date to String error:" + e);
      }

      return date;
   }

   public static Date formatDate(String dateStr) {
      Date date = null;

      try {
         SimpleDateFormat e = new SimpleDateFormat(MINUTE_FORMAT);
         date = e.parse(dateStr);
      } catch (Exception e) {
//			LogUtils.error("format date to String error:" + e);
      }

      return date;
   }

   public static String parseDate(Date date) {
      String str = "";

      try {
         SimpleDateFormat e = new SimpleDateFormat(MINUTE_FORMAT);
         str = e.format(date);
      } catch (Exception e) {
//			LogUtils.error("parse date to String error:" + e);
      }

      return str;
   }

   public static Date parseToDate(String timeStr, String formatStr) {
      Date date = null;
      try {
         SimpleDateFormat sf = new SimpleDateFormat(formatStr);
         date = sf.parse(timeStr);
      } catch (Exception e) {
//			LogUtils.error("format date to String error:" + e);
      }
      return date;
   }

   public static int getDayIndex(String timeStr, String format) {
      long time = DateUtils.getTime(timeStr, format);
      Calendar startCal = Calendar.getInstance();
      startCal.setTimeInMillis(time);
      return startCal.get(Calendar.DAY_OF_YEAR);
   }

   public static int getDayIndex(String timeStr) {
      long time = DateUtils.getTime(timeStr, DateUtils.DAY_FORMAT);
      Calendar startCal = Calendar.getInstance();
      startCal.setTimeInMillis(time);
      return startCal.get(Calendar.DAY_OF_YEAR);
   }

   public static String getDayStr(int index, String year) {
      long time = DateUtils.getTime(year, "yyyy");
      Calendar timeCal = Calendar.getInstance();
      timeCal.setTimeInMillis(time);
      timeCal.add(Calendar.DAY_OF_YEAR, index - 1);
      return DateUtils.getTime(timeCal.getTimeInMillis(), DateUtils.DAY_FORMAT);
   }

   /**
    * 获取当前分钟的世界时
    *
    * @param
    * @Return:
    */
   public static String nowUniversalTime() {
      String now = formatDataFromString(new Date(), SECOND_FORMAT);
      return DateUtils.futureHour(now, SECOND_FORMAT, -8);//北京时间转世界时减8
   }

   /**
    * 获取当前分钟的北京时
    *
    * @param
    * @Return:
    */
   public static String nowBeijingTime() {
      String now = formatDataFromString(new Date(), SECOND_FORMAT1);
      return now;
   }

   /**
    * 获取上一个整5分钟的世界时
    *
    * @param
    * @Return:
    */
   public static String getUniversalTimeLast5Min() {
      Calendar calendar = Calendar.getInstance();
      calendar.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE) / 5 * 5);
      calendar.set(Calendar.SECOND, 0);
      String last5Min = DateUtils.getTime(calendar.getTimeInMillis(), DateUtils.SECOND_FORMAT);
      return DateUtils.futureHour(last5Min, SECOND_FORMAT, -8);//北京时间转世界时减8
   }


   /**
    * 获取下一个整点的世界时
    *
    * @param
    * @Return:
    */
   public static String getNextHourUniversalTime() {
      String now = formatDataFromString(new Date(), DateUtils.HOUR_FORMAT) + "0000";
      return DateUtils.futureHour(now, DateUtils.SECOND_FORMAT, -7);//下一个小时加1，北京时间转世界时减8
   }

   /**
    * 获取当前整点的世界时
    *
    * @param
    * @Return:
    */
   public static String getThisHourUniversalTime() {
      String now = formatDataFromString(new Date(), DateUtils.HOUR_FORMAT) + "0000";
      return DateUtils.futureHour(now, DateUtils.SECOND_FORMAT, -8);//北京时间转世界时减8
   }

   /**
    * 获取上一个整点的世界时
    *
    * @param
    * @Return:
    */
   public static String getLastHourUniversalTime() {
      String now = formatDataFromString(new Date(), DateUtils.HOUR_FORMAT) + "0000";
      return DateUtils.futureHour(now, DateUtils.SECOND_FORMAT, -9);//北京时间转世界时减8
   }

   /**
    * 世界时转北京时
    *
    * @param universalTime 世界时 yyyy-MM-dd HH:mm:ss
    * @Return: 北京时 yyyy-MM-dd HH:mm:ss
    */
   public static String toBeijingTime(String universalTime) {
      return DateUtils.futureHour(universalTime, SECOND_FORMAT1, 8);//世界时转北京时间加8
   }

   /*
    * @para 一年中的第几天
    * @return 该日属于该年的第几个月
    * */
   public static Integer getMonthInt(int index, int year) {
      return Integer.parseInt(getDayStr(index, year + "").substring(4, 6));
   }

   /*
    * @para 年份，月份
    * @return 该月最大天数
    * */
   public static Integer getMaxDayOfMonth(Integer year, Integer month) {
      Calendar calendar = Calendar.getInstance();
      calendar.set(Calendar.YEAR, year);
      calendar.set(Calendar.MONTH, month - 1);
      if (month == 2)
         return 28;
      return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
   }

   /**
    * 日期转星期
    *
    * @param datetime
    * @return
    */
   public static String dateToWeek(String datetime) {
      SimpleDateFormat f = new SimpleDateFormat("yyyyMMdd");
      String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
      Calendar cal = Calendar.getInstance(); // 获得一个日历
      Date datet = null;
      try {
         datet = f.parse(datetime);
         cal.setTime(datet);
      } catch (ParseException e) {
         e.printStackTrace();
      }
      int w = cal.get(Calendar.DAY_OF_WEEK) - 1; // 指示一个星期中的某天。
      if (w < 0)
         w = 0;
      return weekDays[w];
   }

   public static String getNewFormat(String data) {
      String result = "";

      try {
         Date date = new SimpleDateFormat("yyyyMMddHHmm").parse(data);
         result = new SimpleDateFormat("yyyy年MM月dd日HH:mm").format(date);
         return result;
      } catch (Exception e) {
         return data;
      }
   }

   /*
    * 比较两个日期的大小
    * */
   public static Boolean compareToDate2(String startDate, String endDate, String dateFormat) {
      SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
      try {
         Date st = sdf.parse(startDate);
         Date et = sdf.parse(endDate);
         //开始日期小于等于结束日期则返回true
         if (st.before(et))
            return true;
         else {
            if (Objects.equals(startDate, endDate)) {
               return true;
            } else {
               return false;
            }
         }
      } catch (ParseException e) {
         e.printStackTrace();
         return null;
      }
   }

   public static String getCurrentDayTime() {
      SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DateUtils.DAY_FORMAT);
      return simpleDateFormat.format(new Date());
   }
}
