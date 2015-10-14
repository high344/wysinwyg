package wysinwyg.translator.dictionary;

import java.util.Map;

public interface Dictionary {

	public String getPath();

	public void loadDictionary();

	public void saveDictionary();

	public boolean addTranslation(String strokes, String translation);

	public boolean changeTranslation(String strokes, String translation);

	public boolean removeTranslation(String strokes);

	public Map<String, String> getTranslation(String strokes);

}
