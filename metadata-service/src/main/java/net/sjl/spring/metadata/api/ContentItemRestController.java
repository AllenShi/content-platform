package net.sjl.spring.metadata.api;

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

import net.sjl.spring.metadata.model.*;
import java.util.Collection;

@RestController
public class ContentItemRestController {

  @Autowired
  ContentItemService service;

  @GetMapping("/items")
  public Collection<ContentItem> getContentItems() {
    return service.retrieveAllContentItems();
  }

  @GetMapping("/items/{itemId}")
  public ContentItem getContentItem(@PathVariable("itemId") int itemId) {
    return service.retrieveContentItem(itemId);
  } 

  @PostMapping("/items")
  public ResponseEntity createContentItem(@RequestBody ContentItem contentItem) {
    service.addContentItem(contentItem);
    return new ResponseEntity(contentItem, HttpStatus.OK);
  } 

  @DeleteMapping("/items/{itemId}")
  public ResponseEntity deleteContentItem(@PathVariable("itemId") int itemId) {
    service.deleteContentItem(itemId);
    return new ResponseEntity(itemId, HttpStatus.OK);
  }

  @PutMapping("/items/{itemId}")
  public ResponseEntity changeContentItem(@PathVariable("itemId") int itemId, @RequestBody ContentItem contentItem) {
    service.changeContentItem(contentItem);
    return new ResponseEntity(contentItem, HttpStatus.OK);
  }
  
}
