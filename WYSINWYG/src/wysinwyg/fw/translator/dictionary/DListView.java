package wysinwyg.fw.translator.dictionary;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;

public class DListView extends JDialog {

	private static final long serialVersionUID = 2406002707698479689L;

	private JTable table;
	private JTextField textFieldKey;
	private JTextField textFieldValue;
	private JButton btnAdd;
	private JButton btnSave;
	private JButton btnReload;
	private JButton btnRemove;
	private JButton btnEdit;
	private JRadioButton rdbtnAll;
	private JRadioButton rdbtnKey;
	private JRadioButton rdbtnValue;
	private JLabel lblTotal;
	private JLabel lblKeys;
	private JLabel lblValue;

	public DListView() {
		setTitle("Dictionary");
		setModalityType(ModalityType.APPLICATION_MODAL);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		JPanel panel = new JPanel();
		panel.setBorder(new EmptyBorder(5, 5, 0, 5));
		getContentPane().add(panel, BorderLayout.NORTH);
		panel.setLayout(new BorderLayout(0, 0));

		JPanel panel_5 = new JPanel();
		panel.add(panel_5, BorderLayout.WEST);
		panel_5.setLayout(new GridLayout(0, 1, 0, 0));

		JPanel panel_7 = new JPanel();
		panel_5.add(panel_7);

		JLabel lblKey = new JLabel("Key:");
		panel_7.add(lblKey);

		textFieldKey = new JTextField();
		panel_7.add(textFieldKey);
		textFieldKey.setColumns(20);

		JLabel lblNewLabel = new JLabel("Value:");
		panel_7.add(lblNewLabel);

		textFieldValue = new JTextField();
		panel_7.add(textFieldValue);
		textFieldValue.setColumns(20);

		btnAdd = new JButton("Add");
		panel_7.add(btnAdd);

		JPanel panel_6 = new JPanel();
		panel_5.add(panel_6);
		panel_6.setLayout(new BoxLayout(panel_6, BoxLayout.X_AXIS));

		Component horizontalGlue_3 = Box.createHorizontalGlue();
		panel_6.add(horizontalGlue_3);

		JLabel lblNewLabel_1 = new JLabel("Total:");
		panel_6.add(lblNewLabel_1);

		lblTotal = new JLabel("");
		panel_6.add(lblTotal);

		Component horizontalGlue = Box.createHorizontalGlue();
		panel_6.add(horizontalGlue);

		JLabel lblNewLabel_2 = new JLabel("Key:");
		panel_6.add(lblNewLabel_2);

		lblKeys = new JLabel("");
		panel_6.add(lblKeys);

		Component horizontalGlue_1 = Box.createHorizontalGlue();
		panel_6.add(horizontalGlue_1);

		JLabel lblNewLabel_5 = new JLabel("Value:");
		panel_6.add(lblNewLabel_5);

		lblValue = new JLabel("");
		panel_6.add(lblValue);

		Component horizontalGlue_2 = Box.createHorizontalGlue();
		panel_6.add(horizontalGlue_2);

		JPanel panel_4 = new JPanel();
		panel.add(panel_4, BorderLayout.EAST);
		panel_4.setMaximumSize(new Dimension(32767, 1));
		panel_4.setLayout(new GridLayout(0, 1, 0, 0));

		rdbtnAll = new JRadioButton("Show all");
		panel_4.add(rdbtnAll);

		rdbtnKey = new JRadioButton("Show only key");
		panel_4.add(rdbtnKey);

		rdbtnValue = new JRadioButton("Show only value");
		panel_4.add(rdbtnValue);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new EmptyBorder(0, 5, 5, 0));
		getContentPane().add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		panel_1.add(scrollPane);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		table = new JTable(new DListTableModel());
		scrollPane.setViewportView(table);

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new EmptyBorder(0, 5, 5, 5));
		getContentPane().add(panel_2, BorderLayout.EAST);
		panel_2.setLayout(new BoxLayout(panel_2, BoxLayout.Y_AXIS));

		ButtonGroup bg = new ButtonGroup();
		bg.add(rdbtnAll);
		bg.add(rdbtnKey);
		bg.add(rdbtnValue);
		rdbtnAll.setSelected(true);

		Component verticalGlue = Box.createVerticalGlue();
		panel_2.add(verticalGlue);

		JPanel panel_3 = new JPanel();
		panel_3.setMaximumSize(new Dimension(32767, 1));
		panel_2.add(panel_3);
		panel_3.setLayout(new GridLayout(7, 1, 0, 0));

		btnEdit = new JButton("Edit");
		panel_3.add(btnEdit);
		btnEdit.setAlignmentX(Component.CENTER_ALIGNMENT);

		panel_3.add(createGridBLayoutSeparator());

		btnRemove = new JButton("Remove");
		panel_3.add(btnRemove);
		btnRemove.setAlignmentX(Component.CENTER_ALIGNMENT);

		panel_3.add(createGridBLayoutSeparator());

		btnSave = new JButton("Save");
		panel_3.add(btnSave);
		btnSave.setAlignmentX(Component.CENTER_ALIGNMENT);

		panel_3.add(createGridBLayoutSeparator());

		btnReload = new JButton("Reload");
		panel_3.add(btnReload);
		btnReload.setAlignmentX(Component.CENTER_ALIGNMENT);

		Component verticalGlue_1 = Box.createVerticalGlue();
		panel_2.add(verticalGlue_1);

		pack();
		setLocationRelativeTo(null);
	}

	private JPanel createGridBLayoutSeparator() {
		JPanel panel2 = new JPanel(new GridBagLayout());
		JSeparator separator = new JSeparator();
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.weightx = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		panel2.add(separator, gbc);
		return panel2;
	}

	public JTable getTable() {
		return table;
	}

	public JTextField getTextFieldKey() {
		return textFieldKey;
	}

	public JTextField getTextFieldValue() {
		return textFieldValue;
	}

	public JButton getBtnAdd() {
		return btnAdd;
	}

	public JButton getBtnSave() {
		return btnSave;
	}

	public JButton getBtnReload() {
		return btnReload;
	}

	public JButton getBtnRemove() {
		return btnRemove;
	}

}
