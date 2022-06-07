package club.javafamily.utils.common;

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
public class PageQueryInfo implements Serializable {
   private int page;
   private int size;
}
