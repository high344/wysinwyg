/*******************************************************************************
 * Copyright (c) 2015 Balázs Felföldi.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/old-licenses/gpl-2.0.html
 * 
 * Contributors:
 *     Balázs Felföldi - initial API and implementation
 ******************************************************************************/
package wysinwyg.utils;

import java.io.File;

public class FileFilter extends javax.swing.filechooser.FileFilter {

	private String[] supportedFileExtensions;

	public FileFilter(String[] supportedFileExtensions) {
		this.supportedFileExtensions = supportedFileExtensions;
	}

	@Override
	public boolean accept(File f) {
		if (f.isDirectory() || isSupported(f.getPath())) {
			return true;
		}
		return false;
	}

	@Override
	public String getDescription() {
		String s = "";
		for (String st : supportedFileExtensions) {
			s = "*" + st + " ";
		}
		return s;
	}

	public boolean isSupported(String st) {
		for (String str : supportedFileExtensions) {
			if (st.endsWith(str)) {
				return true;
			}
		}
		return false;
	}

}
