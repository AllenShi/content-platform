package net.sjl.spring.metadata.proxy;

import java.util.*;
import java.io.ByteArrayInputStream;

import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import net.sjl.spring.metadata.model.Content;

@FeignClient(
  name = "content-service",
  url = "http://localhost:2223", 
  fallback = ContentService.ContentServiceFallback.class
)
public interface ContentService {

  @GetMapping("/contents")
  public Collection<Content> getContentObjects();

  @GetMapping("/contents/item/{itemId}")
  public Collection<Content> getContentObjectsByItemId(@PathVariable("itemId") String itemId);

  @GetMapping("/contents/{contentId}")
  public Content getContentObjectById(@PathVariable("contentId") String contentId);

  @GetMapping("/contents/{contentId}/content")
  public Resource getContentOfObjectById(@PathVariable("contentId") String contentId);

  @PostMapping(value = "/contents", produces = "application/json")
  public ResponseEntity createContent(@RequestBody Content content);

  @DeleteMapping("/contents/{contentId}")
  public ResponseEntity deleteContent(@PathVariable("contentId") String contentId);

  @DeleteMapping("/contents/item/{itemId}")
  public ResponseEntity deleteContentOjectsByItemId(@PathVariable("itemId") String itemId);

  @PutMapping(value = "/contents/{contentId}", consumes = "application/json")
  public ResponseEntity checkInContent(@PathVariable("contentId") String  contentId, @RequestBody Content content);

  @Component
  public static class ContentServiceFallback implements ContentService {

    private static List<Content> contentList = new ArrayList<>();

    static {
      contentList.add(new Content("blue", "file:///home/allen/Documents/dummy1.txt", "b"));
      contentList.add(new Content("yello", "file:///home/allen/Documents/dummy2.txt", "y"));
      contentList.add(new Content("red", "file:///home/allen/Documents/dummy3.txt", "r"));
    }

    public Collection<Content> getContentObjects() {
      return contentList;
    }

    public Collection<Content> getContentObjectsByItemId(@PathVariable("itemId") String itemId) {
      return contentList;
    }

    public Content getContentObjectById(@PathVariable("contentId") String contentId) {
      return contentList.get(0);
    }

    public Resource getContentOfObjectById(@PathVariable("contentId") String contentId) {
      return new InputStreamResource(new ByteArrayInputStream("hello".getBytes())); 
    }

    public ResponseEntity createContent(@RequestBody Content content) {
      return new ResponseEntity(HttpStatus.PARTIAL_CONTENT);
    }

    public ResponseEntity deleteContent(@PathVariable("contentId") String contentId) {
      return new ResponseEntity(HttpStatus.NOT_MODIFIED);
    }

    public ResponseEntity deleteContentOjectsByItemId(@PathVariable("itemId") String itemId) {
      return new ResponseEntity(HttpStatus.NOT_MODIFIED);
    }

    public ResponseEntity checkInContent(@PathVariable("contentId") String contentId, @RequestBody Content content) {
      return new ResponseEntity(HttpStatus.NOT_MODIFIED);
    }

  }
}
