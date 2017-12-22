package net.sjl.spring.content.model;

import java.util.*;
import java.util.stream.*;
import java.util.concurrent.*;

public class DefaultContentRepository implements ContentRepository {
  private final static Map<Integer, Content> contentStore = new ConcurrentHashMap<>();

  public Content loadById(int id) {
    return contentStore.get(id);
  }

  public Collection<Content> loadAll() {
    return contentStore.entrySet().stream().map(map -> map.getValue()).collect(Collectors.toList());
  }

  public void save(Content content) {
    if(content == null) return;
    contentStore.put(content.getId(), content);
  }

  public void saveAll(Set<Content> contentSet) {
    if(contentSet == null) return;
    contentSet.stream().forEach(c -> contentStore.put(c.getId(), c));
  }

  public void remove(Content content) {
    if(content == null) return;
    contentStore.remove(content.getId());
  }

  public void removeAll(Set<Content> contentSet) {
    if(contentSet == null) return;
    contentSet.stream().forEach(this::remove);
  }
}
