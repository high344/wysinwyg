package wysinwyg.translator.plover;

import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import wysinwyg.translator.dictionary.Dictionary;

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
			if(entries == null) {
				entries = new TreeMap<String, Map<String, String>>();
			}
			new JSONParser().parse(reader, new PloverJSonHandler(entries));
		} catch (IOException | ParseException e) {
			// TODO Auto-generated catch block
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
