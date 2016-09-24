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
package wysinwyg.fb.translator;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import wysinwyg.fb.WysinwygPath;
import wysinwyg.fb.translator.plover.PloverTranslator;
import wysinwyg.fw.translator.Translator;
import wysinwyg.utils.ErrorMessage;
import wysinwyg.utils.PropertiesUtils;

public class Translators {

	public static final File DEFAULT_TRANSLATORS_file;

	static {
		DEFAULT_TRANSLATORS_file = new File(WysinwygPath.getHome() + File.separator + "translators.properties");
		if (!DEFAULT_TRANSLATORS_file.exists()) {

			Properties prop = new Properties();
			prop.put("translator1", PloverTranslator.class.getName());
			try {
				PropertiesUtils
						.saveProperties(
								prop,
								"This file can be edited freely by adding a unique key (name is not important) and a translator implementing the \"wysinwyg.fw.translator.Translator\" interface.",
								DEFAULT_TRANSLATORS_file);
			} catch (IOException e) {
				ErrorMessage.show(e, Boolean.parseBoolean(System.getProperty("wysinwyg.translators")));
			}
		}
	}

	private File propertiesFile;

	public Translators() {
		this(DEFAULT_TRANSLATORS_file);
	}

	public Translators(File propertiesFile) {
		this.propertiesFile = propertiesFile;
	}

	public List<Translator> readUpTranslators() {
		return PropertiesUtils.<Translator> readUpObjects(propertiesFile,
				Boolean.parseBoolean(System.getProperty("wysinwyg.translators")));
	}

}
