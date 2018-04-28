package net.sjl.spring.content.model;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;
import java.net.URI;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.apache.commons.lang3.Validate;

@Getter
@Setter
@NoArgsConstructor
public class Content {

  private String id;
  private URI uri;
  private int size;
  private String eTag;
  // The itemId of ContentItem which is associated with current content
  // One itemId can have many Content
  private String itemId;
  private ZonedDateTime creationDate;
  private ZonedDateTime lastModifiedDate;
  private transient StorageArea storage;

  public Content(String id, String itemId, URI uri) {
    this(id, itemId, uri, -1, null);
  }

  public Content(String id, String itemId, URI uri, StorageArea storage) {
    this(id, itemId, uri, -1, storage);
  }

  public Content(String id, String itemId, URI uri, int size) {
    this(id, itemId, uri, size, null);
  }

  public Content(String id, String itemId, URI uri, int size, StorageArea storage) {
    this.id = id;
    this.itemId = itemId;
    this.uri = uri; 
    this.size = size; 
    this.storage = storage;
  }

  public Content(final Content s) {
    this.id = s.id;
    this.itemId = s.itemId;
    this.uri = s.uri;
    this.size = s.size;
    this.storage = s.storage;
  }

  public InputStream getInputStream() {
    Validate.notNull(uri, "The object uri must not be null");
    Validate.notNull(storage, "The object storage must not be null");
    Validate.notEmpty(id, "The content id must not be empty");

    // Path path = Paths.get(uri);
    String path = uri.getPath();
    if(path.startsWith("file://")) {
      path = path.substring("file://".length(), path.length() - "file://".length()); 
    }
    return storage.getInputStream(id, path);
  }

  public OutputStream getOutputStream() {
    Validate.notNull(uri, "The object uri must not be null");
    Validate.notNull(storage, "The object storage must not be null");
    Validate.notEmpty(id, "The content id must not be empty");

    // Path path = Paths.get(uri);
    String path = uri.getPath();
    if(path.startsWith("file://")) {
      path = path.substring("file://".length(), path.length() - "file://".length()); 
    }
    return storage.getOutputStream(id, path);
  }
}
