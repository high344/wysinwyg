package wysinwyg.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
		OutputStream out = new FileOutputStream(f);

		try {
			prop.store(out, null);
		} catch (IOException e) {
			throw new IOException(e);
		} finally {
			out.close();
		}
	}

}
