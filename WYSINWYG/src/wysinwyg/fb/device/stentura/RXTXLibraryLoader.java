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
package wysinwyg.fb.device.stentura;

import java.io.File;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.Arrays;

import wysinwyg.fb.WysinwygPath;
import wysinwyg.utils.JarUtils;

/**
 * 
 * @author FelfoldiB.
 *
 */
class RXTXLibraryLoader {

	private static boolean success = false;

	private RXTXLibraryLoader() {

	}

	/**
	 * 
	 * @throws Exception
	 */
	static synchronized void load() throws Exception {
		if (!success) {
			URL url = RXTXLibraryLoader.class.getResource("/rxtx" + "/" + WysinwygPath.getOS() + "/"
					+ WysinwygPath.getArch());
			if (url != null) {
				Field USR_PATHS = ClassLoader.class.getDeclaredField("usr_paths");
				USR_PATHS.setAccessible(true);
				if (url.toString().startsWith("jar")) {
					File f = new File(WysinwygPath.getHome() + File.separator + "rxtx" + File.separator
							+ WysinwygPath.getOS() + File.separator + WysinwygPath.getArch());
					f.mkdirs();
					URL url2 = RXTXLibraryLoader.class.getProtectionDomain().getCodeSource().getLocation();
					File f2 = new File(url2.getFile());
					JarUtils.copyJarFileContent(f2, "rxtx" + "/" + WysinwygPath.getOS() + "/" + WysinwygPath.getArch()
							+ "/", f);
					addLibraryPath(USR_PATHS, f.getPath());
				} else {
					addLibraryPath(USR_PATHS, url.getPath());
				}
				success = true;
			}
		}
	}

	/**
	 * 
	 * @param path
	 * @throws Exception
	 */
	private static void addLibraryPath(Field field, String path) throws Exception {
		String[] paths = (String[]) field.get(null);

		for (String p : paths) {
			if (p.equals(path)) {
				return;
			}
		}

		String[] newPaths = Arrays.copyOf(paths, paths.length + 1);
		newPaths[newPaths.length - 1] = path;
		field.set(null, newPaths);
	}

}
