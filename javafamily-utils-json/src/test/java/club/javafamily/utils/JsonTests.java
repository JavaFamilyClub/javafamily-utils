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
      "         \"name\": \"javafamily.http\",\n" +
      "         \"type\": \"club.javafamily.autoconfigre.resttemplate.properties.HttpClientProperties\",\n" +
      "         \"sourceType\": \"club.javafamily.autoconfigre.resttemplate.properties.HttpClientProperties\"\n" +
      "      },\n" +
      "      {\n" +
      "         \"name\": \"javafamily.http.proxy\",\n" +
      "         \"type\": \"club.javafamily.autoconfigre.resttemplate.properties.ProxyConfig\",\n" +
      "         \"sourceType\": \"club.javafamily.autoconfigre.resttemplate.properties.HttpClientProperties\"\n" +
      "      }\n" +
      "   ],\n" +
      "   \"properties\": [\n" +
      "      {\n" +
      "         \"name\": \"javafamily.http.maxTotalConnect\",\n" +
      "         \"type\": \"java.lang.Integer\",\n" +
      "         \"description\": \"连接池的最大连接数，0代表不限；如果取0，需要考虑连接泄露导致系统崩溃的后果.\",\n" +
      "         \"sourceType\": \"club.javafamily.autoconfigre.resttemplate.properties.HttpClientProperties\",\n" +
      "         \"defaultValue\": 1000\n" +
      "      },\n" +
      "      {\n" +
      "         \"name\": \"javafamily.http.maxConnectPerRoute\",\n" +
      "         \"type\": \"java.lang.Integer\",\n" +
      "         \"description\": \"每个路由的最大连接数,如果只调用一个地址,可以将其设置为最大连接数.\",\n" +
      "         \"defaultValue\": 200\n" +
      "      },\n" +
      "      {\n" +
      "         \"name\": \"javafamily.http.connectTimeout\",\n" +
      "         \"type\": \"java.lang.Integer\",\n" +
      "         \"description\": \"指客户端和服务器建立连接的超时时间,ms , 最大约21秒,因为内部tcp在进行三次握手建立连接时,默认tcp超时时间是20秒.\",\n" +
      "         \"defaultValue\": 20000\n" +
      "      },\n" +
      "      {\n" +
      "         \"name\": \"javafamily.http.readTimeout\",\n" +
      "         \"type\": \"java.lang.Integer\",\n" +
      "         \"description\": \"指客户端从服务器读取数据包的间隔超时时间,不是总读取时间,也就是socket timeout, 单位ms.\",\n" +
      "         \"defaultValue\": 30000\n" +
      "      },\n" +
      "      {\n" +
      "         \"name\": \"javafamily.http.charset\",\n" +
      "         \"type\": \"java.lang.String\",\n" +
      "         \"description\": \"编码.\",\n" +
      "         \"defaultValue\": \"UTF-8\"\n" +
      "      },\n" +
      "      {\n" +
      "         \"name\": \"javafamily.http.retryTimes\",\n" +
      "         \"type\": \"java.lang.Integer\",\n" +
      "         \"description\": \"重试次数.\",\n" +
      "         \"defaultValue\": 2\n" +
      "      },\n" +
      "      {\n" +
      "         \"name\": \"javafamily.http.connectionRequestTimout\",\n" +
      "         \"type\": \"java.lang.Integer\",\n" +
      "         \"description\": \"从连接池获取连接的超时时间,不宜过长,单位ms.\",\n" +
      "         \"defaultValue\": 200\n" +
      "      },\n" +
      "      {\n" +
      "         \"name\": \"javafamily.http.keepAliveTargetHost\",\n" +
      "         \"type\": \"java.util.Map<java.lang.String,java.lang.Integer>\",\n" +
      "         \"description\": \"针对不同的网址,长连接保持的存活时间,单位s.\"\n" +
      "      },\n" +
      "      {\n" +
      "         \"name\": \"javafamily.http.keepAliveTime\",\n" +
      "         \"type\": \"java.lang.Integer\",\n" +
      "         \"description\": \"长连接保持时间 单位s,不宜过长.\",\n" +
      "         \"defaultValue\": 10\n" +
      "      },\n" +
      "      {\n" +
      "         \"name\": \"javafamily.http.textPlain2Json\",\n" +
      "         \"type\": \"java.lang.Boolean\",\n" +
      "         \"description\": \"添加 jackson 转换器, 将 text plain mapping 转换为 json.\",\n" +
      "         \"defaultValue\": true\n" +
      "      },\n" +
      "      {\n" +
      "         \"name\": \"javafamily.http.contentType\",\n" +
      "         \"type\": \"java.lang.String\",\n" +
      "         \"description\": \"Content Type 请求头.\",\n" +
      "         \"defaultValue\": \"application/json\"\n" +
      "      },\n" +
      "      {\n" +
      "         \"name\": \"javafamily.http.accept\",\n" +
      "         \"type\": \"java.lang.Integer\",\n" +
      "         \"description\": \"Accept 请求头.\",\n" +
      "         \"defaultValue\": \"application/json, text/plain, */*\"\n" +
      "      },\n" +
      "      {\n" +
      "         \"name\": \"javafamily.http.proxy.type\",\n" +
      "         \"type\": \"java.net.Proxy$Type\",\n" +
      "         \"description\": \"代理类型.\"\n" +
      "      },\n" +
      "      {\n" +
      "         \"name\": \"javafamily.http.proxy.schema\",\n" +
      "         \"type\": \"java.lang.String\",\n" +
      "         \"description\": \"代理 schema.\",\n" +
      "         \"defaultValue\": \"http\"\n" +
      "      },\n" +
      "      {\n" +
      "         \"name\": \"javafamily.http.proxy.host\",\n" +
      "         \"type\": \"java.lang.String\",\n" +
      "         \"description\": \"代理主机.\"\n" +
      "      },\n" +
      "      {\n" +
      "         \"name\": \"javafamily.http.proxy.port\",\n" +
      "         \"type\": \"java.lang.Integer\",\n" +
      "         \"description\": \"代理端口.\"\n" +
      "      },\n" +
      "      {\n" +
      "         \"name\": \"javafamily.http.proxy.userName\",\n" +
      "         \"type\": \"java.lang.String\",\n" +
      "         \"description\": \"代理认证用户名.\"\n" +
      "      },\n" +
      "      {\n" +
      "         \"name\": \"javafamily.http.proxy.password\",\n" +
      "         \"type\": \"java.lang.String\",\n" +
      "         \"description\": \"代理认证密码.\"\n" +
      "      }\n" +
      "   ],\n" +
      "   \"hints\": [\n" +
      "   ]\n" +
      "}\n";

}
