package com.bastard.util;

public class Indent {
	
	public static String $(int times) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < times; i++) {
			sb.append("\t");
		}
		return sb.toString();
	}

}
