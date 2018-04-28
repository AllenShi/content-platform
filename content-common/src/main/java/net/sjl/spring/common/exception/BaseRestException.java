package net.sjl.spring.common.exception;

import org.springframework.http.HttpStatus;

import net.sjl.spring.common.constant.CategoryCode;

public class BaseRestException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	private HttpStatus statusCode;
	private BaseRuntimeException exception;
	
	public BaseRestException(HttpStatus statusCode, CategoryCode categoryCode, int errorCode, String message) {
		this.statusCode = statusCode;
		this.exception = new BaseRuntimeException(categoryCode, errorCode, message);
	}
	
	public BaseRestException(HttpStatus statusCode, BaseRuntimeException exception) {
		this.statusCode = statusCode;
	}

	public HttpStatus getStatusCode() {
		return statusCode;
	}
	
	public BaseRuntimeException getBaseRuntimeException() {
		return exception;
	}

}
