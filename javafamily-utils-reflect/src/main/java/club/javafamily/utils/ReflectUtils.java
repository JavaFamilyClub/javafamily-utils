package club.javafamily.utils;

import org.springframework.lang.Nullable;
import org.springframework.util.ReflectionUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static org.springframework.util.ReflectionUtils.handleReflectionException;

public class ReflectUtils {
   public static List<Field> getAnnotationColumnFields(Class<?> clazz,
                                                       Class<? extends Annotation> annoClazz)
   {
      List<Field> cmaColumnFields = new ArrayList<>();

      ReflectionUtils.doWithFields(clazz, cmaColumnFields::add, (
         field) -> field.getDeclaredAnnotation(annoClazz) != null);

      return cmaColumnFields;
   }

   public static void setValue(Field field, Object targetObj, String value) {
      if(value == null) {
         return;
      }

      field.setAccessible(true);
      Class<?> type = field.getType();

      Object newValue = convertValue(type, value);

      ReflectionUtils.setField(field, targetObj, newValue);
   }

   public static <T> Object convertValue(Class<T> clazz, String value) {
      if(clazz.isAssignableFrom(Number.class)) {
         value = Tool.makeInvalidNumberToNull(value);
      }

      if(value == null) {
         return null;
      }
      else if(clazz == int.class || clazz == Integer.class) {
         return Integer.parseInt(value);
      }
      else if(clazz == float.class || clazz == Float.class) {
         return Float.parseFloat(value);
      }
      else if(clazz == long.class || clazz == Long.class) {
         return Long.parseLong(value);
      }
      else if(clazz == double.class || clazz == Double.class) {
         return Double.parseDouble(value);
      }
      else if(clazz == boolean.class || clazz == Boolean.class) {
         return Boolean.parseBoolean(value);
      }
      else if(clazz == LocalDate.class) {
         return LocalDate.parse(value,
            DateTimeFormatter.ofPattern(DateUtil.ONLY_DATE_FORMAT));
      }
      else if(clazz == LocalTime.class) {
         return LocalTime.parse(value,
            DateTimeFormatter.ofPattern(Tool.DEFAULT_TIME_FORMAT));
      }
      else if(clazz == LocalDateTime.class) {
         return LocalDateTime.parse(value,
            DateTimeFormatter.ofPattern(Tool.DEFAULT_DATETIME_FORMAT));
      }
      else if(clazz == Date.class) {
         return DateUtil.parseNormalDate(value);
      }

      return value;
   }

   /**
    * 获取一个 class 对象所有的 Fields (包括父类)
    * @param clazz class 对象
    * @return List<Field>
    */
   public static List<Field> getAllFields(Class<?> clazz){
      List<Field> fieldList = new ArrayList<>();

      while(clazz != null) {
         fieldList.addAll(new ArrayList<>(Arrays.asList(clazz.getDeclaredFields())));
         clazz = clazz.getSuperclass();
      }

      return fieldList;
   }

   /**
    * 反射获取字段值
    * @param field
    * @param target
    * @return
    */
   @Nullable
   public static <T> T getField(Field field,  @Nullable Object target) {
      try {
         field.setAccessible(true);

         return (T) field.get(target);
      }
      catch (IllegalAccessException ex) {
         handleReflectionException(ex);
      }

      throw new IllegalStateException("Should never get here");
   }
}
