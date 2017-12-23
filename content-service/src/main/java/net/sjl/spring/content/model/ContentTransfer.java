package net.sjl.spring.content.model;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;


@Getter
@Setter
public class ContentTransfer {
  private String id;
  private String itemId;
  private String uri;
  private MultipartFile file;
   
}
