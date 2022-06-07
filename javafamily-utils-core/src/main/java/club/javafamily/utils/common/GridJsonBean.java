package club.javafamily.utils.common;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Jack Li
 * @date 2021/7/28 11:14 上午
 * @description
 */
@Data
@Builder
public class GridJsonBean implements Cloneable, Serializable {

   private Float startLat;
   private Float startLon;

   private Float endLat;
   private Float endLon;

   private Float latInterval;
   private Float lonInterval;

   private Integer latLength;
   private Integer lonLength;

   private Object ds;

   private Date forecastTime;
   private int dateStep;

   @Override
   public GridJsonBean clone() throws CloneNotSupportedException {
      return (GridJsonBean) super.clone();
   }
}
