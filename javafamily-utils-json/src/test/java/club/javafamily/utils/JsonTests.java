package club.javafamily.utils;

import org.junit.jupiter.api.Test;

/**
 * @author Jack Li
 * @date 2022/8/11 上午11:48
 * @description
 */
public class JsonTests {

   @Test
   void testConvert() {
      final String mk = SpringAnnotation2MkTableUtils.convert(json,
         "name", "type", "description", "defaultValue");

      System.out.println(mk);
   }

   String json = "{\n" +
           "   \"groups\": [\n" +
           "      {\n" +
           "         \"name\": \"javafamily.cache\",\n" +
           "         \"type\": \"club.javafamily.autoconfigre.cache.properties.JavaFamilyCacheProperties\",\n" +
           "         \"sourceType\": \"club.javafamily.autoconfigre.cache.properties.JavaFamilyCacheProperties\"\n" +
           "      },\n" +
           "      {\n" +
           "         \"name\": \"javafamily.cache.caffeine\",\n" +
           "         \"type\": \"club.javafamily.autoconfigre.cache.properties.JavaFamilyCacheProperties$Caffeine\",\n" +
           "         \"sourceType\": \"club.javafamily.autoconfigre.cache.properties.JavaFamilyCacheProperties\",\n" +
           "         \"sourceMethod\": \"getCaffeine()\"\n" +
           "      }\n" +
           "   ],\n" +
           "   \"properties\": [\n" +
           "      {\n" +
           "         \"name\": \"javafamily.cache.type\",\n" +
           "         \"type\": \"club.javafamily.autoconfigre.cache.enums.CacheType\",\n" +
           "         \"description\": \"Cache type. By default, auto-detected according to the environment.\",\n" +
           "         \"sourceType\": \"club.javafamily.autoconfigre.cache.properties.JavaFamilyCacheProperties\"\n" +
           "      },\n" +
           "      {\n" +
           "         \"name\": \"javafamily.cache.cache-null-values\",\n" +
           "         \"type\": \"java.lang.Boolean\",\n" +
           "         \"description\": \"Allow caching null values.\",\n" +
           "         \"sourceType\": \"club.javafamily.autoconfigre.cache.properties.JavaFamilyCacheProperties\",\n" +
           "         \"defaultValue\": true\n" +
           "      },\n" +
           "      {\n" +
           "         \"name\": \"javafamily.cache.key-prefix\",\n" +
           "         \"type\": \"java.lang.String\",\n" +
           "         \"description\": \"Key prefix.\",\n" +
           "         \"sourceType\": \"club.javafamily.autoconfigre.cache.properties.JavaFamilyCacheProperties\"\n" +
           "      },\n" +
           "      {\n" +
           "         \"name\": \"javafamily.cache.time-to-live\",\n" +
           "         \"type\": \"java.time.Duration\",\n" +
           "         \"description\": \"Entry expiration. By default the entries never expire.\",\n" +
           "         \"sourceType\": \"club.javafamily.autoconfigre.cache.properties.JavaFamilyCacheProperties\"\n" +
           "      },\n" +
           "      {\n" +
           "         \"name\": \"javafamily.cache.use-key-prefix\",\n" +
           "         \"type\": \"java.lang.Boolean\",\n" +
           "         \"description\": \"Whether to use the key prefix when writing to Redis.\",\n" +
           "         \"sourceType\": \"club.javafamily.autoconfigre.cache.properties.JavaFamilyCacheProperties\",\n" +
           "         \"defaultValue\": true\n" +
           "      },\n" +
           "      {\n" +
           "         \"name\": \"javafamily.cache.caffeine.spec\",\n" +
           "         \"type\": \"java.lang.String\",\n" +
           "         \"description\": \"The spec to use to create caches. See CaffeineSpec for more details on the spec format.\",\n" +
           "         \"sourceType\": \"club.javafamily.autoconfigre.cache.properties.JavaFamilyCacheProperties$Caffeine\"\n" +
           "      },\n" +
           "      {\n" +
           "         \"name\": \"javafamily.cache.caffeine.initSize\",\n" +
           "         \"type\": \"java.lang.Integer\",\n" +
           "         \"description\": \"初始化容量.\",\n" +
           "         \"sourceType\": \"club.javafamily.autoconfigre.cache.properties.JavaFamilyCacheProperties$Caffeine\",\n" +
           "         \"defaultValue\": 100\n" +
           "      },\n" +
           "      {\n" +
           "         \"name\": \"javafamily.cache.caffeine.maxSize\",\n" +
           "         \"type\": \"java.lang.Long\",\n" +
           "         \"description\": \"最大容量.\",\n" +
           "         \"sourceType\": \"club.javafamily.autoconfigre.cache.properties.JavaFamilyCacheProperties$Caffeine\",\n" +
           "         \"defaultValue\": 2000\n" +
           "      },\n" +
           "      {\n" +
           "         \"name\": \"javafamily.cache.caffeine.weakKeys\",\n" +
           "         \"type\": \"java.lang.Boolean\",\n" +
           "         \"description\": \"key 是否启用软引用.\",\n" +
           "         \"sourceType\": \"club.javafamily.autoconfigre.cache.properties.JavaFamilyCacheProperties$Caffeine\",\n" +
           "         \"defaultValue\": false\n" +
           "      },\n" +
           "      {\n" +
           "         \"name\": \"javafamily.cache.caffeine.weakValues\",\n" +
           "         \"type\": \"java.lang.Boolean\",\n" +
           "         \"description\": \"value 是否启用软引用.\",\n" +
           "         \"sourceType\": \"club.javafamily.autoconfigre.cache.properties.JavaFamilyCacheProperties$Caffeine\",\n" +
           "         \"defaultValue\": false\n" +
           "      },\n" +
           "      {\n" +
           "         \"name\": \"javafamily.cache.caffeine.softValues\",\n" +
           "         \"type\": \"java.lang.Boolean\",\n" +
           "         \"description\": \"value 是否启用软引用.\",\n" +
           "         \"sourceType\": \"club.javafamily.autoconfigre.cache.properties.JavaFamilyCacheProperties$Caffeine\",\n" +
           "         \"defaultValue\": false\n" +
           "      },\n" +
           "      {\n" +
           "         \"name\": \"javafamily.cache.caffeine.recordStats\",\n" +
           "         \"type\": \"java.lang.Boolean\",\n" +
           "         \"description\": \"是否启用状态记录.\",\n" +
           "         \"sourceType\": \"club.javafamily.autoconfigre.cache.properties.JavaFamilyCacheProperties$Caffeine\",\n" +
           "         \"defaultValue\": false\n" +
           "      }\n" +
           "   ],\n" +
           "   \"hints\": [\n" +
           "   ]\n" +
           "}\n";

}
