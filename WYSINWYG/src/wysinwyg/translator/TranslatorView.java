package wysinwyg.translator;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import wysinwyg.utils.ComboboxListCellRenderer;

public class TranslatorView extends JPanel {

	private static final long serialVersionUID = 3424951162521741376L;

	JComboBox<Translator> comboBox;
	TranslatorDisplayPanel displayPanel;

	TranslatorModel model;

	public TranslatorView(TranslatorModel model) {
		this.model = model;
		buildGUI();
	}

	private void buildGUI() {
		setBorder(new TitledBorder(null, "Translator", TitledBorder.LEADING, TitledBorder.TOP,
				null, null));
		setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		add(panel, BorderLayout.NORTH);

		comboBox = new JComboBox<Translator>();
		panel.add(comboBox);
		comboBox.setPreferredSize(new Dimension(150, 20));
		comboBox.setRenderer(new ComboboxListCellRenderer());

		JPanel panel_1 = new JPanel();
		add(panel_1, BorderLayout.EAST);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.X_AXIS));

		displayPanel = new TranslatorDisplayPanel();
		panel_1.add(displayPanel);

		if (model != null) {
			if (model.getDictionary() != null) {
				add(model.getDictionary().getView(), BorderLayout.CENTER);
			}
		}
	}

}
