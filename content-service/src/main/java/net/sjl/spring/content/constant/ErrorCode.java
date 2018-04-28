package net.sjl.spring.content.constant;

public enum ErrorCode {
	
	CONTENT_NOT_FOUND(1),
	CONTENT_URI_INVALID(2),
	CONTENT_GENERIC_ERROR(1000);
	
	private int value;
	
	ErrorCode(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}

}
