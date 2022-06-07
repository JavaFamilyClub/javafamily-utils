package club.javafamily.utils.collections;

import java.util.Collection;
import java.util.Map;

/**
 * @author Jack Li
 * @date 2022/6/1 下午4:55
 * @description 集合操作工具类
 */
public final class CollectionUtil {

   public static boolean isEmpty(Collection<?> collection) {
      return collection == null || collection.isEmpty();
   }

   public static boolean isEmpty(Map<?, ?> map) {
      return map == null || map.isEmpty();
   }

   /**
    * 计算集合大小
    * @param c 集合
    * @return 集合大小
    */
   public static int count(Collection<?> c) {
      return CollectionUtil.isEmpty(c) ? 0 : c.size();
   }

}
