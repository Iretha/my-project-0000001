/**
 * 
 */
package com.mbd.tools.other;

import java.io.File;

/**
 * @author М
 * 
 */
public class DeleteDir {

	public static void main(String args[]) {
		boolean deleted = deleteDirectory(new File(
				"D:\\Programming\\geronimo-tomcat6-javaee5-2.2.1\\repository\\default"));
		System.out.println(deleted);
	}

	static public boolean deleteDirectory(File path) {
		if (path.exists()) {
			File[] files = path.listFiles();
			for (int i = 0; i < files.length; i++) {
				if (files[i].isDirectory()) {
					deleteDirectory(files[i]);
				} else {
					files[i].delete();
				}
			}
		}
		return (path.delete());
	}
}
