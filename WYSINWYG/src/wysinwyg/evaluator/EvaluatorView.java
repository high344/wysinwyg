package wysinwyg.evaluator;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.SystemColor;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import wysinwyg.Init;
import wysinwyg.utils.ComboboxListCellRenderer;

public class EvaluatorView extends JPanel {

	private static final long serialVersionUID = -6402526837916236566L;

	private EvaluatorModel model;
	private JComboBox<Evaluator> comboBox;
	private JPanel cardsPanel;
	private JTextField textFieldLastStroke;

	public EvaluatorView(EvaluatorModel model) {
		this.model = model;
		buildGUI();
	}

	private void buildGUI() {
		setBorder(new TitledBorder(null, "Evaluator:", TitledBorder.LEADING, TitledBorder.TOP,
				null, null));
		setLayout(new BorderLayout(0, 0));

		JPanel panel_1 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_1.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		add(panel_1, BorderLayout.NORTH);

		if (model != null && model.getEvaluators() != null) {
			comboBox = new JComboBox<Evaluator>(model.getEvaluators());
		} else {
			comboBox = new JComboBox<Evaluator>();
		}

		panel_1.add(comboBox);
		comboBox.setPreferredSize(new Dimension(150, 20));
		comboBox.setRenderer(new ComboboxListCellRenderer());

		cardsPanel = new JPanel();
		add(cardsPanel);
		cardsPanel.setLayout(new CardLayout(0, 0));

		if (model != null && model.getEvaluators() != null) {
			for (Evaluator d : model.getEvaluators()) {
				if (d instanceof Init) {
					Init init = (Init) d;
					if (init.getView() != null) {
						cardsPanel.add(init.getView(), d.getDisplayName());
					} else {
						cardsPanel.add(new JPanel(), d.getDisplayName());
					}
				}
			}
		}

		JPanel panel = new JPanel();
		add(panel, BorderLayout.SOUTH);

		JLabel lblNewLabel = new JLabel("Last Stroke:");
		panel.add(lblNewLabel);

		textFieldLastStroke = new JTextField();
		textFieldLastStroke.setEditable(false);
		textFieldLastStroke.setBackground(SystemColor.text);
		panel.add(textFieldLastStroke);
		textFieldLastStroke.setColumns(20);
	}

	public JComboBox<Evaluator> getComboBox() {
		return comboBox;
	}

	public JPanel getCardsPanel() {
		return cardsPanel;
	}

	public JTextField getTextFieldLastStroke() {
		return textFieldLastStroke;
	}

}
