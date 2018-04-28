package net.sjl.spring.common.exception;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import net.sjl.spring.common.constant.CategoryCode;

@Getter
@Setter
public class ErrorMessage implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private CategoryCode categoryCode;
	private int errorCode;
	private String message;
	private String cause;
	private String action;
	
	public ErrorMessage(CategoryCode categoryCode, int errorCode, String message) {
		super();
		this.categoryCode = categoryCode;
		this.errorCode = errorCode;
		this.message = message;
	}

	public ErrorMessage(CategoryCode categoryCode, int errorCode, String message, String cause) {
		super();
		this.categoryCode = categoryCode;
		this.errorCode = errorCode;
		this.message = message;
		this.cause = cause;
	}

	public ErrorMessage(CategoryCode categoryCode, int errorCode, String message, String cause, String action) {
		super();
		this.categoryCode = categoryCode;
		this.errorCode = errorCode;
		this.message = message;
		this.cause = cause;
		this.action = action;
	}

}
