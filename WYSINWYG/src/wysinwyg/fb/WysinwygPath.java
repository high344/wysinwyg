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
package wysinwyg.fb;

import java.io.File;

import wysinwyg.utils.ErrorMessage;

/**
 * 
 * @author FelfoldiB.
 *
 */
public class WysinwygPath {

	private static String os;
	private static String arch;
	private static File home;

	static {
		os = System.getProperty("wysinwyg.os");
		arch = System.getProperty("wysinwyg.arch");
		String home = System.getProperty("wysinwyg.home");

		if (os == null) {
			os = System.getProperty("os.name");
		}
		os = os.toLowerCase();

		if (arch == null) {
			if (System.getProperty("java.vm.name").contains("64-Bit")) {
				arch = "64";
			} else {
				arch = "32";
			}
		}

		if (os.contains("win")) {
			os = "win";
		} else if (os.contains("linux")) {
			os = "linux";
		}

		if (os.equals("win")) {
			if (arch.equals("32")) {
				arch = "x86";
			} else if (arch.equals("64")) {
				arch = "x64";
			}
		} else if (os.equals("linux")) {
			if (arch.equals("32")) {
				arch = "i386";
			} else if (arch.equals("64")) {
				arch = "x86_64";
			}
		}

		boolean debug = Boolean.parseBoolean(System.getProperty("wysinwyg.debug"));

		if (home == null) {
			WysinwygPath.home = new File(System.getProperty("user.home") + File.separator + "wysinwyg");
			if (!WysinwygPath.home.exists() && !WysinwygPath.home.mkdir()) {
				ErrorMessage.show(
						new WysinwygException("Error while creating path: " + WysinwygPath.home.getAbsolutePath()),
						debug);
				System.exit(1);
			}
		} else {
			WysinwygPath.home = new File(home);
			if (!WysinwygPath.home.exists()) {
				ErrorMessage.show(
						new WysinwygException("Specified path doesn't exists: " + WysinwygPath.home.getAbsolutePath()),
						debug);
				System.exit(1);
			}
		}

		if (os == null) {
			ErrorMessage.show(new WysinwygException("Current Operating System is not supported!"), debug);
			System.exit(1);
		}

		if (arch == null) {
			ErrorMessage.show(
					new WysinwygException("Current Operating System Architecture is not suppoerted!"), debug);
			System.exit(1);
		}
	}

	/**
	 * 
	 * @return
	 */
	public static String getOS() {
		return os;
	}

	/**
	 * 
	 * @return
	 */
	public static String getArch() {
		return arch;
	}

	/**
	 * 
	 * @return
	 */
	public static String getHome() {
		return home.getAbsolutePath();
	}

	private WysinwygPath() {

	}

}
