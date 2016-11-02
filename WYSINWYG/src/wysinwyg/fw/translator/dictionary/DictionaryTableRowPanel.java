package wysinwyg.fw.translator.dictionary;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class DictionaryTableRowPanel extends JPanel {

	private static final long serialVersionUID = 4885188543390679562L;

	private JButton btnUp;
	private JButton btnDown;
	private JLabel label;
	private JButton btnOpen;

	public DictionaryTableRowPanel() {
		setLayout(new BorderLayout(0, 0));

		label = new JLabel("...");
		add(label, BorderLayout.CENTER);

		JPanel panel = new JPanel();

		btnUp = new JButton();
		btnUp.setEnabled(false);
		btnUp.setIcon(new ImageIcon(DictionaryTableRowPanel.class
				.getResource("/javax/swing/plaf/metal/icons/sortUp.png")));
		panel.add(btnUp);
		btnUp.setPreferredSize(new Dimension(25, 23));

		btnDown = new JButton();
		btnDown.setEnabled(false);
		btnDown.setIcon(new ImageIcon(DictionaryTableRowPanel.class
				.getResource("/javax/swing/plaf/metal/icons/sortDown.png")));
		panel.add(btnDown);
		btnDown.setPreferredSize(new Dimension(25, 23));

		add(addtoMiddle(panel), BorderLayout.WEST);

		JPanel panel_1 = new JPanel();
		btnOpen = new JButton("Open");
		btnOpen.setEnabled(false);
		panel_1.add(btnOpen);

		add(addtoMiddle(panel_1), BorderLayout.EAST);
	}

	private JPanel addtoMiddle(JPanel panel) {
		JPanel panel2 = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.weightx = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		panel2.add(panel, gbc);
		return panel2;
	}

	public JButton getBtnUp() {
		return btnUp;
	}

	public JButton getBtnDown() {
		return btnDown;
	}

	public JLabel getLabel() {
		return label;
	}

	public JButton getBtnOpen() {
		return btnOpen;
	}

}
