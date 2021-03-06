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
package wysinwyg.fb.translator.plover;

import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import wysinwyg.fw.translator.dictionary.Dictionary;

public class PloverJSonDictionary implements Dictionary {

	private TreeMap<String, Map<String, String>> entries;
	private String path;

	public PloverJSonDictionary(String path, TreeMap<String, Map<String, String>> entries) {
		this.path = path;
		this.entries = entries;
	}

	@Override
	public String getPath() {
		return path;
	}

	@Override
	public void loadDictionary() {
		if (path == null) {
			return;
		}
		try {
			FileReader reader = new FileReader(path);
			if (entries == null) {
				entries = new TreeMap<String, Map<String, String>>();
			}
			new JSONParser().parse(reader, new PloverJSonHandler(entries));
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void saveDictionary() {
		// TODO Auto-generated method stub
	};

	@Override
	public boolean addTranslation(String strokes, String translation) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean changeTranslation(String strokes, String translation) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeTranslation(String strokes) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Map<String, String> getTranslation(String stroke) {
		if (stroke != null) {
			return entries.get(stroke);
		}
		return null;
	}

	public TreeMap<String, Map<String, String>> getDictionary() {
		return entries;
	}

}
