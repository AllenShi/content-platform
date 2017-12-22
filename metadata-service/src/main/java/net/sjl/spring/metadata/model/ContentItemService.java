package net.sjl.spring.metadata.model;

import java.util.Collection;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired; 

@Service
public class ContentItemService {

  @Autowired
  private ContentItemRepository repository;

  public ContentItem retrieveContentItem(String id) {
    return repository.findById(id);
  }

  public Collection<ContentItem> retrieveAllContentItems() {
    return repository.queryAll();
  }

  public ContentItem addContentItem(ContentItem item) {
    return repository.create(item);
  } 

  public ContentItem changeContentItem(ContentItem item) {
    return repository.update(item);
  }

  public void deleteContentItem(String itemId) {
    ContentItem item = new ContentItem();
    item.setId(itemId);
    repository.remove(item);
  }

}
