package com.devin.myjaxrsapp.entity;

public class Echo {

	private Long id;
	private String input;

	public Echo(Long id, String input) {
		this.id = id;
		this.input = input;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getInput() {
		return input;
	}

	public void setInput(String input) {
		this.input = input;
	}
	
	

}
