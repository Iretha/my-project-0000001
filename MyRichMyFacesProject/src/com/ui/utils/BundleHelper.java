package com.ui.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class BundleHelper {

	/**
	 * Разделител на име на файл
	 */
	public static String FILE_EXTENSION_SEPARATOR = ".";

	public static String SOURCE_FILE_EXTENSION = "txt";

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String destDir = "C:/prj/MyRichMyFacesProject/src/com/ui/bundle/";
		String sourceFileName = "WebMessages";
		File f = new File(destDir + sourceFileName + FILE_EXTENSION_SEPARATOR
				+ SOURCE_FILE_EXTENSION);
		BufferedReader reader = null;
		// StringBuilder enContent = new StringBuilder();
		// StringBuilder bgContent = new StringBuilder();
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
						System.err.append("На първият ред трябва да е посочен разделител.");
					}
					targetFileName = subs[0];
					targetFileExtension = subs[1];
					for (int i = 2; i < subs.length; i++) {
						locales[i - 2] = subs[i];
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
											+ "\". Думита са ключ и превод за всеки един от посочените local-и. \n");
						} else {

							for (int x = 0; x < locales.length; x++) {
								// TODO
								if (contents[x] == null) {
									StringBuilder b = new StringBuilder();
									contents[x] = b;
								}
							}

							// enContent.append(subs[0] + "=" + subs[1]);
							// enContent.append(System.getProperty("line.separator"));
							// bgContent.append(subs[0] + "=" +
							// convertToHexString(subs[2]));
							// bgContent.append(System.getProperty("line.separator"));
						}

					}

				}
			}

			// String fullFilePath = destDir + targetFileName + enLocale +
			// targetFileExtension;
			// if (enContent.length() > 0) {
			// writeFile(enContent, fullFilePath);
			// }
			//
			// if (bgContent.length() > 0) {
			// fullFilePath = destDir + targetFileName + bgLocale +
			// targetFileExtension;
			// writeFile(bgContent, fullFilePath);
			// }

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (output != null) {
					output.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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

}
