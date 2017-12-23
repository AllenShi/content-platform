package net.sjl.spring.content.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Collection;
import java.util.Set;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.net.URI;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.ByteArrayInputStream;

import net.sjl.spring.content.model.Content;
import net.sjl.spring.content.model.ContentService;
import net.sjl.spring.content.model.ContentTransfer;
import net.sjl.spring.content.exception.ContentNotFoundException;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
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
  public Content findContentObjectById(@PathVariable("contentId") String contentId) {
    Content content = service.retrieveContent(contentId); 

    if(content == null)
      throw new ContentNotFoundException(contentId);
    return content;
  }

  @GetMapping("/contents/{contentId}/content")
  public Resource findContentOfObjectById(@PathVariable("contentId") String contentId) {
    Content content = service.retrieveContent(contentId); 

    if(content == null)
      throw new ContentNotFoundException(contentId);
    return new InputStreamResource(content.getInputStream());
  }

  @GetMapping("/contents/item/{itemId}")
  public Collection<Content> findContentObjectByItemId(@PathVariable("itemId") String itemId) {
    return service.retrieveAllContentByItemId(itemId); 
  }

  @PostMapping("/contents")
  public ResponseEntity createContent(@ModelAttribute ContentTransfer contentTransfer) {
    URI uri = null;
    try {
      uri = new URI(contentTransfer.getUri());
    } catch(Exception e) {
      return new ResponseEntity("The param uri is invalid", HttpStatus.BAD_REQUEST);
    }
    Content content = new Content(null, contentTransfer.getItemId(), uri);
    try(InputStream input = contentTransfer.getFile().getInputStream()) {
      Content newContent = service.addNewContent(content, input);
      return new ResponseEntity(newContent, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity("The server side exception", HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PutMapping("/contents/{contentId}")
  public ResponseEntity checkInContent(@PathVariable("contentId") String contentId, @ModelAttribute ContentTransfer contentTransfer) {
    URI uri = null;
    try {
      uri = new URI(contentTransfer.getUri());
    } catch(Exception e) {
      return new ResponseEntity("The param uri is invalid", HttpStatus.BAD_REQUEST);
    }
    Content content = new Content(contentId, contentTransfer.getItemId(), uri);
    try(InputStream input = contentTransfer.getFile().getInputStream()) {
      Content updatedContent = service.checkInContent(content, input);
      return new ResponseEntity(updatedContent, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity("The server side exception", HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @DeleteMapping("/contents/{contentId}")
  public ResponseEntity deleteContent(@PathVariable("contentId") String contentId) {
    service.deleteContent(contentId);
    return new ResponseEntity(contentId, HttpStatus.OK);
  }

}
