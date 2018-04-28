package net.sjl.spring.common.exception;

import net.sjl.spring.common.constant.CategoryCode;

public class BaseRuntimeException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private CategoryCode categoryCode;
	private int errorCode;
	private String causeMessage;
	private String action;

	public BaseRuntimeException(CategoryCode categoryCode, int errorCode, String message) {
		super(message);
		this.categoryCode = categoryCode;
		this.errorCode = errorCode;
	}
	
	public BaseRuntimeException(CategoryCode categoryCode, int errorCode, String message, String causeMessage, String action) {
		super(message);
		this.categoryCode = categoryCode;
		this.errorCode = errorCode;
		this.causeMessage = causeMessage;
		this.action = action;
	}
	
	public BaseRuntimeException(CategoryCode categoryCode, int errorCode, String message, Throwable cause) {
		super(message, cause);
		this.categoryCode = categoryCode;
		this.errorCode = errorCode;
		this.causeMessage = cause.getLocalizedMessage();
	}

	public CategoryCode getCategoryCode() {
		return categoryCode;
	}
	
	public int getErrorCode() {
		return errorCode;
	}

	public String getCauseMessage() {
		return causeMessage;
	}	
	
	public String getAction() {
		return action;
	}

}
