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
