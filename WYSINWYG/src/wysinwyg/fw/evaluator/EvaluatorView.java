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

import wysinwyg.utils.renderer.ComboboxListCellRenderer;

/**
 * 
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

	private JComboBox<Evaluator> comboBox;
	private JPanel cardsPanel;
	private JTextField textFieldLastStroke;

	/**
	 * Creating a JPanel from the {@code model} elements, if it's {@code null} an empty JComboBox and a JTextField will
	 * still be present.
	 * 
	 * @see EvaluatorView
	 * @param model
	 *            can be {@code null}.
	 */
	public EvaluatorView() {
		setBorder(new TitledBorder(null, "Evaluator:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
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
