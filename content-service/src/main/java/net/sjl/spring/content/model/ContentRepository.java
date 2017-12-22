package net.sjl.spring.content.model;

import java.util.*;

public interface ContentRepository {
  public Content loadById(int id);
  public Collection<Content> loadAll();
  public void save(Content content);
  public void saveAll(Set<Content> contentSet);
  public void remove(Content content);
  public void removeAll(Set<Content> contentSet);
}
