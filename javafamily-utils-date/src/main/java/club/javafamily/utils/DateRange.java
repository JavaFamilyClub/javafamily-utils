package club.javafamily.utils;


import java.util.Date;

public class DateRange {
   private TimeTypeEnum timeTypeEnum;
   private Date start;
   private Date end;
   private boolean includeRight;

   public DateRange() {
   }

   public DateRange(Date start, Date end) {
      this(start, end, false);
   }

   public DateRange(Date start, Date end, boolean includeRight) {
      this(null, start, end, includeRight);
   }

   public DateRange(TimeTypeEnum timeTypeEnum, Date start, Date end) {
      this(timeTypeEnum, start, end, false);
   }

   public DateRange(TimeTypeEnum timeTypeEnum,
                    Date start,
                    Date end,
                    boolean includeRight)
   {
      this.timeTypeEnum = timeTypeEnum;
      this.start = start;
      this.end = end;
      this.includeRight = includeRight;
   }

   public TimeTypeEnum getTimeTypeEnum() {
      return timeTypeEnum;
   }

   public void setTimeTypeEnum(TimeTypeEnum timeTypeEnum) {
      this.timeTypeEnum = timeTypeEnum;
   }

   public Date getStart() {
      return start;
   }

   public void setStart(Date start) {
      this.start = start;
   }

   public Date getEnd() {
      return end;
   }

   public void setEnd(Date end) {
      this.end = end;
   }

   public boolean isIncludeRight() {
      return includeRight;
   }

   public void setIncludeRight(boolean includeRight) {
      this.includeRight = includeRight;
   }

   @Override
   public String toString() {
      return "DateRange{" +
         "timeTypeEnum=" + timeTypeEnum +
         ", start=" + start +
         ", end=" + end +
         ", includeRight=" + includeRight +
         '}';
   }
}
