package com.ui.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Помощен клас за валидация на низове
 * 
 * @author developer0024
 * 
 */
public class StringUtils {

	/**
	 * Формат на датата по който да се сравнява "dd/MM/yyyy HH:mm"
	 */
	public static SimpleDateFormat sdfFull = new SimpleDateFormat("dd/MM/yyyy HH:mm");

	/**
	 * Формат на датата по който да се сравнява "dd/MM/yyyy"
	 */
	public static SimpleDateFormat sdfSimple = new SimpleDateFormat("dd/MM/yyyy");

	/**
	 * Връща true ако датите са еднакви
	 * 
	 * @param date1
	 *            - първа дата
	 * @param date2
	 *            - втора дата
	 * @param simple
	 *            - начин на сравнение, simple = true - игнорира часове и
	 *            минути, false - взима под внимание часове и минути
	 * 
	 * @return boolean
	 * @throws ParseException
	 */
	public static boolean compareDates(Date date1, Date date2, boolean simple)
			throws ParseException {
		long milsDate1 = 0;
		long milsDate2 = 0;
		if (simple) {
			milsDate1 = sdfSimple.parse(sdfSimple.format(date1)).getTime();
			milsDate2 = sdfSimple.parse(sdfSimple.format(date2)).getTime();
		} else {
			milsDate1 = sdfFull.parse(sdfFull.format(date1)).getTime();
			milsDate2 = sdfFull.parse(sdfFull.format(date2)).getTime();
		}

		return milsDate1 == milsDate2;
	}

	/**
	 * @param text
	 * @return com.acsior.util
	 */
	public static String escape(String text) {
		if (text == null) {
			return "";
		}
		StringBuilder escapedText = new StringBuilder();
		for (int i = 0; i < text.length(); i++) {
			char ch = text.charAt(i);
			if (ch == '<') {
				escapedText.append("&lt;");
			} else if (ch == '>') {
				escapedText.append("&gt;");
			} else if (ch == '&') {
				escapedText.append("&amp;");
			} else if (ch == '\"') {
				escapedText.append("&quot;");
			} else if (ch == '\n') {
				escapedText.append(", ");
			} else if (ch == '\r') {
				escapedText.append(" ");
			} else {
				escapedText.append(ch);
			}
		}
		String result = escapedText.toString();
		return result;
	}

	/**
	 * Форматира дата като изчиства часове и минути
	 * 
	 * @param date
	 * @return Date
	 */
	public static Date formatDateFull(Date date) {
		try {
			return sdfSimple.parse(sdfSimple.format(date));
		} catch (ParseException e) {
			return null;
		}
	}

	/**
	 * Форматира дата като изчиства часове и минути
	 * 
	 * @param date
	 * @return Date
	 */
	public static Date formatDateSimple(Date date) {
		try {
			return sdfSimple.parse(sdfSimple.format(date));
		} catch (ParseException e) {
			return null;
		}
	}

	/**
	 * Премахва скобите от идентификатор в CE
	 * 
	 * @param id
	 * @return String
	 */
	public static String getRealCEIdentifier(String id) {
		String realId = null;
		if (!isEmpty(id)) {
			realId = id.replace("{", "").replace("}", "");
		}
		return realId;
	}

	/**
	 * Валидира дали въведеният email е коректен.
	 * 
	 * @param text
	 * @return isCorrectEmail true/false
	 */
	public static boolean isCorrectEmail(String text) {
		String regex = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[_A-Za-z0-9-]+)";

		return text.matches(regex);
	}

	/**
	 * Проверява дали низът се състои само от малки, големи латински букви и/или
	 * цифри
	 * 
	 * @param text
	 * @return isCorrectName true/false
	 */
	public static boolean isCorrectName(String text) {
		String str = "[a-zA-Z0-9]+";
		boolean valid = text.matches(str);
		if (!valid) {
			return true;
		}
		return false;
	}

	/**
	 * Връща true ако първата дата е след втората
	 * 
	 * @param first
	 * @param second
	 * @param simple
	 *            - начин на сравнение, simple = true - игнорира часове и
	 *            минути, false - взима под внимание часове и минути
	 * 
	 * @return boolean
	 */
	public static boolean isDateAfter(Date first, Date second, boolean simple) {
		try {
			if (simple) {
				return sdfSimple.parse(sdfSimple.format(first)).after(
						sdfSimple.parse(sdfSimple.format(second)));
			}
			return sdfFull.parse(sdfFull.format(first))
					.after(sdfFull.parse(sdfFull.format(second)));

		} catch (ParseException e) {
			return false;
		}
	}

	/**
	 * Връща true ако първата дата е преди втората
	 * 
	 * @param first
	 * @param second
	 * @param simple
	 *            - начин на сравнение, simple = true - игнорира часове и
	 *            минути, false - взима под внимание часове и минути
	 * @return boolean
	 */
	public static boolean isDateBefore(Date first, Date second, boolean simple) {
		try {
			if (simple) {
				return sdfSimple.parse(sdfSimple.format(first)).before(
						sdfSimple.parse(sdfSimple.format(second)));
			}
			return sdfFull.parse(sdfFull.format(first)).before(
					sdfFull.parse(sdfFull.format(second)));
		} catch (ParseException e) {
			return false;
		}
	}

	/**
	 * Връща true ако първата дата е равна на втората
	 * 
	 * @param first
	 * @param second
	 * @param simple
	 *            - начин на сравнение, simple = true - игнорира часове и
	 *            минути, false - взима под внимание часове и минути
	 * @return boolean
	 */
	public static boolean isDateEqual(Date first, Date second, boolean simple) {
		try {
			if (simple) {
				return sdfSimple.parse(sdfSimple.format(first)).equals(
						sdfSimple.parse(sdfSimple.format(second)));
			}
			return sdfFull.parse(sdfFull.format(first)).equals(
					sdfFull.parse(sdfFull.format(second)));
		} catch (ParseException e) {
			return false;
		}
	}

	/**
	 * @param s
	 * @return isEmpty
	 */
	public static boolean isEmpty(String s) {
		if (s == null || s.trim().equals("")) {
			return true;
		}
		return false;
	}

	/**
	 * @param d
	 * @return isEmpty
	 */
	public static boolean isEmptyDate(Date d) {
		return d == null;
	}

	/**
	 * Проверява дали низът се състои само от цифри
	 * 
	 * @param text
	 * @return isNumber true/false
	 */
	public static boolean isNumber(String text) {
		String str = "[0-9]+";
		boolean valid = text.matches(str);
		if (!valid) {
			return true;
		}
		return false;
	}

	/**
	 * Валидира дали въведеният ip адрес е коректен.
	 * 
	 * @param text
	 * @return isNumberIP
	 */
	public static boolean isNumberIP(String text) {
		String str = "^(([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])\\.){3}([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])$";
		boolean valid = text.matches(str);
		if (!valid) {
			return true;
		}
		return false;
	}

	/**
	 * 
	 */
	public StringUtils() {
		super();
	}

}
