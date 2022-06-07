package club.javafamily.utils.enums;

/**
 * @author Jack Li
 * @date 2022/5/18 下午3:06
 * @description
 */
public class EnumUtil {

   public static <E extends Enum<E>> E parseFromOrdinal(
      final Class<E> enumClass, final int ordinal)
   {
      return parseFromOrdinal(enumClass, ordinal, null);
   }

   /**
    * 根据枚举 ordinal 获取枚举类
    * @param enumClass 枚举类
    * @param ordinal 下标
    * @param defaultEnum 默认值
    * @param <E> 枚举
    * @return E
    */
   public static <E extends Enum<E>> E parseFromOrdinal(
      final Class<E> enumClass, final int ordinal,
      final E defaultEnum)
   {
      for (E item : enumClass.getEnumConstants()) {
         if(item.ordinal() == ordinal) {
            return item;
         }
      }

      return defaultEnum;
   }

}
