package net.sjl.spring.content.model;

import java.util.*;

public interface ContentRepository {
  public Content loadById(String id);
  public Collection<Content> loadAll();
  public Collection<Content> loadAllContentByItemId(String itemId);
  public Content createNew(Content content);
  public Content update(Content content);
  public Set<Content> createNewAll(Set<Content> contentSet);
  public Set<Content> updateAll(Set<Content> contentSet);
  public void remove(Content content);
  public void removeAll(Set<Content> contentSet);
}
