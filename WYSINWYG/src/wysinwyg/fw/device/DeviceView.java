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
package wysinwyg.fw.device;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.TitledBorder;
import javax.swing.text.DefaultCaret;

import wysinwyg.utils.ComboboxListCellRenderer;

/**
 * Extended {@linkplain JPanel} class with a {@linkplain JComboBox} with
 * {@linkplain Device} type elements and a {@linkplain ComboboxListCellRenderer}
 * for it's renderer. A {@linkplain CardLayout} form and a
 * {@linkplain JTextArea}.
 * 
 * <pre>
 * |------------------------|
 * |        JComboBox       |
 * |------------------------|
 * |        CardLayout      |
 * |------------------------|
 * |        JTextArea       |
 * |------------------------|
 * </pre>
 * 
 * @author FelfoldiB.
 *
 */
public class DeviceView extends JPanel {

	private static final long serialVersionUID = 1550900345407909849L;

	private JComboBox<Device> comboBox;
	private JPanel cardsPanel;
	private JTextArea textArea;

	/**
	 * Creating the {@linkplain JPanel} specified in the {@linkplain DeviceView}
	 * .
	 */
	public DeviceView() {
		setBorder(new TitledBorder(null, "Device:", TitledBorder.LEADING, TitledBorder.TOP, null,
				null));
		setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		add(panel, BorderLayout.NORTH);

		comboBox = new JComboBox<Device>();
		comboBox.setPreferredSize(new Dimension(150, 20));
		comboBox.setRenderer(new ComboboxListCellRenderer());
		panel.add(comboBox);

		cardsPanel = new JPanel();
		add(cardsPanel, BorderLayout.WEST);
		cardsPanel.setLayout(new CardLayout(0, 0));

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Device Input:", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		add(panel_1, BorderLayout.SOUTH);
		panel_1.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setPreferredSize(new Dimension(300, 100));
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		panel_1.add(scrollPane);

		textArea = new JTextArea();
		textArea.setEditable(false);
		((DefaultCaret) textArea.getCaret()).setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		scrollPane.setViewportView(textArea);
	}

	/**
	 * 
	 * @return a {@linkplain JComboBox} containing {@linkplain Device}
	 *         type elements.
	 * @see {@linkplain DeviceView}.
	 */
	protected JComboBox<Device> getComboBox() {
		return comboBox;
	}

	/**
	 * 
	 * @return a {@linkplain JPanel} class, with a {@linkplain CardLayout}.
	 * @see {@linkplain DeviceView}.
	 */
	protected JPanel getCardsPanel() {
		return cardsPanel;
	}

	/**
	 * 
	 * @return a {@linkplain JTextArea} class.
	 * @see {@linkplain DeviceView}.
	 */
	protected JTextArea getTextArea() {
		return textArea;
	}

}
