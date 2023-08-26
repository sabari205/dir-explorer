package com.sabari.explorer.constants;

public enum FileSizeConstants {
	B(0, "b"), KB(1, "kb"), MB(2, "mb"), GB(3, "gb"), TB(4, "tb");

	public final int index;
	public final String unit;

	FileSizeConstants (int index, String unit) {
		this.index = index;
		this.unit = unit;
	}
}