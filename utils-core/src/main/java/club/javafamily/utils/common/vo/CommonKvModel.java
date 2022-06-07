package club.javafamily.utils.common.vo;

/**
 * @author Jack Li
 * @date 2021/7/26 6:01 下午
 * @description
 */
public class CommonKvModel<T, V> {

   public CommonKvModel() {
   }

   public CommonKvModel(T key, V value) {
      this.key = key;
      this.value = value;
   }

   public T getKey() {
      return key;
   }

   public void setKey(T key) {
      this.key = key;
   }

   public V getValue() {
      return value;
   }

   public void setValue(V value) {
      this.value = value;
   }

   @Override
   public String toString() {
      return "CommonKVModel [key=" + key + ", value=" + value + "]";
   }

   private T key;
   private V value;
}
