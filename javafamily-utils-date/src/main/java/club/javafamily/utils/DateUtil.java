package club.javafamily.utils;

import lombok.NonNull;
import org.joda.time.Period;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

   public static final long DAY_MS = 24 * 60 * 60 * 1000;
   public static final String CMA_DATETIME_PATTERN = "yyyyMMddHHmmss";
   public static final String ONLY_DATE_FORMAT = Tool.DEFAULT_DATE_FORMAT;
   public static final String NORMAL_DATE_HOUR_PATTERN = "yyyy-MM-dd HH";
   public static final String CMA_DATE_FORMAT = "yyyyMMdd";
   public static final String HOUR_MINUS_FORMAT_PATTERN = "HH:mm";
   public static final String NORMAL_DATETIME_FORMAT = Tool.DEFAULT_DATETIME_FORMAT;

   public static Date convertToGlobalDate(Date date) {
      return convertDate(date, -8);
   }

   public static Date convertToBJDate(@NonNull Date date) {
      return convertDate(date, 8);
   }

   public static Date convertDate(@NonNull Date date, int offset) {
      final Calendar sc = DateUtil.getCalendar(date);
      sc.add(Calendar.HOUR_OF_DAY, offset);
      date = sc.getTime();

      return date;
   }

   public static String formatGlobalDateTime(@NonNull Date date) {
      date = DateUtil.convertToGlobalDate(date);

      SimpleDateFormat format = DATE_FORMAT.get();

      return format.format(date);
   }

   public static String formatCmaDataRange(DateRange range) {
      return formatCmaDataRange(range.getTimeTypeEnum(),
         range.getStart(), range.getEnd());
   }

   public static String formatCmaDataRange(DateRange range,
                                           TimeTypeEnum timeType)
   {
      return formatCmaDataRange(timeType,
         range.getStart(), range.getEnd(), range.isIncludeRight());
   }

   public static String formatCmaDataRange(TimeTypeEnum timeType,
                                           Date start, Date end)
   {
      return formatCmaDataRange(timeType, start, end, false);
   }

   public static String formatCmaDataRange(TimeTypeEnum timeType,
                                           Date start, Date end,
                                           boolean includeRight)
   {
      if (start == null && end == null) {
         throw new NullPointerException("Format date is null");
      }

      if(timeType == TimeTypeEnum.MINUS || timeType == TimeTypeEnum.HOUR
         || timeType == TimeTypeEnum.HOUR_N)
      {
         // cma time is global zone.
         start = DateUtil.convertToGlobalDate(start);
         end = DateUtil.convertToGlobalDate(end);
      }

      start = fixDateSuffix(timeType, start);
      end = fixDateSuffix(timeType, end);

      String startStr = DateUtil.formatCmaDateTime(start);
      String endStr = DateUtil.formatCmaDateTime(end);

      return "[" + startStr + "," + endStr
         + (includeRight ? "]" : ")");
   }

   public static Date fixDateSuffix(TimeTypeEnum timeType, Date date) {
      final Calendar calendar = DateUtil.getCalendar(date);
      calendar.set(Calendar.MILLISECOND, 0);
      calendar.set(Calendar.SECOND, 0);

      if(timeType == TimeTypeEnum.HOUR || timeType == TimeTypeEnum.HOUR_N
         || timeType == TimeTypeEnum.DAILY)
      {
         calendar.set(Calendar.MINUTE, 0);
      }

      if(timeType == TimeTypeEnum.DAILY) {
         calendar.set(Calendar.HOUR_OF_DAY, 0);
      }

      return calendar.getTime();

   }

   public static String formatCmaDateTime(@NonNull Date date) {
      SimpleDateFormat format = DATE_FORMAT.get();

      return format.format(date);
   }

   public static Date parseCmaDateTime(@NonNull String dateStr) {
      SimpleDateFormat format = DATE_FORMAT.get();

      try {
         return format.parse(dateStr);
      } catch (ParseException e) {
         e.printStackTrace();
      }

      return null;
   }

   public static String formatHourMinus(@NonNull Date date) {
      final SimpleDateFormat format = HOUR_MINUS_FORMAT.get();

      return format.format(date);
   }

   public static String formatNormal(@NonNull Date date) {
      SimpleDateFormat format = NORMAL_DATE_TIME_FORMAT.get();

      return format.format(date);
   }

   public static Date parseNormalDate(@NonNull String dateStr) {
      SimpleDateFormat format = NORMAL_DATE_TIME_FORMAT.get();

      try {
         return format.parse(dateStr);
      } catch (ParseException e) {
         throw new RuntimeException(e);
      }
   }

   public static Date[] getCurrentTenDayRange() {
      return null;
   }

   /**
    * 获得本月的开始时间
    */
   public static Date getMonthStartTime(Date date) {
      Calendar c = Calendar.getInstance();
      c.setTime(date);
      Date dt = null;

      try {
         c.set(Calendar.DATE, 1);
         dt = shortSdf.parse(shortSdf.format(c.getTime()));
      } catch (Exception e) {
         e.printStackTrace();
      }
      return dt;
   }

   /**
    * 本月的结束时间
    */
   public static Date getMonthEndTime(Date date) {
      Calendar c = Calendar.getInstance();
      c.setTime(date);
      Date dt = null;
      try {
         c.set(Calendar.DATE, 1);
         c.add(Calendar.MONTH, 1);
         c.add(Calendar.DATE, -1);
         dt = longSdf.parse(shortSdf.format(c.getTime()) + " 23:59:59.999");
      } catch (Exception e) {
         e.printStackTrace();
      }
      return dt;
   }

   /**
    * 当前年的开始时间
    */
   public static Date getYearStartTime(Date date) {
      Calendar c = Calendar.getInstance();
      c.setTime(date);
      Date dt = null;

      try {
         c.set(Calendar.MONTH, Calendar.JANUARY);
         c.set(Calendar.DATE, 1);
         dt = shortSdf.parse(shortSdf.format(c.getTime()));
      } catch (Exception e) {
         e.printStackTrace();
      }
      return dt;
   }

   /**
    * 当前年的结束时间
    */
   public static Date getYearEndTime(Date date) {
      Calendar c = Calendar.getInstance();
      c.setTime(date);
      Date dt = null;
      try {
         c.set(Calendar.MONTH, 11);
         c.set(Calendar.DATE, 31);
         dt = longSdf.parse(shortSdf.format(c.getTime()) + " 23:59:59.999");
      } catch (Exception e) {
         e.printStackTrace();
      }

      return dt;
   }

   /**
    * 当前季度的开始时间
    */
   public static Date getQuarterStartTime(Date date) {
      Calendar c = Calendar.getInstance();
      c.setTime(date);
      int currentMonth = c.get(Calendar.MONTH) + 1;
      Date dt = null;
      try {
         if (currentMonth <= 3) {
            c.set(Calendar.MONTH, 0);
         }
         else if (currentMonth <= 6) {
            c.set(Calendar.MONTH, 3);
         }
         else if (currentMonth <= 9) {
            c.set(Calendar.MONTH, 6);
         }
         else if (currentMonth <= 12) {
            c.set(Calendar.MONTH, 9);
         }

         c.set(Calendar.DATE, 1);

         dt = longSdf.parse(shortSdf.format(c.getTime()) + " 00:00:00.000");
      } catch (Exception e) {
         e.printStackTrace();
      }

      return dt;
   }

   /**
    * 当前季度的结束时间
    */
   public static Date getQuarterEndTime(Date date) {
      Calendar c = DateUtil.getCalendar(date);

      int currentMonth = c.get(Calendar.MONTH) + 1;
      Date dt = null;

      try {
         if (currentMonth <= 3) {
            c.set(Calendar.MONTH, 2);
            c.set(Calendar.DATE, 31);
         } else if (currentMonth <= 6) {
            c.set(Calendar.MONTH, 5);
            c.set(Calendar.DATE, 30);
         } else if (currentMonth <= 9) {
            c.set(Calendar.MONTH, 8);
            c.set(Calendar.DATE, 30);
         } else if (currentMonth <= 12) {
            c.set(Calendar.MONTH, 11);
            c.set(Calendar.DATE, 31);
         }

         dt = longSdf.parse(shortSdf.format(c.getTime()) + " 23:59:59.999");
      } catch (Exception e) {
         e.printStackTrace();
      }

      return dt;
   }

   public static int getQuarterStart(int quarter) {
      if(quarter == 1)  {
         return 1;
      }
      else if(quarter == 2) {
         return 4;
      }
      else if(quarter == 3) {
         return 7;
      }
      else {
         return 10;
      }
   }

   public static int getQuarterEnd(int quarter) {
      if(quarter == 1)  {
         return 3;
      }
      else if(quarter == 2) {
         return 6;
      }
      else if(quarter == 3) {
         return 9;
      }
      else {
         return 12;
      }
   }

   public static int getQuarter(Date date) {
      Calendar calendar = DateUtil.getCalendar(date);

      int month = calendar.get(Calendar.MONTH);

      if(month < 3) {
         return 1;
      }
      else if(month < 6) {
         return 2;
      }
      else if(month < 9) {
         return 3;
      }
      else {
         return 4;
      }
   }

   /**
    * 获取月旬 三旬: 上旬1-10日 中旬11-20日 下旬21-31日
    */
   public static int getTenDay(Date date) {
      Calendar c = Calendar.getInstance();
      c.setTime(date);
      int i = c.get(Calendar.DAY_OF_MONTH);

      if (i < 11) {
         return 1;
      } else if (i < 21) {
         return 2;
      } else {
         return 3;
      }
   }


   /**
    * 获取所属旬开始时间
    */
   public static Date getTenDayStartTime(Date date) {
      int ten = getTenDay(date);
      try {
         if (ten == 1) {
            return getMonthStartTime(date);
         } else if (ten == 2) {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-11");
            return shortSdf.parse(df.format(date));
         } else {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-21");
            return shortSdf.parse(df.format(date));
         }
      } catch (ParseException e) {
         e.printStackTrace();
      }

      return null;
   }

   /**
    * 获取所属旬结束时间
    */
   public static Date getTenDayEndTime(Date date) {
      int ten = getTenDay(date);
      try {
         if (ten == 1) {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-10 23:59:59.999");
            return longSdf.parse(df.format(date));
         } else if (ten == 2) {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-20 23:59:59.999");
            return longSdf.parse(df.format(date));
         } else {
            return getMonthEndTime(date);
         }
      } catch (ParseException e) {
         e.printStackTrace();
      }
      return null;
   }

   public static boolean isToday(Date date) {
      LocalDate localDate = LocalDate.now();

      if (date.getYear() == localDate.getYear()
         && date.getMonth() == localDate.getMonthValue()
         && date.getDate() == localDate.getDayOfMonth()) {
         return true;
      }

      return false;
   }

   public static boolean isLastDay(Date date) {
      if (date.getMonth() == Calendar.DECEMBER
         && date.getDate() == 31) {
         return true;
      }

      return false;
   }

   public static Calendar getCalendar(Date date) {
      Calendar calendar = Calendar.getInstance();
      calendar.setTime(date);

      return calendar;
   }

   /**
    * Note: This is not precise in month
    */
   @Deprecated
   public static int convertToHour(int limit, int unit) {
      if(unit == Calendar.HOUR) {
         return limit;
      }
      else if(unit == Calendar.DATE) {
         return limit * 24;
      }
      else if(unit == Calendar.MONTH) {
         return limit * 30 * 24;
      }
      else if(unit == Calendar.YEAR) {
         return limit * 12 * 30 * 24;
      }

      LOGGER.warn("Unit is not exist when convertToHour, unit: {}", unit);

      return limit;
   }

   public static Date getPreviousEndDate() {
      Date now = new Date();
      Calendar calendar = getCalendar(now);

      calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) - 1);

      Date previousDate = calendar.getTime();

      return DateUtil.getYearEndTime(previousDate);
   }

   public static String formatOnlyNormalDate(Date date) {
      final SimpleDateFormat format = DAY_FORMAT1_FORMATTER.get();

      return format.format(date);
   }

   public static Date parseOnlyNormalDate(String dateStr) {
      final SimpleDateFormat format = DAY_FORMAT1_FORMATTER.get();

      try {
         return format.parse(dateStr);
      } catch (ParseException e) {
         throw new RuntimeException(e);
      }
   }

   public static int getCurrentYear() {
      return Calendar.getInstance().get(Calendar.YEAR);
   }

   public static Calendar currentIntegralPoint() {
      return getIntegralPoint(new Date());
   }

   public static Calendar getIntegralPoint(Date date) {
      final Calendar calendar = DateUtil.getCalendar(date);

      calendar.set(Calendar.MINUTE, 0);
      calendar.set(Calendar.SECOND, 0);
      calendar.set(Calendar.MILLISECOND, 0);

      return calendar;
   }

   public static Period diffPeriod(Date date1, Date date2) {
      return new Period(date1.getTime(), date2.getTime());
   }

   public static Date parseCmaDate(String dateStr) {
      try {
         return CMA_ONLY_DATE_FORMAT.get().parse(dateStr);
      } catch (ParseException e) {
         throw new RuntimeException(e);
      }
   }

   public static String formatCmaDate(Date date) {
      return CMA_ONLY_DATE_FORMAT.get().format(date);
   }

   public static String formatNormalDateHour(Date date) {
      return DATE_HOUR_FORMAT.get().format(date);
   }

   public static Calendar getTodayStartCalendar() {
      Calendar calendar = DateUtil.getCalendar(new Date());

      calendar.set(Calendar.HOUR_OF_DAY, 0);
      calendar.set(Calendar.MINUTE, 0);
      calendar.set(Calendar.SECOND, 0);
      calendar.set(Calendar.MILLISECOND, 0);

      return calendar;
   }

   public static Date getYesterdayStart() {
      final Calendar today = getTodayStartCalendar();

      // yesterday
      today.add(Calendar.DAY_OF_MONTH, -1);

      return today.getTime();
   }

   public static Date getTomorrowStart() {
      final Calendar today = getTodayStartCalendar();

      today.add(Calendar.DAY_OF_MONTH, 1);

      return today.getTime();
   }

   public static LocalDateTime toLocaleDateTime(Date date) {
      return LocalDateTime.ofInstant(date.toInstant(),
         Tool.DEFAULT_TIME_ZONE.toZoneId());
   }

   public static Date toDateTime(LocalDateTime localDateTime) {
      final Instant instant = localDateTime.atZone(
         Tool.DEFAULT_TIME_ZONE.toZoneId()).toInstant();

      return Date.from(instant);
   }

   public static LocalDate toLocaleDate(Date date) {
      return toLocaleDateTime(date).toLocalDate();
   }

   public static Date toDate(LocalDate localDate) {
      return new Date(localDate.getYear(),
         localDate.getMonthValue() - 1,
         localDate.getDayOfMonth());
   }

   private final static SimpleDateFormat shortSdf = new SimpleDateFormat("yyyy-MM-dd");
   private final static SimpleDateFormat longSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

   private static final ThreadLocal<SimpleDateFormat> DATE_FORMAT
      = ThreadLocal.withInitial(() -> new SimpleDateFormat(CMA_DATETIME_PATTERN));

   private static final ThreadLocal<SimpleDateFormat> NORMAL_DATE_TIME_FORMAT
      = ThreadLocal.withInitial(() -> new SimpleDateFormat(NORMAL_DATETIME_FORMAT));

   private static final ThreadLocal<SimpleDateFormat> DAY_FORMAT1_FORMATTER
      = ThreadLocal.withInitial(() -> new SimpleDateFormat(ONLY_DATE_FORMAT));

   private static final ThreadLocal<SimpleDateFormat> CMA_ONLY_DATE_FORMAT
      = ThreadLocal.withInitial(() -> new SimpleDateFormat(CMA_DATE_FORMAT));

   private static final ThreadLocal<SimpleDateFormat> DATE_HOUR_FORMAT
      = ThreadLocal.withInitial(() -> new SimpleDateFormat(NORMAL_DATE_HOUR_PATTERN));

   private static final ThreadLocal<SimpleDateFormat> HOUR_MINUS_FORMAT
      = ThreadLocal.withInitial(() -> new SimpleDateFormat(HOUR_MINUS_FORMAT_PATTERN));

   private static final Logger LOGGER = LoggerFactory.getLogger(DateUtil.class);
}
