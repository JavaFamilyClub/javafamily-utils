/*
 * Copyright (c) 2020, JavaFamily Technology Corp, All Rights Reserved.
 */

package club.javafamily.util.string;

public final class StringUtils {
   private StringUtils() {
   }

   public static boolean isEmpty(String str) {
      return str == null || str.isEmpty();
   }
}
