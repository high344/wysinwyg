package wysinwyg.fw.translator.dictionary;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import wysinwyg.utils.FileFilter;
import wysinwyg.utils.TestFrame;

public class DictionarySimulator implements DictionaryOptions {

	public static void main(String[] args) {
		DictionarySimulator sim = new DictionarySimulator();
		new TestFrame(new DictionaryBuilder(sim).build().getView());
	}

	private List<Dictionary> list = new ArrayList<Dictionary>();
	private FileFilter ff = new FileFilter("json");

	public DictionarySimulator() {
		Dictionary d1 = new Dictionary() {

			@Override
			public void saveDictionary() {
				// TODO Auto-generated method stub

			}

			@Override
			public boolean removeTranslation(String strokes) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public void loadDictionary() {
				// TODO Auto-generated method stub

			}

			@Override
			public Map<String, String> getTranslation(String strokes) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public String getPath() {
				// TODO Auto-generated method stub
				return "d1";
			}

			@Override
			public boolean changeTranslation(String strokes, String translation) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean addTranslation(String strokes, String translation) {
				// TODO Auto-generated method stub
				return false;
			}
		};
		Dictionary d2 = new Dictionary() {

			@Override
			public void saveDictionary() {
				// TODO Auto-generated method stub

			}

			@Override
			public boolean removeTranslation(String strokes) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public void loadDictionary() {
				// TODO Auto-generated method stub

			}

			@Override
			public Map<String, String> getTranslation(String strokes) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public String getPath() {
				// TODO Auto-generated method stub
				return "d2";
			}

			@Override
			public boolean changeTranslation(String strokes, String translation) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean addTranslation(String strokes, String translation) {
				// TODO Auto-generated method stub
				return false;
			}
		};
		Dictionary d3 = new Dictionary() {

			@Override
			public void saveDictionary() {
				// TODO Auto-generated method stub

			}

			@Override
			public boolean removeTranslation(String strokes) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public void loadDictionary() {
				// TODO Auto-generated method stub

			}

			@Override
			public Map<String, String> getTranslation(String strokes) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public String getPath() {
				// TODO Auto-generated method stub
				return "d3";
			}

			@Override
			public boolean changeTranslation(String strokes, String translation) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean addTranslation(String strokes, String translation) {
				// TODO Auto-generated method stub
				return false;
			}
		};
		Dictionary d4 = new Dictionary() {

			@Override
			public void saveDictionary() {
				// TODO Auto-generated method stub

			}

			@Override
			public boolean removeTranslation(String strokes) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public void loadDictionary() {
				// TODO Auto-generated method stub

			}

			@Override
			public Map<String, String> getTranslation(String strokes) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public String getPath() {
				// TODO Auto-generated method stub
				return "d4";
			}

			@Override
			public boolean changeTranslation(String strokes, String translation) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean addTranslation(String strokes, String translation) {
				// TODO Auto-generated method stub
				return false;
			}
		};
		Dictionary d5 = new Dictionary() {

			@Override
			public void saveDictionary() {
				// TODO Auto-generated method stub

			}

			@Override
			public boolean removeTranslation(String strokes) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public void loadDictionary() {
				// TODO Auto-generated method stub

			}

			@Override
			public Map<String, String> getTranslation(String strokes) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public String getPath() {
				// TODO Auto-generated method stub
				return "d5";
			}

			@Override
			public boolean changeTranslation(String strokes, String translation) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean addTranslation(String strokes, String translation) {
				// TODO Auto-generated method stub
				return false;
			}
		};
		list.add(d1);
		list.add(d2);
		list.add(d3);
		list.add(d4);
		list.add(d5);
	}

	@Override
	public List<Dictionary> getDictionaries() {
		return list;
	}

	@Override
	public FileFilter getDictionaryFileFilter() {
		return ff;
	}

	@Override
	public Dictionary initDictironay(File f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, String> loadDictionary(Dictionary dictionary) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean saveDictionary(Dictionary dictionary) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addDictionaryEntry(Dictionary dictionary, String key, String value) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeDictionaryEntry(Dictionary dictionary, String key, String value) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void dictionaryOrderChanged(List<Dictionary> dictionaries) {
		// TODO Auto-generated method stub

	}

}
