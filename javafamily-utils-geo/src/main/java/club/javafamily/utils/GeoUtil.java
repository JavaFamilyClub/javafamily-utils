package club.javafamily.utils;

import org.gavaghan.geodesy.*;
import org.locationtech.jts.geom.Point;

/**
 * @author Jack Li
 * @date 2022/5/11 上午10:01
 * @description
 */
public final class GeoUtil {

   public static double calcDistance(Point point1, Point point2) {
      return calcDistance(point1, point2, Ellipsoid.WGS84);
   }

   public static double calcDistance(Point point1, Point point2,
                                     Ellipsoid ellipsoid)
   {
      return calcDistance(point1.getY(), point1.getX(),
         point2.getY(), point2.getX(), ellipsoid);
   }

   public static double calcDistance(double lat1, double lon1,
                                     double lat2, double lon2)
   {
      return calcDistance(lat1, lon1, lat2, lon2, Ellipsoid.WGS84);
   }

   /**
    * 计算两个经纬度之间的距离
    * @param lat1 坐标 1 纬度
    * @param lon1 坐标 1 经度
    * @param lat2 坐标 2 纬度
    * @param lon2 坐标 2 经度
    * @param ellipsoid 坐标系
    * @return 距离(m)
    */
   public static double calcDistance(double lat1, double lon1,
                                     double lat2, double lon2,
                                     Ellipsoid ellipsoid)
   {
      GlobalCoordinates source = new GlobalCoordinates(lat1, lon1);
      GlobalCoordinates target = new GlobalCoordinates(lat2, lon2);

      return getDistanceMeter(source, target, ellipsoid);
   }

   public static double getDistanceMeter(GlobalCoordinates gpsFrom,
                                         GlobalCoordinates gpsTo,
                                         Ellipsoid ellipsoid)
   {
      //创建GeodeticCalculator，调用计算方法，传入坐标系、经纬度用于计算距离
      GeodeticCurve geoCurve = new GeodeticCalculator().calculateGeodeticCurve(ellipsoid, gpsFrom, gpsTo);

      return geoCurve.getEllipsoidalDistance();
   }

}
