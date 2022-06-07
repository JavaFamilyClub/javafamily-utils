package club.javafamily.utils.common;

import club.javafamily.utils.enums.QueryMatchType;
import lombok.*;

import java.io.Serializable;

/**
 * @author Jack Li
 * @date 2022/5/19 下午5:46
 * @description
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class FilterInfo implements Serializable {

   private RequestFilterItemVo[] items;

   @Builder
   @NoArgsConstructor
   @AllArgsConstructor
   @Data
   public static class RequestFilterItemVo implements Serializable {
      private String elem;
      private Object[] values;

      @Builder.Default
      private QueryMatchType matchType = QueryMatchType.FUZZY;
   }
}
