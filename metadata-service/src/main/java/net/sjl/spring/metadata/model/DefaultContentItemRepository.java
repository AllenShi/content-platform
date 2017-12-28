package net.sjl.spring.metadata.model;

import java.util.Map;
import java.util.Set;
import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;
import java.util.stream.Collectors;
import java.util.UUID;

import org.springframework.stereotype.Repository;
import org.apache.commons.lang3.Validate;

@Repository
public class DefaultContentItemRepository implements ContentItemRepository {
  private static Map<String, ContentItem> contentItemStore = new ConcurrentHashMap<>();
  
  public ContentItem findById(String id) {
    return contentItemStore.get(id);
  }

  public Collection<ContentItem> queryAll() {
    return contentItemStore.entrySet().stream().map(map -> map.getValue()).collect(Collectors.toList()); 
  }

  public ContentItem create(ContentItem contentItem) {
    Validate.notNull(contentItem, "The contentItem must not be null");

    String id = contentItem.getId();
    String vsId = contentItem.getVsId();
    if(id == null || id.isEmpty()) {
      id = UUID.randomUUID().toString();
      contentItem.setId(id);
    }
   
    if(vsId == null || vsId.isEmpty()) {
      vsId = UUID.randomUUID().toString();
      contentItem.setVsId(vsId);
    }

    contentItemStore.put(id, contentItem);
    return contentItem;
  }

  public ContentItem update(ContentItem contentItem) {
    Validate.notNull(contentItem, "The contentItem must not be null");
    Validate.notEmpty(contentItem.getId(), "The contentItem id must not be empty");

    contentItemStore.put(contentItem.getId(), contentItem);
    return contentItem;
  }

  public ContentItem checkIn(ContentItem contentItem, Content content) {
    Validate.notNull(contentItem, "The contentItem must not be null");
    Validate.notNull(content, "The content must not be null");
    Validate.notEmpty(contentItem.getId(), "The contentItem id must not be empty");

    ContentItem c = new ContentItem(contentItem);
    String id = UUID.randomUUID().toString();
    String vsId = c.getVsId();
    c.setId(id);
   
    if(vsId == null || vsId.isEmpty()) {
      vsId = UUID.randomUUID().toString();
      c.setVsId(vsId);
      contentItem.setVsId(vsId);
    }
    contentItemStore.put(id, c);
    return c;
  }

  public Set<ContentItem> createAll(Set<ContentItem> contentItems) {
    Validate.notNull(contentItems, "The contentItems must not be null");
    return contentItems.stream().map(c -> create(c)).collect(Collectors.toSet());
  }

  public Set<ContentItem> updateAll(Set<ContentItem> contentItems) {
    Validate.notNull(contentItems, "The contentItems must not be null");
    return contentItems.stream().map(c -> update(c)).collect(Collectors.toSet());
  }
  
  public void remove(ContentItem contentItem) {
    if(contentItem == null) return;
    contentItemStore.remove(contentItem.getId());
  }

  public void removeAll(Set<ContentItem> contentItems) {
    Validate.notNull(contentItems, "The contentItems must not be null");
    contentItems.stream().forEach(this::remove);
  }

}
