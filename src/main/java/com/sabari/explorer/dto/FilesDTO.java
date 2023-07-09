package com.sabari.explorer.dto;

public class FilesDTO {
	private String name;
	private boolean type;

	public FilesDTO() {

	}

	public FilesDTO(String name, boolean type) {
		this.name = name;
		this.type = type;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean getType() {
		return this.type;
	}

	public void setType(boolean type) {
		this.type = type;
	}

	public String toString() {
		return String.format("Name: %s, Type: %s", this.name, this.type? "d": "f");
	}
}