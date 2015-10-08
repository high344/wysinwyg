package wysinwyg.utils;

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

public class ComboboxListCellRenderer extends JLabel implements
		ListCellRenderer<ComboboxDisplayName> {

	private static final long serialVersionUID = 8087679519327917588L;

	public ComboboxListCellRenderer() {
		setOpaque(true);
		setHorizontalAlignment(CENTER);
		setVerticalAlignment(CENTER);
	}

	@Override
	public Component getListCellRendererComponent(JList<? extends ComboboxDisplayName> list,
			ComboboxDisplayName value, int index, boolean isSelected, boolean cellHasFocus) {
		if (isSelected) {
			setBackground(list.getSelectionBackground());
			setForeground(list.getSelectionForeground());
		} else {
			setBackground(list.getBackground());
			setForeground(list.getForeground());
		}

		if (value != null) {
			setText(value.getDisplayName());
		}
		return this;
	}

}
