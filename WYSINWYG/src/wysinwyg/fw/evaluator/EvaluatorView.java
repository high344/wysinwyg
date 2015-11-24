/*******************************************************************************
 * Copyright (c) 2015 Balazs Felfoldi.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/old-licenses/gpl-2.0.html
 * 
 * Contributors:
 *     Balazs Felfoldi - initial API and implementation
 ******************************************************************************/
package wysinwyg.fw.evaluator;

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

import wysinwyg.fw.Init;
import wysinwyg.utils.ComboboxListCellRenderer;

/**
 * JPanel with a JComboBox containing {@linkplain Evaluator evaluators}. If the
 * evaluator is an instance of the {@linkplain Init init MVC} than the View
 * component is also added in a CardLayout form. If the View is {@code null}, a
 * new JPanel is added. A JTextField with the last {@linkplain EvaluationEvent
 * evaluation event} is also present at the bottom.
 * 
 * <pre>
 * |---------------------------|
 * |          JComboBox        | 
 * |---------------------------|
 * | CardLayout: EvaluatorView |
 * |---------------------------|
 * |         JTextField        |
 * |---------------------------|
 * </pre>
 * 
 * @author FelfoldiB.
 *
 */
public class EvaluatorView extends JPanel {

	private static final long serialVersionUID = -6402526837916236566L;

	private EvaluatorModel model;
	private JComboBox<Evaluator> comboBox;
	private JPanel cardsPanel;
	private JTextField textFieldLastStroke;

	/**
	 * Creating a JPanel from the {@code model} elements, if it's {@code null}
	 * an empty JComboBox and a JTextField will still be present.
	 * 
	 * @see EvaluatorView
	 * @param model
	 *            can be {@code null}.
	 */
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

	/**
	 * 
	 * @return a JComboBox containing {@linkplain Evaluator evaluators}.
	 */
	public JComboBox<Evaluator> getComboBox() {
		return comboBox;
	}

	/**
	 * 
	 * @return a JPanel class, with CardLayout.
	 */
	public JPanel getCardsPanel() {
		return cardsPanel;
	}

	/**
	 * 
	 * @return a JTextField class.
	 */
	public JTextField getTextFieldLastStroke() {
		return textFieldLastStroke;
	}

}
