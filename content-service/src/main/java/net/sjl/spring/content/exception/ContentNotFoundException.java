package net.sjl.spring.content.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import net.sjl.spring.common.constant.CategoryCode;
import net.sjl.spring.common.exception.BaseRestException;
import net.sjl.spring.content.constant.ErrorCode;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ContentNotFoundException extends BaseRestException {

  private static final long serialVersionUID = 1L;
  
  public ContentNotFoundException(String contentId) {
    super(HttpStatus.NOT_FOUND, CategoryCode.CONTENT_SERVICE, ErrorCode.CONTENT_NOT_FOUND.getValue(), "The content " + contentId + "can not be found");
  }

}
