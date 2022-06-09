package club.javafamily.utils;

import java.util.*;

/**
 * @author Jack Li
 * @date 2021/7/28 10:19 上午
 * @description 天擎访问数据类型及时间跨度
 */
public enum TimeTypeEnum {
   /**
    * 分钟
    */
   MINUS(2, Calendar.DATE),
   HOUR(15, Calendar.DATE),
   HOUR_N(360, Calendar.HOUR), // cma adjust hour from 8760 to 360.
   DAILY(300, Calendar.DATE), // for year data, adjust 24 month to 12.
   // cma adjust day from 12 month to 360 day.
   // 1991 year using 300, 360 is limited.
   TEN_DAY(DAILY.limit, DAILY.unit),
   MONTH(DAILY.limit, DAILY.unit),
   QUARTER(MONTH.limit, MONTH.unit),
   YEAR(QUARTER.limit, QUARTER.unit),
   CUSTOM(YEAR.limit, YEAR.unit);

   private final int limit;
   private final int unit;
   static final String QUARTER_FLAG = "Q";

   TimeTypeEnum() {
      this(-1, -1);
   }

   TimeTypeEnum(int limit, int unit) {
      this.limit = limit;
      this.unit = unit;
   }

   public int getLimit() {
      return limit;
   }

   public int getUnit() {
      return unit;
   }

   public String getIdentifier(Date date) {
      Calendar calendar = DateUtil.getCalendar(date);
      StringJoiner sj = new StringJoiner("/");

      switch (this) {
         case HOUR:
            // yyyy-MM-dd-hh
            sj.add(calendar.get(Calendar.YEAR) + "");
            sj.add(calendar.get(Calendar.MONTH) + "");
            sj.add(calendar.get(Calendar.DATE) + "");
            sj.add(calendar.get(Calendar.HOUR_OF_DAY) + "");

            break;

         case DAILY:
            // yyyy-MM-dd
            sj.add(calendar.get(Calendar.YEAR) + "");
            sj.add(calendar.get(Calendar.MONTH) + "");
            sj.add(calendar.get(Calendar.DATE) + "");
            break;

         case TEN_DAY:
            // yyyy-MM-[1,2,3]: 1: first ten day
            sj.add(calendar.get(Calendar.YEAR) + "");
            sj.add(calendar.get(Calendar.MONTH) + "");
            sj.add(DateUtil.getTenDay(date) + TEN_DAY.name());
            break;

         case MONTH:
            // yyyy-MM
            sj.add(calendar.get(Calendar.YEAR) + "");
            sj.add(calendar.get(Calendar.MONTH) + "");
            break;

         case QUARTER:
            // yyyy-[1,2,3,4]: 1 means spring
            sj.add(calendar.get(Calendar.YEAR) + "");
            sj.add(DateUtil.getQuarter(date) + QUARTER_FLAG);
            break;

         case YEAR:
            // yyyy
            sj.add(calendar.get(Calendar.YEAR) + "");
            break;

         default:
      }

      return sj.toString();
   }
}
