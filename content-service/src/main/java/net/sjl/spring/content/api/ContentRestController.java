package net.sjl.spring.content.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Collection;
import java.util.Set;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import net.sjl.spring.content.model.Content;
import net.sjl.spring.content.model.ContentService;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class ContentRestController {
  
  @Autowired
  ContentService service;
  
  @GetMapping("/contents")
  public Collection<Content> getContents() {
    return service.retrieveAllContent();
  }

  @GetMapping("/contents/{contentId}")
  public Content findContentById(@PathVariable("contentId") int contentId) {
    return service.retrieveContent(contentId); 
  }

  @PostMapping("/contents")
  public ResponseEntity createContent(@RequestBody Content content) {
    service.addContent(content);
    return new ResponseEntity(content, HttpStatus.OK);
  }

  @DeleteMapping("/contents/{contentId}")
  public ResponseEntity deleteContent(@PathVariable("contentId") int contentId) {
    service.deleteContent(contentId);
    return new ResponseEntity(contentId, HttpStatus.OK);
  }

  @PutMapping("/contents/{contentId}")
  public ResponseEntity changeContent(@PathVariable("contentId") int  contentId, @RequestBody Content content) {
    service.changeContent(content);
    return new ResponseEntity(content, HttpStatus.OK);
  }

}
