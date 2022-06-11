package com.example.demopdfitextutils;

import club.javafamily.utils.pdf.PdfUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

/**
 * @author Jack Li
 * @date 2022/6/10 下午6:56
 * @description
 */
public class PdfUtilsTests {

   @Test
   void testCheckPdfValid() throws FileNotFoundException {
      String path = "src/test/resources/invalid.pdf";

      final boolean pdfValid = PdfUtils.checkPdfValid(path);

      Assertions.assertFalse(pdfValid);
   }

   @Test
   void testCheckPdfValid2() throws FileNotFoundException {
      String path = "src/test/resources/valid.pdf";

      final boolean pdfValid = PdfUtils.checkPdfValid(path);

      Assertions.assertTrue(pdfValid);
   }

}
