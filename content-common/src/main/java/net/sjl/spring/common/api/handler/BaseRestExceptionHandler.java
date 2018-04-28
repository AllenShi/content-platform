package net.sjl.spring.common.api.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import net.sjl.spring.common.exception.BaseRestException;
import net.sjl.spring.common.exception.BaseRuntimeException;
import net.sjl.spring.common.exception.ErrorMessage;
import net.sjl.spring.common.nls.Messages;


@ControllerAdvice
public class BaseRestExceptionHandler extends ResponseEntityExceptionHandler {

	@Autowired
	private Messages messages;

	@ExceptionHandler({ BaseRestException.class })
	public ResponseEntity<ErrorMessage> handleECCException(BaseRestException exception, WebRequest request) {

		HttpHeaders headers = new HttpHeaders();
		HttpStatus status = exception.getStatusCode();

		BaseRuntimeException cause = exception.getBaseRuntimeException();
		ErrorMessage errorMessage = new ErrorMessage(cause.getCategoryCode(), cause.getErrorCode(), cause.getMessage());
		
		return new ResponseEntity<>(errorMessage, headers, status);
	}

}
