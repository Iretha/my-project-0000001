package com.mbd.tools.bundle.generator;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Properties;

public class BundleGenerator {

	private static String BUNDLE_KEY = "BUNDLE_PACKAGE";

	private static String SOURCE_FILE_EXTENSION = "SOURCE_FILE_EXTENSION";

	private static String PROP_FILE = "src\\com\\mbd\\tools\\bundle\\generator\\bundle_generator_config.properties";

	private static String COMMENT_CHAR = "#";

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Properties properties = new Properties();
		String bundlePackage = null;
		String sourceFileExt = null;
		String destinationDirPath = null;
		try {
			properties.load(new FileInputStream(PROP_FILE));

			bundlePackage = properties.getProperty(BUNDLE_KEY);
			if (bundlePackage == null) {
				System.err.append("Във файлa " + PROP_FILE + " трябва да има добавен ключ "
						+ BUNDLE_KEY + ".");
			}

			sourceFileExt = properties.getProperty(SOURCE_FILE_EXTENSION);
			if (sourceFileExt == null) {
				System.err.append("Във файлa " + PROP_FILE + " трябва да има добавен ключ "
						+ SOURCE_FILE_EXTENSION + ".");
			}
			destinationDirPath = new File(".").getCanonicalPath() + bundlePackage;
			File destinationDir = new File(destinationDirPath);
			File[] files = null;
			if (destinationDir.exists() && destinationDir.isDirectory()) {
				files = destinationDir.listFiles();
				for (File curr : files) {
					if (curr.getAbsolutePath().toLowerCase().endsWith(sourceFileExt.toLowerCase())) {
						generateBundleFiles(destinationDirPath, curr);
					}
				}
			}
		} catch (IOException e) {
			System.err.append("Възникна грешка при опит за изчитане на текущата директория.");
		}
	}

	private static void generateBundleFiles(String destinationDir, File sourceFile) {
		BufferedReader reader = null;
		try {
			char separator = 0;
			String targetFileName = null;
			String targetFileExtension = null;
			String[] locales = null;
			StringBuilder[] contents = null;
			String line = null;
			String[] subs = null;
			reader = new BufferedReader(new FileReader(sourceFile));
			int cnt = 0;
			while ((line = reader.readLine()) != null) {
				cnt++;
				if (cnt == 1) {
					separator = line.charAt(0);
					if (separator == 0) {
						System.err.append("На първият ред трябва да е посочен разделител.");
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
					writeLine(separator, locales, contents, line, cnt);
				}
			}

			for (int x = 0; x < locales.length; x++) {
				if (contents[x].length() > 0) {
					writeFile(contents[x], destinationDir + targetFileName + locales[x]
							+ targetFileExtension);
				}
			}

		} catch (FileNotFoundException e) {
			System.err.append("Липсва търсеният файл " + destinationDir);
		} catch (IOException e) {
			System.err.append("Проблем с " + destinationDir);
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

	private static void writeLine(char separator, String[] locales, StringBuilder[] contents,
			String line, int cnt) {
		String[] subs;

		if (line.startsWith(COMMENT_CHAR)) {
			for (int x = 0; x < locales.length; x++) {
				contents[x] = appendLine(contents[x], line);
			}
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
						if (locales[x].equalsIgnoreCase("_bg")) {
							contents[x] = appendLine(contents[x], subs[0] + "="
									+ convertToHexString(subs[x + 1]));
						} else {
							contents[x] = appendLine(contents[x], subs[0] + "=" + subs[x + 1]);
						}
					}
				}
			}
		}
	}

	private static StringBuilder appendLine(StringBuilder builder, String line) {
		if (builder == null) {
			builder = new StringBuilder();
		}
		builder.append(line);
		builder.append(System.getProperty("line.separator"));
		return builder;
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
