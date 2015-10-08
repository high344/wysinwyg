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
	private int longestStroke = 0;
	
	public static void main(String[] args) {
		PloverJSonDictionary dict = new PloverJSonDictionary("/resources/dict.json", null);
		dict.loadDictionary();
	}
	
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
			FileReader reader = new FileReader(getClass().getResource(path)
					.getPath());
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

	@Override
	public String[] getStroke(String translation) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public String findLongestFittingStroke(String lastElement, String allElement) {
		// System.out.println("last: "+lastElement+" all:"+allElement);
		Map<String, String> z = entries.get(lastElement);
		if (z == null) {
			return null;
		}
		String trans = allElement;
		do {
			if (z.containsKey(trans)) {
				// System.out.println("found: "+trans);
				break;
			}
			String[] temp = trans.split("/", 2);
			longestStroke++;
			if (temp.length == 2) {
				trans = temp[1];
			} else {
				longestStroke = 0;
				return lastElement;
			}
		} while (true);
		return trans;
	}
	
	@Override
	public String getLongestTranslation(String lastElement, String longestStroke) {
		Map<String, String> z = entries.get(lastElement);
		return z.get(longestStroke);
	}

	@Override
	public int getLongestFitStroke() {
		int temp = longestStroke;
		this.longestStroke = 0;
		return temp;
	}

	public TreeMap<String, Map<String, String>> getDictionary() {
		return entries;
	}

}
