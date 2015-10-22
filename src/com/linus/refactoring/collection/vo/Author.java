package com.linus.refactoring.collection.vo;

public class Author {
	private String name;
	private String twitterHandle;
	private String company;

	
	public Author(String name, String twitterHandle, String company) {
		super();
		this.name = name;
		this.twitterHandle = twitterHandle;
		this.company = company;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTwitterHandle() {
		return twitterHandle;
	}

	public void setTwitterHandle(String twitterHandle) {
		this.twitterHandle = twitterHandle;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}
}
