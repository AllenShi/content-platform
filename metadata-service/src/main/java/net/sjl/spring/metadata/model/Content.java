package net.sjl.spring.metadata.model;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
public class Content {
  private String id;
  private String name; 
  private String description;

  public Content(String id, String name, String description) {
    this.id = id;
    this.name = name;
    this.description = description;
  }
}
