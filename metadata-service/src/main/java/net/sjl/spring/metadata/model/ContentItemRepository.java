package net.sjl.spring.metadata.model;

import java.util.*;

public interface ContentItemRepository {
  public ContentItem findById(String id);
  public Collection<ContentItem> queryAll();
  public ContentItem create(ContentItem contentItem);
  public ContentItem update(ContentItem contentItem);
  public ContentItem checkIn(ContentItem contentItem, Content content);
  public Set<ContentItem> createAll(Set<ContentItem> contentItems);
  public Set<ContentItem> updateAll(Set<ContentItem> contentItems);
  public void remove(ContentItem contentItem);
  public void removeAll(Set<ContentItem> contentItems);
}
