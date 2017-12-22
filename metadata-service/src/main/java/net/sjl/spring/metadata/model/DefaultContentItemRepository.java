package net.sjl.spring.metadata.model;

import java.util.Map;
import java.util.Set;
import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;
import java.util.stream.Collectors;

public class DefaultContentItemRepository implements ContentItemRepository {
  private static Map<Integer, ContentItem> contentItemStore = new ConcurrentHashMap<>();
  
  public ContentItem findById(int id) {
    return contentItemStore.get(id);
  }

  public Collection<ContentItem> queryAll() {
    return contentItemStore.entrySet().stream().map(map -> map.getValue()).collect(Collectors.toList()); 
  }

  public void save(ContentItem contentItem) {
    if(contentItem == null) return;
    contentItemStore.put(contentItem.getId(), contentItem);
  }

  public void saveAll(Set<ContentItem> contentItems) {
    if(contentItems == null) return;
    contentItems.stream().forEach(this::save);
  }
  
  public void remove(ContentItem contentItem) {
    if(contentItem == null) return;
    contentItemStore.remove(contentItem.getId());
  }

  public void removeAll(Set<ContentItem> contentItems) {
    if(contentItems == null) return;
    contentItems.stream().forEach(this::remove);
  }

}
