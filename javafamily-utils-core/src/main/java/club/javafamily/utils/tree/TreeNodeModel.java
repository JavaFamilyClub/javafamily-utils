package club.javafamily.utils.tree;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Jack Li
 * @date 2021/8/18 6:14 下午
 * @description
 */
@Builder
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TreeNodeModel implements Serializable {

   public static final String ROOT_PATH = "/";

   private String label;
   private String value;
   @Builder.Default
   private List<TreeNodeModel> children = new ArrayList<>();
   private boolean leaf;
   private String tooltip;
   private boolean expanded;
   private Object type;
   private String path;
   private Object data;

   @Tolerate
   public TreeNodeModel() {
   }
}
