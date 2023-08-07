package com.sabari.explorer.dto;

public class FileContentDTO {
	private String content;
	private String contentHTML;
	private String fileName;
	private boolean isAsciiFile;

	public FileContentDTO () {}

	public FileContentDTO (String content, String contentHTML, String fileName, boolean isAsciiFile) {
		this.content = content;
		this.contentHTML = contentHTML;
		this.fileName = fileName;
		this.isAsciiFile = isAsciiFile;
	}

	public FileContentDTO (String content, String fileName, boolean isAsciiFile) {
		this.content = content;
		this.fileName = fileName;
		this.isAsciiFile = isAsciiFile;
	}

	public String getContent () {
		return this.content;
	}

	public void setContent (String content) {
		this.content = content;
	}

	public String getContentHTML () {
		return this.contentHTML;
	}

	public void setContentHTML (String contentHTML) {
		this.contentHTML = contentHTML;
	}

	public String getFileName () {
		return this.fileName;
	}

	public void setFileName (String fileName) {
		this.fileName = fileName;
	}

	public boolean getIsAsciiFile () {
		return this.isAsciiFile;
	}

	public void setIsAsciiFile (boolean isAsciiFile) {
		this.isAsciiFile = isAsciiFile;
	}
}