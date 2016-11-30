package wysinwyg.fw.translator.dictionary;

import java.util.AbstractMap.SimpleEntry;
import java.util.List;

public class DListController {
	
	private DListView view;
	
	public DListController(DictionaryOptions options, Dictionary d) {
		List<SimpleEntry<String, String>> l = options.loadDictionary(d);
		if(l != null) {
			((DListTableModel) view.getTable().getModel()).update(l);
		}
		view = new DListView();
		view.setVisible(true);
	}
	
}
