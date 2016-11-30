package wysinwyg.fw.translator.dictionary;

import java.util.List;
import java.util.AbstractMap.SimpleEntry;

import javax.swing.table.AbstractTableModel;

public class DListTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 6807765279091307561L;

	private List<SimpleEntry<String, String>> list;

	public void update(List<SimpleEntry<String, String>> list) {
		this.list = list;
		fireTableDataChanged();
	}

	@Override
	public String getColumnName(int column) {
		if (column == 0) {
			return "Key";
		} else if (column == 1) {
			return "Value";
		}
		return super.getColumnName(column);
	}

	@Override
	public int getRowCount() {
		if (list != null) {
			return list.size();
		}
		return 0;
	}

	@Override
	public int getColumnCount() {
		return 2;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		if (columnIndex == 0) {
			return list.get(rowIndex).getKey();
		} else if (columnIndex == 1) {
			return list.get(rowIndex).getValue();
		}
		return null;
	}

}
