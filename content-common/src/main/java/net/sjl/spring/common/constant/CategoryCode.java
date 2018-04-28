package net.sjl.spring.common.constant;

public enum CategoryCode {
	CONTENT_SERVICE("Content Service"),
	METADATA_SERVICE("Metadata Service"),
	CONFIG_SERVICE("Configuration Service"),
	GATEWAY_SERVICE("Gateway Service"),
	DISCOVERY_SERVICE("Discovery Service");
	
	private String categoryName;
	
	CategoryCode(String categoryName) {
		this.categoryName = categoryName;
	}
	
	public String getCategoryName() {
		return categoryName;
	}

}
