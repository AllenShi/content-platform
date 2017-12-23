package net.sjl.spring.content.model;

import java.util.*;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.commons.io.IOUtils;
import java.io.InputStream;
import java.io.OutputStream;

@Service
public class ContentService {

  @Autowired
  private ContentRepository repository;

  public Content retrieveContent(String id) {
    return repository.loadById(id);
  }

  public Collection<Content> retrieveAllContent() {
    return repository.loadAll();
  }
  public Collection<Content> retrieveAllContentByItemId(String itemId) {
    return repository.loadAllContentByItemId(itemId);
  }

  public Content addNewContent(Content content, InputStream input) {
    Content newContent = repository.createNew(content);
    StorageArea storage = new FileSystemStorageArea();
    newContent.setStorage(storage);
    try (OutputStream output = newContent.getOutputStream()) {
      IOUtils.copy(input, output);
      return newContent;
    } catch (Exception e) {
      throw new IllegalStateException("Copy exception", e);
    }
  }

  public Content checkInContent(Content content, InputStream input) {
    Content newContent = repository.update(content);
    StorageArea storage = new FileSystemStorageArea();
    newContent.setStorage(storage);
    try (OutputStream output = newContent.getOutputStream()) {
      IOUtils.copy(input, output);
      return newContent;
    } catch (Exception e) {
      throw new IllegalStateException("Copy exception", e);
    }
  }

  public void deleteContent(String contentId) {
    Content content = new Content();
    content.setId(contentId);
    repository.remove(content);
  }
  
}
