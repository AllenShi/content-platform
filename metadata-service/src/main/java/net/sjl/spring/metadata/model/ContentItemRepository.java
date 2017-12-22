package net.sjl.spring.metadata.model;

import java.util.*;

public interface ContentItemRepository {
  public ContentItem findById(int id);
  public Collection<ContentItem> queryAll();
  public void save(ContentItem contentItem);
  public void saveAll(Set<ContentItem> contentItems);
  public void remove(ContentItem contentItem);
  public void removeAll(Set<ContentItem> contentItems);
}
