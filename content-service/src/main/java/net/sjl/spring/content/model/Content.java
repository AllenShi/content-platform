package net.sjl.spring.content.model;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
public class Content {
  private int id;
  private String url;
  private int size;
  private byte[] content;

  public Content(int id, String url, int size) {
    this(id, url, size, null);
  }

  public Content(int id, String url, int size, byte[] content) {
    this.id = id;
    this.url = url; 
    this.size = size; 
    this.content = content;
  }
  
}
