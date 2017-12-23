package net.sjl.spring.content.model;

import java.nio.file.Path;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.FileSystem;
import java.nio.file.FileStore;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.IllegalArgumentException;
import java.lang.IllegalStateException;
import org.apache.commons.lang3.Validate;

public class FileSystemStorageArea implements StorageArea {
  private final String baseDir;
  private final Path basePath;
  private final FileStore store;

  public FileSystemStorageArea() {
    this(System.getProperty("user.home"));
  }

  public FileSystemStorageArea(String baseDir) {
    try {
      this.baseDir = baseDir;
      this.basePath = FileSystems.getDefault().getPath(baseDir);
      this.store = Files.getFileStore(basePath);
    } catch (Exception e) {
      throw new IllegalArgumentException("Invalid arguments for baseDir", e);
    }
  }

  public long getTotalSpace() {
    try {
      return this.store.getTotalSpace();
    }catch(Exception e) {}
    return -1;
  }

  public long getUsableSpace() {
    try {
      return this.store.getUsableSpace();
    }catch(Exception e) {}
    return -1;
  }

  public Path getRootDirectory() {
    return this.basePath;
  }

  public OutputStream getOutputStream(String contentId, String filePath) {
    Validate.notEmpty(filePath, "The filePath must not be empty");
    Validate.notEmpty(contentId, "The contentId must not be empty");
   
    String relativePath = normalizePath(contentId, filePath);
   
    Path absPath = basePath.resolve(relativePath);
    System.out.println("absPath is " + absPath);
    if(Files.exists(absPath))
      throw new IllegalArgumentException("File path has already existed");

    try {
      Files.createDirectories(absPath.getParent());
      return Files.newOutputStream(absPath);
    } catch (Exception e) {
      throw new IllegalStateException(e);
    }

  }

  public InputStream getInputStream(String contentId, String filePath) {
    Validate.notEmpty(filePath, "The filePath must not be empty");
    Validate.notEmpty(contentId, "The contentId must not be empty");
   
    String relativePath = normalizePath(contentId, filePath);

    Path absPath = basePath.resolve(relativePath);
    System.out.println("absPath is " + absPath);
    if(!Files.exists(absPath))
      throw new IllegalArgumentException("File path doesn't exist");

    try {
      return Files.newInputStream(absPath);
    } catch (Exception e) {
      throw new IllegalStateException(e);
    }
  }

  private String normalizePath(String contentId, String filePath) {
    String relativePath = filePath;
    if(filePath.startsWith("/")) {
      relativePath = filePath.substring(1, filePath.length() - 1);
    }

    if(relativePath.endsWith("/")) {
      relativePath = relativePath.substring(0, relativePath.length() - 1);
    }

    String fileName = relativePath.substring(relativePath.lastIndexOf("/") + 1, relativePath.length() - relativePath.lastIndexOf("/") - 1);
    relativePath = relativePath.substring(0, relativePath.lastIndexOf("/")) + "/" + contentId + "/" + fileName;
    return relativePath;
  }
}
