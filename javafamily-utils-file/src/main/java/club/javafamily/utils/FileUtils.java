package club.javafamily.utils;

import org.apache.commons.compress.compressors.bzip2.BZip2CompressorInputStream;
import org.springframework.lang.Nullable;
import org.springframework.util.FastByteArrayOutputStream;
import org.springframework.util.StreamUtils;

import java.io.*;
import java.net.*;


/**
 * @author Jack Li
 * @date 2021/7/28 11:02 上午
 * @description 文件相关工具类
 */
public class FileUtils {

   // 5 MINUTE
   private static final int CONNECTION_TIMEOUT = 1000 * 60 * 5;

   /**
    * @Description     删除文件
    * @param dirFile   文件路径
    */
   public static Boolean deleteFiles(File dirFile) {
      if (!dirFile.exists()) {
         return false;
      }
      if (dirFile.isFile()) {
         return dirFile.delete();
      } else {
         for (File file : dirFile.listFiles()) {
            deleteFiles(file);
         }
      }
      return dirFile.delete();
   }

   /**
    * @Description     文件上传方法
    * @param file      文件byte[]
    * @param filePath  文件上传路径
    * @param fileName  文件保存路径
    * @throws IOException
    * @throws Exception
    * void
    */
   public static File uploadFiles(byte[] file, String filePath, String fileName) throws IOException{
      File targetFile = new File(filePath);
      if(!targetFile.exists()){
         targetFile.mkdirs();
      }
      File data = new File(filePath + fileName);
      FileOutputStream out = new FileOutputStream(data);
      out.write(file);
      out.flush();
      out.close();

      return data;
   }

   /**
    * @Description     创建文件
    * @param content   字符串内容
    * @param rootPath  文件根目录
    * @param fileName  文件名称
    * @return          文件创建状态
    */
   public static boolean createFileByStr(String content,String rootPath,String fileName)
   {
      boolean flag=false;
      FileWriter writer;
      try{
         writer=new FileWriter(rootPath+"/"+fileName);
         writer.write(content);
         writer.flush();
         writer.close();
         flag=true;
      }catch (IOException e)
      {
         e.printStackTrace();
      }
      return flag;
   }

   /**
    * @Description 读取文件
    * @param file  文件路径
    * @return
    */
   public static byte[] readBZip2File(String file) {
      try {
         return readBZip2File(new FileInputStream(file));
      } catch (FileNotFoundException e) {
         e.printStackTrace();
      }

      return null;
   }

   /**
    * @Description 读取文件
    * @return
    */
   public static byte[] readBZip2File(InputStream fin) {
      ByteArrayOutputStream out = new ByteArrayOutputStream(1024);

      try {
         BufferedInputStream in = new BufferedInputStream(fin);
         BZip2CompressorInputStream bzIn = new BZip2CompressorInputStream(in);

         byte[] buffer = new byte[1024];
         int n = 0;
         while (-1 != (n = bzIn.read(buffer))) {
            out.write(buffer, 0, n);
         }
         out.close();
         bzIn.close();
      } catch (IOException e) {
         e.printStackTrace();
      }

      return out.toByteArray();
   }

   /**
    * 下载文件到流
    * @param fileUrl 文件下载 url
    * @return InputStream
    * @throws Exception
    */
   public static InputStream downloadFileForStream(String fileUrl) throws Exception {
      final URI uri = URI.create(fileUrl);
      URL url = uri.toURL();
      URLConnection con = url.openConnection();
      con.setConnectTimeout(CONNECTION_TIMEOUT);

      return con.getInputStream();
   }

   public static byte[] downloadFile(String fileUrl) throws Exception {
      byte[] data;

      try(final InputStream in = downloadFileForStream(fileUrl);
          FastByteArrayOutputStream out = convertStream(in))
      {
         data = out.toByteArray();
      }

      return data;
   }

   public static byte[] streamToByte(InputStream in) throws Exception {
      return convertStream(in).toByteArray();
   }

   public static FastByteArrayOutputStream convertStream(InputStream in)
      throws Exception
   {
      return convertStream(in, 200);
   }

   public static FastByteArrayOutputStream convertStream(InputStream in,
                                                         int mb)
      throws Exception
   {
      FastByteArrayOutputStream out
         = new FastByteArrayOutputStream(mb * 1024 * 1024);
      StreamUtils.copy(in, out);

      return out;
   }

   /**
    * 将本地文件读取到 byte 数组
    * @param file 文件
    * @return null if file is not exist.
    * @throws Exception io op error.
    */
   @Nullable
   public static byte[] readFile2ByteArray(File file) throws Exception {
      if(file == null || !file.exists() || file.isDirectory()) {
         return null;
      }

      try(final FileInputStream in = new FileInputStream(file);
          FastByteArrayOutputStream out
             = new FastByteArrayOutputStream(in.available()))
      {
         StreamUtils.copy(in, out);

         return out.toByteArray();
      }
   }
}
