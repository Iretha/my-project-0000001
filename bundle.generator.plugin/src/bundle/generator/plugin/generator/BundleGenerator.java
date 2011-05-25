/**
 * TODO
 */
package bundle.generator.plugin.generator;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * @author MBD
 * 
 */
public class BundleGenerator {

	public static final String SOURCE_FILE_EXTENSION = "txt";

	public static final String FILE_EXTENSION_SEPARATOR = ".";

	public static final String LOCALE_SEPARATOR = "_";

	private static final String PROP_FILE_COMMENT_CHAR = "#";

	private static final String FILE_ENCODING = "UTF-8";

	public static void generateBundleFiles(String destinationDir, String sourceFilePath)
			throws GeneratorException {
		BufferedReader reader = null;
		InputStreamReader inputStream = null;
		FileInputStream fileInput = null;
		try {
			char separator = 0;
			int cnt = 0;
			String targetFileName = null;
			String targetFileExtension = null;
			String[] locales = null;
			StringBuilder[] contents = null;
			String line = null;
			String[] subs = null;
			fileInput = new FileInputStream(new File(sourceFilePath));
			inputStream = new InputStreamReader(fileInput, FILE_ENCODING);
			reader = new BufferedReader(inputStream);
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
								"\"|\" is reserved! Please choose another character!");
					}
					subs = line.split(new String(new char[] { separator }));
					if (subs.length < 3) {
						throw new GeneratorException(
								"Source file is not valid! First line should contain following information: #(the separator itself)WebMessages(output file name)#properties(output file extension)#en(locale1)#bg(locale2)#nn(localeN)");
					}
					targetFileName = subs[1];
					targetFileExtension = FILE_EXTENSION_SEPARATOR + subs[2];
					locales = new String[subs.length - 3];
					for (int i = 3; i < subs.length; i++) {
						locales[i - 3] = LOCALE_SEPARATOR + subs[i];
					}
					contents = new StringBuilder[locales.length];
				} else {
					writeLine(separator, locales, contents, line, cnt);
				}
			}

			for (int x = 0; x < locales.length; x++) {
				if (contents[x].length() > 0) {
					writeFile(contents[x], destinationDir + File.separatorChar + targetFileName
							+ locales[x] + targetFileExtension);
				}
			}

		} catch (FileNotFoundException e) {
			throw new GeneratorException("File not found: " + destinationDir);
		} catch (IOException e) {
			throw new GeneratorException("I/O Problem with: " + destinationDir);
		} finally {
			try {
				if (reader != null) {
					reader.close();
				}
				if (inputStream != null) {
					inputStream.close();
				}
				if (fileInput != null) {
					fileInput.close();
				}
			} catch (IOException e) {
				throw new GeneratorException("I/O Problem with the file reader.");
			}
		}
	}

	private static void writeLine(char separator, String[] locales, StringBuilder[] contents,
			String line, int cnt) throws GeneratorException {
		if (line != null && !line.startsWith(PROP_FILE_COMMENT_CHAR) && line.indexOf(separator) > 0) {
			String[] subs = line.split(new String(new char[] { separator }));
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
					contents[x] = appendLine(contents[x], subs[0] + "="
							+ convertToHexString(subs[x + 1]));
				}
			}
		} else {
			for (int x = 0; x < locales.length; x++) {
				contents[x] = appendLine(contents[x], PROP_FILE_COMMENT_CHAR + line);
			}
		}
	}

	private static StringBuilder appendLine(StringBuilder builder, String line) {
		if (builder == null) {
			builder = new StringBuilder();
		}
		if (line == null) {
			builder.append("");
		} else {
			builder.append(line.trim());
		}
		builder.append(System.getProperty("line.separator"));
		return builder;
	}

	private static void writeFile(StringBuilder builder, String fileName) throws GeneratorException {
		BufferedWriter output = null;
		OutputStreamWriter outStream = null;
		FileOutputStream fileOutStream = null;
		try {
			fileOutStream = new FileOutputStream(new File(fileName));
			outStream = new OutputStreamWriter(fileOutStream, FILE_ENCODING);
			output = new BufferedWriter(outStream);
			output.write(builder.toString());
			output.flush();
		} catch (IOException e) {
			throw new GeneratorException("I/O Probelm with: " + fileName);
		} finally {
			try {
				if (output != null) {
					output.close();
				}
				if (outStream != null) {
					outStream.close();
				}
				if (fileOutStream != null) {
					fileOutStream.close();
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
		StringBuilder output = new StringBuilder();
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
