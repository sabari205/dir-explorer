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