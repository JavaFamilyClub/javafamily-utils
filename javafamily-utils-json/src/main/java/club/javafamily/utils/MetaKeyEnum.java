package club.javafamily.utils;

/**
 * @author Jack Li
 * @date 2022/8/11 上午11:53
 * @description additional-spring-configuration-metadata.json 中一级属性的 key
 */
public enum MetaKeyEnum {

   /**
    * group
    */
   GROUP("groups"),

   /**
    * properties
    */
   PROPERTIES("properties"),

   /**
    * hints
    */
   HINTS("hints");

   private String key;

   MetaKeyEnum(String key) {
      this.key = key;
   }

   public String getKey() {
      return key;
   }
}
