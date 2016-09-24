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
package wysinwyg.fb.evaluator;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import wysinwyg.fb.WysinwygPath;
import wysinwyg.fb.evaluator.steno.StenoEvaluator;
import wysinwyg.fw.evaluator.Evaluator;
import wysinwyg.utils.ErrorMessage;
import wysinwyg.utils.PropertiesUtils;

public class Evaluators {

	public static final File DEFAULT_EVAULATORS_file;

	static {
		DEFAULT_EVAULATORS_file = new File(WysinwygPath.getHome() + File.separator + "evaulators.properties");
		if (!DEFAULT_EVAULATORS_file.exists()) {

			Properties prop = new Properties();
			prop.put("evaulator1", StenoEvaluator.class.getName());
			try {
				PropertiesUtils
						.saveProperties(
								prop,
								"This file can be edited freely by adding a unique key (name is not important) and an evaulator implementing the \"wysinwyg.fw.evaluator.Evaluator\" interface.",
								DEFAULT_EVAULATORS_file);
			} catch (IOException e) {
				ErrorMessage.show(e, Boolean.parseBoolean(System.getProperty("wysinwyg.evaluators")));
			}
		}
	}

	private File propertiesFile;

	public Evaluators() {
		this(DEFAULT_EVAULATORS_file);
	}

	public Evaluators(File propertiesFile) {
		this.propertiesFile = propertiesFile;
	}

	public List<Evaluator> readUpEvaulators() {
		return PropertiesUtils.<Evaluator> readUpObjects(propertiesFile,
				Boolean.parseBoolean(System.getProperty("wysinwyg.evaluators")));
	}

}
