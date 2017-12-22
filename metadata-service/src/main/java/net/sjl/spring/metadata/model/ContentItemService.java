package net.sjl.spring.metadata.model;

import java.util.Collection;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired; 

@Service
public class ContentItemService {

  @Autowired
  private ContentItemRepository repository;

  public ContentItem retrieveContentItem(int id) {
    return repository.findById(id);
  }

  public Collection<ContentItem> retrieveAllContentItems() {
    return repository.queryAll();
  }

  public void addContentItem(ContentItem item) {
    repository.save(item);
  } 

  public void changeContentItem(ContentItem item) {
    item.upgradeVersion();
    repository.save(item);
  }

  public void deleteContentItem(int itemId) {
    ContentItem item = new ContentItem();
    item.setId(itemId);
    repository.remove(item);
  }

}
