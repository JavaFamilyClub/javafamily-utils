package club.javafamily.utils.pdf;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import lombok.extern.slf4j.Slf4j;

import java.io.*;

/**
 * @author Jack Li
 * @date 2022/6/10 上午11:42
 * @description
 */
@Slf4j
public class PdfUtils {

   /**
    * Check pdf file stream is valid
    * @param path pdf file path
    * @return <code>true</code> means valid
    */
   public static boolean checkPdfValid(String path) {
      try {
         return PdfUtils.checkPdfValid(new FileInputStream(path));
      } catch (FileNotFoundException e) {
         log.debug("Pdf file is not exist: {}", path);
      }

      return false;
   }

   /**
    * Check pdf file stream is valid
    * @param in pdf stream
    * @return <code>true</code> means valid
    */
   public static boolean checkPdfValid(InputStream in) {
      boolean valid = false;

      int pageCount = 0;

      PdfDocument pdfDocument = null;

      try {
         PdfReader pdfReader = new PdfReader(in);

         pdfDocument = new PdfDocument(pdfReader);
         pageCount = pdfDocument.getNumberOfPages();

         if(pageCount != 0) {
            valid = true;
         }

//         final PdfPage lastPage = pdfDocument.getFirstPage();
//         String textFromPdfFilePageOne
//            = PdfTextExtractor.getTextFromPage(lastPage);

         pdfDocument.close();
      } catch (IOException e) {
         log.debug("PDF check failed!", e);

         if(pdfDocument != null) {
            pdfDocument.close();
         }
      }

      return valid;
   }

}
