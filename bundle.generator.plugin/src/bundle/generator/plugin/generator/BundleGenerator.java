/**
 * TODO
 */
package bundle.generator.plugin.generator;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

/**
 * @author MBD
 * 
 */
public class BundleGenerator {

	public static final String SOURCE_FILE_EXTENSION = "txt";

	public static final String FILE_EXTENSION_SEPARATOR = ".";

	private static final String PROP_FILE_COMMENT_CHAR = "#";

	public static void generateBundleFiles(String destinationDir, String sourceFilePath)
			throws GeneratorException {
		BufferedReader reader = null;
		try {
			char separator = 0;
			int cnt = 0;
			String targetFileName = null;
			String targetFileExtension = null;
			String[] locales = null;
			StringBuilder[] contents = null;
			String line = null;
			String[] subs = null;
			File sourceFile = new File(sourceFilePath);
			reader = new BufferedReader(new FileReader(sourceFile));
			while ((line = reader.readLine()) != null) {
				cnt++;
				if (cnt == 1) {
					separator = line.charAt(0);
					if (separator == 0) {
						throw new GeneratorException(
								"First line of the source file should begin with the separator!");
					}
					if (separator == '|') {
						throw new GeneratorException(
								"\"|\" is reserved! Please choose another character.");
					}
					subs = line.split(new String(new char[] { separator }));
					if (subs.length < 3) {
						throw new GeneratorException(
								"Source file is invalid! First line should conatin following information: #(the separator itself)WebMessages(output file name)#properties(output file extension)#en(locale1)#bg(locale2)#nn(localeN)");
					}
					targetFileName = subs[1];
					targetFileExtension = "." + subs[2];
					locales = new String[subs.length - 3];
					for (int i = 3; i < subs.length; i++) {
						locales[i - 3] = "_" + subs[i];
					}
					contents = new StringBuilder[locales.length];
				} else {
					writeLine(separator, locales, contents, line, cnt);
				}
			}

			for (int x = 0; x < locales.length; x++) {
				if (contents[x].length() > 0) {
					writeFile(contents[x], destinationDir + "\\" + targetFileName + locales[x]
							+ targetFileExtension);
				}
			}

		} catch (FileNotFoundException e) {
			throw new GeneratorException("File not found: " + destinationDir);
		} catch (IOException e) {
			throw new GeneratorException("I/O Problem with: " + destinationDir);
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					throw new GeneratorException("I/O Problem with the file reader.");
				}
			}
		}
	}

	private static void writeLine(char separator, String[] locales, StringBuilder[] contents,
			String line, int cnt) throws GeneratorException {
		String[] subs;

		if (line.startsWith(PROP_FILE_COMMENT_CHAR)) {
			for (int x = 0; x < locales.length; x++) {
				contents[x] = appendLine(contents[x], line);
			}
		} else {
			if (line.indexOf(separator) > 0) {
				subs = line.split(new String(new char[] { separator }));
				int wordCount = locales.length + 1;
				if (subs.length != wordCount) {
					throw new GeneratorException(
							"Error on the line: "
									+ cnt
									+ ". The Line should contain exact "
									+ wordCount
									+ " words, separated by \""
									+ separator
									+ "\". First one is the keyword, followed by translation for each locale in appropriate order, determinated on the first line.");
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

	private static void writeFile(StringBuilder builder, String fileName) throws GeneratorException {
		Writer output = null;
		try {
			output = new BufferedWriter(new FileWriter(new File(fileName)));
			output.write(builder.toString());
			output.flush();
			System.out.println("File " + fileName + " generated!");
		} catch (IOException e) {
			throw new GeneratorException("I/O Probelm with: " + fileName);
		} finally {
			try {
				if (output != null) {
					output.close();
				}
			} catch (IOException e) {
				throw new GeneratorException("I/O Problems with our writer! :D");
			}
		}
	}

	/**
	 * @param unicodeString
	 * @return String
	 */
	@SuppressWarnings("cast")
	private static String convertToHexString(String unicodeString) {
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
		System.out.println(unicodeString + "->" + output.toString());
		return output.toString();
	}
}
