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

@SuppressWarnings("deprecation")
public class GeronimoProjectCleaner {

	/**
	 * Конфигурационен файл на Geronimo
	 */
	private static final String GERONIMO_CONFIG_FILE = "var\\config\\config.xml";

	public static final String GERONIMO_HOME = "GERONIMO_HOME";

	private static final String GERONIMO_APP_DIR = "repository";

	private static final String GERONIMO_WEB_APP_DIR = "default";

	private static final String GERONIMO_CONF_MODULE_TAG = "module";

	private static final String GERONIMO_CONF_ATTRIBUTES_TAG = "attributes";

	public static void clearRepositoryDir(String geronimoLocation) {
		File repository = new File(geronimoLocation + GERONIMO_APP_DIR + "\\"
				+ GERONIMO_WEB_APP_DIR);
		if (repository.exists() && repository.isDirectory()) {
			File[] contents = repository.listFiles();
			if (contents.length > 0) {
				for (File f : contents) {
					f.delete();
				}
			}
		}
	}

	public static void clearConfigFile(String geronimoLocation) throws CleanerException {
		System.out.println("===================================");
		File config = new File(geronimoLocation + GERONIMO_CONFIG_FILE);
		System.out.println("Looking for modules in " + config.getAbsolutePath() + " ...");
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
									System.out.println("Removed " + moduleChild.getNodeValue());
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
		}

		if (smthFound) {
			serializeConfiguration(config, confDoc);
		}
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
