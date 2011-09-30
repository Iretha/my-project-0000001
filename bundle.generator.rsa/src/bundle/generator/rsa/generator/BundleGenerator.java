package bundle.generator.rsa.generator;

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
 */
public class BundleGenerator {

	/**
	 * Source file extension
	 */
	public static final String SOURCE_FILE_EXTENSION = "txt";

	private static final String FILE_ENCODING = "UTF-8";

	private static final String FILE_EXTENSION_SEPARATOR = ".";

	private static final String LOCALE_SEPARATOR = "_";

	private static final String PROP_FILE_COMMENT_CHAR = "#";

	/**
	 * @param destinationDir
	 *            - директория, където трябва да се съхрани файла
	 * @param sourceFilePath
	 *            - сорс
	 * @throws GeneratorException
	 *             - грешка
	 */
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
			StringBuilder keys = null;
			String enumFilePath = null;
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
					if (subs.length < 5) {
						throw new GeneratorException(
								"Source file is not valid! First line should contain following information: #(the separator itself)WebMessages(output file name)#properties(output file extension)#en(locale1)#bg(locale2)#nn(localeN)");
					}
					targetFileName = subs[1];
					targetFileExtension = FILE_EXTENSION_SEPARATOR + subs[2];
					enumFilePath = subs[3];
					locales = new String[subs.length - 4];
					for (int i = 4; i < subs.length; i++) {
						locales[i - 4] = LOCALE_SEPARATOR + subs[i];
					}
					contents = new StringBuilder[locales.length];
				} else {
					keys = writeLine(keys, separator, locales, contents, line, cnt);
				}
			}

			if (locales != null && contents != null) {
				for (int x = 0; x < locales.length; x++) {
					if (contents[x].length() > 0) {
						writeFile(contents[x], destinationDir + File.separatorChar + targetFileName
								+ locales[x] + targetFileExtension);
					}
				}
			}

			if (keys != null) {
				writeEnumContent(getEnumFullFilePath(destinationDir, enumFilePath), keys);
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

	private static String getEnumFullFilePath(String currDir, String enumFilePath)
			throws GeneratorException {

		if (enumFilePath != null) {

			if (enumFilePath.indexOf("/") > -1) {
				enumFilePath = enumFilePath.replace("/", "\\");
			}
			if (currDir.indexOf("/") > -1) {
				currDir = currDir.replace("/", "\\");
			}

			String srcFolderName = "\\src\\";
			String prjFolderName = currDir.substring(0, currDir.indexOf(srcFolderName));
			String wsFolderName = prjFolderName.substring(0, prjFolderName.lastIndexOf("\\"));
			return wsFolderName + enumFilePath;
		}
		throw new GeneratorException("Missing enum file path!");
	}

	private static StringBuilder addKeyToEnum(StringBuilder addedKeys, String key, String comment) {
		if (addedKeys == null) {
			addedKeys = new StringBuilder();
		}
		addedKeys.append("\t" + "/** " + comment + " */");
		addedKeys.append(System.getProperty("line.separator"));
		addedKeys.append("\t" + key + "(\"" + key + "\"),");
		addedKeys.append(System.getProperty("line.separator"));
		addedKeys.append(System.getProperty("line.separator"));
		return addedKeys;
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

	private static void writeEnumContent(String enumPath, StringBuilder content)
			throws GeneratorException {
		File f = new File(enumPath);
		if (f.exists()) {
			try {

				FileInputStream fileInput = new FileInputStream(f);
				InputStreamReader inputStream = new InputStreamReader(fileInput, FILE_ENCODING);
				BufferedReader reader = new BufferedReader(inputStream);
				String line = null;
				StringBuilder target = new StringBuilder();
				while ((line = reader.readLine()) != null) {
					target.append(line);
					target.append(System.getProperty("line.separator"));
				}

				String header = target.substring(0, target.indexOf("{") + 1);

				String footer = target.substring(target.indexOf("private"), target.length());

				StringBuilder cnt = new StringBuilder();
				cnt.append(header);
				cnt.append(System.getProperty("line.separator"));
				cnt.append(content);
				cnt.append("\t" + ";");
				cnt.append(System.getProperty("line.separator"));
				cnt.append("\t" + footer);

				writeFile(cnt, enumPath);

			} catch (Exception e) {
				throw new GeneratorException("Exception while generating keys enum file "
						+ enumPath + " " + e.getMessage());
			}
		}
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
					outStream.flush();
					outStream.close();
				}
				if (fileOutStream != null) {
					fileOutStream.flush();
					fileOutStream.close();
				}
			} catch (IOException e) {
				throw new GeneratorException("I/O Problems with our writer! :D");
			}
		}
	}

	private static StringBuilder writeLine(StringBuilder addedKeys, char separator,
			String[] locales, StringBuilder[] contents, String line, int cnt)
			throws GeneratorException {
		if (line != null && !line.startsWith(PROP_FILE_COMMENT_CHAR) && line.indexOf(separator) > 0) {
			String[] subs = line.split(new String(new char[] { separator }));
			int wordCount = locales.length + 1;
			if (subs.length == 2 && subs.length != wordCount) {
				// copy to all files
				for (int x = 0; x < locales.length; x++) {
					contents[x] = appendLine(contents[x], subs[0] + "="
							+ convertToHexString(subs[1]));
					if (x == 0) {
						addedKeys = addKeyToEnum(addedKeys, subs[0], subs[1]);
					}
				}
			} else if (subs.length == wordCount) {
				for (int x = 0; x < locales.length; x++) {
					contents[x] = appendLine(contents[x], subs[0] + "="
							+ convertToHexString(subs[x + 1]));
					if (x == 0) {
						addedKeys = addKeyToEnum(addedKeys, subs[0], subs[1]);
					}
				}
			} else {
				throw new GeneratorException(
						"Error on the line: "
								+ cnt
								+ ". The Line should contain exact "
								+ wordCount
								+ " words, separated by \""
								+ separator
								+ "\". First one is the keyword, followed by translation for each locale in appropriate order, determinated on the first line.");
			}

		} else if (line != null && !line.trim().equals("")) {
			for (int x = 0; x < locales.length; x++) {
				contents[x] = appendLine(contents[x], PROP_FILE_COMMENT_CHAR + line);
			}
		}
		return addedKeys;
	}
}
