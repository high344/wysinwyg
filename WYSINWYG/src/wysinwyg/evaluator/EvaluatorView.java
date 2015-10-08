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

import wysinwyg.utils.ComboboxListCellRenderer;

public class EvaluatorView extends JPanel {

	private static final long serialVersionUID = -6402526837916236566L;

	JPanel cardsPanel;
	JComboBox<Evaluator> comboBox;
	JTextField textFieldLastStroke;

	public EvaluatorView() {
		setBorder(new TitledBorder(null, "Evaluator:", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		setLayout(new BorderLayout(0, 0));

		JPanel panel_1 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_1.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		add(panel_1, BorderLayout.NORTH);

		comboBox = new JComboBox<Evaluator>();
		panel_1.add(comboBox);
		comboBox.setPreferredSize(new Dimension(150, 20));
		comboBox.setRenderer(new ComboboxListCellRenderer());

		cardsPanel = new JPanel();
		add(cardsPanel);
		cardsPanel.setLayout(new CardLayout(0, 0));

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

}
