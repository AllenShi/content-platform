package net.sjl.spring.metadata.proxy;

import java.util.*;

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
  public Collection<Content> getContents();

  @GetMapping("/contents/{contentId}")
  public Content findContentById(@PathVariable("contentId") int contentId);

  @PostMapping(value = "/contents", produces = "application/json")
  public ResponseEntity createContent(@RequestBody Content content);

  @DeleteMapping("/contents/{contentId}")
  public ResponseEntity deleteContent(@PathVariable("contentId") int contentId);

  @PutMapping(value = "/contents/{contentId}", consumes = "application/json")
  public ResponseEntity changeContent(@PathVariable("contentId") int  contentId, @RequestBody Content content);

  @Component
  public static class ContentServiceFallback implements ContentService {

    private static List<Content> contentList = new ArrayList<>();

    static {
      contentList.add(new Content("blue", "file:///home/allen/Documents/dummy1.txt", 25));
      contentList.add(new Content("yello", "file:///home/allen/Documents/dummy2.txt", 30));
      contentList.add(new Content("red", "file:///home/allen/Documents/dummy3.txt", 35));
    }

    public Collection<Content> getContents() {
      return contentList;
    }

    public Content findContentById(@PathVariable("contentId") int contentId) {
      return contentList.get(0);
    }

    public ResponseEntity createContent(@RequestBody Content content) {
      return new ResponseEntity(HttpStatus.PARTIAL_CONTENT);
    }

    public ResponseEntity deleteContent(@PathVariable("contentId") int contentId) {
      return new ResponseEntity(HttpStatus.NOT_MODIFIED);
    }

    public ResponseEntity changeContent(@PathVariable("contentId") int  contentId, @RequestBody Content content) {
      return new ResponseEntity(HttpStatus.NOT_MODIFIED);
    }

  }
}
