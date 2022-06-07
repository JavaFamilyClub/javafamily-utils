package club.javafamily.utils.number;

/**
 * @author Jack Li
 * @date 2021/7/29 5:16 下午
 * @description
 */
public class NumberUtil {

   public static <T> double parseDoubleOrElse(T val, double defaultDouble) {
      try {
         return Double.parseDouble((String) val);
      }
      catch(Exception ignore) {
      }

      return defaultDouble;
   }

   /**
    * parse int for string "1" or string "1.00" to int 1
    */
   public static Integer parseWeatherInt(String str) {
      if(str == null) {
         return null;
      }

      if(str.contains(".")) {
         return (int) Double.parseDouble(str);
      }

      return Integer.parseInt(str);
   }
}
