package com.ui.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class BundleGenerator {
	private static String BUNDLE_PACKAGE = "\\src\\com\\ui\\bundle\\";
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String sourceFilePath = null;
		String sourceFileName = "WebMessages.txt";
		try {
			sourceFilePath = new File(".").getCanonicalPath() + BUNDLE_PACKAGE
					+ sourceFileName;
		} catch (IOException e1) {
			System.err
					.append("Възникна грешка при опит за изчитане на текущата директория.");
		}

		File f = new File(sourceFilePath);
		BufferedReader reader = null;
		try {
			char separator = 0;
			String targetFileName = null;
			String targetFileExtension = null;
			String[] locales = null;
			StringBuilder[] contents = null;
			String line = null;
			String[] subs = null;
			reader = new BufferedReader(new FileReader(f));
			int cnt = 0;
			while ((line = reader.readLine()) != null) {
				cnt++;
				if (cnt == 1) {
					separator = line.charAt(0);
					if (separator == 0) {
						System.err
								.append("На първият ред трябва да е посочен разделител.");
					}
					subs = line.split(new String(new char[] { separator }));
					targetFileName = subs[1];
					targetFileExtension = subs[2];
					locales = new String[subs.length - 3];
					for (int i = 3; i < subs.length; i++) {
						locales[i - 3] = subs[i];
					}
					contents = new StringBuilder[locales.length];
				} else {
					if (line.indexOf(separator) > 0) {
						subs = line.split(new String(new char[] { separator }));
						int wordCount = locales.length + 1;
						if (subs.length != wordCount) {
							System.err
									.append("Грешка на ред: "
											+ cnt
											+ " Трябва да има точно "
											+ wordCount
											+ " думи с разделител \""
											+ separator
											+ "\". Думите са ключ и превод за всеки един от посочените local-и. \n");
						} else {

							for (int x = 0; x < locales.length; x++) {
								if (contents[x] == null) {
									StringBuilder b = new StringBuilder();
									contents[x] = b;
								}
								if (locales[x].equalsIgnoreCase("_bg")) {
									contents[x].append(subs[0] + "="
											+ convertToHexString(subs[x + 1]));
								} else {
									contents[x].append(subs[0] + "="
											+ subs[x + 1]);
								}

								contents[x].append(System
										.getProperty("line.separator"));
							}
						}
					}
				}
			}

			for (int x = 0; x < locales.length; x++) {
				if (contents[x].length() > 0) {
					writeFile(contents[x], sourceFilePath + targetFileName
							+ locales[x] + targetFileExtension);
				}
			}

		} catch (FileNotFoundException e) {
			System.err.append("Липсва търсеният файл " + sourceFilePath);
		} catch (IOException e) {
			System.err.append("Проблем с " + sourceFilePath);
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					System.err.append("Четецът не може да бъде затворен!");
				}
			}
		}

	}

	public static void writeFile(StringBuilder builder, String fileName) {
		Writer output = null;
		try {
			output = new BufferedWriter(new FileWriter(new File(fileName)));
			output.write(builder.toString());
			output.flush();
			System.out.println("File " + fileName + " generated!");
		} catch (IOException e) {
			System.err.append("Проблем с " + fileName);
		} finally {
			try {
				if (output != null) {
					output.close();
				}
			} catch (IOException e) {
				System.err.append("Писецът не може да бъде затворен! :)");
			}
		}
	}

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
				if (charToInt >= 65 && charToInt <= 90 || charToInt >= 97
						&& charToInt <= 122) {
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

}
