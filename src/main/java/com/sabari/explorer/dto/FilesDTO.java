package com.sabari.explorer.dto;

import com.sabari.explorer.constants.FileSizeConstants;

public class FilesDTO {
	private String name;
	private String absPath;
	private boolean type;
	private String lastModified;
	private long size;

	public FilesDTO() {

	}

	public FilesDTO(String name, String lastModified, boolean type, String absPath) {
		this.name = name;
		this.lastModified = lastModified;
		this.type = type;
		this.absPath = absPath;
	}

	public FilesDTO(String name, String lastModified, boolean type, String absPath, long size) {
		this.name = name;
		this.lastModified = lastModified;
		this.type = type;
		this.absPath = absPath;
		this.size = size;
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

	public long getSize() {
		return this.size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public String getFileType() {
		if (this.type) {
			return "folder";
		}

        String[] fileTokens = this.name.split("\\.", -1);

		int length = fileTokens.length;
		if (fileTokens[length-1].equalsIgnoreCase("xlsx") 
			|| fileTokens[length-1].equalsIgnoreCase("csv") 
			|| fileTokens[length-1].equalsIgnoreCase("xls")) {
			return "excel-";
		}

		if (fileTokens[length-1].equalsIgnoreCase("zip") 
			|| fileTokens[length-1].equalsIgnoreCase("gz") 
			|| fileTokens[length-1].equalsIgnoreCase("tar")
			|| fileTokens[length-1].equalsIgnoreCase("bz")) {
			return "archive-";
		}

		if (fileTokens[length-1].equalsIgnoreCase("java") 
			|| fileTokens[length-1].equalsIgnoreCase("py") 
			|| fileTokens[length-1].equalsIgnoreCase("pl")
			|| fileTokens[length-1].equalsIgnoreCase("rs")
			|| fileTokens[length-1].equalsIgnoreCase("go")
			|| fileTokens[length-1].equalsIgnoreCase("dat")
			|| fileTokens[length-1].equalsIgnoreCase("md")
			|| fileTokens[length-1].equalsIgnoreCase("html")
			|| fileTokens[length-1].equalsIgnoreCase("sh")) {
			return "code-";
		}

		if (fileTokens[length-1].equalsIgnoreCase("txt")) {
			return "text-";
		}

		if (fileTokens[length-1].equalsIgnoreCase("pdf")) {
			return "pdf-";
		}

		if (fileTokens[length-1].equalsIgnoreCase("png")
			|| fileTokens[length-1].equalsIgnoreCase("jpg")
			|| fileTokens[length-1].equalsIgnoreCase("jpeg")) {
			return "image-";
		}

		if (fileTokens[length-1].equalsIgnoreCase("mp4")
			|| fileTokens[length-1].equalsIgnoreCase("mkv")
			|| fileTokens[length-1].equalsIgnoreCase("webp")) {
			return "video-";
		}

		return "";

	}

	public String formatSize () {
		if (this.type) {
			// Size deduction is not required for directories
			return "0";
		}

		int count = 0;
		long roundedSize = this.size;
		String unit = null;

		while (true) {
			long q = roundedSize / 1024;

			if (q == 0) {
				break;
			}

			roundedSize = q;
			count++;
		}

		for (FileSizeConstants sizeEnum: FileSizeConstants.values()) {
			if (count == sizeEnum.index) {
				unit = sizeEnum.unit;
				break;
			}
		}

		return String.format("%d %s", roundedSize, unit);
	}

	public String toString() {
		return String.format("Name: %s, Type: %s", this.name, this.type? "d": "f");
	}
}
