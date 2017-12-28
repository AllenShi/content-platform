package net.sjl.spring.metadata.model;

import java.util.Collection;
import java.util.ArrayList;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired; 

import net.sjl.spring.metadata.proxy.ContentService;

@Service
public class ContentItemService {

  @Autowired
  private ContentItemRepository repository;

  @Autowired
  private ContentService contentService;

  public ContentItem retrieveContentItem(String id) {
    ContentItem item =  repository.findById(id);
    Collection<Content> contentElements = contentService.getContentObjectsByItemId(id);
    item.setContentElements(new ArrayList<Content>(contentElements));
    return item;
  }

  public Collection<ContentItem> retrieveAllContentItems() {
    Collection<ContentItem> items = repository.queryAll();
    if(items != null) {
      items.stream().forEach(item -> {
        Collection<Content> contentElements = contentService.getContentObjectsByItemId(item.getId());
        item.setContentElements(new ArrayList<Content>(contentElements)); 
      });
    }
    return items;
  }

  public ContentItem addContentItem(ContentItem item) {
    return repository.create(item);
  } 

  public ContentItem changeContentItem(ContentItem item) {
    ContentItem uitem = repository.update(item);
    return uitem;
  }

  public void deleteContentItem(String itemId) {
    ContentItem item = retrieveContentItem(itemId);
    contentService.deleteContentOjectsByItemId(itemId);
    repository.remove(item);
  }

}
