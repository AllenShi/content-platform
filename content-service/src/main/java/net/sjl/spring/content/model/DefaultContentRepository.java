package net.sjl.spring.content.model;

import java.util.*;
import java.util.stream.*;
import java.util.concurrent.*;

import java.time.ZonedDateTime;
import java.time.ZoneOffset;
import java.net.URI;

import org.springframework.stereotype.Repository;

@Repository
public class DefaultContentRepository implements ContentRepository {
  private final static Map<String, Content> contentStore = new ConcurrentHashMap<>();

  public Content loadById(String id) {
    return contentStore.get(id);
  }

  public Collection<Content> loadAll() {
    return contentStore.entrySet().stream().map(map -> map.getValue()).collect(Collectors.toList());
  }
 
  public Collection<Content> loadAllContentByItemId(String itemId) {
    return contentStore.entrySet().stream().map(map -> map.getValue()).filter(e -> itemId.equals(e.getItemId())).collect(Collectors.toList());
  }

  public Content createNew(Content content) {
    if(content == null) return null;
    String id = content.getId();
    if(id == null || id.isEmpty()) {
      id = UUID.randomUUID().toString();
      content.setId(id);
    }

    ZonedDateTime curDT = ZonedDateTime.now(ZoneOffset.UTC);
    content.setCreationDate(curDT);
    content.setLastModifiedDate(curDT);

    contentStore.put(id, content);
    return content;
  }

  public Content update(Content content) {
    if(content == null) return null;
    Content c = new Content(content);
    c.setId(UUID.randomUUID().toString());
    ZonedDateTime curDT = ZonedDateTime.now(ZoneOffset.UTC);
    c.setCreationDate(curDT);
    c.setLastModifiedDate(curDT);
    contentStore.put(c.getId(), c);
    return c;
  }

  public Set<Content> createNewAll(Set<Content> contentSet) {
    if(contentSet == null) return new HashSet<Content>();
    return contentSet.stream().map(this::createNew).collect(Collectors.toSet());
  }

  public Set<Content> updateAll(Set<Content> contentSet) {
    if(contentSet == null) return new HashSet<Content>();
    return contentSet.stream().map(this::update).collect(Collectors.toSet());
  }

  public void remove(Content content) {
    if(content == null) return;
    contentStore.remove(content.getId());
  }

  public void removeAll(Set<Content> contentSet) {
    if(contentSet == null) return;
    contentSet.stream().forEach(this::remove);
  }

  public void removeAllByItemId(String itemId) {
    contentStore.entrySet().removeIf(entry -> entry.getValue().getItemId() == itemId); 
  }
}
