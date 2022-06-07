package club.javafamily.utils.common;

import lombok.*;

import java.io.Serializable;

/**
 * @author Jack Li
 * @date 2022/5/19 下午5:48
 * @description
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class QueryRequest implements Serializable {
   private PageQueryInfo page;
   private FilterInfo filter;
   private SortInfo sort;
}
