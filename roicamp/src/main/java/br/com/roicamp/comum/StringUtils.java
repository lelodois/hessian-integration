package br.com.roicamp.comum;

public class StringUtils {

	private StringUtils() {
	}

	public static String truncateString(String text, int max) {
		if (text != null && text.length() > max) {
			return org.apache.commons.lang3.StringUtils.substring(text, 0, max - 1);
		}
		return text;
	}
}
