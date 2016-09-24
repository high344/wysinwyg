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
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Properties;

public class PropertiesUtils {

	public static Properties loadProperties(File f) throws IOException {
		Properties prop = new Properties();
		InputStream in = new FileInputStream(f);

		try {
			prop.load(in);
		} catch (IOException e) {
			throw new IOException(e);
		} finally {
			in.close();
		}

		return prop;
	}

	public static void saveProperties(Properties prop, File f) throws IOException {
		saveProperties(prop, null, f);
	}

	public static void saveProperties(Properties prop, String comments, File f) throws IOException {
		OutputStream out = new FileOutputStream(f);

		try {
			prop.store(out, comments);
		} catch (IOException e) {
			throw new IOException(e);
		} finally {
			out.close();
		}
	}

	@SuppressWarnings("unchecked")
	public static <T> List<T> readUpObjects(File f, boolean debug) {
		Properties prop = null;
		try {
			prop = loadProperties(f);
		} catch (IOException e) {
			ErrorMessage.show(e, debug);
			return null;
		}

		List<T> arr = new ArrayList<T>(prop.size());
		for (Entry<Object, Object> p : prop.entrySet()) {
			try {
				Class<?> c = Class.forName((String) p.getValue());
				// TODO
				arr.add((T) c.newInstance());
			} catch (Exception e) {
				ErrorMessage.show(e, debug);
			}
		}
		return arr;
	}

}
