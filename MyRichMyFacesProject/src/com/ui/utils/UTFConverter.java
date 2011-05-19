package com.ui.utils;

/**
 * Помощен клас за конвертиране на низове в UTF-8 - формат
 * 
 * @author developer0024
 * 
 */
public class UTFConverter {

	/**
	 * @param unicodeString
	 * @return String
	 */
	@SuppressWarnings("cast")
	public static String convertToHexString(String unicodeString) {
		String ap = "\\u0";
		char[] chars = unicodeString.toCharArray();
		StringBuffer output = new StringBuffer();
		for (char c : chars) {
			int charToInt = (int) c;
			if (Character.isLetter(c)) {
				if (charToInt >= 65 && charToInt <= 90 || charToInt >= 97 && charToInt <= 122) {
					output.append(c);
				} else {
					output.append(ap);
					output.append(Integer.toHexString((int) c));
				}
			} else {
				output.append(c);
			}

		}
		return output.toString();
	}

	/**
	 * @param unicodeString
	 * @return String
	 */
	public static String convertToString(String unicodeString) {
		StringBuffer output = new StringBuffer();
		for (char c : unicodeString.toCharArray()) {
			output.append(c);

		}
		return output.toString();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// Invalid data extract extension id.
		String s = "Вход";
		System.out.println(convertToHexString(s));
	}

}
