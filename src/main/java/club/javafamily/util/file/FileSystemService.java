package club.javafamily.util.file;

import club.javafamily.util.manager.SingletonManager;

import java.io.File;
import java.nio.file.*;

public final class FileSystemService {
   public static FileSystemService getInstance() {
      return SingletonManager.getInstance(FileSystemService.class);
   }

   /**
    * Retrieves the specified file from the File System.
    *
    * @param fileName Full path of the File to retrieve from the File System.
    *
    * @param more Additional files to get from the full path.
    *
    * @return the Path representation of the File.
    *
    * @throws InvalidPathException if the fileName contains an invalid character.
    */
   public Path getPath(String fileName, String... more) throws InvalidPathException {
      return Paths.get(fileName, more);
   }

   /**
    * Retrieves the specified file from the File System.
    *
    * @param fileName Full path of the File to retrieve from the File System.
    *
    * @return the Path representation of the File.
    *
    * @throws InvalidPathException if the fileName contains an invalid character.
    */
   public File getFile(String fileName) throws InvalidPathException {
      return getPath(fileName).toFile();
   }

   /**
    * Retrieves the specified file from the File System given the parent directory.
    *
    * @param parentFile The parent directory from which to retrieve the file from.
    *
    * @param fileName File name of the File to retrieve from the File System.
    *
    * @return the File representation of the File.
    *
    * @throws InvalidPathException if the fileName contains an invalid character.
    */
   public File getFile(File parentFile, String fileName) throws InvalidPathException {
      return getFile(parentFile.getAbsolutePath(), fileName);
   }

   /**
    * Retrieves the specified file from the File System given the parent directory.
    *
    * @param parent The parent directory from which to retrieve the file from.
    *
    * @param fileName File name of the File to retrieve from the File System.
    *
    * @return the File representation of the File.
    *
    * @throws InvalidPathException if the fileName contains an invalid character.
    */
   public File getFile(String parent, String fileName) throws InvalidPathException {
      return getPath(parent).resolve(fileName).toFile();
   }
}
