package net.sjl.spring.content.model;

import java.util.*;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class ContentService {

  @Autowired
  private ContentRepository repository;

  public Content retrieveContent(int id) {
    return repository.loadById(id);
  }

  public Collection<Content> retrieveAllContent() {
    return repository.loadAll();
  }

  public void addContent(Content content) {
    repository.save(content);
  }

  public void changeContent(Content content) {
    repository.save(content);
  }

  public void deleteContent(int contentId) {
    Content content = new Content();
    content.setId(contentId);
    repository.remove(content);
  }
  
}
