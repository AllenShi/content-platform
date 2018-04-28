package net.sjl.spring.content.api;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.APPLICATION_OCTET_STREAM_VALUE;

import java.io.InputStream;
import java.net.URI;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.sjl.spring.common.constant.CategoryCode;
import net.sjl.spring.common.exception.BaseRestException;
import net.sjl.spring.content.constant.ErrorCode;
import net.sjl.spring.content.exception.ContentNotFoundException;
import net.sjl.spring.content.model.Content;
import net.sjl.spring.content.model.ContentService;
import net.sjl.spring.content.model.ContentTransfer;

@RestController
public class ContentRestController {

	@Autowired
	ContentService service;

	@ApiOperation(value = "Return all content objects")
	@GetMapping(value = "/contents", produces = APPLICATION_JSON_VALUE)
	public Collection<Content> getContents() {
		Collection<Content> contentList = service.retrieveAllContent();

		if (contentList == null || contentList.size() == 0)
			throw new ContentNotFoundException("all");

		return contentList;
	}

	@ApiOperation(value = "Find content object per content id")
	@GetMapping(value = "/contents/{contentId}", produces = APPLICATION_JSON_VALUE)
	public Content findContentObjectById(@ApiParam(value = "content id", required = true) @PathVariable("contentId") String contentId) {
		Content content = service.retrieveContent(contentId);

		if (content == null)
			throw new ContentNotFoundException(contentId);
		return content;
	}

	@ApiOperation(value = "Response with content stream")
	@GetMapping(value = "/contents/{contentId}/content", produces = APPLICATION_OCTET_STREAM_VALUE)
	public ResponseEntity<Resource> findContentOfObjectById(@PathVariable("contentId") String contentId) {
		Content content = service.retrieveContent(contentId);

		if (content == null)
			throw new ContentNotFoundException(contentId);

		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, String.format("inline; filename=\"" + content.getId() + "\""))
				.contentType(MediaType.APPLICATION_OCTET_STREAM)
				.body(new InputStreamResource(content.getInputStream()));
	}

	@ApiOperation(value = "Find content object per content id")
	@GetMapping(value = "/contents/item/{itemId}", produces = APPLICATION_JSON_VALUE)
	public Collection<Content> findContentObjectByItemId(@PathVariable("itemId") String itemId) {
		return service.retrieveAllContentByItemId(itemId);
	}

	@ApiOperation(value = "Add new content object")
	@PostMapping(value = "/contents", produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<Content> createContent(@ModelAttribute ContentTransfer contentTransfer) {
		URI uri = null;

		try {
			uri = new URI(contentTransfer.getUri());
		} catch (Exception e) {
			throw new BaseRestException(HttpStatus.BAD_REQUEST, CategoryCode.CONTENT_SERVICE,
					ErrorCode.CONTENT_URI_INVALID.getValue(), "The param uri is invalid");
		}

		Content content = new Content(null, contentTransfer.getItemId(), uri);
		try (InputStream input = contentTransfer.getFiles()[0].getInputStream()) {
			Content newContent = service.addNewContent(content, input);
			return new ResponseEntity<Content>(newContent, HttpStatus.OK);
		} catch (Exception e) {
			throw new BaseRestException(HttpStatus.INTERNAL_SERVER_ERROR, CategoryCode.CONTENT_SERVICE,
					ErrorCode.CONTENT_GENERIC_ERROR.getValue(), e.getMessage());
		}
	}

	@ApiOperation(value = "Check-in")
	@PutMapping(value = "/contents/{contentId}", produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<Content> checkInContent(@PathVariable("contentId") String contentId,
			@ModelAttribute ContentTransfer contentTransfer) {
		URI uri = null;

		try {
			uri = new URI(contentTransfer.getUri());
		} catch (Exception e) {
			throw new BaseRestException(HttpStatus.BAD_REQUEST, CategoryCode.CONTENT_SERVICE,
					ErrorCode.CONTENT_URI_INVALID.getValue(), "The param uri is invalid");
		}

		Content content = new Content(contentId, contentTransfer.getItemId(), uri);
		try (InputStream input = contentTransfer.getFiles()[0].getInputStream()) {
			Content updatedContent = service.checkInContent(content, input);
			return new ResponseEntity<Content>(updatedContent, HttpStatus.OK);
		} catch (Exception e) {
			throw new BaseRestException(HttpStatus.INTERNAL_SERVER_ERROR, CategoryCode.CONTENT_SERVICE,
					ErrorCode.CONTENT_GENERIC_ERROR.getValue(), e.getMessage());
		}
	}

	@ApiOperation(value = "Delete content")
	@DeleteMapping("/contents/{contentId}")
	public ResponseEntity<?> deleteContent(@PathVariable("contentId") String contentId) {
		service.deleteContent(contentId);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@ApiOperation(value = "Delete multiple contents related to item id")
	@DeleteMapping("/contents/item/{itemId}")
	public ResponseEntity<?> deleteContentObjectsByItemId(@PathVariable("itemId") String itemId) {
		service.deleteByItemId(itemId);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
