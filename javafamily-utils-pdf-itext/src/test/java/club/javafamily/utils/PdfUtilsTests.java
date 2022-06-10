package club.javafamily.utils;

import club.javafamily.utils.pdf.PdfUtils;
import org.junit.jupiter.api.Test;

/**
 * @author Jack Li
 * @date 2022/6/10 下午6:56
 * @description
 */
public class PdfUtilsTests {

   @Test
   void testCheckPdfValid() {
      String path = "";

      final boolean pdfValid = PdfUtils.checkPdfValid(path);

      System.out.println(pdfValid);
   }

}
