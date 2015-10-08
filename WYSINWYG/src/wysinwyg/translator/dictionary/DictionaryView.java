package wysinwyg.translator.dictionary;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;

public class DictionaryView extends JPanel {

	private static final long serialVersionUID = 6536417544245864384L;

	JTable table;
	JButton btnAdd;
	JButton btnRemove;

	private DictionaryModel model;

	public DictionaryView(DictionaryModel model) {
		this.model = model;
		buildGUI();
	}

	private void buildGUI() {
		setBorder(new TitledBorder(null, "Dictionary:", TitledBorder.LEADING, TitledBorder.TOP,
				null, null));
		setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		add(panel, BorderLayout.EAST);
		FlowLayout fl_panel = new FlowLayout(FlowLayout.CENTER, 5, 5);
		panel.setLayout(fl_panel);

		JPanel panel_1 = new JPanel();
		panel.add(panel_1);
		panel_1.setLayout(new GridLayout(3, 2, 0, 5));

		btnAdd = new JButton("Add");
		panel_1.add(btnAdd);

		JPanel panel_2 = new JPanel(new GridBagLayout());
		panel_1.add(panel_2);

		JSeparator separator = new JSeparator();
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.weightx = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;

		panel_2.add(separator, gbc);

		btnRemove = new JButton("Remove");
		panel_1.add(btnRemove);

		separator = new JSeparator();
		gbc = new GridBagConstraints();
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.weightx = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setPreferredSize(new Dimension(2, 200));
		add(scrollPane);

		if (model != null) {
			table = new JTable(model.getDictionaryTableModel());

			// TODO DictionaryTableCell vs. DictionaryTableCellRenderer
			// DictionaryTableCell dtc = new DictionaryTableCell();
			table.setDefaultRenderer(DictionaryTableCell.class, new DictionaryTableCell());
			table.setDefaultEditor(DictionaryTableCell.class, new DictionaryTableCell());
			table.setRowHeight(50);
			table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			scrollPane.setViewportView(table);
		}
	}

}
