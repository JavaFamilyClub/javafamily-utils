package club.javafamily.util.string.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.Serializable;

public final class JsonUtils {

   public static String objectToJson(Serializable obj)
      throws JsonProcessingException
   {
      if(obj == null) {
         return null;
      }

      ObjectMapper mapper = new ObjectMapper();

      return mapper.writeValueAsString(obj);
   }

   public static <T> T jsonToObject(String json, Class<T> clazz)
      throws JsonProcessingException
   {
      if(json == null) { // skip empty string, then return empty object
         return null;
      }

      ObjectMapper mapper = new ObjectMapper();

      return mapper.readValue(json, clazz);
   }

   private JsonUtils() {
   }
}
