package com.mbd.tools.geronimo;

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

import com.mbd.tools.prop.manager.PropertiesManager;

public class GeronimoProjectCleaner {

	/**
	 * Конфигурационен файл на Geronimo
	 */
	private static final String GERONIMO_CONFIG_FILE = "var\\config\\config.xml";

	private static final String GERONIMO_HOME = "GERONIMO_HOME";

	private static final String GERONIMO_APP_DIR = "repository";

	private static final String GERONIMO_WEB_APP_DIR = "default";

	private static final String GERONIMO_CONF_MODULE_TAG = "module";

	private static final String GERONIMO_CONF_ATTRIBUTES_TAG = "attributes";

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String geronimoLocation = PropertiesManager.getProperty(
				PropertiesManager.getEnvironmentProperties(), GERONIMO_HOME);
		if (geronimoLocation != null) {
			clearConfigFile(geronimoLocation);
			clearRepositoryDir(geronimoLocation);
		} else {
			System.err.append("Не е открита променлива за " + GERONIMO_HOME + ". /n");
		}
	}

	private static void clearRepositoryDir(String geronimoLocation) {
		boolean smthCleaned = false;
		File repository = new File(geronimoLocation + GERONIMO_APP_DIR + "\\"
				+ GERONIMO_WEB_APP_DIR);
		System.out.println("===================================");
		System.out.println("Trying to clean  " + repository.getAbsolutePath() + " ...");
		if (repository.exists() && repository.isDirectory()) {
			File[] contents = repository.listFiles();
			if (contents.length > 0) {
				System.out.println("Files to be removed " + contents.length);
				for (File f : contents) {
					if (f.delete()) {
						smthCleaned = true;
					}
				}
			} else {
				System.out.println("Nothing to remove...");
			}
		}
		if (!smthCleaned) {
			System.out.println("Nothing removed...");
		}
	}

	private static void clearConfigFile(String geronimoLocation) {
		System.out.println("===================================");
		File config = new File(geronimoLocation + GERONIMO_CONFIG_FILE);
		System.out.println("Looking for modules in " + config.getAbsolutePath() + " ...");
		boolean smthFound = false;
		if (config.exists()) {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = null;
			Document confDoc = null;
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

				if (smthFound) {
					serializeConfiguration(config, confDoc);
				} else {
					System.out.println("No modules found!");
				}

			} catch (ParserConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SAXException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	/**
	 * Сериализира текущата конфигурация в подадения файл.
	 * 
	 * @param targetFile
	 *            - файла в който да се сериализира конфигурацията
	 * 
	 * @throws IOException
	 *             при неуспешна сериализация
	 */
	private static void serializeConfiguration(File targetFile, Document doc) throws IOException {
		Writer writer = new FileWriter(targetFile);
		OutputFormat format = new OutputFormat();
		format.setIndenting(true);
		XMLSerializer serializer = new XMLSerializer(writer, format);
		serializer.serialize(doc);
		System.out.println("File " + targetFile.getAbsolutePath() + " saved!");
	}

}
