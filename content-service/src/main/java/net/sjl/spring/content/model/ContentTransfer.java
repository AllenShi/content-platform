package net.sjl.spring.content.model;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.annotations.ApiParam;


@Getter
@Setter
public class ContentTransfer {

  private String id;
  private String itemId;
  private String uri;
  private MultipartFile[] files;
   
}
