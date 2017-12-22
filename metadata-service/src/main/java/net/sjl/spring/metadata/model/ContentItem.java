package net.sjl.spring.metadata.model;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.util.*;
import java.util.concurrent.*;

@Getter
@Setter
@NoArgsConstructor
public class ContentItem {
  private String id;
  private String vsId;
  private String name;
  private int epochId = 0;
  private List<Content> contentElements;
  private String parentId = null;

  public ContentItem(String name) {
    this.name = name;
  }

  public ContentItem(String id, String name, String vsId) {
    this.id = id;
    this.name = name;
    this.vsId = vsId;
  }

  public ContentItem(final ContentItem o) {
    ContentItem c = this;
    c.setId(o.getId());
    c.setName(o.getName());
    c.setVsId(o.getVsId());
    c.setEpochId(o.getEpochId());
    List<Content> sl = o.getContentElements();
    if(sl != null) {
      List<Content> cl = new CopyOnWriteArrayList(sl);
      c.setContentElements(cl);
    }
    c.setParentId(o.getParentId());
  }

  public synchronized void addContent(Content content) {
    if(content == null) return; 

    if(contentElements == null) {
      contentElements = new ArrayList<>();
    }
    contentElements.add(content);
  }
  
}
