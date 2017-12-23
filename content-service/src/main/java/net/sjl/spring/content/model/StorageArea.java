package net.sjl.spring.content.model;

import java.nio.file.Path;
import java.io.InputStream;
import java.io.OutputStream;

public interface StorageArea {
  public long getTotalSpace();
  public long getUsableSpace();
  public Path getRootDirectory();
  public OutputStream getOutputStream(String contentId, String filePath);
  public InputStream getInputStream(String contentId, String filePath);
}
