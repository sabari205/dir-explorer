package com.sabari.explorer.dto;

public class FilesDTO {
	private String name;
	private String absPath;
	private boolean type;
	private String lastModified;

	public FilesDTO() {

	}

	public FilesDTO(String name, String lastModified, boolean type, String absPath) {
		this.name = name;
		this.type = type;
		this.absPath = absPath;
		this.lastModified = lastModified;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastModified() {
		return this.lastModified;
	}

	public void setLastModified(String lastModified) {
		this.lastModified = lastModified;
	}

	public String getAbsPath() {
		return this.absPath;
	}

	public void setAbsPath(String absPath) {
		this.absPath = absPath;
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