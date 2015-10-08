package wysinwyg.translator.dictionary;

import java.util.Map;
import java.util.TreeMap;

public interface Dictionary {
	
	public String getPath();
	public void loadDictionary();
	public void saveDictionary();
	public boolean addTranslation(String strokes, String translation);
	public boolean changeTranslation(String strokes, String translation);
	public boolean removeTranslation(String strokes);
	public Map<String, String> getTranslation(String strokes);
	public String[] getStroke(String translation);
	
	public String findLongestFittingStroke(String stroke, String allElements);
	public String getLongestTranslation(String stroke, String fittedStroke);
	public int getLongestFitStroke();
	
}
