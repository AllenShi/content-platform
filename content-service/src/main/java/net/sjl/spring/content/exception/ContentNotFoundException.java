package net.sjl.spring.content.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(org.springframework.http.HttpStatus.NOT_FOUND)
public class ContentNotFoundException extends RuntimeException {

  private static final long serialVersionUID = 1L;
  
  public ContentNotFoundException(String contentId) {
    super("The content " + contentId + "can not be found");
  }

}
