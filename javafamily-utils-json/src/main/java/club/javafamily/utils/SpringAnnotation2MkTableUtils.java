package club.javafamily.utils;

import club.javafamily.utils.spring.ObjectUtils;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Jack Li
 * @date 2022/8/11 上午10:42
 * @description 将 additional-spring-configuration-metadata.json 转化为 Markdown Table
 */
public class SpringAnnotation2MkTableUtils {

   /**
    * 将 additional-spring-configuration-metadata.json 转化为 Markdown Table
    * @param metadataJson additional-spring-configuration-metadata.json
    * @param headers 表头属性
    * @return markdown 字符串
    */
   public static String convert(String metadataJson,
                                String...headers)
   {
      return convert(metadataJson,
         MetaKeyEnum.PROPERTIES,
         headers);
   }

   /**
    * 将 additional-spring-configuration-metadata.json 转化为 Markdown Table
    * @param metadataJson additional-spring-configuration-metadata.json
    * @param key 属性 key
    * @param headers 表头属性
    * @return markdown 字符串
    */
   public static String convert(String metadataJson,
                                MetaKeyEnum key,
                                String...headers)
   {
      final JSONObject jsonObject = JSONObject.parseObject(metadataJson);

      final JSONArray jsonArray = jsonObject.getJSONArray(key.getKey());

      List<String> rows = new ArrayList<>();

      rows.add(JsonUtilsConstant.MK_TABLE_FLAG
         + String.join(JsonUtilsConstant.MK_TABLE_FLAG, Arrays.asList(headers))
         + JsonUtilsConstant.MK_TABLE_FLAG);

      rows.add(JsonUtilsConstant.MK_TABLE_FLAG
         + Arrays.stream(headers)
                 .map(s -> JsonUtilsConstant.MK_TABLE_SPLIT_PLACEHOLDER)
                 .collect(Collectors.joining(JsonUtilsConstant.MK_TABLE_FLAG))
         + JsonUtilsConstant.MK_TABLE_FLAG);

      for (int i = 0; i < jsonArray.size(); i++) {
         final JSONObject item = jsonArray.getJSONObject(i);
         StringJoiner sj = new StringJoiner(JsonUtilsConstant.MK_TABLE_FLAG);

         for (String header : headers) {
            final Object value = item.get(header);

            String valueStr = ObjectUtils.getDisplayString(value);

            sj.add(valueStr);
         }

         rows.add(JsonUtilsConstant.MK_TABLE_FLAG
            + sj.toString()
            + JsonUtilsConstant.MK_TABLE_FLAG);
      }

      return String.join("\n", rows);
   }

}
