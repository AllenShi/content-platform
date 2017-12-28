package net.sjl.spring.content.model;

import java.util.*;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.commons.io.IOUtils;
import java.io.InputStream;
import java.io.OutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class ContentService {
  
  private static final Logger logger = LoggerFactory.getLogger(ContentService.class);
  
  @Autowired
  private ContentRepository repository;

  public Content retrieveContent(String id) {
    logger.info("Enter into retrieveContent with content id:", id);  
    return repository.loadById(id);
  }

  public Collection<Content> retrieveAllContent() {
    logger.info("Enter into retrieveAllContent");  
    return repository.loadAll();
  }
  public Collection<Content> retrieveAllContentByItemId(String itemId) {
    logger.info("Enter into retrieveAllContentByItemId with itemid: ", itemId);  
    return repository.loadAllContentByItemId(itemId);
  }

  public Content addNewContent(Content content, InputStream input) {
    logger.info("Enter into addNewContent");  
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
    logger.info("Enter into checkInContent");  
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
    logger.info("Enter into deleteContent with contentId: ", contentId);  
    Content content = new Content();
    content.setId(contentId);
    repository.remove(content);
  }

  public void deleteByItemId(String itemId) {
    logger.info("Enter into deleteByItemId with itemid: ", itemId);  
    repository.removeAllByItemId(itemId);
  }
  
}
