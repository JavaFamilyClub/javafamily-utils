package org.javafamily.util;

import org.javafamily.test.MockUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JsonPathUtilsTests {

   @Test
   public void testParsePath() {
      String jsonPath = "$.reason";
      String reason = JsonPathUtils.parsePath(MockUtils.WEATHER_JSON, jsonPath);

      Assertions.assertTrue("Succes".equals(reason), "JSON Parse Error: " + jsonPath);
   }

   private static final Logger LOGGER = LoggerFactory.getLogger(JsonPathUtilsTests.class);
}
