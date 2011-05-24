package geronimo.cleaner;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.xml.serialize.OutputFormat;
import org.apache.xml.serialize.XMLSerializer;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * 
 * @author MBD
 * 
 */
@SuppressWarnings("deprecation")
public class GeronimoProjectCleaner {

	/**
	 * Конфигурационен файл на Geronimo
	 */
	private static final String GERONIMO_CONFIG_FILE = "\\var\\config\\config.xml";

	public static final String GERONIMO_HOME = "GERONIMO_HOME";

	private static final String GERONIMO_APP_DIR = "repository";

	private static final String GERONIMO_WEB_APP_DIR = "default";

	private static final String GERONIMO_CONF_MODULE_TAG = "module";

	private static final String GERONIMO_CONF_ATTRIBUTES_TAG = "attributes";

	private static final String FILE_PATH_SEPARATOR = "\\";

	public static boolean clearRepositoryDir(String geronimoLocation) throws CleanerException {
		File repository = new File(geronimoLocation + FILE_PATH_SEPARATOR + GERONIMO_APP_DIR
				+ FILE_PATH_SEPARATOR + GERONIMO_WEB_APP_DIR);
		return deleteDirectory(repository);
	}

	private static boolean deleteDirectory(File file) throws CleanerException {
		if (file.exists()) {
			File[] files = file.listFiles();
			for (int i = 0; i < files.length; i++) {
				if (files[i].isDirectory()) {
					deleteDirectory(files[i]);
				} else {
					files[i].delete();
				}
			}
		} else {
			throw new CleanerException("File does not exists: " + file.getAbsolutePath());
		}
		return (file.delete());
	}

	public static boolean clearConfigFile(String geronimoLocation) throws CleanerException {
		File config = new File(geronimoLocation + GERONIMO_CONFIG_FILE);
		boolean smthFound = false;
		DocumentBuilder docBuilder = null;
		Document confDoc = null;
		if (config.exists()) {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			try {
				docBuilder = dbf.newDocumentBuilder();
				confDoc = docBuilder.parse(config);
				NodeList attributes = confDoc.getElementsByTagName(GERONIMO_CONF_ATTRIBUTES_TAG);
				Node currAttribute = null;
				NodeList children = null;
				Node currChild = null;
				Node moduleChild = null;
				for (int i = 0; i < attributes.getLength(); i++) {
					currAttribute = attributes.item(i);
					children = currAttribute.getChildNodes();
					for (int y = 0; y < children.getLength(); y++) {
						currChild = children.item(y);
						if (currChild.getNodeName().equals(GERONIMO_CONF_MODULE_TAG)) {
							if (currChild.hasAttributes()) {
								moduleChild = currChild.getAttributes().getNamedItem("name");
								moduleChild.getParentNode();
								if (moduleChild.getNodeValue().startsWith(GERONIMO_WEB_APP_DIR)) {
									currAttribute.removeChild(currChild);
									smthFound = true;
								}
							}
						}
					}
				}
			} catch (ParserConfigurationException e) {
				throw new CleanerException(e);
			} catch (SAXException e) {
				throw new CleanerException(e);
			} catch (IOException e) {
				throw new CleanerException(e);
			}
		} else {
			throw new CleanerException("File does not exists: " + config.getAbsolutePath());
		}

		if (smthFound) {
			serializeConfiguration(config, confDoc);
		}

		return smthFound;
	}

	/**
	 * Сериализира текущата конфигурация в подадения файл.
	 * 
	 * @param targetFile
	 *            - файла в който да се сериализира конфигурацията
	 * @throws CleanerException
	 * 
	 * @throws IOException
	 *             при неуспешна сериализация
	 */
	private static void serializeConfiguration(File targetFile, Document doc)
			throws CleanerException {
		Writer writer;
		try {
			writer = new FileWriter(targetFile);
			OutputFormat format = new OutputFormat();
			format.setIndenting(true);
			XMLSerializer serializer = new XMLSerializer(writer, format);
			serializer.serialize(doc);
		} catch (IOException e) {
			throw new CleanerException(e);
		}
	}
}
