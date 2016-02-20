/*******************************************************************************
 * Copyright (c) 2015 Balazs Felfoldi.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/old-licenses/gpl-2.0.html
 * 
 * Contributors:
 *     Balazs Felfoldi - initial API and implementation
 ******************************************************************************/
package wysinwyg.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * 
 * @author FelfoldiB.
 *
 */
public class JarUtils {

	/**
	 * 
	 * @param jarFile
	 * @param extension
	 * @return
	 * @throws IOException
	 */
	public static List<String> listJarFileContents(File jarFile, String extension) throws IOException {
		JarFile jar = new JarFile(jarFile);
		try {
			Enumeration<JarEntry> en = jar.entries();
			List<String> list = new ArrayList<String>();
			while (en.hasMoreElements()) {
				if (extension == null || en.nextElement().getName().endsWith(extension)) {
					list.add(en.nextElement().getName());
				}
			}
			return list;
		} finally {
			jar.close();
		}
	}

	/**
	 * 
	 * @param jarFile
	 * @param filename
	 * @return
	 * @throws IOException
	 */
	public static List<String> isFileInJar(File jarFile, String filename) throws IOException {
		JarFile jar = new JarFile(jarFile);
		try {
			Enumeration<JarEntry> en = jar.entries();
			List<String> list = new ArrayList<String>();
			while (en.hasMoreElements()) {
				JarEntry entry = (JarEntry) en.nextElement();
				if (entry.getName().endsWith(filename)) {
					list.add(en.nextElement().getName());
				}
			}
			return list;
		} finally {
			jar.close();
		}
	}

	/**
	 * 
	 * @param jarFile
	 * @param sourceName
	 *            rxtx/win/x64/, rxtx/win/x64/rxtxParallel.dll
	 * @param targetDirectoy
	 * @throws IOException
	 */
	public static void copyJarFileContent(File jarFile, String sourceName, File targetDirectoy) throws IOException {
		if (!targetDirectoy.isDirectory()) {
			throw new IOException("target is not a directory or doesn't exists!");
		}
		JarFile jar = new JarFile(jarFile);
		try {
			JarEntry entry = jar.getJarEntry(sourceName);
			if (entry != null) {
				if (entry.isDirectory()) {
					Enumeration<JarEntry> en = jar.entries();
					while (en.hasMoreElements()) {
						JarEntry entry2 = (JarEntry) en.nextElement();
						if (!entry2.isDirectory() && entry2.getName().startsWith(sourceName)
								&& (entry2.getName().indexOf("/", sourceName.length()) == -1)) {
							String name = entry2.getName().substring(entry2.getName().lastIndexOf("/"),
									entry2.getName().length());
							File f = new File(targetDirectoy + File.separator + name);
							Files.copy(jar.getInputStream(entry2), f.toPath(), StandardCopyOption.REPLACE_EXISTING);
						}
					}

				} else {
					String name = entry.getName().substring(entry.getName().lastIndexOf("/"), entry.getName().length());
					File f = new File(targetDirectoy + File.separator + name);
					Files.copy(jar.getInputStream(entry), f.toPath(), StandardCopyOption.REPLACE_EXISTING);
				}
			}
		} finally {
			jar.close();
		}
	}

	private JarUtils() {

	}

}
