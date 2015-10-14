package wysinwyg.translator.plover;

import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

import org.json.simple.parser.ContentHandler;
import org.json.simple.parser.ParseException;

public class PloverJSonHandler implements ContentHandler {

	private TreeMap<String, Map<String, String>> entries;
	private String entry;

	public PloverJSonHandler(TreeMap<String, Map<String, String>> entries) {
		this.entries = entries;
	}

	@Override
	public boolean startObjectEntry(String entry) throws ParseException, IOException {
		this.entry = entry;
		for (String str : entry.split("/")) {
			if (entries.containsKey(str)) {
				entries.get(str).put(entry, null);
			} else {
				TreeMap<String, String> tr = new TreeMap<String, String>();
				tr.put(entry, null);
				entries.put(str, tr);
			}
		}
		return true;
	}

	@Override
	public boolean primitive(Object element) throws ParseException, IOException {
		for (String str : entry.split("/")) {
			entries.get(str).put(entry, (String) element);
		}
		return true;
	}

	@Override
	public boolean endObjectEntry() throws ParseException, IOException {
		return true;
	}

	@Override
	public void startJSON() throws ParseException, IOException {
	}

	@Override
	public void endJSON() throws ParseException, IOException {
	}

	@Override
	public boolean startObject() throws ParseException, IOException {
		return true;
	}

	@Override
	public boolean endObject() throws ParseException, IOException {
		return true;
	}

	@Override
	public boolean startArray() throws ParseException, IOException {
		return true;
	}

	@Override
	public boolean endArray() throws ParseException, IOException {
		return true;
	}

}
