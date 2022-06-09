package club.javafamily.utils;

import java.util.*;

/**
 * This is not thread safe class, so using it in local scope
 */
public class DateRangeIterator implements Iterator<DateRange> {

   private List<DateRange> ranges = new ArrayList<>();
   private int index = 0;

   public DateRangeIterator(DateRange range) {
      this(range.getTimeTypeEnum(), range.getStart(), range.getEnd(),
         range.isIncludeRight());
   }

   public DateRangeIterator(TimeTypeEnum timeTypeEnum, Date start, Date end) {
      this(timeTypeEnum, start, end, false);
   }

   public DateRangeIterator(TimeTypeEnum timeTypeEnum, Date start, Date end,
                            boolean includeRight)
   {
      int limit = timeTypeEnum.getLimit();
      Calendar startCalendar = DateUtil.getCalendar(start);
      Calendar endCalendar = DateUtil.getCalendar(end);

      if(limit <= 0 || startCalendar.compareTo(endCalendar) == 0) {
         // single range
         ranges.add(new DateRange(timeTypeEnum, start, end, includeRight));
         return;
      }

      int unit = timeTypeEnum.getUnit();

      // convert to hour? or minus?
      if(unit != Calendar.HOUR) {
         // convert to hour
         limit = DateUtil.convertToHour(limit, unit);
      }

      Date sDate, eDate;

      while(startCalendar.before(endCalendar)) {
         sDate = startCalendar.getTime();

         startCalendar.add(Calendar.HOUR, limit);

         if(!startCalendar.before(endCalendar)) {
            eDate = end;
         }
         else {
            eDate = startCalendar.getTime();
         }

         ranges.add(new DateRange(timeTypeEnum, sDate, eDate, includeRight));
      }
   }

   @Override
   public boolean hasNext() {
      return index < ranges.size();
   }

   @Override
   public DateRange next() {
      return ranges.get(index++);
   }

}
