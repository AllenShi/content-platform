package net.sjl.spring.metadata.model;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
public class ContentItem {
  private int id;
  private int vsId;
  private String name;
  private int epochId;
  private int contentId;
  private int parentId;


  public ContentItem(int id, String name, int vsId) {
    this.id = id;
    this.name = name;
    this.vsId = vsId;
    this.epochId = 0;
  }

  public void upgradeVersion() {
    vsId++;
  }
  
}
