package club.javafamily.utils.common;

import com.mlog.utils.enums.SortOptionEnum;
import lombok.*;

import java.io.Serializable;

/**
 * @author Jack Li
 * @date 2022/5/19 下午5:47
 * @description
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class SortInfo implements Serializable {
   private SortOptionEnum sortOption;
   private String sortCol;

   public String toQueryValue() {
      return sortCol + ":" + sortOption.name();
   }
}
